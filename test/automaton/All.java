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
        dynamicContainer("Structure", structure()),
        dynamicContainer("NFSA", Sample.s().map(s -> test(s, s.NFSA(), "NFSA"))), //
        dynamicContainer("DFSA", Sample.s().map(s -> test(s, s.DFSA(), "DFSA"))), //
        dynamicContainer("MDFSA", Sample.s().map(s -> test(s, s.MDFSA(), "MDFSA"))) //
    );
  }
  @TestFactory Stream<DynamicNode> verbose() {
    return Sample.s().map(test());
  }
  Stream<DynamicNode> structure() {
    return Stream.of(//
        dynamicContainer("NFSA", Sample.s().map(s -> test(s + "", s.NFSA()))), //
        dynamicContainer("DFSA", Sample.s().map(s -> test(s + "", s.DFSA()))), //
        dynamicContainer("MDFSA", Sample.s().map(s -> test(s + "", s.MDFSA())))//
    );
  }
  private String accept(Case s, String input) { return String.format("%s rejects '%s'", s, input); }
  private String reject(Case s, String input) { return String.format("%s accepts '%s'", s, input); }
  private boolean run(FSA<Character> c, String input) { return c.run(input); }
  private Function<Case, DynamicContainer> test() {
    return s -> dynamicContainer(s + "", Stream.of(//
        test(x -> x.NFSA(), s, "NFSA"), //
        test(x -> x.DFSA(), s, "DFSA"), //
        test(x -> x.MDFSA(), s, "Minimized DFSA")));
  }
  private DynamicContainer test(Function<Case, FSA<Character>> MDFSA, Case s, String extracted) {
    return dynamicContainer(extracted, Stream.of(//
        dynamicContainer("Accepts: ", accepts(s)//
            .map(input -> test(input, () -> {
              assert run(MDFSA.apply(s), input) : accept(s, input);
            }))), //
        dynamicContainer("Rejects: ", rejects(s)//
            .map(input -> test(input, () -> {
              assert !run(MDFSA.apply(s), input) : reject(s, input);
            })))));
  }
  private DynamicTest test(String input, Executable x) { return dynamicTest("'" + input + "'", x); }
  private DynamicContainer test(String name, FSA<?> ¢) {
    return dynamicContainer(name, Stream.of(//
        dynamicTest("Non-null q0", () -> assertNotNull(¢.q0)), //
        dynamicTest("Non-null Q", () -> assertNotNull(¢.Q)), //
        dynamicTest("Non-null Δ", () -> assertNotNull(¢.Δ)), //
        dynamicTest("Non-null ζ ", () -> assertNotNull(¢.ζ)), //
        dynamicTest("All states are reachable", () -> assertTrue(set.minus(¢.Q, ¢.QQ()).isEmpty())),
        dynamicTest("No stray states", () -> assertTrue(set.minus(¢.QQ(), ¢.Q).isEmpty())),
        dynamicTest("No NULL letter", () -> assertFalse(¢.Σ.contains(null))),
        dynamicTest("No NULL state", () -> assertFalse(¢.Q.contains(null))),
        dynamicTest("Q contains q0", () -> assertTrue(¢.Q.contains(¢.q0))),
        dynamicTest("Q contains ζ", () -> assertTrue(¢.Q.containsAll(¢.ζ)))//
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
}