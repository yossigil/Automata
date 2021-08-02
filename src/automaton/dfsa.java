package automaton;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import utils.empty;

public enum dfsa {
  ;
  static <Σ> FSA<Σ> of(NFSA<Σ> ¢) {
    return ¢.new External() {
      final Set<? extends STATE<Σ>> ss = new DFS_LIST<STATE<Σ>>() {}.dfs(my().s0);
      Stream<? extends STATE<Σ>> ss() { return ss.stream().filter(s->s.valid()); }
      final Map<STATE<Σ>, Q> code = empty.Map();
      {
        ss().forEach(s -> code.put(s, new Q()));
      }
      FSA<Σ> FSA() {
        return FSA.<Σ>builder(q0())//
            .ζ(ζ()) //
            .Δ(map()) //
            .build();
      }
      Q encode(STATE<Σ> ¢) { return code.get(¢); }
      Q q0() { return encode(my().s0); }
      Map<Q, Map<Σ, Q>> map() {
        return ss().collect(toMap(s->encode(s),s ->map(s)));
      }
      Map<Σ, Q> map(STATE<Σ> ¢) {
        return my().Σ.stream().filter(σ->¢.δ(σ).valid()).collect(toMap(σ->σ, σ->encode(¢.δ(σ))));
      }
      Set<Q> ζ() { return ss().filter(s -> s.ζ()).map(s -> encode(s)).collect(toSet()); }
    }.FSA();
  }
}