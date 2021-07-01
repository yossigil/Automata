package finite;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@FunctionalInterface interface DFS<Vertex extends V<Vertex>> {
  void visit(Vertex t);

  default Set<Vertex> dfs(Vertex v) {
    Set<Vertex> $ = new LinkedHashSet<>();
    new Object() {
      void dfs(Vertex v) {
        if (!$.contains(v)) {
          visit(v);
          $.add(v);
          for (var n : v.neighbours())
            dfs(n);
        }
      }
    }.dfs(v);
    return $;
  }

}

interface V<Self> {
  Iterable<Self> neighbours();
}
