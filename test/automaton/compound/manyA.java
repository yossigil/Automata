package automaton.compound;


import org.junit.Test;

import automaton.Lexer;

public class manyA {
  final Lexer $ = Lexer.c('a');
  {
    System.out.println($.inner);
    System.out.println($.TikZ());
  }
 @Test void alphabetSize() {
    assert $.inner.Σ.size() == 1;
  }
  @Test void alphabetCorrect() {
    assert $.inner.Σ.contains(Character.valueOf('a'));
  }
  @Test void StateSize() {
    assert $.inner.Q.size() == 2;
  }
  @Test void accept() { assert $.run("a"); }
  @Test void reject0() { assert !$.run(""); }
  @Test void reject8() { assert !$.run("ba"); }
  @Test void reject7() { assert !$.run("aa"); }
  @Test void reject6() { assert !$.run("aaa"); }
  @Test void reject1() { assert !$.run("ba"); }
  @Test void reject2() { assert !$.run("babab"); }
  @Test void reject3() { assert !$.run("abababa"); }
  @Test void reject4() { assert !$.run("c"); }
}
