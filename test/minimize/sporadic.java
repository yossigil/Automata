package minimize;

import org.junit.jupiter.api.Test;

import finite.NFSA;

@SuppressWarnings("static-method")public class sporadic {
  @Test void or() {
    NFSA.<Character>σ('a').or(NFSA.<Character>σ('b')).minimize();
  }
  @Test void many() {
    NFSA.<Character>σ('a').many().minimize();
  }
  @Test void then() {
    NFSA.<Character>σ('a').then(NFSA.<Character>σ('a')).minimize();
  }
  @Test void σ() {
    NFSA.<Character>σ('a').minimize();
  }
  @Test void ε() {
    NFSA.<Character>ε().minimize();
  }
  @Test void ʘ() {
    NFSA.<Character>ʘ().minimize();
  }
  @Test void Φ() {
    NFSA.<Character>Φ().minimize();
  }
}