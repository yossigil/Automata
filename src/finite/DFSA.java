package finite;

import java.util.Map;
import java.util.Set;

public class DFSA<Σ> extends FSA<Σ> {
  public DFSA(Set<Q> ζ, Map<Σ, Map<Q, Q>> δ) {
    super(ζ, δ);
  }
  boolean run(Iterable<Σ> w) {
    var q = q0;
    for (var σ : w)
      q = δ(q, σ);
    return ζ.contains(q);
  }
}
