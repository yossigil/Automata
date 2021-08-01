package symbolic;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RETest {
  final RE ε           = Atoms.ε;
  final RE ʘ           = Atoms.ʘ;
  final RE a           = Atomic.c('a');
  final RE b           = Atomic.c('b');
  final RE c           = Atomic.c('c');
  final RE d           = Atomic.c('d');
  final RE ab          = a.then(b);
  final RE a_b         = a.or(b);
  final RE not_a       = a.not();
  final RE a_star      = a.many();
  final RE a_plus      = a.plenty();
  final RE a_and_b     = a.and(b);
  final RE abStar      = ab.many();
  final RE abStar$c    = ab.many().then(c);
  final RE abStarNot   = ab.many().not();
  final RE abStar$cNot = abStar$c.not();
  final RE A           = a.or(b).then(c).many().or(a).then(b).then(ʘ).and(c).or(ε).plenty().then(ε.or(a)).except(b);
  final RE z           = a.or(b).then(c).many().then(b).and(c).plenty().then(ε.or(a)).except(b);
  final RE y           = z.or(z).then(z).many().then(z).and(z).plenty().then(a.or(b)).except(z);
  final RE x           = y.or(z).then(y).many().then(z).and(y).plenty().then(ʘ.or(ε)).except(y);
  final RE w           = x.or(y).then(z).many().then(x).and(y).plenty().then(a.or(a_b)).except(x);
  final RE v           = w.or(x).then(y).many().then(z).and(w).plenty().then(b.or(ʘ)).except(w);
  final RE u           = v.or(w).then(x).many().then(y).and(z).plenty().then(ε.or(a)).except(v);
  final RE t           = u.or(v).then(w).many().then(x).and(y).plenty().then(a_b.or(b)).except(u);
  final RE s           = t.or(u).then(v).many().then(w).and(x).plenty().then(ʘ.or(ε)).except(t);
  final RE r           = s.or(t).then(u).many().then(v).and(w).plenty().then(a.or(a_b)).except(s);
  final RE q           = r.or(s).then(t).many().then(u).and(v).plenty().then(b.or(ʘ)).except(r);
  final RE p           = r.or(q).then(s).many().then(t).and(u).plenty().then(c.or(a)).except(q);
  final RE o           = r.or(p).then(q).many().then(s).and(t).plenty().then(d.or(b)).except(p);
  @Test void getString$aOK() { assertEquals("a", a + ""); }
  @Test void getString$bOK() { assertEquals("b", b + ""); }
  @Test void getString$cOK() { assertEquals("c", c + ""); }
  @Test void getString$εOK() { assertEquals("ε", ε + ""); }
  @Test void getString$ʘOK() { assertEquals("ʘ", ʘ + ""); }
  @Test void getString$abOK() { assertEquals("ab", ab + ""); }
  @Test void getString$a_bOK() { assertEquals("a|b", a_b + ""); }
  @Test void getString$a_and_bOK() { assertEquals("a&b", a_and_b + ""); }
  @Test void getString$a_plusOK() { assertEquals("a+", a_plus + ""); }
  @Test void getString$a_starOK() { assertEquals("a*", a_star + ""); }
  @Test void getString$a_notOK() { assertEquals("a!", not_a + ""); }
  @Test void getString$abStar() { assertEquals("ab*", abStar + ""); }
  @Test void getString$abStar$c() { assertEquals("ab*c", abStar$c + ""); }
  @Test void getString$abStarNot() { assertEquals("ab*!", abStarNot + ""); }
  @Test void getString$abStar$cNot() { assertEquals("ab*c!", abStar$cNot + ""); }
  @Test void TikZaOK() { assertNotEquals("a", a.TikZ()); }
  @Test void TikZbOK() { assertNotEquals("b", b.TikZ()); }
  @Test void TikZcOK() { assertNotEquals("c", c.TikZ()); }
  @Test void TikZεOK() { assertNotEquals("ε", ε.TikZ()); }
  @Test void TikZʘOK() { assertNotEquals("ʘ", ʘ.TikZ()); }
  @Test void TikZabOK() { assertNotEquals("ab", ab.TikZ()); }
  @Test void TikZa_bOK() { assertNotEquals("a|b", a_b.TikZ()); }
  @Test void TikZa_and_bOK() { assertNotEquals("a&b", a_and_b.TikZ()); }
  @Test void TikZa_plusOK() { assertNotEquals("a+", a_plus.TikZ()); }
  @Test void TikZa_starOK() { assertNotEquals("a*", a_star.TikZ()); }
  @Test void TikZa_notOK() { assertNotEquals("a!", not_a.TikZ()); }
  @Test void TikZabStar() { assertNotEquals("ab*", abStar.TikZ()); }
  @Test void TikZabStar$c() { assertNotEquals("ab*c", abStar$c.TikZ()); }
  @Test void TikZabStarNot() { assertNotEquals("ab*c", abStarNot.TikZ()); }
  @Test void TikZabStar$cNot() { assertNotEquals("ab*c!", abStar$cNot.TikZ()); }
  @Test void TikZ$z$() { assertNotEquals("ab*c!", z.TikZ()); }
  //
  @Test void Size$aOK() { assertEquals(1, a.size()); }
  @Test void Size$bOK() { assertEquals(1, b.size()); }
  @Test void Size$cOK() { assertEquals(1, c.size()); }
  @Test void Size$εOK() { assertEquals(1, ε.size()); }
  @Test void Size$ʘOK() { assertEquals(1, ʘ.size()); }
  @Test void Size$abOK() { assertEquals(3, ab.size()); }
  @Test void Size$a_bOK() { assertEquals(3, a_b.size()); }
  @Test void Size$a_and_bOK() { assertEquals(3, a_and_b.size()); }
  @Test void Size$a_plusOK() { assertEquals(2, a_plus.size()); }
  @Test void Size$a_starOK() { assertEquals(2, a_star.size()); }
  @Test void Size$a_notOK() { assertEquals(2, not_a.size()); }
  @Test void Size$abStar() { assertEquals(4, abStar.size()); }
  @Test void Size$abStar$c() { assertEquals(6, abStar$c.size()); }
  @Test void Size$abStarNot() { assertEquals(5, abStarNot.size()); }
  @Test void Size$abStar$cNot() { assertEquals(7, abStar$cNot.size()); }
//
  @Test void Size$o$() { assertEquals(4_420_683, o.size()); }
  @Test void Size$p$() { assertEquals(1_694_921, p.size()); }
  @Test void Size$q$() { assertEquals(649_937, q.size()); }
  @Test void Size$r$() { assertEquals(249_011, r.size()); }
  @Test void Size$s$() { assertEquals(95_347, s.size()); }
  @Test void Size$t$() { assertEquals(36_535, t.size()); }
  @Test void Size$u$() { assertEquals(14_143, u.size()); }
  @Test void Size$v$() { assertEquals(5_879, v.size()); }
  @Test void Size$w$() { assertEquals(1_747, w.size()); }
  @Test void Size$x$() { assertEquals(497, x.size()); }
  @Test void Size$y$() { assertEquals(113, y.size()); }
  @Test void Size$z$() { assertEquals(17, z.size()); }
  //
  @Test void Depth$o$() { assertEquals(97, o.depth()); }
  @Test void Depth$p$() { assertEquals(89, p.depth()); }
  @Test void Depth$q$() { assertEquals(81, q.depth()); }
  @Test void Depth$r$() { assertEquals(73, r.depth()); }
  @Test void Depth$s$() { assertEquals(65, s.depth()); }
  @Test void Depth$t$() { assertEquals(57, t.depth()); }
  @Test void Depth$u$() { assertEquals(49, u.depth()); }
  @Test void Depth$v$() { assertEquals(41, v.depth()); }
  @Test void Depth$w$() { assertEquals(33, w.depth()); }
  @Test void Depth$x$() { assertEquals(25, x.depth()); }
  @Test void Depth$y$() { assertEquals(17, y.depth()); }
  @Test void Depth$z$() { assertEquals(9, z.depth()); }
  //
  @Test void Depth$aOK() { assertEquals(1, a.depth()); }
  @Test void Depth$bOK() { assertEquals(1, b.depth()); }
  @Test void Depth$cOK() { assertEquals(1, c.depth()); }
  @Test void Depth$εOK() { assertEquals(1, ε.depth()); }
  @Test void Depth$ʘOK() { assertEquals(1, ʘ.depth()); }
  @Test void Depth$abOK() { assertEquals(2, ab.depth()); }
  @Test void Depth$a_bOK() { assertEquals(2, a_b.depth()); }
  @Test void Depth$a_and_bOK() { assertEquals(2, a_and_b.depth()); }
  @Test void Depth$a_plusOK() { assertEquals(2, a_plus.depth()); }
  @Test void Depth$a_starOK() { assertEquals(2, a_star.depth()); }
  @Test void Depth$a_notOK() { assertEquals(2, not_a.depth()); }
  @Test void Depth$abStar() { assertEquals(3, abStar.depth()); }
  @Test void Depth$abStar$c() { assertEquals(4, abStar$c.depth()); }
  @Test void Depth$abStarNot() { assertEquals(4, abStarNot.depth()); }
  @Test void Depth$abStar$cNot() { assertEquals(5, abStar$cNot.depth()); }
  @Test void xxx() {
    System.out.println(A.TikZ());
  }
}
