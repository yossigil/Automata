package finite;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class invariant: all nodes are numbered between start and stop.
 */
public class NFSA extends FSA {
  final List<Integer>[] ε;

  NFSA() {
    this(1);
    ζ.add(0);
  }



  NFSA(char c) {
    this(2);
    ζ.add(1);
    δ[0].put(c,1);
  }

  NFSA ε(int from, int to) {
    ε[from].add(to);
    return this;
  }

  public NFSA(int n) {
    super(n);
    ε = lists(n);

  }

  public NFSA(NFSA a1, NFSA a2) {
    this(1 + a1.n() + a2.n());
    merge(a1, 1).merge(a2, 1 + a1.n());
  }

  public boolean run(String s) {
    Set<Integer> q = new LinkedHashSet<>();
    q.add(0);
    for (char c : s.toCharArray()) {
      if ((q = δ(q, c)).isEmpty())
        return false;
    }
    return accepting(q);
  }

  Set<Integer> ε(Set<Integer> $) {
    for (;;) {
      final Set<Integer> ss = new LinkedHashSet<>();
      for (var s : $)
        for (var to : ε[s])
          if (!$.contains(to))
            ss.add(to);
      if (ss.isEmpty())
        return $;
      $.addAll(ss);
    }
  }

  public Set<Integer> δ(Set<Integer> ss, char c) {
    final Set<Integer> $ = new LinkedHashSet<>();
    for (var s : ε(ss))
      if (δ[s].get(c) != null)
        $.add( δ[s].get(c));
    return ε($);
  }

  NFSA or(NFSA a) {
    return new NFSA(this, a).merge(ζ, 1).merge(a.ζ, 1 + n()).ε(0, 1).ε(0, 1 + n());
  }

  NFSA then(NFSA a) {
    var $ = new NFSA(this, a).merge(a.ζ, 1 + n()).ε(0, 1);
    for (var s : ζ)
      $.ε(s + 1, n() + 1);
    return $;
  }

  NFSA and(NFSA a) {
    throw new RuntimeException(this + "");
  }

  NFSA star() {
    final var oldStart = 0;
    final var newStart = 1;
    final var $ = new NFSA(n() + 1).merge(this, 1).ε(oldStart, newStart);
    for (var s : ζ)
      $.ε(s + 1, newStart);
    return $;
  }

  NFSA not() {
    final var $ = new NFSA(n() + 1).merge(this, 1);
    for (var s = 0; s < n(); s++)
      $.ζ.add(s);
    for (var s : ζ)
      $.ζ.remove(s);
    return $;
  }

  NFSA merge(NFSA a, int Δ) {
    return ε(a.ε, Δ).δ(a.δ, Δ);
  }

  NFSA merge(Set<Integer> A2, int Δ) {
    for (var s : A2)
      ζ.add(s + Δ);
    return this;
  }

  NFSA ε(List<Integer>[] ε2, int Δ) {
    for (var i = 0; i < ε2.length; ++i)
      merge(ε[i + Δ], ε2[i], Δ);
    return this;
  }

  NFSA δ(Map<Character, Integer>[] δ2, int Δ) {
    for (var i = 0; i < δ2.length; ++i) 
      δ(δ[i + Δ], δ2[i], Δ);
    return this;
  }

  static void δ(Map<Character, Integer> into, Map<Character, Integer> from, int Δ) {
    for (Character c : from.keySet()) {
      if (!into.containsKey(c))
        into.put(c, from.get(c)+ Δ);
    }
  }

  static void merge(List<Integer> into, List<Integer> from, int Δ) {
    for (var s : from)
      into.add(s + Δ);
  }

  @SuppressWarnings("unchecked") static <T> List<T>[] lists(int n) {
    var $ = new List[n];
    for (int i = 0; i < n; ++i)
      $[i] = new ArrayList<>();
    return $;
  }

  public DFSA d() {
    int current;
    Map<Set<Integer>, Integer> ordinal = new LinkedHashMap<>();
    Set<Integer> accepting = new LinkedHashSet<>();
    Map<Character, Integer>[] delta=null;
    
    for (Set<Set<Integer>> todo = start(),done= empty();;) {
      if (todo.isEmpty()) return done;
      return done;
      for (set
    }
    return new DFSA(accepting, delta);
  }

  private Set<Set<Integer>> empty() {
    // TODO Auto-generated method stub
    return null;
  }



  private Set<Set<Integer>> start() {
    // TODO Auto-generated method stub
    return null;
  }



  public boolean accepting(Set<Integer> ss) {
    for (var s : ss)
      if (ζ.contains(s))
        return true;
    return false;
  }
}