package finite;

import java.util.Set;

import utils.empty;

@FunctionalInterface interface DFS<V extends Vertex<V>> {
  void visit(V v);
  default Set<V> dfs(V v) {
    final Set<V> $ = empty.Set();
    new Object() { //@formatter:off 
      boolean mint(V ¢) { return $.add(¢); }
      void recurse(V ¢) { visit(¢); for (var n : ¢.neighbours()) dfs(n); }
      void dfs(V ¢) { if (mint(¢)) recurse(¢); }
    }.dfs(v);
    return $;
  }
}