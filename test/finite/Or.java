package finite;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Or {
  final Text a_b = new Text(new Text('a').or( new Text('b')));

  @Test void acceptA() {
    assertTrue(a_b.run("a"));
  }
  @Test void acceptB() {
    assertTrue(a_b.run("b"));
  }
  @Test void rejectEmpty() {
    assertFalse(a_b.run(""));
  }
  @Test void rejectAB() {
    assertFalse(a_b.run("ab"));
  }
  @Test void rejectAA() {
    assertFalse(a_b.run("ab"));
  }
  @Test void rejectBB() {
    assertFalse(a_b.run("bb"));
  }
  @Test void rejectBA() {
    assertFalse(a_b.run("ba"));
  }
}