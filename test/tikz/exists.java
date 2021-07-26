package tikz;

import org.junit.Test;

import automaton.NFSA;

@SuppressWarnings("static-method")public class exists {
  @Test void or() {
    NFSA.<Character>σ('a').or(NFSA.<Character>σ('a')).TikZ();
  }
  @Test void many() {
    NFSA.<Character>σ('a').many().TikZ();
  }
  @Test void then() {
    NFSA.<Character>σ('a').then(NFSA.<Character>σ('a')).TikZ();
  }
  @Test void σ() {
    NFSA.<Character>σ('a').TikZ();
  }
  @Test void ε() {
    NFSA.<Character>ε().TikZ();
  }
  @Test void ʘ() {
    NFSA.<Character>ʘ().TikZ();
  }
  @Test void Φ() {
    NFSA.<Character>Φ().TikZ();
  }
}