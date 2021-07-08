package finite;

import static org.junit.jupiter.api.Assertions.*;

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
    DFSA<Character> dfsa = abStar.DFSA();
    assertTrue(dfsa.run(""));
  }

  @Test void DFSA1() {
    DFSA<Character> dfsa = abStar.DFSA();
    assertTrue(dfsa.run("ab"));
  }

  @Test void DFSA2() {
    DFSA<Character> dfsa = abStar.DFSA();
    assertTrue(dfsa.run("abab"));
  }

  @Test void DFSA3() {
    DFSA<Character> dfsa = abStar.DFSA();
    assertTrue(dfsa.run("ababab"));
  }

  @Test void DFSA4() {
    DFSA<Character> dfsa = abStar.DFSA();
    assertTrue(dfsa.run("abababab"));
  }

  @Test void DFSAx() {
    DFSA<Character> dfsa = abStar.DFSA();
    assertFalse(dfsa.run("a"));
    assertFalse(dfsa.run("b"));
    assertFalse(dfsa.run("aa"));
    assertFalse(dfsa.run("bb"));
    assertFalse(dfsa.run("ba"));
    assertFalse(dfsa.run("aababab"));
    assertFalse(dfsa.run("aab"));
    assertFalse(dfsa.run("abb"));
    assertFalse(dfsa.run("aaa"));
    assertFalse(dfsa.run("bbb"));
  }

  private String node(String options, String name) {
    return "\\node [" + options + "] {{" + name + "}};";
  }

  @Test void minimize() {
    DFSA<Character> dfsa = abStar.DFSA();
    DFSA<Character> minimize = dfsa.minimize();
    assertTrue(minimize.run(""));
  }

  @Test void TikZ() {
    DFSA<Character> dfsa = abStar.DFSA();
    dfsa.TikZ();
    DFSA<Character> minimize = dfsa.minimize();
    assertTrue(minimize.run(""));
    minimize.TikZ();
  }

  @Test void accept0() {
    assertTrue(abStar.run(""));
    assertTrue(abStar.DFSA().run(""));
  }

  @Test void accept1() {
    assertTrue(abStar.run("ab"));
    assertTrue(abStar.run("ab"));
  }

  @Test void accept2() {
    assertTrue(abStar.run("abab"));
    assertTrue(abStar.run("ab"));
  }

  @Test void accept3() {
    assertTrue(abStar.run("ababab"));
    assertTrue(abStar.run("ab"));
  }

  @Test void abStarReject0() {
    assertFalse(abStar.run("a"));
  }

  @Test void abStarReject1() {
    assertFalse(abStar.run("ba"));
    assertFalse(abStar.DFSA().run("ba"));
  }

  @Test void abStarReject2() {
    assertFalse(abStar.run("babab"));
    assertFalse(abStar.DFSA().run("babab"));
  }

  @Test void abStarReject3() {
    assertFalse(abStar.DFSA().run("abababa"));
    assertFalse(abStar.DFSA().run("abababa"));
  }
}
