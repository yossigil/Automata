package finite;

import java.util.Map;
import java.util.Set;

class DFSA<Σ> extends FSA<Σ> {
  // @formatter: off
  DFSA(Q q0, Set<Q> ζ, Map<Σ, Map<Q, Q>> Δ) {
    super(q0, ζ, Δ);
  }

  boolean run(Iterable<Σ> w) {
    q0();
    for (var σ : w)
      feed(σ);
    return ζ();
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
        for (Q from : DFSA.this.Q()) {
          Set<Σ> seen = empty.Set();
          Set<Σ> set = empty.Set();
          for (Σ σ : Σ()) {
            if (seen.contains(σ))
              continue;
            final Q to = δ(from, σ);
            for (Σ σ1 : Σ())
              if (δ(from, σ) == to) {
                set.add(σ1);
                seen.add(σ1);
              }
            $ += "\t " + Q(from) + "->[\"" + labels(set) + "\"] " + Q(to) + ";\n";
            set.clear();
          }
        }
        return $;
      }

      private String labels(Set<Σ> σs) {
        String $ = "";
        boolean special = true;
        for (Σ σ : σs) {
          if (special)
            special = false;
          else
            $ += ", ";
          $ += "" + σ;
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
