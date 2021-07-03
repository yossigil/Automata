package finite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Then4Test {
  final Text abc = new Text(new Text('a').then(new Text('b').then(new Text('c')).then(new Text('d'))));

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
