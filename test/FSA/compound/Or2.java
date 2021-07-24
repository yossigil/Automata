package FSA.compound;

import org.junit.jupiter.api.Test;

import automaton.Lexer;

class Or2 {
  final Lexer a_b = Lexer.c('a').or(Lexer.c('b'));
  @Test void acceptA() {
    assert a_b.run("a");
  }
  @Test void acceptB() {
    assert a_b.run("b");
  }
  @Test void rejectEmpty() {
    assert !a_b.run("");
  }
  @Test void rejectAB() {
    assert !a_b.run("ab");
  }
  @Test void rejectAA() {
    assert !a_b.run("ab");
  }
  @Test void rejectBB() {
    assert !a_b.run("bb");
  }
  @Test void rejectBA() {
    assert !a_b.run("ba");
  }
}