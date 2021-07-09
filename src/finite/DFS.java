package finite;

import java.util.Set;

import utils.empty;

@FunctionalInterface interface DFS<Vertex extends V<Vertex>> {
  void v(Vertex t);

  default Set<Vertex> dfs(Vertex v) {
    Set<Vertex> $ = empty.Set(); 
    new Object() { 
      void dfs(Vertex v) {
        if (!$.contains(v)) {
          v(v);
          $.add(v);
          for (var n : v.neighbours())
            dfs(n);
        }
      }
    }.dfs(v);
    return $;
  }

}