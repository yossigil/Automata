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
      final Set<? extends STATE<Σ>> ss = new DFS<STATE<Σ>>() {}.dfs(This().s0);
      Stream<? extends STATE<Σ>> ss() { return ss.stream(); }
      final Map<STATE<Σ>, Q> code = empty.Map();
      {
        for (final var s : ss) code.put(s, new Q());
      }
      FSA<Σ> FSA() {
        return FSA.<Σ>builder(q0())//
            .ζ(ζ()) //
            .Δ(map()) //
            .build();
      }
      Q encode(STATE<Σ> ¢) { return code.get(¢); }
      Q q0() { return encode(This().s0); }
      Map<Q, Map<Σ, Q>> map() {
        return ss().collect(toMap(s->encode(s),s ->map(s)));
      }
      Map<Σ, Q> map(STATE<Σ> ¢) {
        return This().Σ.stream().collect(toMap(σ->σ, σ->encode(¢.δ(σ))));
      }
      Set<? extends Q> ζ() { return ss().filter(s -> s.ζ()).map(s -> encode(s)).collect(toSet()); }
    }.FSA();
  }
}