package automaton;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import utils.empty;

public enum tex {
  ;
  private static final int   ETC  = 7;
  static Map<Object, String> memo = empty.Map();
  static String show(Object ¢) { return "$" + info(¢) + "$"; }
  static String show(Set<?> os) {
    if (os.isEmpty()) return "$\\emptyset$";
    if (os.size() <= ETC) return String.format("$\\{ %s \\}$", tex.elements(os));
    return String.format("$\\{ %s,\\ldots_{+%d} \\}$", tex.elements(os), os.size() - ETC);
  }
  static <Σ> String show(Δ<Σ> fsa) {
    Set<Entry<Q, Entry<Σ, Q>>> δ = fsa.δ().collect(toSet());
    if (δ.isEmpty()) return "$\\emptyset$";
    if (δ.size() <= ETC) return String.format("$\\{ %s \\}$", tex.maps(δ));
    return String.format("$\\{ %s,\\ldots_{+%d} \\}$", tex.maps(δ), δ.size() - ETC);
  }
  static <Σ> String maps(Set<Entry<Q, Entry<Σ, Q>>> δ) {
    return δ.stream().limit(ETC).map(e -> String.format("%s\\stackrel{%s}{\\rightarrow}%s", info(e.getKey()),
        info(e.getValue().getKey()), info(e.getValue().getValue()))).collect(joining(","));
  }
  static String map(Entry<?, Map<?, ?>> ¢) {
    Map<?, ?> $ = ¢.getValue();
    return String.format("$%s\\Rightarrow\\{ %s \\}_{%d}$", info(¢.getKey()), tex.xxx($), $.size());
  }
  static String xxx(Map<?, ?> m) {
    return stream.map(m).limit(5).map(e -> info(e.getKey()) + "\\rightarrow" + e.getValue()).collect(joining(", "));
  }
  static String elements(Set<?> os) {
    return os.stream().limit(ETC).map(x -> info(x)).collect(joining(","));
  }
  static String info(Object x) {
    if (memo.containsKey(x)) return memo.get(x);
    return x + "";
  }
  static <Σ> String showMapSet(Map<Q, Set<Q>> ¢) {
    if (¢.isEmpty()) return "$\\emptyset$";
    if (¢.size() <= ETC) return String.format("$\\{ %s \\}$", tex.mapSet(¢));
    return String.format("$\\{ %s,\\ldots_{+%d} \\}$", tex.mapSet(¢), ¢.size() - ETC);
  }
  static String mapSet(Map<Q, Set<Q>> m) {
    return stream.map(m).filter(e -> !e.getValue().isEmpty()).map(e -> process(e)).collect(joining(","));
  }
  static String process(Entry<Q, Set<Q>> qs) {
    return String.format("%s \\rightarrow \\{ %s \\}", //
        info(qs.getKey()), qs.getValue().stream().map(x -> info(x)).collect(joining(",")));
  }
}
