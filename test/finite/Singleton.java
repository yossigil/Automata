package finite;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Singleton {

  final Text a = new Text('a');
  final Text b = new Text('b');

  @Test void aOK() {
    assertTrue(a.run("a"));
  }

  @Test void aOther() {
    assertFalse(a.run("b"));
  }

  @Test void aEmpty() {
    assertFalse(a.run(""));
  }

  @Test void bOK() {
    assertTrue(b.run("b"));
  }
  
  @Test void singletonSize() {
    assertEquals(a.Δ.keySet().size(), 1);
  }
}