package finite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

 class ThenTest {
  final Text ab = new Text(new Text('a').then(new Text('b')));
  
  @Test void accept() {
    assertTrue(ab.run("ab"));
  }
  @Test void rejectA() {
    assertFalse(ab.run("a"));
  }
  @Test void rejectB() {
    assertFalse(ab.run("b"));
  }
  @Test void rejectEmpty() {
    assertFalse(ab.run(""));
  }
  @Test void rejectBA() {
    assertFalse(ab.run("ba"));
  }
}
