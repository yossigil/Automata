package automaton.atomic;

import org.junit.Test;

import automaton.Lexer;

class EmptyLanguage {
  final Lexer empty = Lexer.Φ();
  @Test void emptyLanguage() {
    assert !empty.run("");
    assert !empty.run("a");
    assert !empty.run("abca");
  }
}