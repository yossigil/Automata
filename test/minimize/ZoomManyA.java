package minimize;

import org.junit.jupiter.api.Test;

import automaton.FSA;

public class ZoomManyA {
  final FSA<Character> dfsa = Sample.many.MDFSA();
  {
    Sample.many.show();
  }
  @Test void aaaa() { assert dfsa.run("aaaa"); }
  @Test void aaa() { assert dfsa.run("aaa"); }
  @Test void aa() { assert dfsa.run("aa"); }
  @Test void aababab() { assert !dfsa.run("aababab"); }
  @Test void aab() { assert !dfsa.run("aab"); }
  @Test void a() { assert dfsa.run("a"); }
  @Test void abababa() { assert !dfsa.run("abababa"); }
  @Test void abb() { assert !dfsa.run("abb"); }
  @Test void ac() { assert !dfsa.run("ac"); }
  @Test void accept0() { assert dfsa.run(""); }
  @Test void ba() { assert !dfsa.run("ba"); }
  @Test void b() { assert !dfsa.run("b"); }
  @Test void bbb() { assert !dfsa.run("bbb"); }
  @Test void c() { assert !dfsa.run("c"); }
  @Test void cb() { assert !dfsa.run("cb"); }
}
