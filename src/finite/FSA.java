package finite;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

class Q {
  static int count = 0;
  private int n;
  Q() {
    this.n = count++;
  }
  
  @Override public String toString() {
    return "q" + n;
  }

  static Q make() {
    return new Q();
  }
}

class FSA<Σ> {
  //@formatter:off
  final Q q0;
  final Q q$ = Q.make();
  final Set<Q> ζ;
  final Map<Σ, Map<Q, Q>> δ;
  Set<Σ> Σ() { return δ.keySet(); }
  void δ(Q from, Σ σ, Q to) { δ(σ).put(from, to);  }
  void ζ(Q q) { ζ.add(q);  } 
  void ζ(Set<Q> qs) { ζ.addAll(qs);  } 
  FSA() { this(Q.make(),empty.Set(), empty.Map()); } 
  Q δ(Q q, Σ σ) { return q == q$ || !δ(σ).containsKey(q) ? q$ : δ(σ).get(q); } 
  //@formatter:on

  Map<Q, Q> δ(Σ σ) {
    if (δ.get(σ) == null)
      δ.put(σ, new LinkedHashMap<>());
    return δ.get(σ);
  }
  FSA(Q q0, Set<Q> ζ, Map<Σ, Map<Q, Q>> δ) {
    this.q0 = q0;
    this.ζ = ζ;
    this.δ = δ;
  }
  void δ(Map<Σ, Map<Q, Q>> δ) {
    for (Σ σ: δ.keySet())
      copy(δ(σ), δ.get(σ)); 
  }
  static void copy(Map<Q, Q> into, Map<Q, Q> from) {
    for (Q q: from.keySet()) 
      into.put(q, from.get(q));
  }
}