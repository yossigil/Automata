package finite;

import org.junit.jupiter.api.Test;

class ε {

  final Text ε = new Text(NFSA.ε());

  @Test void emptyLanguage() {
    assert ε.run("");
    assert !ε.run("a");
    assert !ε.run("abca");
  }
}