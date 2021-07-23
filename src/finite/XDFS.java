package finite;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public interface XDFS<V> {
  default V visit(final V ¢) { return ¢; }
  Collection<V> neighbours(V v);
  default Set<V> dfs(final V v) {
    final Set<V> $ = new LinkedHashSet<>();
    new Object() {
      void go(final V v) {
        if ($.contains(v)) return;
        $.add(visit(v));
        for (final var n : neighbours(v)) go(n);
      }
    }.go(v);
   return $;
  }
}
