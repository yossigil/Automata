package finite;

import org.junit.jupiter.api.Test;

public class Then2 {
  final Text ab = new Text(new Text('a').then(new Text('b')));
  @Test void accept() {
    assert ab.run("ab");
  }
  @Test void rejectA() {
    assert !ab.run("a");
  }
  @Test void rejectB() {
    assert !ab.run("b");
  }
  @Test void rejectEmpty() {
    assert !ab.run("");
  }
  @Test void rejectBA() {
    assert !ab.run("ba");
  }
}
