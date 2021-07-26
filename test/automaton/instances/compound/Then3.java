package automaton.instances.compound;

import org.junit.Test;

import automaton.Lexer;

public class Then3 {
  final Lexer abc = Lexer.c('a').Then('b').Then('c');
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
