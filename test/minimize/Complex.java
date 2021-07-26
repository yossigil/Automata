package minimize;

import static minimize.Sample.a;
import static minimize.Sample.aba;
import static minimize.Sample.b;
import static minimize.Sample.c;
import static minimize.Sample.ʘ;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.junit.Test;

import automaton.NFSA;
public enum Complex implements Case {
  ʘ$or$a(".|a", ʘ.or(a)), //
  ʘ$then$a(".a", ʘ.then(a)), //
  ʘ$many$then$a(".*a", ʘ.many().then(a.NFSA())), //
  b$ʘ$many$then$a("b.*a", b.then(ʘ.many().then(c.NFSA()))), //
  aba$or$a$many("((aba)|(a))*", aba.or(b.NFSA()).many()), //
  ; //
  public static Stream<Complex> s() { return Arrays.stream(values()); }
  @Override public String toString() { return asString(); }
  private final NFSA<Character> inner;
  private final String          pattern;
  Complex(String pattern, NFSA<Character> inner) {
    this.pattern = pattern;
    this.inner   = inner;
  }
  @Override public boolean accept(String input) { return Pattern.compile(pattern).matcher(input).matches(); }
  @Override public NFSA<Character> inner() { return inner; }
  public static Stream<SimpleEntry<Complex, String>> pairs() {
    return s().flatMap((Complex x) -> Input.inputs().map(v -> new SimpleEntry<Complex, String>(x, v)));
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
  public String pattern() { return pattern; }
}
