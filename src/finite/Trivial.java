package finite;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

@SuppressWarnings("static-method") class Trivial {

  final Text empty = new Text();
  final Text a = new Text('a');
  final Text b = new Text('b');
  final Text a_b = a.or(b);

  @Test void singletonOk() {
    assertTrue(a.run("a"));
  }

  @Test void singletonOther() {
    assertFalse(a.run("b"));
  }

  @Test void singletonEmpty() {
    assertFalse(a.run(""));
  }
  
  @Test void singletonSize() {
    assertEquals(a.δ.keySet().size(), 1);
  }
  @Test void emptyStringRecognizer() {
    assertTrue(new Text().run(""));
    assertFalse(new Text().run("a"));
  }
  @Test void a_bAccept() {
    assertTrue(a_b.run("a"));
    assertTrue(a_b.run("b"));
  }
  @Test void a_bRejectEmpty() {
    assertFalse(a_b.run(""));
  }
  @Test void a_bRejectAB() {
    assertFalse(a_b.run("ab"));
  }
}