package finite;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

class Q {
  static Q make() {
    return new Q();
  }
}

class FSA<Σ> {
  final Q q0;
  final Q q$ = Q.make();
  final Set<Q> ζ;
  final Map<Σ, Map<Q, Q>> δ;
  Set<Σ> Σ() { return δ.keySet(); }

  private Map<Q, Q> δ(Σ σ) {
    if (δ.get(σ) == null)
      δ.put(σ, new LinkedHashMap<>());
    return δ.get(σ);
  }

  Q δ(Q q, Σ σ) {
    return q == q$ || !δ(σ).containsKey(q) ? q$ : δ(σ).get(q);
  }

  void δ(Q from, Σ σ, Q to) {
    δ(σ).put(from, to);
  }

  void ζ(Q q) {
    ζ.add(q);
  }

  FSA() {
    this(Q.make(),empty.Set(), empty.Map());
  }

  FSA(Q q0, Set<Q> ζ, Map<Σ, Map<Q, Q>> δ) {
    this.q0 = q0;
    this.ζ = ζ;
    this.δ = δ;
  }
}
