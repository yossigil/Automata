package finite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SuppressWarnings("static-method") class DFSATest {
  final Text empty = new Text();
  final Text a = new Text('a');
  final Text b = new Text('b');
  final Text ab = a.then(b);
  final Text a_b = a.or(b);
  final Text abStar = ab.star();
  final Text x = a_b.then(ab).then(a_b).or(a.then(a).then(a).then(a));

  @Test void t00() {
    new DFSA(x);
  }

  @Test void t01() {
    new DFSA(a).run("a");
  }

  @Test void t02() {
    assertTrue(new DFSA(a).run("a"));
  }


  @Test void singletonOk() {
    assertTrue(a.d().run("a"));
  }

  @Test void singletonOther() {
    assertFalse(a.d().run("b"));
  }

  @Test void singletonEmpty() {
    assertFalse(a.d().run(""));
  }
  
  @Test void singletonSize() {
    assertEquals(a.d().n(),2);
  }
  @Test void emptySize() {
    assertEquals(empty.d().n(),1);
  }
  @Test void emptyStringRecognizer() {
    assertTrue(new Text().run(""));
    assertFalse(new Text().run("a"));
  }
  @Test void abAccept() {
    assertTrue(ab.run("ab"));
  }
  @Test void abRejectA() {
    assertFalse(ab.run("a"));
  }
  @Test void abRejectB() {
    assertFalse(ab.run(""));
  }
  @Test void abRejectEmpty() {
    assertFalse(ab.run(""));
  }
  @Test void a_bAccept() {
    assertTrue(a_b.run("a"));
    assertTrue(a_b.run("b"));
  }
  @Test void a_bRejectEmpty() {
    assertFalse(a_b.run(""));
  }
  @Test void a_bRejectAB() {
    assertFalse(a_b.run("ab"));
  }
  @Test void a_bSize() {
    assertEquals(a_b.n(), 5);
  }
  @Test void abStarAccept0() {
    assertFalse(abStar.run(""));
  }
  @Test void abStarAccept1() {
    assertFalse(abStar.run("ab"));
  }
  @Test void abStarAccept2() {
    assertFalse(abStar.run("abab"));
  }
  @Test void abStarAccept3() {
    assertFalse(abStar.run("ababab"));
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
