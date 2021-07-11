package finite;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class Text extends NFSA<Character> {
  //@formatter:off
  Text And(final char ¢) { return new Text(and(new Text(¢))); }
  Text Or(final char ¢) { return new Text(or(new Text(¢))); }
  Text Then(final char ¢) { return new Text(then(new Text(¢))); }
  Text and(final Text ¢) { return new Text(super.and(¢)); }
  Text or(final Text ¢) { return new Text(super.or(¢)); }
  Text then(final Text ¢) { return new Text(super.then(¢)); }
  @Override public Text many() { return new Text(super.many()); }
  Text(final Q q0, final Set<Q> ζ, final Map<Character, Map<Q, Q>> δ, final Map<Q, Set<Q>> ε) { super(q0, ζ, δ, ε); }
  Text() { }
  Text(final Text t) { super(t); }
  Text(final NFSA<Character> a) { super(a); }
  Text(final char c) { final var q1 = new Q(); δ(q0, c, q1); ζ(q1); }
  //@formatter:on
  /* Dense: //@formatter:off */
  boolean run(final String ¢) { return run(¢.toCharArray()); }
  boolean run(final char[] cs) {
    return run(() -> new Iterator<>() {
      int i;
      @Override public boolean hasNext() { return i < cs.length; }
      @Override public Character next() { return cs[i++]; }
    });
  }
}