package convert;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import finite.FSA;
import minimize.Sample;

@SuppressWarnings("static-method") //
public class extensive {
  @EnumSource @ParameterizedTest void terminates(Sample ¢) { ¢.DFSA(); }
  @EnumSource @ParameterizedTest void compare(Sample s) {
    FSA<Character> dfsa = s.DFSA();
    for (String input : Sample.inputs)//
      if (s.inner.run(input)) //
        assert dfsa.run(input) : "DFSA(" + s.name + ") fails to accept '" + input + "'";
      else//
        assert !dfsa.run(input) : "DFSA(" + dfsa + ") incorrectly accepts '" + input + "'";
  }
  @EnumSource @ParameterizedTest void fixedPoint(Sample s) {
    FSA<Character> dfsa = s.DFSA();
    FSA<Character> c    = dfsa.minimal();
    for (String input : Sample.inputs)//
      if (dfsa.run(input)) //
        assert c.run(input) : "Minimize(DFSA(" + s.name + ")) fails to accept '" + input + "'";
      else//
        assert !c.run(input) : "Minimize(DFSA(" + dfsa + ")) incorrectly accepts '" + input + "'";
  }
}
