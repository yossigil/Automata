package automaton;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import automaton.minimize.Sample;
import utils.set;

@SuppressWarnings("static-method") public class Pairs {
  @TestFactory Stream<? super DynamicTest> brief() {
    return Stream.of(//
        dynamicContainer("NFSA", Sample.s().map(s -> xtest(s, s.NFSA(), "NFSA"))), //
        dynamicContainer("DFSA", Sample.s().map(s -> xtest(s, s.DFSA(), "DFSA"))), //
        dynamicContainer("MDFSA", Sample.s().map(s -> xtest(s, s.MDFSA(), "MDFSA"))) //
    );
  }
  @TestFactory Stream<? super DynamicTest> byCase() {
    return Sample.s().map(makeTest());
  }
  @TestFactory Stream<? super DynamicTest> structure() {
    return Stream.of(//
        dynamicContainer("NFSA", Sample.s().map(s -> test(s + "", s.NFSA()))), //
        dynamicContainer("DFSA", Sample.s().map(s -> test(s + "", s.DFSA()))), //
        dynamicContainer("MDFSA", Sample.s().map(s -> test(s + "", s.MDFSA())))//
    );
  }
  private Function<Case, DynamicContainer> makeTest() {
    return s -> dynamicContainer(s + "", //
        Stream.of(//
            dynamicContainer("NFSA", //
                Stream.of(//
                    dynamicContainer("Accepts: ", accepts(s)//
                        .map(input -> test(input, accept(s, input)))),
                    dynamicContainer("Rejects: ", rejects(s)//
                        .map(input -> test(input, reject(s, input))))//
                )//
            ), //
            dynamicContainer("DFSA", Stream.of(//
                dynamicContainer("Accepts: ", //
                    accepts(s).map(input -> test(input, () -> assertTrue(s.DFSA().run(input))))), //
                dynamicContainer("Rejects: ", //
                    rejects(s).map(input -> test(input, () -> assertFalse(s.DFSA().run(input))))))), //
            dynamicContainer("Minimized DFSA", Stream.of(//
                dynamicContainer("Accepts: ", accepts(s)//
                    .map(input -> test(input, () -> assertTrue(s.MDFSA().run(input))))), //
                dynamicContainer("Rejects: ", rejects(s)//
                    .map(input -> test(input, () -> assertFalse(s.MDFSA().run(input)))))))//
        ));
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
  private static Executable accept(Case c, String input) {
    return () -> assertTrue(c.NFSA().run(input));
  }
  private static Stream<String> accepts(Case c) {
    return Input.inputs().filter(input -> c.accept(input));
  }
  private static Executable reject(Case c, String input) {
    return () -> assertFalse(c.NFSA().run(input));
  }
  private static Stream<String> rejects(Case c) {
    return Input.inputs().filter(input -> !c.accept(input));
  }
  private static void test(Case c, String name, FSA<Character> f) {
    for (String input : Input.inputs)//
      if (c.accept(input)) //
        assert f.run(input) : String.format("%s rejects '%s'", name, input);
      else//
        assert !f.run(input) : String.format("%s accepts '%s'", name, input);
  }
  private static DynamicTest xtest(Case c, FSA<Character> f, String name) {
    return dynamicTest(c + "", () -> test(c, String.format("%s(%s)", c, name), f));
  }
}