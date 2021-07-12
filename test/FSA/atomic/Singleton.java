package FSA.atomic;

import org.junit.jupiter.api.Test;

import finite.Lexer;

class Singleton {
  final Lexer a = new Lexer('a');
  final Lexer b = new Lexer('b');
  @Test void aOK() {
    assert a.run("a");
  }
  @Test void aOther() {
    assert !a.run("b");
  }
  @Test void aEmpty() {
    assert !a.run("");
  }
  @Test void bOK() {
    assert b.run("b");
  }
}