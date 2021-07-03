package finite;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class Text extends NFSA<Character> {
  public Text Then(char c) {
    return new Text(super.then(new Text(c)));
  }
  Text(char c) {
    Q q1 = new Q();
    δ(q0, c, q1);
    ζ(q1);
  }

  Text(NFSA<Character> a) {
    this(a.q0, a.ζ, a.δ, a.ε);
    System.out.println(a.TikZ());
  }

  Text(Q q0, Set<Q> ζ, Map<Character, Map<Q, Q>> δ, Map<Q, Set<Q>> ε) {
    super(q0, ζ, δ, ε);
  }

  public Text() {
    // TODO Auto-generated constructor stub
  }

  /* Dense: //@formatter:off */
  boolean run(String s) { return run(s.toCharArray()); }
  boolean run(char[] cs) {
    return run(new Iterable<Character>() {
      @Override public Iterator<Character> iterator() {
        return new Iterator<Character>() {
          int i = 0;
          @Override public boolean hasNext() { return i < cs.length; }
          @Override public Character next() { return cs[i++]; }
        };
       }
    });
  }

  public Text And(char c) {
    return new Text(super.and(new Text(c)));
  }
  public Text Or(char c) {
    return new Text(super.or(new Text(c)));
  }
  public Text star() {
    return new Text(super.star());
  }
}