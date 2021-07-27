package automaton.instances;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import automaton.FSA;
import automaton.minimize.Sample;

public class ABA {
  final Sample $ = Sample.aba;
  {
    $.show();
  }
  @Nested class NFSA {
    FSA<Character> $ = ABA.this.$.NFSA();
    @Test void ε() { assert !$.run(""); }
    @Test void ab() { assert !$.run("ab"); }
    @Test void abb() { assert !$.run("abb"); }
    @Test void aba() { assert $.run("aba"); }
    @Test void abc() { assert !$.run("abc"); }
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
    @Test void abaaba() { assert !$.run("abaaba"); }
    @Test void ababab() { assert !$.run("ababab"); }
  }
  @Nested class DFSA {
    FSA<Character> $ = ABA.this.$.DFSA();
    @Test void ε() { assert !$.run(""); }
    @Test void ab() { assert !$.run("ab"); }
    @Test void abb() { assert !$.run("abb"); }
    @Test void aba() { assert $.run("aba"); }
    @Test void abc() { assert $.run("aba"); }
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
    @Test void abaaba() { assert! $.run("abaaba"); }
    @Test void ababab() { assert !$.run("ababab"); }
  }

  @Nested class MDFSA {
    FSA<Character> $ = ABA.this.$.MDFSA();
    @Test void ε() { assert !$.run(""); }
    @Test void ab() { assert !$.run("ab"); }
    @Test void abb() { assert !$.run("abb"); }
    @Test void aba() { assert $.run("aba"); }
    @Test void abc() { assert $.run("aba"); }
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
    @Test void abaaba() { assert !$.run("abaaba"); }
    @Test void ababab() { assert !$.run("ababab"); }
  }

}
