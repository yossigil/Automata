package automaton;

public interface Case {
  NFSA<Character> inner();
  String name();
  String pattern();
  default NFSA<Character> NFSA() { return inner(); }
  default FSA<Character> DFSA() { return inner().DFSA(); }
  default FSA<Character> MDFSA() { return DFSA().minimal(); }
  default NFSA<Character> then(Case other) { return then(other.inner()); }
  default NFSA<Character> then(NFSA<Character> other) { return inner().then(other); }
  default NFSA<Character> or(Case other) { return or(other.inner()); }
  default NFSA<Character> or(NFSA<Character> other) { return inner().or(other); }
  default NFSA<Character> many() { return inner().many(); }
  default String asString() { return String.format("/%s/ (%s)", pattern(), name()); }
  boolean accept(String input);
  default void show() {
//    System.out.println("NFSA");
//    System.out.println(NFSA());
//    System.out.println("DFSA");
//    System.out.println(DFSA());
//    System.out.println("DFSA (minimal)}");
//    System.out.println(minimal());
    System.out.println("\\begin{tikzpicture}");
    System.out.println("\\matrix[anchor=north,ampersand replacement=\\&]{%");
    System.out.println("\\node{NFSA of \\verb+" + this + "+};\\\\");
    System.out.println(NFSA().TikZ() + "\\\\");
    System.out.println("\\node{DFSA of \\verb+" + this + "+};\\\\");
    System.out.println(DFSA().TikZ() + "\\\\");
    System.out.println("\\node{Minimal DFSA of \\verb+" + this + "+};\\\\");
    System.out.println(DFSA().minimal().TikZ() + "\\\\");
    System.out.println("};");
    System.out.println("\\end{tikzpicture}\n");
  }
}