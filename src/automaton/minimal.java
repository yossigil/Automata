package automaton;

import static java.util.stream.Collectors.toMap;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import utils.empty;
import utils.set;

enum minimal {
  ;
  static <Σ> FSA<Σ> FSA(FSA<Σ> ¢) { return encode(¢, partition(¢)); }
  private static <Σ> Set<Set<Q>> partition(FSA<Σ> a) {
    return a.new External() {
      Set<Set<Q>> P = set.of(set.copy(self().ζ), set.minus(self().Q, self().ζ));
      { // Hoprcroft's algorithm, from Wikipedia
        final Set<Set<Q>> W = set.of(set.copy(self().ζ), set.minus(self().Q, self().ζ));
        while (!W.isEmpty()) {
          final Set<Q> A = set.pick(W);
          W.remove(A);
          for (final Σ σ : self().Σ) {
            final Set<Q> X = X(A, σ);
            final Set<Set<Q>> newP = empty.Set();
            for (final Set<Q> Y : P) {
              final Set<Q> intersection = set.intersection(Y, X);
              final Set<Q> minus        = set.minus(Y, X);
              if (intersection.isEmpty() || minus.isEmpty()) {
                newP.add(Y);
                continue;
              }
              newP.add(intersection);
              newP.add(minus);
              if (W.contains(Y)) {
                W.remove(Y);
                W.add(intersection);
                W.add(minus);
              } else if (intersection.size() <= minus.size()) W.add(minus);
              else W.add(intersection);
            }
            P = newP;
          }
        }
      }
      /** let X be the set of states for which a transition on c leads to a state in A
       * 
       * @param A
       * @param σ
       * @return */
      Set<Q> X(final Set<Q> A, final Σ σ) { //@formatter:on
        final Set<Q> $ = empty.Set();
        for (final Q q : self().Q) if (A.contains(self().δ(q, σ))) $.add(q);
        return $;
      }
    }.P;
  }
  private static <Σ> FSA<Σ> encode(FSA<Σ> a, Set<Set<Q>> P) {
    return a.new External() {
      /** Data: A newly created state representing each p∈P */
      final Map<Set<Q>, Q> encoding    = P.stream().collect(toMap(λ -> λ, λ -> new Q()));
      /** Data: Equivalence class, p∈P, associated with recoded state */
      final Map<Q, Set<Q>> equivalence = equivalence();
      final FSA<Σ>         $           = FSA.<Σ>builder(encode(self().q0))               //
          .ζ(setEncode(self().ζ))                                                        //
          .Δ(mapEncode())                                                                //
          .build();
      Map<Q, Map<Σ, Q>> mapEncode() {
        return P.stream().filter(x -> !x.isEmpty()).map(s -> verify(s)).map(s -> set.pick(s)).map(q -> verify(q)).collect(toMap(x -> encode(x), x -> encode(self().Δ.get(x))));
      }
      Set<Q> verify(Set<Q> q) {
        assert q != null;
        assert  !q.isEmpty();
        return q;
      }
      Q verify(Q q) {
        assert q != null: P+"\n"+self();
        assert encode(q) != null;
        assert self().Δ.get(q) != null;
        assert q != null;
        return q;
      }
      Map<Σ, Q> encode(Map<Σ, Q> m) {
        Function<? super Entry<Σ, Q>, ? extends Σ> keyMapper   = x -> x.getKey();
        Function<? super Entry<Σ, Q>, ? extends Q> valueMapper = x -> encode(x.getValue());
        return stream.map(m).map(e -> {
          assert e != null;
          assert e.getKey() != null : "He";
          assert e.getValue() != null : "He";
          return e;
        }).//
        collect(toMap(keyMapper, valueMapper));
      }
      Q encode(Q ¢) { return encoding.get(equivalence.get(¢)); }
      Set<Q> setEncode(Set<Q> ¢) { return ¢.isEmpty() ? ¢ : set.of(encode(set.pick(¢))); }
      //@formatter:on
      Stream<Entry<Q, Set<Q>>> invert() {
        return P.stream().flatMap(s -> s.stream().map(q -> new SimpleEntry<>(q, s)));
      }
      Map<Q, Set<Q>> collect(Stream<Entry<Q, Set<Q>>> qss) {
        return qss.collect(toMap(x -> x.getKey(), x -> x.getValue()));
      }
      Map<Q, Set<Q>> equivalence() {
        return collect(invert());
      }
    }.$;
  }
}