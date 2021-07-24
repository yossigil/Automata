package minimize;

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

import finite.FSA;
import utils.set;

@SuppressWarnings("static-method") //
public class Cartesean {
  DynamicContainer test(String name, FSA<?> ¢) {
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
  @TestFactory Stream<? super DynamicTest> automata() {
    return Stream.of(//
        dynamicContainer("DFSA", Sample.s().map(s -> test(s + "", s.NFSA()))), //
        dynamicContainer("DFSA", Sample.s().map(s -> test(s + "", s.DFSA()))), //
        dynamicContainer("DFSA (minimial)", Sample.s().map(s -> test(s + "", s.minimal())))//
    );
  }
  @TestFactory Stream<? super DynamicTest> runs() {
    return Sample.s().map(makeTest());
  }
  private DynamicTest test(String input, Executable x) { return dynamicTest("'" + input + "'", x); }
  Function<Case, DynamicContainer> makeTest() {
    return s -> dynamicContainer(s + "", Stream.of(//
        dynamicContainer("NFSA", Stream.of(//
            dynamicContainer("Accepts: ", yes(s)//
                .map(input -> test(input, () -> assertTrue(s.NFSA().run(input))))),
            dynamicContainer("Rejects: ", no(s)//
                .map(input -> test(input, () -> assertFalse(s.NFSA().run(input))))))), //
        dynamicContainer("DFSA", Stream.of(//
            dynamicContainer("Accepts: ", //
                yes(s).map(input -> test(input, () -> assertTrue(s.DFSA().run(input))))), //
            dynamicContainer("Rejects: ", //
                no(s).map(input -> test(input, () -> assertFalse(s.DFSA().run(input))))))), //
        dynamicContainer("Minimized DFSA", Stream.of(//
            dynamicContainer("Accepts: ", yes(s)//
                .map(input -> test(input, () -> assertTrue(s.minimal().run(input))))), //
            dynamicContainer("Rejects: ", no(s)//
                .map(input -> test(input, () -> assertFalse(s.minimal().run(input)))))))//
    ));
  }
  private Stream<String> no(Case c) {
    return Case.inputs().filter(input -> !c.accept(input));
  }
  private Stream<String> yes(Case c) {
    return Case.inputs().filter(input -> c.accept(input));
  }
}