package minimize;

import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

@SuppressWarnings("static-method") //
public class Cartesean {
  @TestFactory Stream<? super DynamicTest> all() {
    return Sample.s().map(makeTest());
  }
  Function<Sample, DynamicContainer> makeTest() {
    return s -> dynamicContainer("/" + s.name + "/", Stream.of(//
        dynamicContainer("NFSA", Stream.of(//
            dynamicContainer("Accepts: ", yes(s)
                .map(input -> dynamicTest("'" + input + "'", () -> s.run(input)))),
            dynamicContainer("Rejects: ", no(s)//
                .map(input -> dynamicTest("'" + input + "'", () -> s.rejects(input)))))), //
        dynamicContainer("DFSA", Stream.of(//
            dynamicContainer("Accepts: ", yes(s)
                .map(input -> dynamicTest("'" + input + "'", () -> s.DFSA().run(input)))), //
            dynamicContainer("Rejects: ", no(s)
                .map(input -> dynamicTest("'" + input + "'", () -> s.DFSAreject(input)))))), //
        dynamicContainer("Minimized DFSA", Stream.of(//
            dynamicContainer("Accepts: ", yes(s)//
                .map(input -> dynamicTest("'" + input + "'", () -> s.minimal().run(input)))), //
            dynamicContainer("Rejects: ", //
                no(s)
                    .map(input -> dynamicTest("'" + input + "'", //
                        () -> s.minimalRejects(input))))))//
    ));
  }
  private Stream<String> no(Sample s) { return Sample//
      .inputs()//
      .filter(input -> !s.accept(input)); }
  private Stream<String> yes(Sample s) { return Sample//
      .inputs()//
      .filter(input -> s.accept(input)); }
  private DynamicContainer accept(Sample ¢) { return dynamicContainer("Accepts: ", accepts(¢)); }
  private DynamicContainer reject(Sample ¢) { return dynamicContainer("Rejects: ", rejects(¢)); }
  private Stream<DynamicTest> accepts(Sample s) {
    return yes(s)
        .map(input -> dynamicTest("'" + input + "'", () -> s.run(input)));
  }
  private Stream<DynamicTest> rejects(Sample s) {
    return no(s)
        .map(input -> dynamicTest("'" + input + "'", () -> s.rejects(input)));
  }
}