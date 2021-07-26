package automaton.atomic;

import org.junit.Test;

import automaton.Lexer;

public class SingleCharacterLanguage {
  final Lexer a = Lexer.c('a');
  final Lexer b = Lexer.c('b');
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