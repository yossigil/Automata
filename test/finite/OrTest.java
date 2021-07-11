package finite;

import org.junit.jupiter.api.Test;

class OrTest {
  final Text a_b = new Text(new Text('a').or(new Text('b')));
  @Test void acceptA() {
    assert a_b.run("a");
  }
  @Test void acceptB() {
    assert a_b.run("b");
  }
  @Test void rejectEmpty() {
    assert !a_b.run("");
  }
  @Test void rejectAB() {
    assert !a_b.run("ab");
  }
  @Test void rejectAA() {
    assert !a_b.run("ab");
  }
  @Test void rejectBB() {
    assert !a_b.run("bb");
  }
  @Test void rejectBA() {
    assert !a_b.run("ba");
  }
}