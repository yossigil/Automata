package finite;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ε {

  final Text ε = new Text(NFSA.ε());

  @Test void emptyLanguage() {
    assertTrue(ε.run(""));
    assertFalse(ε.run("a"));
    assertFalse(ε.run("abca"));
  }
}