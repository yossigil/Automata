package minimize;


import org.junit.Test;

import automaton.NFSA;

@SuppressWarnings("static-method")public class sporadic {
  @Test void or() {
    NFSA.<Character>σ('a').or(NFSA.<Character>σ('b')).minimal();
  }
  @Test void many() {
    NFSA.<Character>σ('a').many().minimal();
  }
  @Test void then() {
    NFSA.<Character>σ('a').then(NFSA.<Character>σ('a')).minimal();
  }
  @Test void σ() {
    NFSA.<Character>σ('a').minimal();
  }
  @Test void ε() {
    NFSA.<Character>ε().minimal();
  }
  @Test void ʘ() {
    NFSA.<Character>ʘ().minimal();
  }
  @Test void Φ() {
    NFSA.<Character>Φ().minimal();
  }
}