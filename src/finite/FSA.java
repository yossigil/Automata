package finite;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

class Q {
  static Q make() {return new Q(); }
};


class FSA<Σ> {
  Q q0 = Q.make();
  final Set<Q> ζ;
  final Σ any = null;
  final Map<Σ, Map<Q, Q>> δ;

  void q0(Q q0) {
    this.q0 = q0;
  }
  void ζ(Q q) {
    ζ.add(q);
  }
  void δ(Q from, Σ σ, Q to) {
    δ(σ).put(from, to);
  }
  Map<Q, Q> δ(Σ σ) {
    if (δ.get(σ) == null)
      δ.put(σ, new LinkedHashMap<>());
    return δ.get(σ);
  }

  Q δ(Q q, Σ σ) {
    return δ(σ).get(q);
  }
  FSA() {
    ζ = new LinkedHashSet<>();
    δ = new LinkedHashMap<>();
  }
  FSA(Set<Q> ζ, Map<Σ, Map<Q, Q>> δ) {
    this.ζ = ζ;
    this.δ = δ;
  }
}
