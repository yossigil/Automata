package automaton.minimize;


import org.junit.jupiter.api.Test;

import automaton.NFSA;

@SuppressWarnings("static-method")public class afterDFSA {
  @Test void or() {
    NFSA.<Character>σ('a').or(NFSA.<Character>σ('a')).DFSA().minimal();
  }
  @Test void many() {
    NFSA.<Character>σ('a').many().DFSA().minimal();
  }
  @Test void then() {
    NFSA.<Character>σ('a').then(NFSA.<Character>σ('a')).DFSA().minimal();
  }
  @Test void σ() {
    NFSA.<Character>σ('a').DFSA().minimal();
  }
  @Test void ε() {
    NFSA.<Character>ε().DFSA().minimal();
  }
  @Test void ʘ() {
    NFSA.<Character>ʘ().DFSA().minimal();
  }
  @Test void Φ() {
    NFSA.<Character>Φ().DFSA().minimal();
  }
}