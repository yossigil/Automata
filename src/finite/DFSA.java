package finite;

import java.util.Map;
import java.util.Set;

public class DFSA<Σ> extends FSA<Σ> {
  public DFSA(Q q, Set<Q> ζ, Map<Σ, Map<Q, Q>> δ) {
    super(q, ζ, δ);
  }
  boolean run(Iterable<Σ> w) {
    var q = q0;
    for (var σ : w)
      q = δ(q, σ);
    return ζ.contains(q);
  }
  String TikZ() {
    return new Object() {
// @formatter:off
      String render() { return wrap(traverse()); } 
      String wrap(String s) { return "\\graph{\n" + s + "};\n"; } 
// @formatter:on
      String traverse() {
        dfs(q -> Q(q));
        String $ = "";
        for (Σ σ : Σ()) {
          Map<Q, Q> δσ = δ(σ);
          for (Q from : δσ.keySet())
            $ += "\t " + Q(from) + "->[\"" + σ + "\"] " + Q(δσ.get(from)) + ";\n";
        }
        return $;
      }

      int ordinal = 0;
      Map<Q, Integer> n = empty.Map();

      String Q(Q q) {
        if (n.get(q) == null)
          n.put(q, ordinal++);
        return "\"$q_{" + n.get(q) + "}$\"" + properties(q);
      }

      private String properties(Q q) {
        if (q == q0 && ζ.contains(q))
          return "[initial,accept]";
        if (q == q0)
          return "[initial]";
        if (ζ.contains(q))
          return "[accept]";
        return "";
      }
    }.render();
  }
}
