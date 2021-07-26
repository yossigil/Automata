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

  @Test void Φ() {
    fsa4 = NFSA.<Character>Φ();
    assertNotNull(fsa4.q0);
    assertNotNull(fsa4.Q);
    assertNotNull(fsa4.Δ);
    assertNotNull(fsa4.ζ);
    assertEquals(fsa4.Δ.keySet().size(),1);
    assertEquals(fsa4.ζ.size(),0);
    assertEquals(fsa4.Q.size(),1);
    assert fsa4.Δ.containsKey(fsa4.q0);
    assert !fsa4.Σ.contains(null);
    assertEquals("Weird characters " +fsa4.Σ, fsa4.Σ.size(),0);
    fsa4.hashCode();
    (fsa4 + "").hashCode();
    fsa4.run("abc");
  }
}
