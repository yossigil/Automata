package tikz;

import org.junit.jupiter.api.Test;

import finite.NFSA;

@SuppressWarnings("static-method")public class existsDFSAMinimize {
  @Test void or() {
    NFSA.<Character>σ('a').or(NFSA.<Character>σ('a')).DFSA().minimal().TikZ();
  }
  @Test void many() {
    NFSA.<Character>σ('a').many().DFSA().minimal().TikZ();
  }
  @Test void then() {
    NFSA.<Character>σ('a').then(NFSA.<Character>σ('a')).DFSA().minimal().TikZ();
  }
  @Test void σ() {
    NFSA.<Character>σ('a').DFSA().minimal().TikZ();
  }
  @Test void ε() {
    NFSA.<Character>ε().DFSA().minimal().TikZ();
  }
  @Test void ʘ() {
    NFSA.<Character>ʘ().DFSA().minimal().TikZ();
  }
  @Test void Φ() {
    NFSA.<Character>Φ().DFSA().minimal().TikZ();
  }
}