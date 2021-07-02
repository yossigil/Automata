package finite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StarTest2 {
  final Text abStar = new Text(new Text('a').then(new Text('b')).star());
  
  @Test void accept0() {
    assertTrue(abStar.run(""));
  }
  @Test void accept1() {
    assertTrue(abStar.run("ab"));
  }
  @Test void accept2() {
    assertTrue(abStar.run("abab"));
  }
  @Test void accept3() {
    assertTrue(abStar.run("ababab"));
  }
  @Test void abStarReject0() {
    assertFalse(abStar.run("a"));
  }
  @Test void abStarReject1() {
    assertFalse(abStar.run("ba"));
  }
  @Test void abStarReject2() {
    assertFalse(abStar.run("babab"));
  }
  @Test void abStarReject3() {
    assertFalse(abStar.run("abababa"));
  }
}
