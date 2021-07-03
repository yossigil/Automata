package finite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Star {
  final Text aStar = new Text(new Text('a').star());
  
  @Test void accept0() {
    assertTrue(aStar.run(""));
  }
  @Test void accept1() {
    assertTrue(aStar.run("aa"));
  }
  @Test void accept2() {
    assertTrue(aStar.run("aa"));
  }
  @Test void accept3() {
    assertTrue(aStar.run("aaa"));
  }
  @Test void reject0() {
    assertFalse(aStar.run("ba"));
  }
  @Test void reject1() {
    assertFalse(aStar.run("ba"));
  }
  @Test void reject2() {
    assertFalse(aStar.run("babab"));
  }
  @Test void reject3() {
    assertFalse(aStar.run("abababa"));
  }
}
