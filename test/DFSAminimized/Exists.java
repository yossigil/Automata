package DFSAminimized;

import org.junit.jupiter.api.Test;

import finite.Lexer;

class Exists {
   final Lexer abStar = new Lexer('a').Then('b').many();
  @Test void m1() { abStar.DFSA(); }
  @Test void m2() { abStar.DFSA().minimize(); }
  @Test void t0() { abStar.TikZ(); }
  @Test void t1() { abStar.DFSA().TikZ(); }
  @Test void t3() { abStar.DFSA().minimize().TikZ(); }
  @Test void DFSA() { abStar.DFSA(); }
}