package finite;

import java.util.LinkedHashSet;
import java.util.Set;

interface XDFS<V> {
  default V v(V v) {
    return v;
  }

  Set<V> n(V v);

  default Set<V> dfs(V v) {
    Set<V> $ = new LinkedHashSet<>();
    new Object() {
      void dfs(V v) {
        if ($.contains(v))
          return;
        $.add(v(v));
        for (var n : n(v))
          dfs(n);
      }
    }.dfs(v);
    return $;
  }
}

interface V<Self> {
  Iterable<Self> neighbours();
}
