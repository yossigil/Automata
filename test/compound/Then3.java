package compound;

import org.junit.jupiter.api.Test;

import finite.Lexer;

public class Then3 {
  final Lexer abc = new Lexer('a').Then('b').Then('c');
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
