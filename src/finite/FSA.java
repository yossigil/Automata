package finite;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

//@formatter:off
class Q {
  static int count = 0;
  final int n = count++;
  @Override public String toString() { return "q" + n; }
}
//@formatter:on

class FSA<Σ> {
  Set<Q> Q() {
    final Set<Q> $ = empty.Set();
    dfs(q->$.add(q));
    return $;
  }
  void dfs(Consumer<Q> v) { //@formatter:off
    new XDFS<Q>() {
      @Override public Set<Q> n(Q q) { return FSA.this.n(q); } 
      @Override public void v(Q q) { v.accept(q); } //@formatter:on
    }.dfs(q0);
  }
  Set<Q> n(Q q) {
    final Set<Q> $ = empty.Set();
    for (Σ σ : Σ())
      if (δ(σ).get(q) != null)
        $.add(δ(σ).get(q));
    return $;
  }

//@formatter:off
  final Q q0;
  final Q q$ = new Q();
    // Managing set of all states used
  Set<Q> Q = empty.Set();
  Q Q(Q q) { Q.add(q); return q; }
  Set<Q> Q(Set<Q> qs) { Q.addAll(qs); return qs; }

  // Managing set of accepting states 
  final Set<Q> ζ;
  void ζ(Q q) { ζ.add(Q(q)); } 
  void ζ(Set<Q> qs) { ζ.addAll(Q(qs));  } 

  // Managing deterministic transitions 
  final Map<Σ, Map<Q, Q>> δ;
  void δ(Q from, Σ σ, Q to) { δ(σ).put(Q(from), Q(to));  }
  Q δ(Q q, Σ σ) { return q == q$ || !δ(σ).containsKey(q) ? q$ : δ(σ).get(q); } 
  Map<Q, Q> δ(Σ σ) { if (δ.get(σ) == null) δ.put(σ, empty.Map()); return δ.get(σ); }

  // Set of letters seen
  Set<Σ> Σ() { return δ.keySet(); }
  //@formatter:on

  FSA() {
    this(new Q(), empty.Set(), empty.Map());
  }

  FSA(Q q0, Set<Q> ζ, Map<Σ, Map<Q, Q>> δ) {
    this.q0 = q0;
    this.ζ = ζ;
    this.δ = δ;
  }

  void δ(Map<Σ, Map<Q, Q>> δ) {
    for (Σ σ : δ.keySet())
      copy(δ(σ), δ.get(σ));
  }

  static void copy(Map<Q, Q> into, Map<Q, Q> from) {
    for (Q q : from.keySet())
      into.put(q, from.get(q));
  }
}