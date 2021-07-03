package finite;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Or3 {
  final Text a_b_c = new Text('a').Or('b').Or('c');

  @Test void acceptA() {
    assertTrue(a_b_c.run("a"));
  }
  @Test void acceptB() {
    assertTrue(a_b_c.run("b"));
  }
  @Test void acceptC() {
    assertTrue(a_b_c.run("c"));
  }
  @Test void rejectEmpty() {
    assertFalse(a_b_c.run(""));
  }
  @Test void rejectAB() {
    assertFalse(a_b_c.run("ab"));
  }
  @Test void rejectAA() {
    assertFalse(a_b_c.run("aa"));
  }
  @Test void rejectD() {
    assertFalse(a_b_c.run("d"));
  }
  @Test void rejectBA() {
    assertFalse(a_b_c.run("ba"));
  }
}