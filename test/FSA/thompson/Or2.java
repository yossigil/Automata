package FSA.thompson;

import org.junit.jupiter.api.Test;

import finite.Lexer;

class Or2 {
  final Lexer a_b = new Lexer(new Lexer('a').or(new Lexer('b')));
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