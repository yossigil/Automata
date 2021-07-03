package finite;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class Empty {

  final Text empty = new Text();

  @Test void emptyLanguage() {
    assertFalse(empty.run(""));
    assertFalse(empty.run("a"));
    assertFalse(empty.run("abca"));
  }
}