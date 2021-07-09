package finite;

import org.junit.jupiter.api.Test;

class Demo {
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
  final Text abcd = new Text(new Text('a').then(new Text('b').then(new Text('c')).then(new Text('d'))));

  @Test void nfsa() {
    show(x4.TikZ());
  }

  @Test void dfsa() {
    show(x4.DFSA().TikZ());
  }

  private static void show(String graph) {
    print(path(graph));
  }

  @Test void minimized() {
    print(path(x4.DFSA().minimize().TikZ()));
  }

  private static void print(String s) {
    System.out.println(s);
  }

  private static String path(String graph) {
    return "\\path " + graph + ";";
  }

}
