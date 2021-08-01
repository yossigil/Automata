package automaton;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import automaton.minimize.Sample;
import utils.set;

@SuppressWarnings("static-method") public class All {
  @TestFactory Stream<? super DynamicTest> brief() {
    return Stream.of(//
        dynamicContainer("Sanity", sanity()),
        dynamicContainer("NFSA vs. RE  ", Sample.s().map(s -> test(s, s.NFSA(), "NFSA"))), //
        dynamicContainer("DFSA vs. RE  ", Sample.s().map(s -> test(s, s.DFSA(), "DFSA"))), //
        dynamicContainer("MDFSA vs. RE ", Sample.s().map(s -> test(s, s.MDFSA(), "MDFSA"))), //
        dynamicContainer("NFSA = DFSA  ", Sample.s().map(s -> compare(s.NFSA(), s.DFSA(), "NFSA", "DFSA"))),
        dynamicContainer("DFSA = MDFSA  ", Sample.s().map(s -> compare(s.DFSA(), s.MDFSA(), "DFSA", "MDFSA"))));
  }
  @TestFactory Stream<DynamicNode> verbose() {
    return Sample.s().map(test());
  }
  Stream<DynamicNode> sanity() {
    return Stream.of(//
        dynamicContainer("NFSA", Sample.s().map(s -> test(s + "", s.NFSA()))), //
        dynamicContainer("DFSA", Sample.s().map(s -> test(s + "", s.DFSA()))), //
        dynamicContainer("MDFSA", Sample.s().map(s -> test(s + "", s.MDFSA())))//
    );
  }
  private String accept(Case c, String input) { return String.format("%s rejects '%s'", c, input); }
  private String reject(Case c, String input) { return String.format("%s accepts '%s'", c, input); }
  private boolean run(FSA<Character> c, String input) { return c.run(input); }
  private Function<Case, DynamicContainer> test() {
    return s -> dynamicContainer(s + "", Stream.of(//
        test(x -> x.NFSA(), s, "NFSA"), //
        test(x -> x.DFSA(), s, "DFSA"), //
        test(x -> x.MDFSA(), s, "Minimized DFSA")));
  }
  private DynamicContainer test(Function<Case, FSA<Character>> MDFSA, Case c, String extracted) {
    return dynamicContainer(extracted, Stream.of(//
        dynamicContainer("Accepts: ", accepts(c)//
            .map(input -> test(input, () -> {
              assert run(MDFSA.apply(c), input) : accept(c, input);
            }))), //
        dynamicContainer("Rejects: ", rejects(c)//
            .map(input -> test(input, () -> {
              assert !run(MDFSA.apply(c), input) : reject(c, input);
            })))));
  }
  private DynamicTest test(String input, Executable x) { return dynamicTest("'" + input + "'", x); }
  private DynamicContainer test(String name, FSA<?> ¢) {
    return dynamicContainer(name, Stream.of(//
        dynamicTest("q0 != null", () -> assertNotNull(¢.q0)), //
        dynamicTest("Q != null", () -> assertNotNull(¢.Q)), //
        dynamicTest("Δ != null", () -> assertNotNull(¢.Δ)), //
        dynamicTest("ζ != null", () -> assertNotNull(¢.ζ)), //
        dynamicTest("|Q|>1", () -> assertFalse(¢.Q.isEmpty())), dynamicTest("|ζ|>0", () -> assertFalse(¢.ζ.isEmpty())),
        dynamicTest("All states are reachable", () -> assertTrue(set.minus(¢.Q, ¢.QQ()).isEmpty())),
        dynamicTest("No stray states", () -> assertTrue(set.minus(¢.QQ(), ¢.Q).isEmpty())),
        dynamicTest("No NULL state", () -> assertFalse(¢.Q.contains(null))),
        dynamicTest("q0∈Q", () -> assertTrue(¢.Q.contains(¢.q0))),
        dynamicTest("ζ⊆Q", () -> assertTrue(¢.Q.containsAll(¢.ζ)))//
    ));
  }
  private static Stream<String> accepts(Case c) {
    return Input.inputs().filter(input -> c.accept(input));
  }
  private static Stream<String> rejects(Case c) {
    return Input.inputs().filter(input -> !c.accept(input));
  }
  private static DynamicTest test(Case c, FSA<Character> f, String name) {
    return dynamicTest(c + "", () -> test(c, String.format("%s(%s)", c, name), f));
  }
  private static void test(Case c, String name, FSA<Character> f) {
    for (String input : Input.inputs)//
      if (c.accept(input)) //
        assert f.run(input) : String.format("%s rejects '%s'", name, input);
      else//
        assert !f.run(input) : String.format("%s accepts '%s'", name, input);
  }
  private static DynamicContainer compare(FSA<Character> f1, FSA<Character> f2, String name1, String name2) {
    return dynamicContainer(name1 + "vs." + name2, Stream.of(//
        dynamicTest(name1 + "⊆" + name2, () -> contained(f1, f2, name1, name2)),
        dynamicTest(name2 + "⊆" + name1, () -> contained(f2, f1, name2, name1)), //
        dynamicContainer("By input", Input.s().map(//
            (String s) -> dynamicTest("'" + s + "'", () -> {
              for (String input : Input.inputs)//
                if (f1.run(input)) //
                  assert f2.run(input) : String.format("'%s': accepted by %s but rejected by %s", input, name1, name2);
              else//
                  assert !f2.run(input) : String.format("'%s': rejected by %s but accepted by %s", input, name1, name2);
            })))));
  }
  private static void contained(FSA<Character> f1, FSA<Character> f2, String name1, String name2) {
    for (String input : Input.inputs)//
      if (f1.run(input)) //
        assert f2.run(input) : String.format("'%s': accepted by %s but rejected by %s", input, name1, name2);
  }
  private static DynamicTest compareEQ(FSA<Character> f1, FSA<Character> f2, String name1, String name2) {
    return dynamicTest(name1 + "=" + name2, () -> {
      for (String input : Input.inputs)//
        if (f1.run(input)) //
          assert f2.run(input) : String.format("'%s': accepted by %s but rejected by %s", input, name1, name2);
      else//
          assert !f2.run(input) : String.format("'%s': rejected by %s but accepted by %s", input, name1, name2);
    });
  }
}