package tikz;

import org.junit.jupiter.api.Test;

import finite.NFSA;

@SuppressWarnings("static-method")public class existsDFSAMinimize {
  @Test void or() {
    NFSA.<Character>σ('a').or(NFSA.<Character>σ('a')).DFSA().minimize().TikZ();
  }
  @Test void many() {
    NFSA.<Character>σ('a').many().DFSA().minimize().TikZ();
  }
  @Test void then() {
    NFSA.<Character>σ('a').then(NFSA.<Character>σ('a')).DFSA().minimize().TikZ();
  }
  @Test void σ() {
    NFSA.<Character>σ('a').DFSA().minimize().TikZ();
  }
  @Test void ε() {
    NFSA.<Character>ε().DFSA().minimize().TikZ();
  }
  @Test void ʘ() {
    NFSA.<Character>ʘ().DFSA().minimize().TikZ();
  }
  @Test void Φ() {
    NFSA.<Character>Φ().DFSA().minimize().TikZ();
  }
}