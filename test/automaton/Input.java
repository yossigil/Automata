package automaton;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.Test;


public interface Input {

  static Stream<String> inputs() { return Arrays.stream(inputs); }

  String[] inputs = { // All strings of length up 4
  "", //
  "a", "b", "c", //
  "aa", "ab", "ac", //
  "ba", "bb", "bc", //
  "ca", "cb", "cc", //
  "aaa", "aab", "aac", "aba", "abb", "abc", "aca", "acb", "acc",//
  "baa", "bab", "bac", "bba", "bbb", "bbc", "bca", "bcb", "bcc",//
  "caa", "cab", "cac", "cba", "cbb", "cbc", "cca", "ccb", "ccc",//
  "aaaa", "aaab", "aaac", "aaba",
  "aabb", "aabc", //
  "aaca", "aacb", "aacc", "abaa", "abab", "abac", //
  "abba", "abbb", "abbc", "abca", "abcb", "abcc", //
  "acaa", "acab", "acac", "acba", "acbb", "acbc", //
  "acca", "accb", "accc", "baaa", "baab", "baac", //
  "baba", "babb", "babc", "baca", "bacb", "bacc", //
  "bbaa", "bbab", "bbac", "bbba", "bbbb", "bbbc", //
  "bbca", "bbcb", "bbcc", "bcaa", "bcab", "bcac", //
  "bcba", "bcbb", "bcbc", "bcca", "bccb", "bccc", //
  "caaa", "caab", "caac", "caba", "cabb", "cabc", //
  "caca", "cacb", "cacc", "cbaa", "cbab", "cbac", //
  "cbba", "cbbb", "cbbc", "cbca", "cbcb", "cbcc", //
  "ccaa", "ccab", "ccac", "ccba", "ccbb", "ccbc", //
  "ccca", "cccb", "cccc", };

  @SuppressWarnings("static-method") class TEST {
    @Test public void count() {
      assertEquals(inputs.length, 1 + 3 + 9 + 27 + 81);
    }
    @Test public void unique() {
      assertEquals(inputs().distinct().count(), inputs().count());
    }
  }
}
