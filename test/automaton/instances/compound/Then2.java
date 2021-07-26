package automaton.instances.compound;

import org.junit.Test;

import automaton.Lexer;

public class Then2 {
  final Lexer ab = Lexer.c('a').then(Lexer.c('b'));
  {
    System.out.println(ab.inner);
    System.out.println(ab.TikZ());
  }
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
