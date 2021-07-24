package automaton;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Singleton {
  final Lexer a = Lexer.c('a');
  @Test void singletonSize() {
    assertEquals(a.inner.Δ.size(), 2);
  }
}