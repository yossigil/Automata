package finite;

import java.util.LinkedHashSet;
import java.util.Set;

import utils.empty;

interface XDFS<V> {
  default V v(V v) {
    return v;
  }

  Set<V> n(V v);

  default Set<V> dfs(V v) {
    Set<V> $ = new LinkedHashSet<>();
    new Object() {
      void dfs(V v) {
        if (!$.contains(v)) {
          $.add(v(v));
          for (var n : n(v))
            dfs(n);
        }
      }
    }.dfs(v);
    return $;
  }
}

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

interface V<Self> {
  Iterable<Self> neighbours();
}
