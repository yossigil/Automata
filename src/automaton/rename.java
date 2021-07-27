package automaton;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.util.Map;
import java.util.Set;

enum rename {
  ;
  static <Σ> NFSA<Σ> of(NFSA<Σ> ¢) {
    return ¢.new External() {
      final Map<Q, Q> encoding = self().Q.stream().collect(toMap(λ -> λ, λ -> new Q()));
      Q encode(Q ¢) { return encoding.get(¢); }
      final NFSA<Σ> $ = NFSA.<Σ>builder(encode(self().q0)).ζ(encode(self().ζ)).Δ(mapEncode(self().Δ)).ε(encodeMapOfSets(self().ε))
          .build();
      Map<Q, Map<Σ, Q>> mapEncode(Map<Q, Map<Σ, Q>> m) {
        return stream.map(m).collect(toMap(x -> encode(x.getKey()), x -> encode(x.getValue())));
      }
      Map<Q, Set<Q>> encodeMapOfSets(Map<Q, Set<Q>> ¢) {
        return stream.map(¢).collect(toMap(x -> encode(x.getKey()), x -> encode(x.getValue())));
      }
      Map<Σ, Q> encode(Map<Σ, Q> m) {
        return stream.map(m).collect(toMap(x -> x.getKey(), x -> encode(x.getValue())));
      }
      Set<Q> encode(Set<Q> ¢) {
        return ¢.stream().map(x -> encode(x)).collect(toSet());
      }
    }.$;
  }
}