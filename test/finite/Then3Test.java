package finite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Then3Test {
  final Text abc = new Text('a').Then('b').Then('c');

  @Test void accept() {
    assertTrue(abc.run("abc"));
  }

  @Test void rejectA() {
    assertFalse(abc.run("a"));
  }

  @Test void rejectB() {
    assertFalse(abc.run("b"));
  }

  @Test void rejectEmpty() {
    assertFalse(abc.run(""));
  }

  @Test void rejectBA() {
    assertFalse(abc.run("ba"));
  }
}
