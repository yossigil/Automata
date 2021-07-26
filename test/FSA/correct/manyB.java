package FSA.correct;


import org.junit.Test;

import automaton.Lexer;

public class manyB {
  final Lexer $ = Lexer.c('b').many();
  {
    System.out.println($.inner);
    System.out.println($.TikZ());
  }
  @Test void alphabetNotEmpty() {
    assert !$.inner.Σ.isEmpty();
  }
  @Test void alphabetLess() {
    assert $.inner.Σ.size() <= 1;
  }
  @Test void alphabetMore() {
    assert $.inner.Σ.size() >= 1;
  }
  @Test void alphabetCorrect() {
    assert $.inner.Σ.contains(Character.valueOf('b'));
  }
  @Test void StatesSetSize() {
    assert $.inner.Q.size() == 2;
  }
  @Test void StatesSetContainsQ0() {
    assert $.inner.Q.contains($.inner.q0);
  }
  @Test void accept0() { assert $.run(""); }
  @Test void accept1() { assert $.run("b"); }
  @Test void accept2() { assert $.run("bb"); }
  @Test void accept3() { assert $.run("bbb"); }
  @Test void reject0() { assert !$.run("ba"); }
  @Test void reject1() { assert !$.run("ba"); }
  @Test void reject2() { assert !$.run("babab"); }
  @Test void reject3() { assert !$.run("abababa"); }
}
