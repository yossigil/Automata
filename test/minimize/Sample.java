package minimize;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import finite.NFSA;

public enum Sample implements Case {
  ε("", NFSA.<Character>ε()), //
  ʘ(".", NFSA.<Character>ʘ()), //
  Φ("Φ", NFSA.<Character>Φ()), //
  a("a", NFSA.<Character>σ('a')), //
  b("b", NFSA.<Character>σ('b')), //
  c("c", NFSA.<Character>σ('c')), //
  or("a|b", NFSA.<Character>σ('a').or(b.inner)), //
  then("ab", a.inner.then(b.inner)), //
  many("a*", a.inner.many()), //
  or$abc("a|b|c", a.inner.or(b.inner.or(c.inner))), //
  or$aba("a|b|a", a.inner.or(b.inner.or(a.inner))), //
  many$aa("(a*)*", a.inner.many().many()), //
  a$b$many("ab*", a.inner.then(b.inner.many())), //
  many$ab("(ab)*", a.inner.then(b.inner).many()), //
  many$many$ab("((ab)*)*", many$ab.inner.many()), //
  then$abc("abc", a.inner.then(b.inner).then(c.inner)), //
  then$aba("aba", a.inner.then(b.inner).then(a.inner)), //
  // ʘ$or$a(".|a", ʘ.inner.or(a.inner)), //
//  ʘ$then$a(".a", ʘ.inner.then(a.inner)), //
  /// ʘ$many$then$a(".*a", ʘ.inner.many().then(a.inner)), //
//  b$ʘ$many$then$a("b.*a", b.inner.then(ʘ.inner.many().then(c.inner))), //
  aba$or$a$many("((aba)|(a))*", then$aba.inner.or(b.inner).many()), //
  ; //
  public static Stream<Sample> s() { return Arrays.stream(values()); }
  @Override public String toString() { return asString(); }
  private final NFSA<Character> inner;
  private final String          pattern;
  Sample(String pattern, NFSA<Character> inner) {
    this.pattern    = pattern;
    this.inner   = inner;
  }
  @Override public boolean accept(String input) { return Pattern.compile(pattern).matcher(input).matches(); }
  @Override public NFSA<Character> inner() { return inner; }
  public static Stream<SimpleEntry<Sample, String>> pairs() {
    return s().flatMap((Sample x) -> Case.inputs().map(v -> new SimpleEntry<Sample, String>(x, v)));
  }
  @SuppressWarnings("static-method") static class RunAllPairs {
    @Test public void runAllNFSAs() {
      pairs().forEach(e -> e.getKey().NFSA().run(e.getValue()));
    }
    @Test public void runAllDFSAs() {
      pairs().forEach(e -> e.getKey().DFSA().run(e.getValue()));
    }
    @Test public void runAllMinimals() {
      pairs().forEach(e -> e.getKey().minimal().run(e.getValue()));
    }
  }
  public String pattern() { return pattern; }
}
