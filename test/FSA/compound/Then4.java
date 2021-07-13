package FSA.compound;

import org.junit.jupiter.api.Test;

import finite.Lexer;

public class Then4 {
  final Lexer abcd = new Lexer(new Lexer('a').then(new Lexer('b').then(new Lexer('c')).then(new Lexer('d'))));
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
