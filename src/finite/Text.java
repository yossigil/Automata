package finite;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class Text extends NFSA<Character> {
  //@formatter:off
  Text And(char ¢) { return new Text(and(new Text(¢))); }
  Text Or(char ¢) { return new Text(or(new Text(¢))); }
  Text Then(char ¢) { return new Text(then(new Text(¢))); }
  Text and(Text ¢) { return new Text(super.and(¢)); }
  Text or(Text ¢) { return new Text(super.or(¢)); }
  Text then(Text ¢) { return new Text(super.then(¢)); }
  Text star() { return new Text(super.star()); }
  Text(Q q0, Set<Q> ζ, Map<Character, Map<Q, Q>> δ, Map<Q, Set<Q>> ε) { super(q0, ζ, δ, ε); } 
  Text() { }
  Text(Text t) { super(t); }
  Text(NFSA<Character> a) { super(a); }
  Text(char c) { Q q1 = new Q(); δ(q0, c, q1); ζ(q1); }
  //@formatter:on

  /* Dense: //@formatter:off */
  boolean run(String ¢) { return run(¢.toCharArray()); }
  boolean run(char[] cs) {
    return run(new Iterable<Character>() {
      @Override public Iterator<Character> iterator() {
        return new Iterator<Character>() {
          int i;
          @Override public boolean hasNext() { return i < cs.length; }
          @Override public Character next() { return cs[i++]; }
        };
       }
    });
  }
}