package automaton.misc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import automaton.Case;
import automaton.FSA;
import automaton.Input;
import automaton.minimize.Sample;

@SuppressWarnings("static-method") //
public class extensive {
  @EnumSource(Sample.class)
  @ParameterizedTest void terminates(Case ¢) { ¢.DFSA(); }
  @EnumSource(Sample.class)
  @ParameterizedTest void compare(Case c) {
    FSA<Character> dfsa = c.DFSA();
    for (String input : Input.inputs)//
      if (c.NFSA().run(input)) //
        assert dfsa.run(input) : "DFSA(" + c + ") fails to accept '" + input + "'";
      else//
        assert !dfsa.run(input) : "DFSA(" + dfsa + ") incorrectly accepts '" + input + "'";
  }
  @EnumSource(Sample.class)
  @ParameterizedTest void fixedPoint(Case s) {
    FSA<Character> dfsa = s.DFSA();
    FSA<Character> c    = dfsa.minimal();
    for (String input : Input.inputs)//
      if (dfsa.run(input)) //
        assert c.run(input) : "Minimize(DFSA(" + s + ")) fails to accept '" + input + "'";
      else//
        assert !c.run(input) : "Minimize(DFSA(" + dfsa + ")) incorrectly accepts '" + input + "'";
  }
}
