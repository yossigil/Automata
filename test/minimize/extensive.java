package minimize;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import finite.FSA;
import finite.NFSA;

enum Sample {
  or("a|b", NFSA.<Character>σ('a').or(NFSA.<Character>σ('b'))), //
  many("a*", NFSA.<Character>σ('a').many()), //
  then("ab", NFSA.<Character>σ('a').then(NFSA.<Character>σ('b'))), //
  a("a", NFSA.<Character>σ('a')), //
  b("b", NFSA.<Character>σ('b')), //
  ε("ε", NFSA.<Character>ε()), //
  ʘ("ʘ", NFSA.<Character>ʘ()), //
  Φ("Φ", NFSA.<Character>Φ())//
  ;
  static final String[] inputs = {                            // All strings of length up
      "",                                                     //
      "a", "b", "c",                                          //
      "aa", "ab", "ac",                                       //
      "ba", "bb", "bc",                                       //
      "ca", "cb", "cc",                                       //
      "aaa", "aab", "aac",                                    //
      "aba", "abb", "abc",                                    //
      "aca", "acb", "acc",                                    //
      "baa", "bab", "aac",                                    //
      "bba", "bbb", "abc",                                    //
      "bca", "bcb", "acc",                                    //
      "caa", "cab", "cac",                                    //
      "cba", "cbb", "cbc",                                    //
      "cca", "ccb", "ccc",                                    //
  };
  final NFSA<Character> inner;
  final String name;
  Sample(String name, NFSA<Character> nfsa) {
    this.name = name;
    this.inner = nfsa;
  }
}

@SuppressWarnings("static-method") //
public class extensive {
  @EnumSource(Sample.class)
  @ParameterizedTest void terminates(Sample ¢) { ¢.inner.minimize(); }
  @EnumSource(Sample.class)
  @ParameterizedTest void twice(Sample ¢) { ¢.inner.minimize().minimize(); }
  @EnumSource(Sample.class)
  @ParameterizedTest void thrice(Sample ¢) { ¢.inner.minimize().minimize(); }
  @EnumSource(Sample.class)
  @ParameterizedTest void compare(Sample s) {
    FSA<Character> b = s.inner.minimize();
    for (String input : Sample.inputs) if (s.inner.run(input)) //
      assert b.run(input) : "Minimize(" + s.name + ") fails to accept '" + input + "'";
    else assert !b.run(input) : "Minimize(" + b + ") incorrectly accepts '" + input + "'";
  }
  @ParameterizedTest void fixedPoint(Sample s) {
    FSA<Character> b = s.inner.minimize();
    FSA<Character> c = b.minimize();
    for (String input : Sample.inputs) if (b.run(input)) //
      assert c.run(input) : "Minimize(" + s.name + ") fails to accept '" + input + "'";
    else assert !c.run(input) : "Minimize(" + b + ") incorrectly accepts '" + input + "'";
  }


}
