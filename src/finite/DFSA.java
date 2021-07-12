package finite;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import utils.empty;
import utils.set;

public class DFSA<Σ> extends FSA<Σ> {
  //@formatter:off
  DFSA(final Q q0, final Set<Q> ζ, final Map<Σ, Map<Q, Q>> Δ) { super(q0, ζ, Δ); }
  boolean run(final Iterable<Σ> w) {
    q0();
    for (final var σ : w) feed(σ);
    return ζ();
  }

  public DFSA<Σ> minimize() {
    return new Object() { //@formatter:off
      DFSA<Σ> DFSA() { return new DFSA<>(q0(), ζ(), δ()); }
      Q q0() { return Q(Q(q0)); }
      Set<Q> ζ() { return set.of(Q(Q(set.pick(ζ)))); }
      Set<Q> Q(final Q ¢) { return container.get(¢); }
      Map<Set<Q>, Q> code() { final Map<Set<Q>,Q> $ = empty.Map(); for (final var s: P) $.put(s, new Q()); return $; }
      Q Q(final Set<Q> ¢) { return code.get(¢); }
      final Set<Set<Q>> P = Hopcroft();
      final Map<Set<Q>, Q> code = code();
      final Map<Q, Set<Q>> container = container();
//@formatter:on
      Map<Σ, Map<Q, Q>> δ() {
        return Σ().stream().collect(Collectors.toMap(Function.identity(), λ -> δ(λ)));
      }
      Map<Q, Q> δ(final Σ σ) {
        final Map<Q, Q> $ = empty.Map();
        for (final var s : P) $.put(Q(s), Q(Q(DFSA.this.δ(set.pick(s), σ))));
        return $;
      }
      Map<Q, Set<Q>> container() {
        final Map<Q, Set<Q>> $ = empty.Map();
        for (final Set<Q> s : P) for (final Q q : s) $.put(q, s);
        return $;
      }
      Set<Set<Q>> Hopcroft() {
        Set<Set<Q>>       $ = set.of(set.of(ζ), set.minus(Q(), ζ));
        final Set<Set<Q>> W = set.of(set.of(ζ), set.minus(Q(), ζ));
        while (!W.isEmpty()) {
          final Set<Q> A = set.pick(W);
          W.remove(A);
          for (final Σ σ : Σ()) {
            final var         X    = X(A, σ);
            final Set<Set<Q>> new$ = empty.Set();
            for (final Set<Q> Y : $) {
              final Set<Q> intersection = set.intersection(Y, X);
              final Set<Q> minus        = set.minus(Y, X);
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
              } else if (intersection.size() <= minus.size()) W.add(minus);
              else W.add(intersection);
            }
            $ = new$;
          }
        }
        return $;
      }
      Set<Q> X(final Set<Q> A, final Σ σ) {
        final Set<Q> $ = empty.Set();
        for (final Q q : Q()) if (A.contains(DFSA.this.δ(q, σ))) $.add(q);
        return $;
      }
      Set<Q> Q() {
        return DFSA.this.Q();
      }
    }.DFSA();
  }
  /* Dense: //@formatter:off */
    public boolean run(final String ¢) { return run(¢.toCharArray()); }
    boolean run(final char[] cs) {
      return DFSA.this.run(() -> new Iterator<>() {
        int i;
        @Override public boolean hasNext() { return i < cs.length; }
        @Override
        @SuppressWarnings("unchecked") public Σ next() {
          return (Σ) (Character) cs[i++];
        }
      });
    }
}
