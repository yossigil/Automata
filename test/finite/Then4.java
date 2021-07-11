package finite;

import org.junit.jupiter.api.Test;

public class Then4 {
  final Text abcd = new Text(new Text('a').then(new Text('b').then(new Text('c')).then(new Text('d'))));
  @Test void accept() {
    assert abcd.run("abcd");
  }
  @Test void rejectA() {
    assert !abcd.run("a");
  }
  @Test void rejectB() {
    assert !abcd.run("b");
  }
  @Test void rejectEmpty() {
    assert !abcd.run("");
  }
  @Test void rejectBA() {
    assert !abcd.run("ba");
  }
}
