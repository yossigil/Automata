package FSA.compound;

import org.junit.Test;

import automaton.Lexer;

class Or3 {
  final Lexer a_b_c = Lexer.c('a').Or('b').Or('c');
  @Test void acceptA() {
    assert a_b_c.run("a");
  }
  @Test void acceptB() {
    assert a_b_c.run("b");
  }
  @Test void acceptC() {
    assert a_b_c.run("c");
  }
  @Test void rejectEmpty() {
    assert !a_b_c.run("");
  }
  @Test void rejectAB() {
    assert !a_b_c.run("ab");
  }
  @Test void rejectAA() {
    assert !a_b_c.run("aa");
  }
  @Test void rejectD() {
    assert !a_b_c.run("d");
  }
  @Test void rejectBA() {
    assert !a_b_c.run("ba");
  }
}