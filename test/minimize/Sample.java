package minimize;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.junit.Test;

import automaton.NFSA;

public enum Sample implements Case {
  ε("", NFSA.<Character>ε()), //
  ʘ(".", NFSA.<Character>ʘ()), //
  Φ("Φ", NFSA.<Character>Φ()), //
  a("a", NFSA.<Character>σ('a')), //
  b("b", NFSA.<Character>σ('b')), //
  c("c", NFSA.<Character>σ('c')), //
  or("a|b", a.or(b)), //
  ab("ab", a.then(b)), //
  many("a*", a.many()), //
  or$abc("a|b|c", a.or(b).or(c.NFSA())), //
  orMany("(a|b)*", or.many()), //
  or$aba("a|b|a", a.or(b.or(a))), //
  many$aa("(a*)*", a.many().many()), //
  a$b$many("ab*", a.then(b.many())), //
  many$ab("(ab)*", ab.many()), //
  many$many$ab("((ab)*)*", many$ab.many()), //
  abc("abc", a.then(b).then(c.NFSA())), //
  aba("aba", a.then(b.then(a))), //
  aba$or$a$many("((aba)|(a))*", aba.or(b).many()) //
  ; //
  public static Stream<Sample> s() { return Arrays.stream(values()); }
  @Override public String toString() { return asString(); }
  @Override public String pattern() { return pattern; }
  private final NFSA<Character> inner;
  private final String          pattern;
  Sample(String pattern, NFSA<Character> inner) {
    this.pattern = pattern;
    this.inner   = inner;
  }
  @Override public boolean accept(String input) { return Pattern.compile(pattern).matcher(input).matches(); }
  @Override public NFSA<Character> inner() { return inner; }
  public static Stream<SimpleEntry<Sample, String>> pairs() {
    return s().flatMap((Sample x) -> Input.inputs().map(v -> new SimpleEntry<Sample, String>(x, v)));
  }
  @SuppressWarnings("static-method") static class RunAllPairs {
    @Test public void runAllNFSAs() {
      pairs().forEach(e -> e.getKey().NFSA().run(e.getValue()));
    }
    @Test public void runAllDFSAs() {
      pairs().forEach(e -> e.getKey().DFSA().run(e.getValue()));
    }
    @Test public void runAllMinimals() {
      pairs().forEach(e -> e.getKey().MDFSA().run(e.getValue()));
    }
  }
}
