package automaton.minimize;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import automaton.Case;
import automaton.Input;
import automaton.NFSA;

public enum Sample implements Case {
  ε("", NFSA.<Character>ε()), //
  ʘ(".", NFSA.<Character>ʘ()), //
  Φ("Φ", NFSA.<Character>Φ()), //
  a("a", NFSA.<Character>σ('a')), //
  b("b", NFSA.<Character>σ('b')), //
  c("c", NFSA.<Character>σ('c')), //
  or("a|b", a.or(b)), //
  manyA("a*", a.many()), //
  manyB("b*", a.many()), //
  ab("ab", a.then(b)), //
  ba("ab", b.then(a)), //
  manyAThenManyB("a*b*", manyA.then(manyB)), //
  many$manyAThenManyB("(a*b*)", manyAThenManyB.many()), //
  abba("abba", ab.then(ba)), //
  abOrba("(ab)|(ba)", ab.then(ba)), //
  ManyAbOrBa("(ab)|(ba)", abOrba.many()), //
  or$abc("a|b|c", a.or(b).or(c.NFSA())), //
  orMany("(a|b)*", or.many()), //
  orAbc("a|b|c", a.or(b.or(c))), //
  orAba("a|b|a", a.or(b.or(a))), //
  many$aa("(a*)*", a.many().many()), //
  a$b$many("ab*", a.then(b.many())), //
  many$ab("(ab)*", ab.many()), //
  many$many$ab("((ab)*)*", many$ab.many()), //
  abc("abc", ab.then(c.NFSA())), //
  aba("aba", a.then(ba)), //
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
