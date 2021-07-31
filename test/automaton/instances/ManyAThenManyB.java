package automaton.instances;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import automaton.FSA;
import automaton.minimize.Sample;

public class ManyAThenManyB {
  @Nested class Nest {
    final Sample $ = Sample.manyAThenManyB;
    Nest() {
      $.show();
    }
    @Nested class NFSA {
      final FSA<Character> $ = Nest.this.$.NFSA();
      @Test public void ε() { assert $.run(""); }
      @Test public void a() { assert $.run("a"); }
      @Test public void b() { assert $.run("b"); }
      @Test public void aa() { assert $.run("aa"); }
      @Test public void ab() { assert $.run("ab"); }
      @Test public void ba() { assert !$.run("ba"); }
      @Test public void bb() { assert $.run("bb"); }
      @Test public void aaa() { assert $.run("aaa"); }
      @Test public void aab() { assert $.run("aab"); }
      @Test public void aba() { assert !$.run("aba"); }
      @Test public void abb() { assert $.run("abb"); }
      @Test public void baa() { assert !$.run("baa"); }
      @Test public void bab() { assert !$.run("bab"); }
      @Test public void bba() { assert !$.run("bba"); }
      @Test public void bbb() { assert $.run("bbb"); }
      @Test public void aaaa() { assert $.run("aaaa"); }
      @Test public void aaab() { assert $.run("aaab"); }
      @Test public void aaba() { assert !$.run("aaba"); }
      @Test public void aabb() { assert $.run("aabb"); }
      @Test public void abaa() { assert !$.run("abaa"); }
      @Test public void abab() { assert !$.run("abab"); }
      @Test public void abba() { assert !$.run("abba"); }
      @Test public void abbb() { assert $.run("abbb"); }
      @Test public void baaa() { assert !$.run("baaa"); }
      @Test public void baab() { assert !$.run("baab"); }
      @Test public void baba() { assert !$.run("baba"); }
      @Test public void babb() { assert !$.run("babb"); }
      @Test public void bbaa() { assert !$.run("bbaa"); }
      @Test public void bbab() { assert !$.run("bbab"); }
      @Test public void bbba() { assert !$.run("bbba"); }
      @Test public void bbbb() { assert $.run("bbbb"); }
    }
    @Nested class DFSA {
      final FSA<Character> $ = Nest.this.$.DFSA();
      @Test public void ε() { assert $.run(""); }
      @Test public void a() { assert $.run("a"); }
      @Test public void b() { assert $.run("b"); }
      @Test public void aa() { assert $.run("aa"); }
      @Test public void ab() { assert $.run("ab"); }
      @Test public void ba() { assert !$.run("ba"); }
      @Test public void bb() { assert $.run("bb"); }
      @Test public void aaa() { assert $.run("aaa"); }
      @Test public void aab() { assert $.run("aab"); }
      @Test public void aba() { assert !$.run("aba"); }
      @Test public void abb() { assert $.run("abb"); }
      @Test public void baa() { assert !$.run("baa"); }
      @Test public void bab() { assert !$.run("bab"); }
      @Test public void bba() { assert !$.run("bba"); }
      @Test public void bbb() { assert $.run("bbb"); }
      @Test public void aaaa() { assert $.run("aaaa"); }
      @Test public void aaab() { assert $.run("aaab"); }
      @Test public void aaba() { assert !$.run("aaba"); }
      @Test public void aabb() { assert $.run("aabb"); }
      @Test public void abaa() { assert !$.run("abaa"); }
      @Test public void abab() { assert !$.run("abab"); }
      @Test public void abba() { assert !$.run("abba"); }
      @Test public void abbb() { assert $.run("abbb"); }
      @Test public void baaa() { assert !$.run("baaa"); }
      @Test public void baab() { assert !$.run("baab"); }
      @Test public void baba() { assert !$.run("baba"); }
      @Test public void babb() { assert !$.run("babb"); }
      @Test public void bbaa() { assert !$.run("bbaa"); }
      @Test public void bbab() { assert !$.run("bbab"); }
      @Test public void bbba() { assert !$.run("bbba"); }
      @Test public void bbbb() { assert $.run("bbbb"); }
    }
    @Nested class MDFSA {
      final FSA<Character> $ = Nest.this.$.MDFSA();
      @Test public void ε() {
        verify($);
        assert $.run("");
      }
      private void verify(FSA<Character> $) {
        verify1($);
        verify2($);
        verify3($);
        verify4($);
        verify5($);
      }
      void verify1(FSA<Character> $) {
        if (!$.ζ.contains($.q0)) assert false;
      }
      private void verify2(FSA<Character> $) {
        if ($.Q.size() != 2) assert false;
      }
      void verify3(FSA<Character> $) {
        if ($.ζ.size() != 2) assert false;
      }
      void verify4(FSA<Character> $) {
      }
      void verify5(FSA<Character> $) {
      }
      @Test public void a() { verify($); assert $.run("a"); }
      @Test public void b() { verify($); assert $.run("b"); }
      @Test public void aa() { verify($); assert $.run("aa"); }
      @Test public void ab() { verify($); assert $.run("ab"); }
      @Test public void ba() { verify($); assert !$.run("ba"); }
      @Test public void bb() { verify($); assert $.run("bb"); }
      @Test public void aaa() { verify($); assert $.run("aaa"); }
      @Test public void aab() { verify($); assert $.run("aab"); }
      @Test public void aba() { verify($); assert !$.run("aba"); }
      @Test public void abb() { verify($); assert $.run("abb"); }
      @Test public void baa() { verify($); assert !$.run("baa"); }
      @Test public void bab() { verify($); assert !$.run("bab"); }
      @Test public void bba() { verify($); assert !$.run("bba"); }
      @Test public void bbb() { verify($); assert $.run("bbb"); }
      @Test public void aaaa() { verify($); assert $.run("aaaa"); }
      @Test public void aaab() { verify($); assert $.run("aaab"); }
      @Test public void aaba() { verify($); assert !$.run("aaba"); }
      @Test public void aabb() { verify($); assert $.run("aabb"); }
      @Test public void abaa() { verify($); assert !$.run("abaa"); }
      @Test public void abab() { verify($); assert !$.run("abab"); }
      @Test public void abba() { verify($); assert !$.run("abba"); }
      @Test public void abbb() { verify($); assert $.run("abbb"); }
      @Test public void baaa() { verify($); assert !$.run("baaa"); }
      @Test public void baab() { verify($); assert !$.run("baab"); }
      @Test public void baba() { verify($); assert !$.run("baba"); }
      @Test public void babb() { verify($); assert !$.run("babb"); }
      @Test public void bbaa() { verify($); assert !$.run("bbaa"); }
      @Test public void bbab() { verify($); assert !$.run("bbab"); }
      @Test public void bbba() { verify($); assert !$.run("bbba"); }
      @Test public void bbbb() { verify($); assert $.run("bbbb"); }
      @Test public void verify_ε() { verify($); assert $.run(""); }
      @Test public void verify_a() { verify($); assert $.run("a"); }
      @Test public void verify_b() { verify($); assert $.run("b"); }
      @Test public void verify_aa() { verify($); assert $.run("aa"); }
      @Test public void verify_ab() { verify($); assert $.run("ab"); }
      @Test public void verify_ba() { verify($); assert !$.run("ba"); }
      @Test public void verify_bb() { verify($); assert $.run("bb"); }
      @Test public void verify_aaa() { verify($); assert $.run("aaa"); }
      @Test public void verify_aab() { verify($); assert $.run("aab"); }
      @Test public void verify_aba() { verify($); assert !$.run("aba"); }
      @Test public void verify_abb() { verify($); assert $.run("abb"); }
      @Test public void verify_baa() { verify($); assert !$.run("baa"); }
      @Test public void verify_bab() { verify($); assert !$.run("bab"); }
      @Test public void verify_bba() { verify($); assert !$.run("bba"); }
      @Test public void verify_bbb() { verify($); assert $.run("bbb"); }
      @Test public void verify_aaaa() { verify($); assert $.run("aaaa"); }
      @Test public void verify_aaab() { verify($); assert $.run("aaab"); }
      @Test public void verify_aaba() { verify($); assert !$.run("aaba"); }
      @Test public void verify_aabb() { verify($); assert $.run("aabb"); }
      @Test public void verify_abaa() { verify($); assert !$.run("abaa"); }
      @Test public void verify_abab() { verify($); assert !$.run("abab"); }
      @Test public void verify_abba() { verify($); assert !$.run("abba"); }
      @Test public void verify_abbb() { verify($); assert $.run("abbb"); }
      @Test public void verify_baaa() { verify($); assert !$.run("baaa"); }
      @Test public void verify_baab() { verify($); assert !$.run("baab"); }
      @Test public void verify_baba() { verify($); assert !$.run("baba"); }
      @Test public void verify_babb() { verify($); assert !$.run("babb"); }
      @Test public void verify_bbaa() { verify($); assert !$.run("bbaa"); }
      @Test public void verify_bbab() { verify($); assert !$.run("bbab"); }
      @Test public void verify_bbba() { verify($); assert !$.run("bbba"); }
      @Test public void verify_bbbb() { verify($); assert $.run("bbbb"); }
      @Nested public class V1 {
        @Test public void verify1_ε() { verify1($); assert $.run(""); }
        @Test public void verify1_a() { verify1($); assert $.run("a"); }
        @Test public void verify1_b() { verify1($); assert $.run("b"); }
        @Test public void verify1_aa() { verify1($); assert $.run("aa"); }
        @Test public void verify1_ab() { verify1($); assert $.run("ab"); }
        @Test public void verify1_ba() { verify1($); assert !$.run("ba"); }
        @Test public void verify1_bb() { verify1($); assert $.run("bb"); }
        @Test public void verify1_aaa() { verify1($); assert $.run("aaa"); }
        @Test public void verify1_aab() { verify1($); assert $.run("aab"); }
        @Test public void verify1_aba() { verify1($); assert !$.run("aba"); }
        @Test public void verify1_abb() { verify1($); assert $.run("abb"); }
        @Test public void verify1_baa() { verify1($); assert !$.run("baa"); }
        @Test public void verify1_bab() { verify1($); assert !$.run("bab"); }
        @Test public void verify1_bba() { verify1($); assert !$.run("bba"); }
        @Test public void verify1_bbb() { verify1($); assert $.run("bbb"); }
        @Test public void verify1_aaaa() { verify1($); assert $.run("aaaa"); }
        @Test public void verify1_aaab() { verify1($); assert $.run("aaab"); }
        @Test public void verify1_aaba() { verify1($); assert !$.run("aaba"); }
        @Test public void verify1_aabb() { verify1($); assert $.run("aabb"); }
        @Test public void verify1_abaa() { verify1($); assert !$.run("abaa"); }
        @Test public void verify1_abab() { verify1($); assert !$.run("abab"); }
        @Test public void verify1_abba() { verify1($); assert !$.run("abba"); }
        @Test public void verify1_abbb() { verify1($); assert $.run("abbb"); }
        @Test public void verify1_baaa() { verify1($); assert !$.run("baaa"); }
        @Test public void verify1_baab() { verify1($); assert !$.run("baab"); }
        @Test public void verify1_baba() { verify1($); assert !$.run("baba"); }
        @Test public void verify1_babb() { verify1($); assert !$.run("babb"); }
        @Test public void verify1_bbaa() { verify1($); assert !$.run("bbaa"); }
        @Test public void verify1_bbab() { verify1($); assert !$.run("bbab"); }
        @Test public void verify1_bbba() { verify1($); assert !$.run("bbba"); }
        @Test public void verify1_bbbb() { verify1($); assert $.run("bbbb"); }
      }
      @Nested public class V2 {
        @Test public void verify2_ε() { verify1($); assert $.run(""); }
        @Test public void verify2_a() { verify1($); assert $.run("a"); }
        @Test public void verify2_b() { verify1($); assert $.run("b"); }
        @Test public void verify2_aa() { verify1($); assert $.run("aa"); }
        @Test public void verify2_ab() { verify1($); assert $.run("ab"); }
        @Test public void verify2_ba() { verify1($); assert !$.run("ba"); }
        @Test public void verify2_bb() { verify1($); assert $.run("bb"); }
        @Test public void verify2_aaa() { verify1($); assert $.run("aaa"); }
        @Test public void verify2_aab() { verify1($); assert $.run("aab"); }
        @Test public void verify2_aba() { verify1($); assert !$.run("aba"); }
        @Test public void verify2_abb() { verify1($); assert $.run("abb"); }
        @Test public void verify2_baa() { verify1($); assert !$.run("baa"); }
        @Test public void verify2_bab() { verify1($); assert !$.run("bab"); }
        @Test public void verify2_bba() { verify1($); assert !$.run("bba"); }
        @Test public void verify2_bbb() { verify1($); assert $.run("bbb"); }
        @Test public void verify2_aaaa() { verify1($); assert $.run("aaaa"); }
        @Test public void verify2_aaab() { verify1($); assert $.run("aaab"); }
        @Test public void verify2_aaba() { verify1($); assert !$.run("aaba"); }
        @Test public void verify2_aabb() { verify1($); assert $.run("aabb"); }
        @Test public void verify2_abaa() { verify1($); assert !$.run("abaa"); }
        @Test public void verify2_abab() { verify1($); assert !$.run("abab"); }
        @Test public void verify2_abba() { verify1($); assert !$.run("abba"); }
        @Test public void verify2_abbb() { verify1($); assert $.run("abbb"); }
        @Test public void verify2_baaa() { verify1($); assert !$.run("baaa"); }
        @Test public void verify2_baab() { verify1($); assert !$.run("baab"); }
        @Test public void verify2_baba() { verify1($); assert !$.run("baba"); }
        @Test public void verify2_babb() { verify1($); assert !$.run("babb"); }
        @Test public void verify2_bbaa() { verify1($); assert !$.run("bbaa"); }
        @Test public void verify2_bbab() { verify1($); assert !$.run("bbab"); }
        @Test public void verify2_bbba() { verify1($); assert !$.run("bbba"); }
        @Test public void verify2_bbbb() { verify1($); assert $.run("bbbb"); }
      }
      @Nested public class V3 {
        @Test public void verify3_ε() { verify3($); assert $.run(""); }
        @Test public void verify3_a() { verify3($); assert $.run("a"); }
        @Test public void verify3_b() { verify3($); assert $.run("b"); }
        @Test public void verify3_aa() { verify3($); assert $.run("aa"); }
        @Test public void verify3_ab() { verify3($); assert $.run("ab"); }
        @Test public void verify3_ba() { verify3($); assert !$.run("ba"); }
        @Test public void verify3_bb() { verify3($); assert $.run("bb"); }
        @Test public void verify3_aaa() { verify3($); assert $.run("aaa"); }
        @Test public void verify3_aab() { verify3($); assert $.run("aab"); }
        @Test public void verify3_aba() { verify3($); assert !$.run("aba"); }
        @Test public void verify3_abb() { verify3($); assert $.run("abb"); }
        @Test public void verify3_baa() { verify3($); assert !$.run("baa"); }
        @Test public void verify3_bab() { verify3($); assert !$.run("bab"); }
        @Test public void verify3_bba() { verify3($); assert !$.run("bba"); }
        @Test public void verify3_bbb() { verify3($); assert $.run("bbb"); }
        @Test public void verify3_aaaa() { verify3($); assert $.run("aaaa"); }
        @Test public void verify3_aaab() { verify3($); assert $.run("aaab"); }
        @Test public void verify3_aaba() { verify3($); assert !$.run("aaba"); }
        @Test public void verify3_aabb() { verify3($); assert $.run("aabb"); }
        @Test public void verify3_abaa() { verify3($); assert !$.run("abaa"); }
        @Test public void verify3_abab() { verify3($); assert !$.run("abab"); }
        @Test public void verify3_abba() { verify3($); assert !$.run("abba"); }
        @Test public void verify3_abbb() { verify3($); assert $.run("abbb"); }
        @Test public void verify3_baaa() { verify3($); assert !$.run("baaa"); }
        @Test public void verify3_baab() { verify3($); assert !$.run("baab"); }
        @Test public void verify3_baba() { verify3($); assert !$.run("baba"); }
        @Test public void verify3_babb() { verify3($); assert !$.run("babb"); }
        @Test public void verify3_bbaa() { verify3($); assert !$.run("bbaa"); }
        @Test public void verify3_bbab() { verify3($); assert !$.run("bbab"); }
        @Test public void verify3_bbba() { verify3($); assert !$.run("bbba"); }
        @Test public void verify3_bbbb() { verify3($); assert $.run("bbbb"); }
      }
      @Nested public class V4 {
        @Test public void verify4_ε() { verify4($); assert $.run(""); }
        @Test public void verify4_a() { verify4($); assert $.run("a"); }
        @Test public void verify4_b() { verify4($); assert $.run("b"); }
        @Test public void verify4_aa() { verify4($); assert $.run("aa"); }
        @Test public void verify4_ab() { verify4($); assert $.run("ab"); }
        @Test public void verify4_ba() { verify4($); assert !$.run("ba"); }
        @Test public void verify4_bb() { verify4($); assert $.run("bb"); }
        @Test public void verify4_aaa() { verify4($); assert $.run("aaa"); }
        @Test public void verify4_aab() { verify4($); assert $.run("aab"); }
        @Test public void verify4_aba() { verify4($); assert !$.run("aba"); }
        @Test public void verify4_abb() { verify4($); assert $.run("abb"); }
        @Test public void verify4_baa() { verify4($); assert !$.run("baa"); }
        @Test public void verify4_bab() { verify4($); assert !$.run("bab"); }
        @Test public void verify4_bba() { verify4($); assert !$.run("bba"); }
        @Test public void verify4_bbb() { verify4($); assert $.run("bbb"); }
        @Test public void verify4_aaaa() { verify4($); assert $.run("aaaa"); }
        @Test public void verify4_aaab() { verify4($); assert $.run("aaab"); }
        @Test public void verify4_aaba() { verify4($); assert !$.run("aaba"); }
        @Test public void verify4_aabb() { verify4($); assert $.run("aabb"); }
        @Test public void verify4_abaa() { verify4($); assert !$.run("abaa"); }
        @Test public void verify4_abab() { verify4($); assert !$.run("abab"); }
        @Test public void verify4_abba() { verify4($); assert !$.run("abba"); }
        @Test public void verify4_abbb() { verify4($); assert $.run("abbb"); }
        @Test public void verify4_baaa() { verify4($); assert !$.run("baaa"); }
        @Test public void verify4_baab() { verify4($); assert !$.run("baab"); }
        @Test public void verify4_baba() { verify4($); assert !$.run("baba"); }
        @Test public void verify4_babb() { verify4($); assert !$.run("babb"); }
        @Test public void verify4_bbaa() { verify4($); assert !$.run("bbaa"); }
        @Test public void verify4_bbab() { verify4($); assert !$.run("bbab"); }
        @Test public void verify4_bbba() { verify4($); assert !$.run("bbba"); }
        @Test public void verify4_bbbb() { verify4($); assert $.run("bbbb"); }
      }
      @Nested public class V5 {
        @Test public void verify5_ε() { verify5($); assert $.run(""); }
        @Test public void verify5_a() { verify5($); assert $.run("a"); }
        @Test public void verify5_b() { verify5($); assert $.run("b"); }
        @Test public void verify5_aa() { verify5($); assert $.run("aa"); }
        @Test public void verify5_ab() { verify5($); assert $.run("ab"); }
        @Test public void verify5_ba() { verify5($); assert !$.run("ba"); }
        @Test public void verify5_bb() { verify5($); assert $.run("bb"); }
        @Test public void verify5_aaa() { verify5($); assert $.run("aaa"); }
        @Test public void verify5_aab() { verify5($); assert $.run("aab"); }
        @Test public void verify5_aba() { verify5($); assert !$.run("aba"); }
        @Test public void verify5_abb() { verify5($); assert $.run("abb"); }
        @Test public void verify5_baa() { verify5($); assert !$.run("baa"); }
        @Test public void verify5_bab() { verify5($); assert !$.run("bab"); }
        @Test public void verify5_bba() { verify5($); assert !$.run("bba"); }
        @Test public void verify5_bbb() { verify5($); assert $.run("bbb"); }
        @Test public void verify5_aaaa() { verify5($); assert $.run("aaaa"); }
        @Test public void verify5_aaab() { verify5($); assert $.run("aaab"); }
        @Test public void verify5_aaba() { verify5($); assert !$.run("aaba"); }
        @Test public void verify5_aabb() { verify5($); assert $.run("aabb"); }
        @Test public void verify5_abaa() { verify5($); assert !$.run("abaa"); }
        @Test public void verify5_abab() { verify5($); assert !$.run("abab"); }
        @Test public void verify5_abba() { verify5($); assert !$.run("abba"); }
        @Test public void verify5_abbb() { verify5($); assert $.run("abbb"); }
        @Test public void verify5_baaa() { verify5($); assert !$.run("baaa"); }
        @Test public void verify5_baab() { verify5($); assert !$.run("baab"); }
        @Test public void verify5_baba() { verify5($); assert !$.run("baba"); }
        @Test public void verify5_babb() { verify5($); assert !$.run("babb"); }
        @Test public void verify5_bbaa() { verify5($); assert !$.run("bbaa"); }
        @Test public void verify5_bbab() { verify5($); assert !$.run("bbab"); }
        @Test public void verify5_bbba() { verify5($); assert !$.run("bbba"); }
        @Test public void verify5_bbbb() { verify5($); assert $.run("bbbb"); }
      }
    }
  }
}
