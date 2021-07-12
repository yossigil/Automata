package FSA.complex;

import org.junit.jupiter.api.Test;

import finite.Lexer;

class Demo {
  final Lexer empty  = new Lexer();
  final Lexer a      = new Lexer('a').or(empty);
  final Lexer b      = new Lexer('b');
  final Lexer ab     = new Lexer(a.then(b));
  final Lexer a_b    = new Lexer(a.or(b));
  final Lexer abStar = new Lexer(ab.many());
  final Lexer x0     = new Lexer(a_b.then(ab).Then('c').then(a_b).or(a.then(a).then(a).then(a))).Then('c');
  final Lexer x1     = x0.or(abStar.then(a).or(x0));
  final Lexer x2     = x1.many().Or('c').then(abStar.Then('c').Then('a'));
  final Lexer x3     = x2.then(a_b).then(x2).many();
  final Lexer x4     = x3.then(x3).many().or(x2).many().then(x1);
  final Lexer abcd   = new Lexer(new Lexer('a').then(new Lexer('b').then(new Lexer('c')).then(new Lexer('d'))));
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
