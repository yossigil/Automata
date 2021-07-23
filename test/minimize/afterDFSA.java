package minimize;

import org.junit.jupiter.api.Test;

import finite.NFSA;

@SuppressWarnings("static-method")public class afterDFSA {
  @Test void or() {
    NFSA.<Character>σ('a').or(NFSA.<Character>σ('a')).DFSA().minimize();
  }
  @Test void many() {
    NFSA.<Character>σ('a').many().DFSA().minimize();
  }
  @Test void then() {
    NFSA.<Character>σ('a').then(NFSA.<Character>σ('a')).DFSA().minimize();
  }
  @Test void σ() {
    NFSA.<Character>σ('a').DFSA().minimize();
  }
  @Test void ε() {
    NFSA.<Character>ε().DFSA().minimize();
  }
  @Test void ʘ() {
    NFSA.<Character>ʘ().DFSA().minimize();
  }
  @Test void Φ() {
    NFSA.<Character>Φ().DFSA().minimize();
  }
}