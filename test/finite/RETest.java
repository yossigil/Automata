package finite;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RETest {
  final RE ε           = RE.ε;
  final RE Φ           = RE.Φ;
  final RE ʘ           = RE.ʘ;
  final RE a           = RE.c('a');
  final RE b           = RE.c('b');
  final RE c           = RE.c('c');
  final RE d           = RE.c('d');
  final RE ab          = a.then(b);
  final RE a_b         = a.or(b);
  final RE not_a       = a.not();
  final RE a_star      = a.star();
  final RE a_plus      = a.plus();
  final RE a_and_b     = a.and(b);
  final RE abStar      = ab.star();
  final RE abStar$c    = ab.star().then(c);
  final RE abStarNot   = ab.star().not();
  final RE abStar$cNot = abStar$c.not();
  final RE z           = a.or(b).then(c).star().then(b).and(c).plus().then(ε.or(a));
  final RE y           = z.or(z).then(z).star().then(z).and(z).plus().then(Φ.or(b));
  final RE x           = y.or(z).then(y).star().then(z).and(y).plus().then(ʘ.or(ε));
  final RE w           = x.or(y).then(z).star().then(x).and(y).plus().then(a.or(Φ));
  final RE v           = w.or(x).then(y).star().then(z).and(w).plus().then(b.or(ʘ));
  final RE u           = v.or(w).then(x).star().then(y).and(z).plus().then(ε.or(a));
  final RE t           = u.or(v).then(w).star().then(x).and(y).plus().then(Φ.or(b));
  final RE s           = t.or(u).then(v).star().then(w).and(x).plus().then(ʘ.or(ε));
  final RE r           = s.or(t).then(u).star().then(v).and(w).plus().then(a.or(Φ));
  final RE q           = r.or(s).then(t).star().then(u).and(v).plus().then(b.or(ʘ));
  final RE p           = r.or(q).then(s).star().then(t).and(u).plus().then(c.or(a));
  final RE o           = r.or(p).then(q).star().then(s).and(t).plus().then(d.or(b));
  @Test void getString$aOK() { assertEquals("a", a + ""); }
  @Test void getString$bOK() { assertEquals("b", b + ""); }
  @Test void getString$cOK() { assertEquals("c", c + ""); }
  @Test void getString$ΦOK() { assertEquals("Φ", Φ + ""); }
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
  @Test void TikZaOK() { assertEquals("a", a.TikZ()); }
  @Test void TikZbOK() { assertEquals("b", b.TikZ()); }
  @Test void TikZcOK() { assertEquals("c", c.TikZ()); }
  @Test void TikZΦOK() { assertEquals("Φ", Φ.TikZ()); }
  @Test void TikZεOK() { assertEquals("ε", ε.TikZ()); }
  @Test void TikZʘOK() { assertEquals("ʘ", ʘ.TikZ()); }
  @Test void TikZabOK() { assertEquals("ab", ab.TikZ()); }
  @Test void TikZa_bOK() { assertEquals("a|b", a_b.TikZ()); }
  @Test void TikZa_and_bOK() { assertEquals("a&b", a_and_b.TikZ()); }
  @Test void TikZa_plusOK() { assertEquals("a+", a_plus.TikZ()); }
  @Test void TikZa_starOK() { assertEquals("a*", a_star.TikZ()); }
  @Test void TikZa_notOK() { assertEquals("a!", not_a.TikZ()); }
  @Test void TikZabStar() { assertEquals("ab*", abStar.TikZ()); }
  @Test void TikZabStar$c() { assertEquals("ab*c", abStar$c.TikZ()); }
  @Test void TikZabStarNot() { assertEquals("ab*c", abStarNot.TikZ()); }
  @Test void TikZabStar$cNot() { assertEquals("ab*c!", abStar$cNot.TikZ()); }
  @Test void TikZ$z$() { assertEquals("ab*c!", z.TikZ()); }
  @Test void TikZ$o$() { assertEquals("a", o.TikZ()); }
  //
  @Test void Size$aOK() { assertEquals(1, a.size()); }
  @Test void Size$bOK() { assertEquals(1, b.size()); }
  @Test void Size$cOK() { assertEquals(1, c.size()); }
  @Test void Size$ΦOK() { assertEquals(1, Φ.size()); }
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
  @Test void Size$o$() { assertEquals(188105, o.size()); }
  @Test void Size$p$() { assertEquals(95635, p.size()); }
  @Test void Size$q$() { assertEquals(48805, q.size()); }
  @Test void Size$r$() { assertEquals(24795, r.size()); }
  @Test void Size$s$() { assertEquals(12545, s.size()); }
  @Test void Size$t$() { assertEquals(6315, t.size()); }
  @Test void Size$u$() { assertEquals(3165, u.size()); }
  @Test void Size$v$() { assertEquals(1975, v.size()); }
  @Test void Size$w$() { assertEquals(785, w.size()); }
  @Test void Size$x$() { assertEquals(295, x.size()); }
  @Test void Size$y$() { assertEquals(85, y.size()); }
  @Test void Size$z$() { assertEquals(15, z.size()); }
  //
  @Test void Depth$o$() { assertEquals(85, o.depth()); }
  @Test void Depth$p$() { assertEquals(78, p.depth()); }
  @Test void Depth$q$() { assertEquals(71, q.depth()); }
  @Test void Depth$r$() { assertEquals(64, r.depth()); }
  @Test void Depth$s$() { assertEquals(57, s.depth()); }
  @Test void Depth$t$() { assertEquals(50, t.depth()); }
  @Test void Depth$u$() { assertEquals(43, u.depth()); }
  @Test void Depth$v$() { assertEquals(36, v.depth()); }
  @Test void Depth$w$() { assertEquals(29, w.depth()); }
  @Test void Depth$x$() { assertEquals(22, x.depth()); }
  @Test void Depth$y$() { assertEquals(15, y.depth()); }
  @Test void Depth$z$() { assertEquals(8, z.depth()); }
  //
  @Test void Depth$aOK() { assertEquals(1, a.depth()); }
  @Test void Depth$bOK() { assertEquals(1, b.depth()); }
  @Test void Depth$cOK() { assertEquals(1, c.depth()); }
  @Test void Depth$ΦOK() { assertEquals(1, Φ.depth()); }
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
}
