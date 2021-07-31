package automaton;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.util.Map;
import java.util.Set;

enum refresh {
  ;
  static <Σ> FSA<Σ> of(FSA<Σ> ¢) {
    return ¢.new External() {
      final Map<Q, Q> encoding = my().Q.stream().collect(toMap(λ -> λ, λ -> new Q()));
      Q encode(Q ¢) { return encoding.get(¢); }
      final NFSA<Σ> $ = NFSA.<Σ>builder(encode(my().q0)).ζ(encode(my().ζ)).Δ(mapEncode(my().Δ)).build();
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
  static <Σ> NFSA<Σ> of(NFSA<Σ> ¢) {
    return ¢.new External() {
      final Map<Q, Q> encoding = my().Q.stream().collect(toMap(λ -> λ, λ -> new Q()));
      Q encode(Q ¢) { return encoding.get(¢); }
      final NFSA<Σ> $ = NFSA.<Σ>builder(encode(my().q0)).ζ(encode(my().ζ)).Δ(mapEncode(my().Δ))
          .ε(encodeMapOfSets(my().ε)).build();
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