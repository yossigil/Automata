package finite;

import org.junit.jupiter.api.Test;

public class εTest {
  final Text ε = new Text(NFSA.ε());
  @Test void emptyLanguage() {
    assert ε.run("");
    assert !ε.run("a");
    assert !ε.run("abca");
  }
}