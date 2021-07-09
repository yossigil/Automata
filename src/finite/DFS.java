package finite;

import java.util.Set;

import utils.empty;

@FunctionalInterface interface DFS<Vertex extends V<Vertex>> {
  void visit(Vertex v);

  default Set<Vertex> dfs(Vertex v) {
    final Set<Vertex> $ = empty.Set();
    new Object() { //@formatter:off 
      boolean mint(Vertex ¢) { return $.add(¢); }
      void recurse(Vertex ¢) { visit(¢); for (var n : ¢.neighbours()) dfs(n); }
      void dfs(Vertex ¢) { if (mint(¢)) recurse(¢); }
    }.dfs(v);
    return $;
  }
}