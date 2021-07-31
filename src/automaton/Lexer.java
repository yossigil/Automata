package automaton;

import java.util.Iterator;

public class Lexer { //@formatter:off
  public final NFSA<Character>  inner;
  public static Lexer c(char ¢) { return new Lexer(NFSA.σ(¢)); }
  public static Lexer ʘ()      { return new Lexer(NFSA.ʘ()); } 
  public static Lexer ε()      { return new Lexer(NFSA.ε()); } 
  public Lexer And(final char ¢) { return and(Lexer.c(¢)); }
  public Lexer Or(final char ¢)  { return or(Lexer.c(¢)); }
  public Lexer Then(final char ¢) { return then(Lexer.c(¢)); }
  public Lexer and(final Lexer ¢) { return new Lexer(inner.and(¢.inner)); }
  public Lexer or(final Lexer ¢) { return new Lexer(inner.or(¢.inner)); }
  public Lexer then(final Lexer ¢) { return new Lexer(inner.then(¢.inner)); }
  private Lexer(NFSA<Character> inner) { this.inner = inner; } 
  public Lexer many() { return new Lexer(inner.many()); }
  //@formatter:on
  /* Dense: //@formatter:off */
  public boolean run(final String ¢) { return run(¢.toCharArray()); }
  boolean run(final char[] cs) {
    return inner.run(() -> new Iterator<>() {
      int i;
      @Override public boolean hasNext() { return i < cs.length; }
      @Override public Character next() { return cs[i++]; }
    });
  }
  public String TikZ() { return inner.TikZ(); }
  public FSA<Character> DFSA() { return inner.DFSA(); }
}