package automaton;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

public class Factories {
  private FSA<Character> fsa;
  private FSA<Character> fsa2;
  private FSA<Character> fsa3;
  private FSA<Character> fsa4;

  @Test void σ() {
    fsa = NFSA.<Character>σ('a');
    assert !fsa.Σ.contains(null);
    fsa.hashCode();
    (fsa + "").hashCode();
  } 
  @Test void ε() {
    fsa2 = NFSA.<Character>ε();
    assert !fsa2.Σ.contains(null);
    fsa2.hashCode();
    (fsa2 + "").hashCode();
  }
  @Test void ʘ() {
    fsa3 = NFSA.<Character>ʘ();
    assertNotNull(fsa3.q0);
    assertNotNull(fsa3.Q);
    assertNotNull(fsa3.Δ);
    assertNotNull(fsa3.ζ);
    assertEquals(fsa3.Δ.keySet().size(),2);
    assertEquals(fsa3.ζ.size(),1);
    assertEquals(fsa3.Q.size(),2);
    assert !fsa3.Σ.contains(null);
    assert fsa3.Δ.containsKey(fsa3.q0);
     fsa3.hashCode();
    (fsa3 + "").hashCode();
  }

}
