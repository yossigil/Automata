package FSA.atomic;

import org.junit.jupiter.api.Test;

import finite.Lexer;

class EmptyLanguage {
  final Lexer empty = Lexer.Φ();
  @Test void emptyLanguage() {
    assert !empty.run("");
    assert !empty.run("a");
    assert !empty.run("abca");
  }
}