package automaton.instances.compound;


import org.junit.Test;

import automaton.NFSA;

@SuppressWarnings("static-method")public class existsDFSA {
  @Test void or() {
    NFSA.<Character>σ('a').or(NFSA.<Character>σ('a')).DFSA().TikZ();
  }
  @Test void many() {
    NFSA.<Character>σ('a').many().DFSA().TikZ();
  }
  @Test void then() {
    NFSA.<Character>σ('a').then(NFSA.<Character>σ('a')).DFSA().TikZ();
  }
  @Test void σ() {
    NFSA.<Character>σ('a').DFSA().TikZ();
  }
  @Test void ε() {
    NFSA.<Character>ε().DFSA().TikZ();
  }
  @Test void ʘ() {
    NFSA.<Character>ʘ().DFSA().TikZ();
  }
  @Test void Φ() {
    NFSA.<Character>Φ().DFSA().TikZ();
  }
}