package finite;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class invariant: all nodes are numbered between start and stop.
 */
class NFSA<Σ> extends FSA<Σ> {

  boolean ζ(Set<Q> qs) {
    for (var s : qs)
      if (ζ.contains(s))
        return true;
    return false;
  }

  final Map<Q, Set<Q>> ε;

  boolean run(Iterable<Σ> w) {
    var q = Set.of(q0);
    for (var σ : w)
      q = δ(q, σ);
    return ζ(q);
  }

  NFSA<Σ, Q> ε(Q from, Q to) {
    ε(from).add(to);
    return this;
  }

  private Set<Q> ε(Q q) {
    if (ε.get(q) == null)
      ε.put(q, new LinkedHashSet<>());
    return ε.get(q);
  }

  Set<Q> ε(Set<Q> $) {
    for (;;) {
      final Set<Q> todo = new LinkedHashSet<>();
      for (var q : $)
        for (var qε : ε(q))
          if (!$.contains(qε))
            todo.add(qε);
      if (todo.isEmpty())
        return $;
      $.addAll(todo);
    }
  }

  Set<Q> δ(Set<Q> qs, Σ σ) {
    final Set<Q> $ = new LinkedHashSet<>();
    for (var q : ε(qs))
      $.add(δ(q, σ));
    return ε($);
  }

  NFSA<Σ> or(NFSA<Σ> a) {
    var $ = new NFSA<Σ>();
    $.merge(ζ);
    $.merge(δ);
    $.merge(ε);
    $.merge(a.ζ);
    $.merge(a.δ);
    $.merge(a.ε);
    return $;
  }

  private void δ(Map<Σ, Map<Q, Q>> δ) {
    // TODO Auto-generated method stub

  }

  NFSA<Σ> then(NFSA<Σ> a) {
    var $ = new NFSA<Σ>(this, a).copy(a.ζ, 1 + n()).ε(0, 1);
    for (var s : ζ)
      $.ε(s + 1, n() + 1);
    return $;
  }

  NFSA<Σ> and(NFSA<Σ> a) {
    throw new RuntimeException(this + "");
  }

  NFSA<Σ> star() {
    final var oldStart = 0;
    final var newStart = 1;
    final var $ = new NFSA<Σ>(n() + 1).copy(this, 1).ε(oldStart, newStart);
    for (var s : ζ)
      $.ε(s + 1, newStart);
    return $;
  }

  NFSA<Σ> not() {
    final var $ = new NFSA(n() + 1).copy(this, 1);
    for (var s = 0; s < n(); s++)
      $.ζ.add(s);
    for (var s : ζ)
      $.ζ.remove(s);
    return $;
  }

  NFSA<Σ> copy(NFSA a, Q Δ) {
    return ε(a.ε, Δ).δ(a.δ, Δ);
  }

  NFSA<Σ> copy(Set<Q> A2, Q Δ) {
    for (var s : A2)
      ζ.add(s + Δ);
    return this;
  }

  NFSA<Σ> ε(List<Q>[] ε2, Q Δ) {
    for (var i = 0; i < ε2.length; ++i)
      copy(ε[i + Δ], ε2[i], Δ);
    return this;
  }

  NFSA<Σ> δ(Map<Σ, Q>[] δ2, Q Δ) {
    for (var i = 0; i < δ2.length; ++i)
      δ(δ[i + Δ], δ2[i], Δ);
    return this;
  }

  void δ(Map<Σ, Q> into, Map<Σ, Q> from, Q Δ) {
    for (Σ c : from.keySet()) {
      if (!into.containsKey(c))
        into.put(c, from.get(c) + Δ);
    }
  }

  static private void copy(List<Q> into, List<Q> from, Q Δ) {
    for (var s : from)
      into.add(s + Δ);
  }

  @SuppressWarnings("unchecked") static <T> List<T>[] lists(Q n) {
    var $ = new List[n];
    for (Q i = 0; i < n; ++i)
      $[i] = new ArrayList<>();
    return $;
  }

  class State implements V<State> {
    Set<Q> qs = new HashSet<>();

    State(Q q) {
      qs.add(q);
    }

    @Override public Iterable<State> neighbours() {
      Set<State> $ = new HashSet<>();
      for (var q : ε(qs))
        return $;
      return $;
    }

  }

  DFSA<Σ> d() {
    Set<NFSA<Σ>.State> states = new DFS<State>() {
      @Override public void visit(NFSA<Σ>.State s) {
        // TODO Auto-generated method stub

      }
    }.dfs(new State(0));
    Q n = states.size();
    Map<Σ, Q>[] δ = maps(n);
    return new DFSA(null, null);
  }

  NFSA() {
    q0(q0)
    ζ.add(0);
  }

  NFSA(Σ c) {
    this(2);
    ζ.add(1);
    δ[0].put(c, 1);
  }
}