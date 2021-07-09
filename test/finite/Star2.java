package finite;

import org.junit.jupiter.api.Test;

class Star2 {
  final Text abStar = new Text('a').Then('b').star();

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

  <Σ> void show(NFSA<Σ> a) {
    System.out.println("\\begin{tikzpicture}\n");
    System.out.println("\\begin{scope}[start chain=going down]\n");
//    System.out.println(node("on chain", a + ""));
    System.out.println("\\begin{scope}[local bounding box=NFSA,on chain] \\path \n" + a.TikZ() + ";\n\\end{scope}");
 //   System.out.println(node("", a.DFSA() + ""));
    System.out.println("\\begin{scope}[local bounding box=DFSA] \\path \n" + a.DFSA().TikZ() + ";\n\\end{scope}");
  //  System.out.println(node("", a.DFSA().minimize() + ""));
    System.out
        .println("\\begin{scope}[local bounding box=DFSA] \\path \n" + a.DFSA().minimize().TikZ() + ";\n\\end{scope}");
    System.out.println("\\end{scope}");
    System.out.println("\\end{tikzpicture}\n");
  }

  @Test void DFSADemo() {
    show(new Text('a').Then('b').then(new Text('a').Or('b')).star());
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
    DFSA<Character> dfsa = abStar.DFSA();
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

  private String node(String options, String name) {
    return "\\node [" + options + "] {{" + name + "}};";
  }

  @Test void minimize() {
    assert abStar.DFSA().minimize().run("");
  }

  @Test void TikZ() {
    DFSA<Character> dfsa = abStar.DFSA();
    dfsa.TikZ();
    DFSA<Character> minimize = dfsa.minimize();
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
