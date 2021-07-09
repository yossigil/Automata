package finite;

import org.junit.jupiter.api.Test;

class Star2 {
  final Text abStar = new Text('a').Then('b').star();
  final Text star   = new Text('a').Then('b').then(new Text('a').Or('b')).star();

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

  static void scope(String s) {
    System.out.println("\\begin{scope} \\path");
    System.out.println(s);
    System.out.println("; \\end{scope}");
  }

  static <Σ> void show(final NFSA<Σ> ¢) {
    System.out.println("\\begin{tikzpicture}[start chain=going down]\n");
    System.out.println("\\node[on chain] {NFSA};");
    scope(¢.TikZ() );
    System.out.println("\\node[on chain] {DFSA};");
    scope(¢.DFSA().TikZ() );
    System.out.println("\\node[on chain] {minimized DFSA};");
    scope(¢.DFSA().minimize().TikZ());
    System.out.println("\\end{tikzpicture}\n");
  }

  @Test void DFSADemo() {
    show(star);
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
    final DFSA<Character> dfsa = abStar.DFSA();
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
    final DFSA<Character> dfsa = abStar.DFSA();
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
