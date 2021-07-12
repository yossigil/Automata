package FSA.atomic;

import org.junit.jupiter.api.Test;

import finite.NFSA;
import finite.Lexer;

public class EmptyStringLanguage {
  final Lexer ε = new Lexer(NFSA.ε());
  @Test void emptyLanguage() {
    assert ε.run("");
    assert !ε.run("a");
    assert !ε.run("abca");
  }
}