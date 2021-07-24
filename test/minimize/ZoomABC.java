package minimize;

import org.junit.jupiter.api.Test;

import automaton.FSA;

public class ZoomABC {
  final FSA<Character> dfsa = Sample.abc.DFSA();
  {
    Sample.abc.show();
  }
  @Test void ε() { assert !dfsa.run(""); }
  @Test void ab() { assert !dfsa.run("ab"); }
  @Test void abb() { assert !dfsa.run("abb"); }
  @Test void aba() { assert !dfsa.run("aba"); }
  @Test void abc() { assert dfsa.run("abc"); }
  @Test void a() { assert !dfsa.run("a"); }
  @Test void b() { assert !dfsa.run("b"); }
  @Test void aa() { assert !dfsa.run("aa"); }
  @Test void bb() { assert !dfsa.run("bb"); }
  @Test void ba() { assert !dfsa.run("ba"); }
  @Test void aab() { assert !dfsa.run("aab"); }
  @Test void bab() { assert !dfsa.run("bab"); }
  @Test void aaa() { assert !dfsa.run("aaa"); }
  @Test void bbb() { assert !dfsa.run("bbb"); }
  @Test void abababa() { assert !dfsa.run("abababa"); }
  @Test void ababab() { assert !dfsa.run("ababab"); }
  @Test void abababb() { assert !dfsa.run("abababb"); }
}
