package FSA.atomic;

import org.junit.jupiter.api.Test;

import finite.NFSA;
import finite.Lexer;

public class εTest {
  final Lexer ε = new Lexer(NFSA.ε());
  @Test void emptyLanguage() {
    assert ε.run("");
    assert !ε.run("a");
    assert !ε.run("abca");
  }
}