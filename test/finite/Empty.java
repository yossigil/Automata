package finite;

import org.junit.jupiter.api.Test;

class Empty {

  final Text empty = new Text();

  @Test void emptyLanguage() {
    assert !empty.run("");
    assert !empty.run("a");
    assert !empty.run("abca");
  }
}