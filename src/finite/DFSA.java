package finite;

import java.util.Map;
import java.util.Set;

class xDFSA<Σ> extends DFSA<Σ,xDFSA<Σ>> {
  public xDFSA(Q q, Set<Q> ζ, Map<Σ, Map<Q, Q>> δ) {
    super(q, ζ, δ);
  }
}
public class DFSA<Σ, Self extends DFSA<Σ,Self>> extends FSA<Σ, Self> {
  public DFSA(Q q, Set<Q> ζ, Map<Σ, Map<Q, Q>> δ) {
    super(q, ζ, δ);
  }
  boolean run(Iterable<Σ> w) {
    var q = q0;
    for (var σ : w)
      q = δ(q, σ);
    return ζ.contains(q);
  }
}
