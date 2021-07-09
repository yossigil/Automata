package finite;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import utils.empty;
import utils.set;

class DFSA<Σ> extends FSA<Σ> {
  //@formatter:off
  DFSA(Q q0, Set<Q> ζ, Map<Σ, Map<Q, Q>> Δ) { super(q0, ζ, Δ); }
  String TikZ() { return new TikZ().render(); }
  //@formatter:on

  boolean run(Iterable<Σ> w) {
    q0();
    for (var σ : w)
      feed(σ);
    return ζ();
  }

  DFSA<Σ> minimize() {
    return new Object() { //@formatter:off 
      DFSA<Σ> DFSA() { return new DFSA<Σ>(q0(), ζ(), δ()); }  
      Q q0() { return Q(Q(q0)); } 
      Set<Q> ζ() { return set.of(Q(Q(set.pick(ζ)))); }
      Set<Q> Q(Q ¢) { return container.get(¢); }
      Map<Set<Q>, Q> code() { Map<Set<Q>,Q> $ = empty.Map(); for (var s: P) $.put(s, new Q()); return $; }
      Q Q(Set<Q> ¢) { return code.get(¢); } 
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

      Map<Q, Set<Q>> container() {
        Map<Q, Set<Q>> $ = empty.Map();
        for (Set<Q> s : P)
          for (Q q : s)
            $.put(q, s);
        return $;
      }

      Set<Set<Q>> Hopcroft() {
        Set<Set<Q>> $ = set.of(set.of(ζ), set.minus(Q(), ζ));
        final Set<Set<Q>> W = set.of(set.of(ζ), set.minus(Q(), ζ));
        while (!W.isEmpty()) {
          final Set<Q> A = set.pick(W);
          W.remove(A);
          for (Σ σ : Σ()) {
            final Set<Q> X = X(A, σ);
            Set<Set<Q>> new$ = empty.Set();
            for (Set<Q> Y : $) {
              final Set<Q> intersection = set.intersection(Y, X);
              final Set<Q> minus = set.minus(Y, X);
              if (intersection.isEmpty() || minus.isEmpty()) {
                new$.add(Y);
                continue;
              }
              new$.add(intersection);
              new$.add(minus);
              if (W.contains(Y)) {
                W.remove(Y);
                W.add(intersection);
                W.add(minus);
              } else if (intersection.size() <= minus.size())
                W.add(minus);
              else
                W.add(intersection);
            }
            $ = new$;
          }
        }
        return $;
      }

      Set<Q> X(Set<Q> A, Σ σ) {
        Set<Q> $ = empty.Set();
        for (Q q : Q())
          if (A.contains(DFSA.this.δ(q, σ)))
            $.add(q);
        return $;
      }

      Set<Q> Q() {
        return DFSA.this.Q();
      }

    }.DFSA();

  }

  /* Dense: //@formatter:off */
    boolean run(String ¢) { return run(¢.toCharArray()); }
    boolean run(char[] cs) {
      return DFSA.this.run(new Iterable<Σ>() {
        @Override public Iterator<Σ> iterator() {
          return new Iterator<Σ>() {
            int i;
            @Override public boolean hasNext() { return i < cs.length; }
            @Override
            @SuppressWarnings("unchecked") public Σ next() {
              return (Σ) (Character) cs[i++];
            }
          };
         }
      });
    }
}
