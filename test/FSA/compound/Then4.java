package FSA.compound;

import org.junit.Test;

import automaton.Lexer;

public class Then4 {
  final Lexer abcd = Lexer.c('a').then(Lexer.c('b').then(Lexer.c('c')).then(Lexer.c('d')));
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
