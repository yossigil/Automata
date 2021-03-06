package automaton;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import automaton.minimize.Sample;

public class ScrutinizeManyAThenManyB {
  @Nested public class All {
    All() { $.show(); }
    final Sample $ = Sample.manyAThenManyB;
    @Nested public class DFSA {
      @Test public void a() { assert $.run("a"); }
      @Test public void aa() { assert $.run("aa"); }
      @Test public void aaa() { assert $.run("aaa"); }
      @Test public void aaaa() { assert $.run("aaaa"); }
      @Test public void aaab() { assert $.run("aaab"); }
      @Test public void aab() { assert $.run("aab"); }
      @Test public void aaba() { assert !$.run("aaba"); }
      @Test public void aabb() { assert $.run("aabb"); }
      @Test public void ab() { assert $.run("ab"); }
      @Test public void aba() { assert !$.run("aba"); }
      @Test public void abaa() { assert !$.run("abaa"); }
      @Test public void abab() { assert !$.run("abab"); }
      @Test public void abb() { assert $.run("abb"); }
      @Test public void abba() { assert !$.run("abba"); }
      @Test public void abbb() { assert $.run("abbb"); }
      @Test public void b() { assert $.run("b"); }
      @Test public void ba() { assert !$.run("ba"); }
      @Test public void baa() { assert !$.run("baa"); }
      @Test public void baaa() { assert !$.run("baaa"); }
      @Test public void baab() { assert !$.run("baab"); }
      @Test public void bab() { assert !$.run("bab"); }
      @Test public void baba() { assert !$.run("baba"); }
      @Test public void babb() { assert !$.run("babb"); }
      @Test public void bb() { assert $.run("bb"); }
      @Test public void bba() { assert !$.run("bba"); }
      @Test public void bbaa() { assert !$.run("bbaa"); }
      @Test public void bbab() { assert !$.run("bbab"); }
      @Test public void bbb() { assert $.run("bbb"); }
      @Test public void bbba() { assert !$.run("bbba"); }
      @Test public void bbbb() { assert $.run("bbbb"); }
      @Test public void ??() { assert $.run(""); }
      final FSA<Character> $ = All.this.$.DFSA();
    }
    @Nested public class MDFSA {
      Q q(FSA<Character> $) { for (Q q : $.Q) if (q != $.q0) return q; return null; }
      public void q0in??(FSA<Character> $) { if (!$.??.contains($.q0)) assert false : "q0in??"; }
      public void QSizeIs2(FSA<Character> $) { if ($.Q.size() != 2) assertEquals($.Q.size(), 2); }
      @TestFactory Stream<? super DynamicTest> structure() {
        Q q0 = q0($), q1 = q1($), ABORT = ??.REJECT;
        assertEquals($.??($.q0, Character.valueOf('a')), q0($));
        Character a = Character.valueOf('a'), b = Character.valueOf('b'), c = Character.valueOf('c');
        return Stream.of(//
            dynamicTest("q0?????", () -> q0in??($)), dynamicTest("q1?????", () -> assertTrue($.??.contains(q1($)))), //
            dynamicTest("|Q|=2", () -> assertEquals(2, $.Q.size())), dynamicTest("???=NULL", () -> assertNull(??.REJECT)),
            dynamicTest("??????Q", () -> assertFalse($.Q.contains(??.REJECT))), //
            dynamicTest("|??|=2", () -> assertEquals(2, $.??.size())),
            dynamicTest("??(q0,a)=q0", () -> assertEquals(q0, $.??(q0, a))), //
            dynamicTest("??(q0,b)=q1", () -> assertEquals(q1, $.??(q0, b))), //
            dynamicTest("??(q0,c)=???", () -> assertEquals(ABORT, $.??(q0, c))), //
            dynamicTest("??(q1,a)=???", () -> assertEquals(ABORT, $.??(q1, a))), //
            dynamicTest("??(q1,b)=q1", () -> assertEquals(q1, $.??(q1, b))), //
            dynamicTest("??(q1,c)=???", () -> assertEquals(ABORT, $.??(q1, c))) //
        );
      }
      public void ??q0(FSA<Character> $) { ??q0a($); }
      public void ??q0a(FSA<Character> $) { assertEquals($.??($.q0, Character.valueOf('a')), q0($)); }
      public void ??SizeIs2(FSA<Character> $) { assertEquals(2, $.??.size()); }
      private Q q0(FSA<Character> $2) { return $.q0; }
      private Q q1(FSA<Character> $2) { for (Q q : $.Q) if (q != $.q0) return q; return null; }
      final FSA<Character> $ = All.this.$.MDFSA();
      @Test public void exists() {
        $.toString();
      }
      @Test public void a() { assert $.run("a"); }
      @Test public void aa() { assert $.run("aa"); }
      @Test public void aaa() { assert $.run("aaa"); }
      @Test public void aaaa() { assert $.run("aaaa"); }
      @Test public void aaab() { assert $.run("aaab"); }
      @Test public void aab() { assert $.run("aab"); }
      @Test public void aaba() { assert !$.run("aaba"); }
      @Test public void aabb() { assert $.run("aabb"); }
      @Test public void ab() { assert $.run("ab"); }
      @Test public void aba() { assert !$.run("aba"); }
      @Test public void abaa() { assert !$.run("abaa"); }
      @Test public void abab() { assert !$.run("abab"); }
      @Test public void abb() { assert $.run("abb"); }
      @Test public void abba() { assert !$.run("abba"); }
      @Test public void abbb() { assert $.run("abbb"); }
      @Test public void b() { assert $.run("b"); }
      @Test public void ba() { assert !$.run("ba"); }
      @Test public void baa() { assert !$.run("baa"); }
      @Test public void baaa() { assert !$.run("baaa"); }
      @Test public void baab() { assert !$.run("baab"); }
      @Test public void bab() { assert !$.run("bab"); }
      @Test public void baba() { assert !$.run("baba"); }
      @Test public void babb() { assert !$.run("babb"); }
      @Test public void bb() { assert $.run("bb"); }
      @Test public void bba() { assert !$.run("bba"); }
      @Test public void bbaa() { assert !$.run("bbaa"); }
      @Test public void bbab() { assert !$.run("bbab"); }
      @Test public void bbb() { assert $.run("bbb"); }
      @Test public void bbba() { assert !$.run("bbba"); }
      @Test public void bbbb() { assert $.run("bbbb"); }
      @Test public void ??() { assert $.run(""); }
    }
    @Nested public class NFSA {
      @Test public void a() { assert $.run("a"); }
      @Test public void aa() { assert $.run("aa"); }
      @Test public void aaa() { assert $.run("aaa"); }
      @Test public void aaaa() { assert $.run("aaaa"); }
      @Test public void aaab() { assert $.run("aaab"); }
      @Test public void aab() { assert $.run("aab"); }
      @Test public void aaba() { assert !$.run("aaba"); }
      @Test public void aabb() { assert $.run("aabb"); }
      @Test public void ab() { assert $.run("ab"); }
      @Test public void aba() { assert !$.run("aba"); }
      @Test public void abaa() { assert !$.run("abaa"); }
      @Test public void abab() { assert !$.run("abab"); }
      @Test public void abb() { assert $.run("abb"); }
      @Test public void abba() { assert !$.run("abba"); }
      @Test public void abbb() { assert $.run("abbb"); }
      @Test public void b() { assert $.run("b"); }
      @Test public void ba() { assert !$.run("ba"); }
      @Test public void baa() { assert !$.run("baa"); }
      @Test public void baaa() { assert !$.run("baaa"); }
      @Test public void baab() { assert !$.run("baab"); }
      @Test public void bab() { assert !$.run("bab"); }
      @Test public void baba() { assert !$.run("baba"); }
      @Test public void babb() { assert !$.run("babb"); }
      @Test public void bb() { assert $.run("bb"); }
      @Test public void bba() { assert !$.run("bba"); }
      @Test public void bbaa() { assert !$.run("bbaa"); }
      @Test public void bbab() { assert !$.run("bbab"); }
      @Test public void bbb() { assert $.run("bbb"); }
      @Test public void bbba() { assert !$.run("bbba"); }
      @Test public void bbbb() { assert $.run("bbbb"); }
      @Test public void ??() { assert $.run(""); }
      final FSA<Character> $ = All.this.$.NFSA();
    }
  }
}
