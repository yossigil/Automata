package automaton;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public interface Input {
  static Stream<String> inputs() { return Arrays.stream(inputs); }
  String[] inputs = { // All strings of length up to 5
      "", //
      "a", "b", "c", //
      "aa", "ab", "ac", //
      "ba", "bb", "bc", //
      "ca", "cb", "cc", //
      "aaa", "aab", "aac", "aba", "abb", "abc", "aca", "acb", "acc", //
      "baa", "bab", "bac", "bba", "bbb", "bbc", "bca", "bcb", "bcc", //
      "caa", "cab", "cac", "cba", "cbb", "cbc", "cca", "ccb", "ccc", //
      "aaaa", "aaab", "aaac", "aaba", "aabb", "aabc", //
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
      "ccca", "cccb", "cccc", //
      "aaaaa", "aaaab", "aaaac", "aaaba", "aaabb", "aaabc", "aaaca", "aaacb", "aaacc", "aabaa", "aabab", "aabac",
      "aabba", "aabbb", "aabbc", "aabca", "aabcb", "aabcc", "aacaa", "aacab", "aacac", "aacba", "aacbb", "aacbc",
      "aacca", "aaccb", "aaccc", "abaaa", "abaab", "abaac", "ababa", "ababb", "ababc", "abaca", "abacb", "abacc",
      "abbaa", "abbab", "abbac", "abbba", "abbbb", "abbbc", "abbca", "abbcb", "abbcc", "abcaa", "abcab", "abcac",
      "abcba", "abcbb", "abcbc", "abcca", "abccb", "abccc", "acaaa", "acaab", "acaac", "acaba", "acabb", "acabc",
      "acaca", "acacb", "acacc", "acbaa", "acbab", "acbac", "acbba", "acbbb", "acbbc", "acbca", "acbcb", "acbcc",
      "accaa", "accab", "accac", "accba", "accbb", "accbc", "accca", "acccb", "acccc", "baaaa", "baaab", "baaac",
      "baaba", "baabb", "baabc", "baaca", "baacb", "baacc", "babaa", "babab", "babac", "babba", "babbb", "babbc",
      "babca", "babcb", "babcc", "bacaa", "bacab", "bacac", "bacba", "bacbb", "bacbc", "bacca", "baccb", "baccc",
      "bbaaa", "bbaab", "bbaac", "bbaba", "bbabb", "bbabc", "bbaca", "bbacb", "bbacc", "bbbaa", "bbbab", "bbbac",
      "bbbba", "bbbbb", "bbbbc", "bbbca", "bbbcb", "bbbcc", "bbcaa", "bbcab", "bbcac", "bbcba", "bbcbb", "bbcbc",
      "bbcca", "bbccb", "bbccc", "bcaaa", "bcaab", "bcaac", "bcaba", "bcabb", "bcabc", "bcaca", "bcacb", "bcacc",
      "bcbaa", "bcbab", "bcbac", "bcbba", "bcbbb", "bcbbc", "bcbca", "bcbcb", "bcbcc", "bccaa", "bccab", "bccac",
      "bccba", "bccbb", "bccbc", "bccca", "bcccb", "bcccc", "caaaa", "caaab", "caaac", "caaba", "caabb", "caabc",
      "caaca", "caacb", "caacc", "cabaa", "cabab", "cabac", "cabba", "cabbb", "cabbc", "cabca", "cabcb", "cabcc",
      "cacaa", "cacab", "cacac", "cacba", "cacbb", "cacbc", "cacca", "caccb", "caccc", "cbaaa", "cbaab", "cbaac",
      "cbaba", "cbabb", "cbabc", "cbaca", "cbacb", "cbacc", "cbbaa", "cbbab", "cbbac", "cbbba", "cbbbb", "cbbbc",
      "cbbca", "cbbcb", "cbbcc", "cbcaa", "cbcab", "cbcac", "cbcba", "cbcbb", "cbcbc", "cbcca", "cbccb", "cbccc",
      "ccaaa", "ccaab", "ccaac", "ccaba", "ccabb", "ccabc", "ccaca", "ccacb", "ccacc", "ccbaa", "ccbab", "ccbac",
      "ccbba", "ccbbb", "ccbbc", "ccbca", "ccbcb", "ccbcc", "cccaa", "cccab", "cccac", "cccba", "cccbb", "cccbc",
      "cccca", "ccccb", "ccccc", };
  @SuppressWarnings("static-method") class TEST {
    @Test public void count() {
      assertEquals(inputs.length, 1 + 3 + 9 + 27 + 81 + 243);
    }
    @Test public void unique() {
      assertEquals(inputs().distinct().count(), inputs().count());
    }
  }
}