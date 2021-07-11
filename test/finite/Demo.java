package finite;

import org.junit.jupiter.api.Test;

class Demo {
  final Text empty  = new Text();
  final Text a      = new Text('a').or(empty);
  final Text b      = new Text('b');
  final Text ab     = new Text(a.then(b));
  final Text a_b    = new Text(a.or(b));
  final Text abStar = new Text(ab.many());
  final Text x0     = new Text(a_b.then(ab).Then('c').then(a_b).or(a.then(a).then(a).then(a))).Then('c');
  final Text x1     = x0.or(abStar.then(a).or(x0));
  final Text x2     = x1.many().Or('c').then(abStar.Then('c').Then('a'));
  final Text x3     = x2.then(a_b).then(x2).many();
  final Text x4     = x3.then(x3).many().or(x2).many().then(x1);
  final Text abcd   = new Text(new Text('a').then(new Text('b').then(new Text('c')).then(new Text('d'))));
  @Test void nfsa() {
    show(x4.TikZ());
  }
  @Test void dfsa() {
    show(x4.DFSA().TikZ());
  }
  private static void show(final String graph) {
    print(path(graph));
  }
  @Test void minimized() {
    print(path(x4.DFSA().minimize().TikZ()));
  }
  private static void print(final String ¢) {
    System.out.println(¢);
  }
  private static String path(final String graph) {
    return "\\path " + graph + ";";
  }
}
