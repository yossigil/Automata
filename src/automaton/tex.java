package automaton;

import java.util.Set;

import utils.empty;

import static java.util.stream.Collectors.joining;

import java.util.Map;
import java.util.Map.Entry;

public enum tex {
  ;
  static Map<Object, String> memo = empty.Map();
  static String show(Object ¢) { return "$" + info(¢) + "$"; }
  static String show(Set<?> os) { return String.format("$\\{ %s \\}_{%d}$", tex.elements(os), os.size()); }
  static String show(Map<?, Map<?, ?>> m) {
    return String.format("$\\{ %s \\}_{%d}$", tex.elements(m), m.size());
  }
  static String elements(Map<?, Map<?, ?>> m) { // TODO Auto-generated method stub
    return stream.ify(m).limit(5).map(x -> map(x)).collect(joining(", "));
  }
  static String map(Entry<?, Map<?, ?>> m) {
    Map<?, ?> $ = m.getValue();
    return String.format("$%s\\Rightarrow\\{ %s \\}_{%d}$", info(m.getKey()), tex.xxx($), $.size());
  }
  static String xxx(Map<?, ?> map) {
    return stream.ify(map).limit(5).map(e -> info(e.getKey()) + "\\rightarrow" + e.getValue()).collect(joining(", "));
  }
  static String elements(Set<?> s) {
    return s.stream().limit(7).map(x -> info(x)).collect(joining(","));
  }
  static String info(Object x) {
    if (memo.containsKey(x)) return memo.get(x);
    return x + "";
  }
}
