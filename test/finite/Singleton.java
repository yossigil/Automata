package finite;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Singleton {
  final Lexer a = new Lexer('a');
  @Test void singletonSize() {
    assertEquals(a.Δ.size(), 1);
  }
}