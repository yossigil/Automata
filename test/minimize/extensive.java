package minimize;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import finite.FSA;

@SuppressWarnings("static-method") //
public class extensive {
  @EnumSource
  @ParameterizedTest void nothing(Sample s) {
  }
  @EnumSource
  @ParameterizedTest void terminates(Sample ¢) { ¢.inner.minimal(); }
  @EnumSource
  @ParameterizedTest void twice(Sample ¢) { ¢.inner.minimal().minimal(); }
  @EnumSource
  @ParameterizedTest void thrice(Sample ¢) { ¢.inner.minimal().minimal(); }
  @EnumSource
  @ParameterizedTest void compare(Sample s) {
    FSA<Character> b = s.minimal();
    for (String input : Sample.inputs)//
      if (s.inner.run(input)) //
        assert b.run(input) : "Minimize(" + s.name + ") fails to accept '" + input + "'";
      else//
        assert !b.run(input) : "Minimize(" + b + ") incorrectly accepts '" + input + "'";
  }
  @EnumSource
  @ParameterizedTest void NFSA(Sample s) {
    for (String input : Sample.inputs)//
      if (s.accept(input)) //
        assert s.inner.run(input) : "NFSA(" + s.name + ") fails to accept '" + input + "'";
      else//
        assert !s.inner.run(input) : "NFSA(" + s.name + ") incorrectly accepts '" + input + "'";
  }
}