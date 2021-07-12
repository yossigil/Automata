package finite;

import org.junit.jupiter.api.Test;

class Deterministic {
  final Lexer a = new Lexer('a');
  @Test void exists() {
    a.DFSA();
  }
}
