package minimize;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import automaton.FSA;
import automaton.NFSA;

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
    System.out.println("\\matrix[ampersand replacement=\\&]{%");
    System.out.println("\\node{NFSA of \\verb+" + this + "+};\\\\");
    System.out.println(NFSA().TikZ() + "\\\\");
    System.out.println("\\node{DFSA of \\verb+" + this + "+};\\\\");
    System.out.println(DFSA().TikZ() + "\\\\");
    System.out.println("\\node{Minimal DFSA of \\verb+" + this + "+};\\\\");
    System.out.println(DFSA().minimal().TikZ() + "\\\\");
    System.out.println("};");
    System.out.println("\\end{tikzpicture}\n");
  }
  static Stream<String> inputs() { return Arrays.stream(inputs); }
  String[] inputs = { // All strings of length up 4
      "", //
      "a", "b", "c", //
      "aa", "ab", "ac", //
      "ba", "bb", "bc", //
      "ca", "cb", "cc", //
      "aaa", "aab", "aac", //
      "aba", "abb", "abc", //
      "aca", "acb", "acc", //
      "baa", "bab", "aac", //
      "bba", "bbb", "abc", //
      "bca", "bcb", "acc", //
      "caa", "cab", "cac", //
      "cba", "cbb", "cbc", //
      "cca", "ccb", "ccc", //
      "aaaa", "baab", "caac", //
      "aaba", "babb", "cabc", //
      "aaca", "bacb", "cacc", //
      "abaa", "bbab", "caac", //
      "abba", "bbbb", "cabc", //
      "abca", "bbcb", "cacc", //
      "acaa", "bcab", "ccac", //
      "acba", "bcbb", "ccbc", //
      "acca", "bccb", "cccc", //
      "aaaa", "baab", "caac", //
      "aaba", "babb", "cabc", //
      "aaca", "bacb", "cacc", //
      "abaa", "bbab", "caac", //
      "abba", "bbbb", "cabc", //
      "abca", "bbcb", "cacc", //
      "acaa", "bcab", "ccac", //
      "acba", "bcbb", "ccbc", //
      "acca", "bccb", "cccc", //
      "aaaa", "baab", "caac", //
      "aaba", "babb", "cabc", //
      "aaca", "bacb", "cacc", //
      "abaa", "bbab", "caac", //
      "abba", "bbbb", "cabc", //
      "abca", "bbcb", "cacc", //
      "acaa", "bcab", "ccac", //
      "acba", "bcbb", "ccbc", //
      "acca", "bccb", "cccc", //
  };
  @SuppressWarnings("static-method") class TEST {
    @Test public void count() {
      assertEquals(inputs.length, 1 + 3 + 9 + 27 + 81);
    }
    @Test public void unique() {
      assertEquals(inputs().distinct().count(), inputs().count());
    }
  }
}