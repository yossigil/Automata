package automaton;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

import utils.empty;
import utils.set;

enum minimal {
  ;
  static <Σ> FSA<Σ> FSA(FSA<Σ> ¢) {
    Set<Set<Q>> $ = partition(¢);
    $.toString();
    return encode(¢, $);
  }
  private static <Σ> Set<Set<Q>> partition(FSA<Σ> a) {
    return a.new External() {
      Set<Set<Q>> P = set.of(set.copy(self().ζ), rejects());
      {
        /** Almost verbatim implementation of Hoprcroft's algorithm, as drawn from
         * Wikipedia
         * {@link https://en.wikipedia.org/wiki/DFA_minimization#Hopcroft's_algorithm} */
        final Set<Set<Q>> W = set.copy(P);
        explain("Initially, P=%s, W=P=%s\n", P, W);
        while (!W.isEmpty()) {
          assert !W.contains(empty.Set());
          assert !P.contains(empty.Set());
          final Set<Q> A = set.pick(W);
          explain("Selected a set A=%s\n", A);
          assert A != null;
          assert !A.isEmpty();
          assert W.contains(A);
          W.remove(A);
          explain("Removed A from W, now is =%s\n", W);
          assert !W.contains(A);
          for (final Σ σ : self().Σ) {
            final Set<Q>      X    = X(A, σ);
            final Set<Set<Q>> newP = empty.Set();
            for (final Set<Q> Y : P) {
              assert !Y.isEmpty();
              final Set<Q> intersection = set.intersection(Y, X);
              final Set<Q> minus        = set.minus(Y, X);
              if (intersection.isEmpty() || minus.isEmpty()) {
                newP.add(Y);
                continue;
              }
              assert !intersection.isEmpty();
              assert !minus.isEmpty();
              newP.add(intersection);
              newP.add(minus);
              if (W.contains(Y)) {
                W.remove(Y);
                W.add(intersection);
                W.add(minus);
              } else //
                if (intersection.size() <= minus.size())//
                  W.add(minus);
              else//
                  W.add(intersection);
            }
            P = newP;
          }
        }
      }
      /** Set of <b>all</b> states that are not in {@link #ζ}, including, the special
       * {@link #REJECT} state. */
      Set<Q> rejects() {
        return set.union(set.of(Δ.REJECT), set.minus(self().Q, self().ζ));
      }
      private String explain(String format, Object ...os) {
        return String.format(format, os); 
       }
      /** @return the set X as in Wikipedia, defined as the set of states for which a
       *         transition on σ leads to a state in A */
      Set<Q> X(final Set<Q> A, final Σ σ) { //@formatter:on
        final Set<Q> $ = empty.Set();
        for (final Q q : self().Q) if (A.contains(self().δ(q, σ))) $.add(q);
        if (A.contains(Δ.REJECT)) 
          $.add(Δ.REJECT);
        return $;
      }
    }.P;
  }
  /** Returns the automaton obtained from the parameter, by unifying all states
   * that occur in the given partition. */
  private static <Σ> FSA<Σ> encode(FSA<Σ> a, Set<Set<Q>> P) {
    FSA<Σ> x = a.new External() {
      /** Data: A newly created state representing each p∈P; be ware that this
       * partition includes the (possibly singleton) set of states with the unique
       * {@link #Δ.REJECT} state; this set should not be encoded as a set in the newly
       * created automaton */
      final Map<Set<Q>, Q> encoding    = P.stream().collect(toMap(λ -> λ, λ -> new Q()));
      /** Data: Equivalence class, p∈P, associated with recoded state */
      final Map<Q, Set<Q>> equivalence = equivalence();
      {
        assert equivalence.get(Δ.REJECT) != null;
        assert equivalence.get(Δ.REJECT).contains(Δ.REJECT);
        assert encode(Δ.REJECT) != Δ.REJECT;
        encoding.put(equivalence.get(Δ.REJECT), Δ.REJECT);
        assert encode(Δ.REJECT) == Δ.REJECT;
      }
      final FSA<Σ> $ = FSA.<Σ>builder(encode(self().q0)) //
          .ζ(setEncode(self().ζ)) //
          .Δ(mapEncode()) //
          .build();
      Map<Q, Map<Σ, Q>> mapEncode() {
        return P.stream().map(s -> verify(s)).map(s -> set.pick(s)).map(q -> verify(q)).filter(q -> q != Δ.REJECT) //
            .collect(toMap(//
                x -> encode(x), //
                x -> encode(self().Δ.get(x)//
        )));
      }
      Set<Q> verify(Set<Q> ¢) {
        assert ¢ != null;
        assert !¢.isEmpty();
        return ¢;
      }
      Q verify(Q ¢) {
        return ¢;
      }
      Map<Σ, Q> encode(Map<Σ, Q> m) {
        assert m != null;
        return stream.map(m).map(e -> {
          assert e != null;
          assert e.getKey() != null : "Key" + e;
          assert e.getValue() != null : "Value" + e;
          return e;
        }).//
        collect(toMap(x -> x.getKey(), x -> encode(x.getValue())));
      }
      Q encode(Q ¢) { return encoding.get(equivalence.get(¢)); }
      Set<Q> setEncode(Set<Q> ¢) {
        return ¢.stream().map(q->encode(q)).collect(toSet());
      }
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
    if (!x.Q.contains(x.q0)) assert false;
    return x;
  }
}