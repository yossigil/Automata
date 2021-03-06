package automaton.minimize;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import automaton.Case;
import automaton.FSA;
import automaton.Input;

@SuppressWarnings("static-method") //
public class extensive {
  @EnumSource(Sample.class)
  @ParameterizedTest void nothing(Case c) {
  }
  @EnumSource(Sample.class)
  @ParameterizedTest void terminates(Case ¢) { ¢.MDFSA(); }
  @EnumSource(Sample.class)
  @ParameterizedTest void twice(Case ¢) { ¢.MDFSA().minimal(); }
  @EnumSource(Sample.class)
  @ParameterizedTest void thrice(Case ¢) { ¢.MDFSA().minimal(); }
  @EnumSource(Sample.class)
  @ParameterizedTest void DFSA(Case c) {
    FSA<Character> b = c.DFSA();
    for (String input : Input.inputs)//
      if (c.NFSA().run(input)) //
        assert b.run(input) : "DFSA(" + c + ") fails to accept '" + input + "'";
      else//
        assert !b.run(input) : "DFSA(" + b + ") incorrectly accepts '" + input + "'";
  }
  @EnumSource(Sample.class)
  @ParameterizedTest void MDFSA(Case c) {
    FSA<Character> b = c.MDFSA();
    for (String input : Input.inputs)//
      if (c.NFSA().run(input)) //
        assert b.run(input) : "Minimize(" + c + ") fails to accept '" + input + "'";
      else//
        assert !b.run(input) : "Minimize(" + b + ") incorrectly accepts '" + input + "'";
  }
  @EnumSource(Sample.class)
  @ParameterizedTest void NFSA(Case c) {
    for (String input : Input.inputs)//
      if (c.accept(input)) //
        assert c.NFSA().run(input) : "NFSA(" + c + ") fails to accept '" + input + "'";
      else//
        assert !c.NFSA().run(input) : "NFSA(" + c + ") incorrectly accepts '" + input + "'";
  }
}