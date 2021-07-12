package FSA.thompson;

import org.junit.jupiter.api.Test;

import finite.Lexer;
public class Many2 {
  final Lexer abStar = new Lexer('a').Then('b').many();
  @Test void m1() {
    abStar.DFSA();
  }
  @Test void m2() {
    abStar.DFSA().minimize();
  }
  @Test void t0() {
    abStar.TikZ();
  }
  @Test void t1() {
    abStar.DFSA().TikZ();
  }
  @Test void t3() {
    abStar.DFSA().minimize().TikZ();
  }
  @Test void DFSA() {
    abStar.DFSA();
  }
  @Test void DFSA0() {
    assert abStar.DFSA().run("");
  }
  @Test void DFSA1() {
    assert abStar.DFSA().run("ab");
  }
  @Test void DFSA2() {
    assert abStar.DFSA().run("abab");
  }
  @Test void DFSA3() {
    assert abStar.DFSA().run("ababab");
  }
  @Test void DFSA4() {
    assert abStar.DFSA().run("abababab");
  }
  @Test void DFSAx() {
    final var dfsa = abStar.DFSA();
    assert !dfsa.run("a");
    assert !dfsa.run("b");
    assert !dfsa.run("aa");
    assert !dfsa.run("bb");
    assert !dfsa.run("ba");
    assert !dfsa.run("aababab");
    assert !dfsa.run("aab");
    assert !dfsa.run("abb");
    assert !dfsa.run("aaa");
    assert !dfsa.run("bbb");
  }
  @Test void minimize() {
    assert abStar.DFSA().minimize().run("");
  }
  @Test void TikZ() {
    final var dfsa = abStar.DFSA();
    dfsa.TikZ();
    final var minimize = dfsa.minimize();
    assert minimize.run("");
    minimize.TikZ();
  }
  @Test void accept0() {
    assert abStar.run("");
    assert abStar.DFSA().run("");
  }
  @Test void accept1() {
    assert abStar.run("ab");
    assert abStar.run("ab");
  }
  @Test void accept2() {
    assert abStar.run("abab");
    assert abStar.run("ab");
  }
  @Test void accept3() {
    assert abStar.run("ababab");
    assert abStar.run("ab");
  }
  @Test void abStarReject0() {
    assert !abStar.run("a");
  }
  @Test void abStarReject1() {
    assert !abStar.run("ba");
    assert !abStar.DFSA().run("ba");
  }
  @Test void abStarReject2() {
    assert !abStar.run("babab");
    assert !abStar.DFSA().run("babab");
  }
  @Test void abStarReject3() {
    assert !abStar.DFSA().run("abababa");
    assert !abStar.DFSA().run("abababa");
  }
}
