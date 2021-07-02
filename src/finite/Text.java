package finite;

import java.util.Iterator;

class Text extends NFSA<Character, Text> {
  public Text(char c) {
    Q q1 = new Q();
    δ(q0,c, q1);
    ζ(q1);
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
}