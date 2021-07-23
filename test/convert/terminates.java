package convert;

import org.junit.jupiter.api.Test;

import finite.NFSA;

@SuppressWarnings("static-method")public class terminates {
  @Test void or() {
    NFSA.<Character>σ('a').or(NFSA.<Character>σ('a')).DFSA();
  }
  @Test void many() {
    NFSA.<Character>σ('a').many().DFSA();
  }
  @Test void then() {
    NFSA.<Character>σ('a').then(NFSA.<Character>σ('a')).DFSA();
  }
  @Test void σ() {
    NFSA.<Character>σ('a').DFSA();
  }
  @Test void ε() {
    NFSA.<Character>ε().DFSA();
  }
  @Test void ʘ() {
    NFSA.<Character>ʘ().DFSA();
  }
  @Test void Φ() {
    NFSA.<Character>Φ().DFSA();
  }
}