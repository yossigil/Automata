package finite;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class DFSA<Σ> extends FSA<Σ> {
  //@formatter:off
  DFSA(Q q0, Set<Q> ζ, Map<Σ, Map<Q, Q>> Δ) { super(q0, ζ, Δ); }
  //@formatter:on
  final class Renderer {
   // @formatter:off
    private Set<Q> seen = empty.Set();
    private int ordinal = 0;
    private Map<Q, Integer> n = empty.Map();
    String render() { return wrap(traverse()); }
    String wrap(String s) { return "graph{\n" + s + "}\n"; }
    private boolean seen(Q q) { return seen.contains(q); }
    // @formatter:on

    String traverse() {
      dfs(q -> Q(q));
      String $ = "";
      for (Q from : DFSA.this.Q()) {
        if (q == q$)
          continue;
        Set<Σ> seen = empty.Set();
        for (final Σ σ : Σ()) {
          if (seen.contains(σ))
            continue;
          final Q to = δ(from, σ);
          if (to == q$)
            continue;
          Set<Σ> same = empty.Set();
          for (Σ σ1 : Σ())
            if (δ(from, σ1) == to) {
              same.add(σ1);
              seen.add(σ1);
            }
          $ += "\t " + Q(from) + "->[\"" + labels(same) + "\"";
          if (to == from)
            $ += ", loop";
          $ += "] " + Q(to) + ";\n";
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

    String Q(Q q) {
      if (n.get(q) == null)
        n.put(q, ordinal++);
      return "\"$q_{" + n.get(q)  + "}$\"" + properties(q);
    }

    private String properties(Q q) {
      if (seen(q))
        return "";
      if (q == q0 && ζ.contains(q))
        return "[initial,accept]";
      if (q == q0)
        return "[initial]";
      if (ζ.contains(q))
        return "[accept]";
      return "";
    }
  }

  boolean run(Iterable<Σ> w) {
    q0();
    for (var σ : w)
      feed(σ);
    return ζ();
  }

  String TikZ() {
    return new Renderer().render();
  }

  DFSA<Σ> minimize() {
    return new Object() { //@formatter:off 
      DFSA<Σ> DFSA() { return new DFSA<Σ>(q0(), ζ(), δ()); }  
      Q q0() { return Q(Q(q0)); } 
      Set<Q> ζ() { return set.of(Q(Q(set.pick(ζ)))); }
      Set<Q> Q(Q q) { return container.get(q); }
      Map<Set<Q>, Q> code() { Map<Set<Q>,Q> $ = empty.Map(); for (var s: P) $.put(s, new Q()); return $; }
      Q Q(Set<Q> s) { return code.get(s); } 
      final Set<Set<Q>> P = Hopcroft(); 
      final Map<Set<Q>, Q> code = code();
      final Map<Q, Set<Q>> container = container(); 
//@formatter:on 
      Map<Σ, Map<Q, Q>> δ() {
        Map<Σ, Map<Q, Q>> $ = empty.Map();
        for (var σ : Σ())
          $.put(σ, δ(σ));
        return $;
      }

      Map<Q, Q> δ(Σ σ) {
        Map<Q, Q> $ = empty.Map();
        for (var s : P)
          $.put(Q(s), Q(Q(DFSA.this.δ(set.pick(s), σ))));
        return $;
      }

      private Map<Q, Set<Q>> container() {
        Map<Q, Set<Q>> $ = empty.Map();
        for (Set<Q> s: P)
          for (Q q: s)
            $.put(q,s);
        return $;
      }
      Set<Set<Q>> Hopcroft() {
        Set<Set<Q>> P = set.of(set.of(ζ), set.minus(Q(), ζ));
        final Set<Set<Q>> W = set.of(set.of(ζ), set.minus(Q(), ζ));
        while (!W.isEmpty()) {
          final Set<Q> A = set.pick(W);
          W.remove(A);
          for (Σ σ : Σ()) {
            final Set<Q> X = X(A, σ);
            Set<Set<Q>> newP = empty.Set();
            for (Set<Q> Y : P) {
              final Set<Q> intersection = set.intersection(Y, X);
              final Set<Q> minus = set.minus(Y, X);
              if (intersection.isEmpty() || minus.isEmpty())
                newP.add(Y);
              else {
                newP.add(intersection);
                newP.add(minus);
              }
              if (W.contains(Y)) {
                W.remove(Y);
                W.add(intersection);
                W.add(minus);
              } else {
                if (intersection.size() <= minus.size())
                  W.add(minus);
              }
            }
            P = newP;
          }
        }
        return P;
      }

      Set<Q> X(Set<Q> A, Σ σ) {
        Set<Q> X = empty.Set();
        for (Q q : Q())
          if (A.contains(DFSA.this.δ(q, σ)))
            X.add(q);
        return X;
      }

      Set<Q> Q() {
        return DFSA.this.Q();
      }

    }.DFSA();

  }

    /* Dense: //@formatter:off */
    boolean run(String s) { return run(s.toCharArray()); }
    boolean run(char[] cs) {
      return DFSA.this.run(new Iterable<Σ>() {
        @Override public Iterator<Σ> iterator() {
          return new Iterator<Σ>() {
            int i = 0;
            @Override public boolean hasNext() { return i < cs.length; }
            @SuppressWarnings("unchecked") @Override public Σ next() { return (Σ) (Character) cs[i++]; }
          };
         }
      });
    }

  /**
   * <pre>
  P := {F, Q \ F};
  W := {F, Q \ F};
  while (W is not empty) do
      choose and remove a set A from W
      for each c in Σ do
           let X be the set of states for which a transition on c leads to a state in A
           for each set Y in P for which X ∩ Y is nonempty and Y \ X is nonempty do
                replace Y in P by the two sets X ∩ Y and Y \ X
                if Y is in W
                     replace Y in W by the same two sets
                else
                     if |X ∩ Y| <= |Y \ X|
                          add X ∩ Y to W
                     else
                          add Y \ X to W
           end;
      end;
  end;
   * </pre>
   */

}
