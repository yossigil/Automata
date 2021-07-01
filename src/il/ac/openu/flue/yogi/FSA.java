package il.ac.openu.flue.yogi;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

abstract class FSA {

  @SuppressWarnings("unchecked") static <F, T> Map<F, T>[] maps(int n) {
    var $ = new Map[n];
    for (int i = 0; i < n; ++i)
      $[i] = new LinkedHashMap<>();
    return $;
  }

  abstract boolean run(String string);

  final Map<Character, Integer>[] δ;
  protected final Set<Integer> ζ;

  public FSA(int n) {
    δ = maps(n);
    ζ = new LinkedHashSet<>();
  }

  public FSA(Set<Integer> ζ, Map<Character, Integer>[] δ) {
    this.δ = δ;
    this.ζ = ζ;
  }

  public int n() {
    return δ.length;
  }

}
