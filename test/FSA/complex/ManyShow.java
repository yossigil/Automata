package FSA.complex;

import org.junit.jupiter.api.Test;

import finite.Lexer;
import finite.NFSA;
public class ManyShow {

  final Lexer many = new Lexer('a').Then('b').then(new Lexer('a').Or('b')).many();

  static void scope(String ¢) {
    System.out.println("\\begin{scope} \\path");
    System.out.println(¢);
    System.out.println("; \\end{scope}");
  }

  static <Σ> void show(final NFSA<Σ> ¢) {
    System.out.println("\\begin{tikzpicture}[start chain=going down]\n");
    System.out.println("\\node[on chain] {NFSA};");
    scope(¢.TikZ());
    System.out.println("\\node[on chain] {DFSA};");
    scope(¢.DFSA().TikZ());
    System.out.println("\\node[on chain] {minimized DFSA};");
    scope(¢.DFSA().minimize().TikZ());
    System.out.println("\\end{tikzpicture}\n");
  }

  @Test void DFSADemo() {
    show(many);
  }
  
}