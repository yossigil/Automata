package finite;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class Text extends NFSA<Character> {
  //@formatter:off
  Text And(char c) { return new Text(and(new Text(c))); }
  Text Or(char c) { return new Text(or(new Text(c))); }
  Text Then(char c) { return new Text(then(new Text(c))); }
  Text and(Text t) { return new Text(super.and(t)); }
  Text or(Text t) { return new Text(super.or(t)); }
  Text then(Text t) { NFSA<Character> then = super.then(t);
  System.out.println("Then = " + then.TikZ());
  Text text = new Text(then);
  System.out.println("Text = " + text.TikZ());
  return text; }
  Text star() { return new Text(super.star()); }
  //@formatter:on
  Text(char c) {
    Q q1 = new Q();
    δ(q0, c, q1);
    ζ(q1);
  }


  Text(Q q0, Set<Q> ζ, Map<Character, Map<Q, Q>> δ, Map<Q, Set<Q>> ε) {
    super(q0, ζ, δ, ε);
  }

  public Text() {
    // TODO Auto-generated constructor stub
  }

  public Text(Text t) {
    super(t); 
  }
  public Text(NFSA<Character> a) {
    super(a); 
    System.out.println("After constructor = " + TikZ());
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

}