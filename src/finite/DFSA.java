package finite;

import java.util.Map;
import java.util.Set;

class DFSA<Σ> extends FSA<Σ> {
  // @formatter: off
  DFSA(Q q0, Set<Q> ζ, Map<Σ, Map<Q, Q>> Δ) { super(q0, ζ, Δ); }
  boolean run(Iterable<Σ> w) { q0(); for (var σ : w) feed(σ); return ζ(); }  
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
