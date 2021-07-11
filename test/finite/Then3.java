package finite;

import org.junit.jupiter.api.Test;

public class Then3 {
  final Text abc = new Text('a').Then('b').Then('c');
  @Test void accept() {
    assert abc.run("abc");
  }
  @Test void rejectA() {
    assert !abc.run("a");
  }
  @Test void rejectB() {
    assert !abc.run("b");
  }
  @Test void rejectEmpty() {
    assert !abc.run("");
  }
  @Test void rejectBA() {
    assert !abc.run("ba");
  }
}
