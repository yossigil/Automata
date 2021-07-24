package FSA.atomic;

import org.junit.jupiter.api.Test;

import automaton.Lexer;

public class EmptyStringLanguage {
  final Lexer ε = Lexer.ε();
  @Test void emptyLanguage() {
    assert ε.run("");
    assert !ε.run("a");
    assert !ε.run("abca");
  }
}