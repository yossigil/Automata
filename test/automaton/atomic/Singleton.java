package automaton.atomic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import automaton.Lexer;

class Singleton {
  final Lexer a = Lexer.c('a');
  @Test void singletonSize() {
    assertEquals(a.inner.Δ.size(), 2);
  }
}