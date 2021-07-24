package minimize;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import automaton.FSA;

@SuppressWarnings("static-method") //
public class extensive {
  @EnumSource(Sample.class)
  @ParameterizedTest void nothing(Case c) {
  }
  @EnumSource(Sample.class)
  @ParameterizedTest void terminates(Case ¢) { ¢.minimal(); }
  @EnumSource(Sample.class)
  @ParameterizedTest void twice(Case ¢) { ¢.minimal().minimal(); }
  @EnumSource(Sample.class)
  @ParameterizedTest void thrice(Case ¢) { ¢.minimal().minimal(); }
  @EnumSource(Sample.class)
  @ParameterizedTest void compare(Case c) {
    FSA<Character> b = c.minimal();
    for (String input : Case.inputs)//
      if (c.NFSA().run(input)) //
        assert b.run(input) : "Minimize(" + c + ") fails to accept '" + input + "'";
      else//
        assert !b.run(input) : "Minimize(" + b + ") incorrectly accepts '" + input + "'";
  }
  @EnumSource(Sample.class)
  @ParameterizedTest void NFSA(Case c) {
    for (String input : Case.inputs)//
      if (c.accept(input)) //
        assert c.NFSA().run(input) : "NFSA(" + c + ") fails to accept '" + input + "'";
      else//
        assert !c.NFSA().run(input) : "NFSA(" + c + ") incorrectly accepts '" + input + "'";
  }
}