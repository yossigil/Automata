package minimize;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import finite.FSA;
import finite.NFSA;

public enum Sample {
  a("a", NFSA.<Character>σ('a')), //
  b("b", NFSA.<Character>σ('b')), //
  ε("", NFSA.<Character>ε()), //
  ʘ(".", NFSA.<Character>ʘ()), //
  Φ("Φ", NFSA.<Character>Φ()), //
  or("a|b", NFSA.<Character>σ('a').or(NFSA.<Character>σ('b'))), //
  or$abc("a|b|c", NFSA.<Character>σ('a').or(NFSA.<Character>σ('b').or(NFSA.<Character>σ('c')))), //
  or$aba("a|b|a", NFSA.<Character>σ('a').or(NFSA.<Character>σ('b').or(NFSA.<Character>σ('a')))), //
  many("a*", NFSA.<Character>σ('a').many()), //
  many$aa("(a*)*", NFSA.<Character>σ('a').many().many()), //
  many$ab("(ab)*", NFSA.<Character>σ('a').then(NFSA.<Character>σ('b').many())), //
  many$many$ab("((ab)*)*", NFSA.<Character>σ('a').then(NFSA.<Character>σ('b').many().many())), //
  then("ab", NFSA.<Character>σ('a').then(NFSA.<Character>σ('b'))), //
  then$abc("abc", NFSA.<Character>σ('a').then(NFSA.<Character>σ('b')).then(NFSA.<Character>σ('c'))), //
  then$aba("aba", NFSA.<Character>σ('a').then(NFSA.<Character>σ('b')).then(NFSA.<Character>σ('a'))), //
  aba$or$a$many("((aba)|(a))*", then$aba.inner.or(b.inner).many()), //
  ; //
  public static final String[] inputs = {                              // All strings of length up 4
      "",                                                              //
      "a", "b", "c",                                                   //
      "aa", "ab", "ac",                                                //
      "ba", "bb", "bc",                                                //
      "ca", "cb", "cc",                                                //
      "aaa", "aab", "aac",                                             //
      "aba", "abb", "abc",                                             //
      "aca", "acb", "acc",                                             //
      "baa", "bab", "aac",                                             //
      "bba", "bbb", "abc",                                             //
      "bca", "bcb", "acc",                                             //
      "caa", "cab", "cac",                                             //
      "cba", "cbb", "cbc",                                             //
      "cca", "ccb", "ccc",                                             //
      "aaaa", "baab", "caac",                                          //
      "aaba", "babb", "cabc",                                          //
      "aaca", "bacb", "cacc",                                          //
      "abaa", "bbab", "caac",                                          //
      "abba", "bbbb", "cabc",                                          //
      "abca", "bbcb", "cacc",                                          //
      "acaa", "bcab", "ccac",                                          //
      "acba", "bcbb", "ccbc",                                          //
      "acca", "bccb", "cccc",                                          //
      "aaaa", "baab", "caac",                                          //
      "aaba", "babb", "cabc",                                          //
      "aaca", "bacb", "cacc",                                          //
      "abaa", "bbab", "caac",                                          //
      "abba", "bbbb", "cabc",                                          //
      "abca", "bbcb", "cacc",                                          //
      "acaa", "bcab", "ccac",                                          //
      "acba", "bcbb", "ccbc",                                          //
      "acca", "bccb", "cccc",                                          //
      "aaaa", "baab", "caac",                                          //
      "aaba", "babb", "cabc",                                          //
      "aaca", "bacb", "cacc",                                          //
      "abaa", "bbab", "caac",                                          //
      "abba", "bbbb", "cabc",                                          //
      "abca", "bbcb", "cacc",                                          //
      "acaa", "bcab", "ccac",                                          //
      "acba", "bcbb", "ccbc",                                          //
      "acca", "bccb", "cccc",                                          //
  };
  public final NFSA<Character> inner;
  public final String          name;
  private Pattern              pattern;
  public FSA<Character> DFSA() {
    return inner.DFSA();
  }
  public FSA<Character> minimal() {
    return inner.DFSA().minimal();
  }
  Sample(String name, NFSA<Character> nfsa) {
    this.name    = name;
    this.inner   = nfsa;
    this.pattern = Pattern.compile(name);
  }
  public boolean accept(String input) {
    return pattern.matcher(input).matches();
  }
  public boolean run(String input) {
    return inner.run(input);
  }
  public boolean rejects(String input) {
    return !inner.run(input);
  }
  public static Stream<Sample> s() { return Arrays.stream(values()); }
  public static Stream<String> inputs() { return Arrays.stream(inputs); }
  public boolean DFSAreject(String input) { return !DFSA().run(input); }
  public <Σ> void show() {
    System.out.println("NFSA");
    System.out.println(inner);
    System.out.println("DFSA");
    System.out.println(inner.DFSA());
    System.out.println("DFSA (minimal)}");
    System.out.println(inner.DFSA().minimal());
    System.out.println("\\begin{tikzpicture}");
    System.out.println("\\matrix{%");
    System.out.println("\\node{" + name + "}\\\\");
    System.out.println("\\node{NFSA}\\\\");
    System.out.println("\\" + inner.TikZ() + ";\\\\");
    System.out.println("\\node{DFSA}\\\\");
    System.out.println("\\" + inner.DFSA().TikZ() + ";\\\\");
    System.out.println("\\node{DFSA (minimal)}\\\\");
    System.out.println("\\" + inner.DFSA().minimal().TikZ() + ";\\\\");
    System.out.println("\\end{tikzpicture}\n");
  }
  boolean minimalRejects(String input) {
    return !minimal().run(input);
  }
}
