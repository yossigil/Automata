package finite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
@SuppressWarnings("static-method")public class Factories {
  @Test void σ() {
    FSA<Character> fsa = NFSA.<Character>σ('a');
    assert !fsa.Σ.contains(null);
    fsa.hashCode();
    (fsa + "").hashCode();
  } 
  @Test void ε() {
    FSA<Character> fsa = NFSA.<Character>ε();
    assert !fsa.Σ.contains(null);
    fsa.hashCode();
    (fsa + "").hashCode();
  }
  @Test void ʘ() {
    FSA<Character> fsa = NFSA.<Character>ʘ();
    assertNotNull(fsa.q0);
    assertNotNull(fsa.Q);
    assertNotNull(fsa.Δ);
    assertNotNull(fsa.ζ);
    assertEquals(fsa.Δ.keySet().size(),2);
    assertEquals(fsa.ζ.size(),1);
    assertEquals(fsa.Q.size(),2);
    assert !fsa.Σ.contains(null);
    assert fsa.Δ.containsKey(fsa.q0);
     fsa.hashCode();
    (fsa + "").hashCode();
  }

  @Test void Φ() {
    FSA<Character> fsa = NFSA.<Character>Φ();
    assertNotNull(fsa.q0);
    assertNotNull(fsa.Q);
    assertNotNull(fsa.Δ);
    assertNotNull(fsa.ζ);
    assertEquals(fsa.Δ.keySet().size(),1);
    assertEquals(fsa.ζ.size(),0);
    assertEquals(fsa.Q.size(),1);
    assert fsa.Δ.containsKey(fsa.q0);
    assert !fsa.Σ.contains(null);
    assertEquals("Weird characters " +fsa.Σ, fsa.Σ.size(),0);
    fsa.hashCode();
    (fsa + "").hashCode();
    fsa.run("abc");
  }
}
