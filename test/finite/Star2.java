package finite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Star2 {
  final Text abStar = new Text('a').Then('b').star();

  @Test void DFSA0() {
    DFSA<Character> dfsa = abStar.DFSA();
    assertTrue(dfsa.run(""));
    assertTrue(dfsa.run("ab"));
    assertTrue(dfsa.run("abab"));
    assertTrue(dfsa.run("ababab"));
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

    System.out.println("\\begin{tikzpicture}\n");
    System.out.println("\\path " + abStar.TikZ() +";");
    System.out.println(node("above","$(ab)^*$ NFSA") );
    System.out.println(node("below","$(ab)^*$ DFSA") );
    System.out.println("\\path " + dfsa.TikZ() +";");
    System.out.println("\\end{tikzpicture}\n");
  }

  private String node(String location, String name) {
    return "\\node [" + location + "=of current bounding box] {{"  + name+ "}};";
  }

  @Test void minimize0() {
    DFSA<Character> dfsa = abStar.DFSA();
    System.out.println("DFDA (minimized): (AB)*" + dfsa.minimize().TikZ());
    assertTrue(dfsa.run(""));
  }

  @Test void accept0() {
    System.out.println("NFSA: $(ab)^*$" + abStar.TikZ());
    System.out.println("DFSA: (AB)*" + abStar.DFSA().TikZ());
//    System.out.println("DFDA (minimized): (AB)*"+abStar.DFSA().minimize().TikZ());
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
    assertTrue(abStar.DFSA().run("ab"));
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
