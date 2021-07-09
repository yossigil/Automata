package finite;

import java.util.LinkedHashSet;
import java.util.Set;

interface XDFS<V> {
  default V v(final V ¢) {
    return ¢;
  }

  Set<V> n(V v);

  default Set<V> dfs(final V v) {
    final Set<V> $ = new LinkedHashSet<>();
    new Object() {
      void dfs(final V v) {
        if ($.contains(v)) return;
        $.add(v(v));
        for (final var n : n(v)) dfs(n);
      }
    }.dfs(v);
    return $;
  }
}
