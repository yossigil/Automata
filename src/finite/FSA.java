package finite;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

class Q {
  static Q make() {
    return new Q();
  }
}

class FSA<Σ, Self extends FSA<Σ, Self>> {
  //@formatter:off
  Self selfie() { return (Self) null; }
  final Q q0;
  final Q q$ = Q.make();
  final Set<Q> ζ;
  final Map<Σ, Map<Q, Q>> δ;
  Set<Σ> Σ() { return δ.keySet(); }
  Self δ(Q from, Σ σ, Q to) { δ(σ).put(from, to);return this.selfie();  }
  Self ζ(Q q) { ζ.add(q); return this.selfie(); } 
  Self ζ(Set<Q> qs) { for (Q q: qs) ζ.add(q); return this.selfie(); } 
  FSA() { this(Q.make(),empty.Set(), empty.Map()); } 
  Q δ(Q q, Σ σ) { return q == q$ || !δ(σ).containsKey(q) ? q$ : δ(σ).get(q); } 
  //@formatter:on

  private Map<Q, Q> δ(Σ σ) {
    if (δ.get(σ) == null)
      δ.put(σ, new LinkedHashMap<>());
    return δ.get(σ);
  }

  FSA(Q q0, Set<Q> ζ, Map<Σ, Map<Q, Q>> δ) {
    this.q0 = q0;
    this.ζ = ζ;
    this.δ = δ;
  }
}
