package finite;

import org.junit.jupiter.api.Test;

class TikZ {
  final Text empty = new Text();
  final Text a = new Text('a').or(empty);
  final Text b = new Text('b');
  final Text ab = new Text(a.then(b));
  final Text a_b = new Text(a.or(b));
  final Text abStar = new Text(ab.star());
  final Text x0 = new Text(a_b.then(ab).Then('c').then(a_b).or(a.then(a).then(a).then(a))).Then('c');
  final Text x1 = x0.or(abStar.then(a).or(x0));
  final Text x2 = x1.star().Or('c').then(abStar.Then('c').Then('a'));
  final Text x3 = x2.then(a_b).then(x2).star();
  final Text x4 = x3.then(x3).star().or(x2).star().then(x1);

  @Test void exists() {
    // System.out.println(x4.TikZ());
    DFSA<Character> d = x4.d();
    System.out.println(d.TikZ());
  }
}
