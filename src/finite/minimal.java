package finite;

import static java.util.stream.Collectors.toMap;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

import utils.empty;
import utils.set;

public enum minimal{
  ;
  static <Σ> FSA<Σ> FSA(FSA<Σ> ¢) { return encode(¢, partition(¢)); }
  private static <Σ> Set<Set<Q>> partition(FSA<Σ> a) {
    return a.new External() {
      Set<Set<Q>>       P = set.of(set.of(self().ζ), set.minus(self().Q, self().ζ));
      final Set<Set<Q>> W = set.of(set.of(self().ζ), set.minus(self().Q, self().ζ));
      { // Hoprcroft's algorithm, from Wikipedia
        while (!W.isEmpty()) {
          final Set<Q> A = set.pick(W);
          W.remove(A);
          for (final Σ σ : self().Σ) {
            final var         X    = X(A, σ);
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
      Set<Q> X(final Set<Q> A, final Σ σ) { //@formatter:on
        final Set<Q> $ = empty.Set();
        for (final Q q : self().Q) if (A.contains(self().δ(q, σ))) $.add(q);
        return $;
      }
    }.P;
  }
  static <Σ> FSA<Σ> encode(FSA<Σ> a, Set<Set<Q>> P) {
    return a.new External() {
      /** Data: A newly created state representing each p∈P */
      final Map<Set<Q>, Q> encoding    = P.stream().collect(toMap(λ -> λ, λ -> new Q()));
      /** Data: Equivalence class, p∈P, associated with recoded state */
      final Map<Q, Set<Q>> equivalence = equivalence();
      /** Auxiliary shortcut for decoding a newly created state */
      Q Q(Set<Q> ¢) { return encoding.get(¢); }
      /** Only service */
      FSA<Σ> minimize() {
        return FSA.<Σ>builder()//
            .q0(encode(self().q0))//
            .ζ(encode(self().ζ))//
            .Δ(mapEncode(self().Δ))//
            .build();
      }
      Map<Q, Map<Σ, Q>> mapEncode(Map<Q, Map<Σ, Q>> m) {
        return stream.ify(m).collect(toMap(x -> x.getKey(), x -> encode(x.getValue())));
      }
      Map<Σ, Q> encode(Map<Σ, Q> m) {
        return stream.ify(m).collect(toMap(x -> x.getKey(), x -> encode(x.getValue())));
      }
      Q encode(Q ¢) { return Q(equivalence.get(¢)); }
      Set<Q> encode(Set<Q> ¢) { return ¢.isEmpty() ? ¢ : set.of(encode(set.pick(¢))); }
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
    }.minimize();
  }
}