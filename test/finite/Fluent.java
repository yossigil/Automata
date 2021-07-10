package finite;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Fluent {
  final RE ε   = RE.ε;
  final RE Φ   = RE.Φ;
  final RE ʘ   = RE.ʘ;
  final RE a   = RE.c('a');
  final RE b   = RE.c('b');
  final RE ab  = a.then(b);
  final RE a_b = a.or(b);
  final RE not_a = a.not();
  final RE a_star = a.star();
  @SuppressWarnings("static-access") final RE a_plus = a.plus(); 
  final RE a_and_b = a.and(b);
  final RE c   = RE.c('c');
  final RE z   = a.or(b).then(c).star().then(b).and(c);
  final RE y   = z.or(z).then(z).star().then(z).and(z);
  final RE x   = y.or(z).then(y).star().then(z).and(y);
  final RE w   = x.or(y).then(z).star().then(x).and(y);
  final RE v   = w.or(x).then(y).star().then(z).and(w);
  final RE u   = v.or(w).then(x).star().then(y).and(z);
  final RE t   = u.or(v).then(w).star().then(x).and(y);
  final RE s   = t.or(u).then(v).star().then(w).and(x);
  final RE r   = s.or(t).then(u).star().then(v).and(w);
  final RE q   = r.or(s).then(t).star().then(u).and(v);
  final RE p   = r.or(q).then(s).star().then(t).and(u);
  final RE o   = r.or(p).then(q).star().then(s).and(t);

  @Test void aOK() {
    assertEquals("a", a + "");
  }

  @Test void bOK() {
    assertEquals("b", b + "");
  }

  @Test void cOK() {
    assertEquals("c", c + "");
  }

  @Test void ΦOK() {
    assertEquals("Φ", Φ + "");
  }

  @Test void εOK() {
    assertEquals("ε", ε + "");
  }

  @Test void ʘOK() {
    assertEquals("ε", ε + "");
  }

  @Test void abOK() {
    assertEquals("ab", ab + "");
  }

  @Test void a_bOK() {
    assertEquals("a|b", a_b + "");
  }
  @Test void a_and_bOK() {
    assertEquals("a&b", a_and_b + "");
  }
  @Test void a_plusOK() {
    assertEquals("a+", a_plus + "");
  }
  @Test void a_starOK() {
    assertEquals("a*", a_star + "");
  }
  @Test void a_notOK() {
    assertEquals("a!", not_a + "");
  }
}
