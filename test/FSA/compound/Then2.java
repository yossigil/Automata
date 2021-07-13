package FSA.compound;

import org.junit.jupiter.api.Test;

import finite.Lexer;

public class Then2 {
  final Lexer ab = new Lexer(new Lexer('a').then(new Lexer('b')));
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
  @Test void print() {
    System.out.println(ab.TikZ());
    System.out.println(ab);
  }
}
