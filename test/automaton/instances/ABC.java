package automaton.instances;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import automaton.FSA;
import automaton.minimize.Sample;

public class ABC {
  final Sample $ = Sample.abc;
  {
    $.show();
  }
  @Nested class NFSA {
    FSA<Character> $ = ABC.this.$.NFSA();
    @Test void ε() { assert !$.run(""); }
    @Test void ab() { assert !$.run("ab"); }
    @Test void abb() { assert !$.run("abb"); }
    @Test void aba() { assert !$.run("aba"); }
    @Test void abc() { assert $.run("abc"); }
    @Test void a() { assert !$.run("a"); }
    @Test void b() { assert !$.run("b"); }
    @Test void aa() { assert !$.run("aa"); }
    @Test void bb() { assert !$.run("bb"); }
    @Test void ba() { assert !$.run("ba"); }
    @Test void aab() { assert !$.run("aab"); }
    @Test void bab() { assert !$.run("bab"); }
    @Test void aaa() { assert !$.run("aaa"); }
    @Test void bbb() { assert !$.run("bbb"); }
    @Test void abababa() { assert !$.run("abababa"); }
    @Test void ababab() { assert !$.run("ababab"); }
    @Test void abababb() { assert !$.run("abababb"); }
  }
  @Nested class DFSA {
    FSA<Character> $ = ABC.this.$.DFSA();
    @Test void ε() { assert !$.run(""); }
    @Test void ab() { assert !$.run("ab"); }
    @Test void abb() { assert !$.run("abb"); }
    @Test void aba() { assert !$.run("aba"); }
    @Test void abc() { assert $.run("abc"); }
    @Test void a() { assert !$.run("a"); }
    @Test void b() { assert !$.run("b"); }
    @Test void aa() { assert !$.run("aa"); }
    @Test void bb() { assert !$.run("bb"); }
    @Test void ba() { assert !$.run("ba"); }
    @Test void aab() { assert !$.run("aab"); }
    @Test void bab() { assert !$.run("bab"); }
    @Test void aaa() { assert !$.run("aaa"); }
    @Test void bbb() { assert !$.run("bbb"); }
    @Test void abababa() { assert !$.run("abababa"); }
    @Test void ababab() { assert !$.run("ababab"); }
    @Test void abababb() { assert !$.run("abababb"); }
  }
  @Nested class MDFSA {
    FSA<Character> $ = ABC.this.$.MDFSA();
    @Test void ε() { assert !$.run(""); }
    @Test void ab() { assert !$.run("ab"); }
    @Test void abb() { assert !$.run("abb"); }
    @Test void aba() { assert !$.run("aba"); }
    @Test void abc() { assert $.run("abc"); }
    @Test void a() { assert !$.run("a"); }
    @Test void b() { assert !$.run("b"); }
    @Test void aa() { assert !$.run("aa"); }
    @Test void bb() { assert !$.run("bb"); }
    @Test void ba() { assert !$.run("ba"); }
    @Test void aab() { assert !$.run("aab"); }
    @Test void bab() { assert !$.run("bab"); }
    @Test void aaa() { assert !$.run("aaa"); }
    @Test void bbb() { assert !$.run("bbb"); }
    @Test void abababa() { assert !$.run("abababa"); }
    @Test void ababab() { assert !$.run("ababab"); }
    @Test void abababb() { assert !$.run("abababb"); }
  }
}
