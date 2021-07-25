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

import automaton.FSA;
import utils.set;

@SuppressWarnings("static-method") //
public class Pairs {
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
  @TestFactory Stream<? super DynamicTest> structure() {
    return Stream.of(//
        dynamicContainer("NFSA", Sample.s().map(s -> test(s + "", s.NFSA()))), //
        dynamicContainer("DFSA", Sample.s().map(s -> test(s + "", s.DFSA()))), //
        dynamicContainer("MDFSA", Sample.s().map(s -> test(s + "", s.MDFSA())))//
    );
  }
  @TestFactory Stream<? super DynamicTest> byProcess() {
    return Stream.of(//
        dynamicContainer("NFSA", //
            Stream.of(//
                dynamicContainer("All: ", Sample.s().map(s -> byInput(s, s.NFSA()))),
                dynamicContainer("Accepts: ", Sample.s().map(s -> newAccept(s, s.NFSA()))),
                dynamicContainer("Rejects: ", Sample.s().map(s -> newReject(s, s.NFSA()))))//
        ), //
        dynamicContainer("DFSA", //
            Stream.of(//
                dynamicContainer("All: ", Sample.s().map(s -> byInput(s, s.DFSA()))),
                dynamicContainer("Accepts: ", Sample.s().map(s -> newAccept(s, s.DFSA()))),
                dynamicContainer("Rejects: ", Sample.s().map(s -> newReject(s, s.DFSA()))))//
        ), //
        dynamicContainer("MDFSA", //
            Stream.of(//
                dynamicContainer("All: ", Sample.s().map(s -> byInput(s, s.MDFSA()))),
                dynamicContainer("Accepts: ", Sample.s().map(s -> newAccept(s, s.MDFSA()))),
                dynamicContainer("Rejects: ", Sample.s().map(s -> newReject(s, s.MDFSA()))))//
        )//
    );
  }
  private DynamicContainer byInput(Sample s, FSA<Character> nfsa) {
    return dynamicContainer(s + "", //
        Case.inputs().map(//
            input -> nfsa.run(input) ? test(input + "+", accept(s, input)) : 
              test(input + "-", reject(s, input)))
        );
  }
  private DynamicContainer newAccept(Sample s, FSA<Character> nfsa) {
    return dynamicContainer(s + "", accepts(s).map(input -> test(input, accept(s, input))));
  }
  private DynamicContainer newReject(Sample s, FSA<Character> nfsa) {
    return dynamicContainer(s + "", accepts(s).map(input -> test(input, reject(s, input))));
  }
  @TestFactory Stream<? super DynamicTest> byCase() {
    return Sample.s().map(makeTest());
  }
  private DynamicTest test(String input, Executable x) { return dynamicTest("'" + input + "'", x); }
  Function<Case, DynamicContainer> makeTest() {
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
  private Executable reject(Case c, String input) {
    return () -> assertFalse(c.NFSA().run(input));
  }
  private Executable accept(Case c, String input) {
    return () -> assertTrue(c.NFSA().run(input));
  }
  private Stream<String> rejects(Case c) {
    return Case.inputs().filter(input -> !c.accept(input));
  }
  private Stream<String> accepts(Case c) {
    return Case.inputs().filter(input -> c.accept(input));
  }
}