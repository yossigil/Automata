package automaton.instances;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import automaton.FSA;
import automaton.minimize.Sample;

public class ManyA {
  final Sample $ = Sample.manyA;
  {
    $.show();
  }
  @Nested class DFSA {
    FSA<Character> $ = ManyA.this.$.DFSA();
    @Test void aaaa() { assert $.run("aaaa"); }
    @Test void aaa() { assert $.run("aaa"); }
    @Test void aa() { assert $.run("aa"); }
    @Test void aababab() { assert !$.run("aababab"); }
    @Test void aab() { assert !$.run("aab"); }
    @Test void a() { assert $.run("a"); }
    @Test void abababa() { assert !$.run("abababa"); }
    @Test void abb() { assert !$.run("abb"); }
    @Test void ac() { assert !$.run("ac"); }
    @Test void accept0() { assert $.run(""); }
    @Test void ba() { assert !$.run("ba"); }
    @Test void b() { assert !$.run("b"); }
    @Test void bbb() { assert !$.run("bbb"); }
    @Test void c() { assert !$.run("c"); }
    @Test void cb() { assert !$.run("cb"); }
  }
  @Nested class NFSA {
    FSA<Character> $ = ManyA.this.$.NFSA();
    @Test void aaaa() { assert $.run("aaaa"); }
    @Test void aaa() { assert $.run("aaa"); }
    @Test void aa() { assert $.run("aa"); }
    @Test void aababab() { assert !$.run("aababab"); }
    @Test void aab() { assert !$.run("aab"); }
    @Test void a() { assert $.run("a"); }
    @Test void abababa() { assert !$.run("abababa"); }
    @Test void abb() { assert !$.run("abb"); }
    @Test void ac() { assert !$.run("ac"); }
    @Test void accept0() { assert $.run(""); }
    @Test void ba() { assert !$.run("ba"); }
    @Test void b() { assert !$.run("b"); }
    @Test void bbb() { assert !$.run("bbb"); }
    @Test void c() { assert !$.run("c"); }
    @Test void cb() { assert !$.run("cb"); }
  }
  @Nested class MDFSA {
    FSA<Character> $ = ManyA.this.$.MDFSA();
    @Test void aaaa() { assert $.run("aaaa"); }
    @Test void aaa() { assert $.run("aaa"); }
    @Test void aa() { assert $.run("aa"); }
    @Test void aababab() { assert !$.run("aababab"); }
    @Test void aab() { assert !$.run("aab"); }
    @Test void a() { assert $.run("a"); }
    @Test void abababa() { assert !$.run("abababa"); }
    @Test void abb() { assert !$.run("abb"); }
    @Test void ac() { assert !$.run("ac"); }
    @Test void accept0() { assert $.run(""); }
    @Test void ba() { assert !$.run("ba"); }
    @Test void b() { assert !$.run("b"); }
    @Test void bbb() { assert !$.run("bbb"); }
    @Test void c() { assert !$.run("c"); }
    @Test void cb() { assert !$.run("cb"); }
  }
}
