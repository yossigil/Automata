package finite;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Lexer extends NFSA<Character> {
  //@formatter:off
  public Lexer And(final char ¢) { return new Lexer(and(Lexer.c(¢))); }
  public Lexer Or(final char ¢)  { return new Lexer(or(Lexer.c(¢))); }
  private static Lexer c(char ¢) { return new Lexer(NFSA.σ(¢)); }
  public static Lexer any()      { return new Lexer(NFSA.ʘ()); } 
  public Lexer Then(final char ¢) { return new Lexer(then(Lexer.c(¢))); }
  public Lexer and(final Lexer ¢) { return new Lexer(super.and(¢)); }
  public Lexer or(final Lexer ¢) { return new Lexer(super.or(¢)); }
  public Lexer then(final Lexer ¢) { return new Lexer(super.then(¢)); }
  @Override public Lexer many() { return new Lexer(super.many()); }
  Lexer(final Q q0, final Set<Q> ζ, final Map<Character, Map<Q, Q>> δ, final Map<Q, Set<Q>> ε) { super(q0, ζ, δ, ε); }
  public Lexer() { }
  Lexer(final Lexer t) { super(t); }
  public Lexer(final NFSA<Character> a) { super(a); }
  public Lexer(final char c) { final var q1 = new Q(); δ(q0, c, q1); ζ(q1); }
  //@formatter:on
  /* Dense: //@formatter:off */
  public boolean run(final String ¢) { return run(¢.toCharArray()); }
  boolean run(final char[] cs) {
    return run(() -> new Iterator<>() {
      int i;
      @Override public boolean hasNext() { return i < cs.length; }
      @Override public Character next() { return cs[i++]; }
    });
  }
}