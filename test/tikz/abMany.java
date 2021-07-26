package tikz;


import org.junit.Test;

import automaton.Lexer;

public class abMany {
  final Lexer abStar = Lexer.c('a').Then('b').many();
  @Test void m1() { abStar.DFSA(); }
  @Test void m2() { abStar.DFSA().minimal(); }
  @Test void t0() { abStar.TikZ(); }
  @Test void t1() { abStar.DFSA().TikZ(); }
  @Test void t3() { abStar.DFSA().minimal().TikZ(); }
  @Test void DFSA() { abStar.DFSA(); }
}