package automaton.instances;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import automaton.FSA;
import automaton.minimize.Sample;

public class ManyAB {
  final Sample $ = Sample.many$ab;
  {
    Sample.many$ab.show();
  }  @Nested class NFSA {
    final FSA<Character> $ = ManyAB.this.$.NFSA();
    @Test public void ε() { assert $.run(""); }
    @Test public void a() { assert !$.run("a"); }
    @Test public void b() { assert !$.run("b"); }
    @Test public void aa() { assert !$.run("aa"); }
    @Test public void ab() { assert $.run("ab"); }
    @Test public void ba() { assert !$.run("ba"); }
    @Test public void bb() { assert !$.run("bb"); }
    @Test public void aaa() { assert !$.run("aaa"); }
    @Test public void aab() { assert !$.run("aab"); }
    @Test public void aba() { assert !$.run("aba"); }
    @Test public void abb() { assert !$.run("abb"); }
    @Test public void baa() { assert !$.run("baa"); }
    @Test public void bab() { assert !$.run("bab"); }
    @Test public void bba() { assert !$.run("bba"); }
    @Test public void bbb() { assert !$.run("bbb"); }
    @Test public void aaaa() { assert !$.run("aaaa"); }
    @Test public void aaab() { assert !$.run("aaab"); }
    @Test public void aaba() { assert !$.run("aaba"); }
    @Test public void aabb() { assert !$.run("aabb"); }
    @Test public void abaa() { assert !$.run("abaa"); }
    @Test public void abab() { assert $.run("abab"); }
    @Test public void abba() { assert !$.run("abba"); }
    @Test public void abbb() { assert !$.run("abbb"); }
    @Test public void baaa() { assert !$.run("baaa"); }
    @Test public void baab() { assert !$.run("baab"); }
    @Test public void baba() { assert !$.run("baba"); }
    @Test public void babb() { assert !$.run("babb"); }
    @Test public void bbaa() { assert !$.run("bbaa"); }
    @Test public void bbab() { assert !$.run("bbab"); }
    @Test public void bbba() { assert !$.run("bbba"); }
    @Test public void bbbb() { assert !$.run("bbbb"); }
  }


  @Nested class DFSA {
    final FSA<Character> $ = ManyAB.this.$.DFSA();
    @Test public void ε() { assert $.run(""); }
    @Test public void a() { assert !$.run("a"); }
    @Test public void b() { assert !$.run("b"); }
    @Test public void aa() { assert !$.run("aa"); }
    @Test public void ab() { assert $.run("ab"); }
    @Test public void ba() { assert !$.run("ba"); }
    @Test public void bb() { assert !$.run("bb"); }
    @Test public void aaa() { assert !$.run("aaa"); }
    @Test public void aab() { assert !$.run("aab"); }
    @Test public void aba() { assert !$.run("aba"); }
    @Test public void abb() { assert !$.run("abb"); }
    @Test public void baa() { assert !$.run("baa"); }
    @Test public void bab() { assert !$.run("bab"); }
    @Test public void bba() { assert !$.run("bba"); }
    @Test public void bbb() { assert !$.run("bbb"); }
    @Test public void aaaa() { assert !$.run("aaaa"); }
    @Test public void aaab() { assert !$.run("aaab"); }
    @Test public void aaba() { assert !$.run("aaba"); }
    @Test public void aabb() { assert !$.run("aabb"); }
    @Test public void abaa() { assert !$.run("abaa"); }
    @Test public void abab() { assert $.run("abab"); }
    @Test public void abba() { assert !$.run("abba"); }
    @Test public void abbb() { assert !$.run("abbb"); }
    @Test public void baaa() { assert !$.run("baaa"); }
    @Test public void baab() { assert !$.run("baab"); }
    @Test public void baba() { assert !$.run("baba"); }
    @Test public void babb() { assert !$.run("babb"); }
    @Test public void bbaa() { assert !$.run("bbaa"); }
    @Test public void bbab() { assert !$.run("bbab"); }
    @Test public void bbba() { assert !$.run("bbba"); }
    @Test public void bbbb() { assert !$.run("bbbb"); }
  }

  @Nested class MDFSA {
    final FSA<Character> $ = ManyAB.this.$.MDFSA();
    @Test public void ε() { assert $.run(""); }
    @Test public void a() { assert !$.run("a"); }
    @Test public void b() { assert !$.run("b"); }
    @Test public void aa() { assert !$.run("aa"); }
    @Test public void ab() { assert $.run("ab"); }
    @Test public void ba() { assert !$.run("ba"); }
    @Test public void bb() { assert !$.run("bb"); }
    @Test public void aaa() { assert !$.run("aaa"); }
    @Test public void aab() { assert !$.run("aab"); }
    @Test public void aba() { assert !$.run("aba"); }
    @Test public void abb() { assert !$.run("abb"); }
    @Test public void baa() { assert !$.run("baa"); }
    @Test public void bab() { assert !$.run("bab"); }
    @Test public void bba() { assert !$.run("bba"); }
    @Test public void bbb() { assert !$.run("bbb"); }
    @Test public void aaaa() { assert !$.run("aaaa"); }
    @Test public void aaab() { assert !$.run("aaab"); }
    @Test public void aaba() { assert !$.run("aaba"); }
    @Test public void aabb() { assert !$.run("aabb"); }
    @Test public void abaa() { assert !$.run("abaa"); }
    @Test public void abab() { assert $.run("abab"); }
    @Test public void abba() { assert !$.run("abba"); }
    @Test public void abbb() { assert !$.run("abbb"); }
    @Test public void baaa() { assert !$.run("baaa"); }
    @Test public void baab() { assert !$.run("baab"); }
    @Test public void baba() { assert !$.run("baba"); }
    @Test public void babb() { assert !$.run("babb"); }
    @Test public void bbaa() { assert !$.run("bbaa"); }
    @Test public void bbab() { assert !$.run("bbab"); }
    @Test public void bbba() { assert !$.run("bbba"); }
    @Test public void bbbb() { assert !$.run("bbbb"); }
    @Test public void ababab() { assert $.run("ababab"); }
  }

}

