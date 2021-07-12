package FSA.atomic;

import org.junit.jupiter.api.Test;

import finite.Lexer;

class Empty {
  final Lexer empty = new Lexer();
  @Test void emptyLanguage() {
    assert !empty.run("");
    assert !empty.run("a");
    assert !empty.run("abca");
  }
}