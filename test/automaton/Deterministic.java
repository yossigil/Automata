package automaton;

import org.junit.Test;

class Deterministic {
  final Lexer a = Lexer.c('a');
  @Test void exists() {
    dfsa.of(a.inner);
  }
}
