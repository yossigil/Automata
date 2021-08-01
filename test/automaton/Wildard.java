package automaton;

import org.junit.jupiter.api.Test;

@SuppressWarnings("static-method") public class Wildard {
  @Test public void anyDFSA() {
    NFSA<Character> v = NFSA.<Character>ʘ();
    assert v.DFSA().run("a") : v.DFSA();
  }
  @Test public void anyNFSA() {
    NFSA<Character> v = NFSA.<Character>ʘ();
    assert v.run("a") : v;
  }
}