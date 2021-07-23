package utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public enum set {
  ;
  @SafeVarargs public static <T> Set<T> of(final T... ¢) {
    final Set<T> $ = empty.Set();
    Collections.addAll($, ¢);
    return $;
  }
  public static <T> T pick(final Iterable<T> ts) {
    for (final T t : ts) return t;
    return null;
  }
  public static <T> Set<T> of(final Iterable<T> ts) {
    final Set<T> $ = empty.Set();
    for (final T t : ts) $.add(t);
    return $;
  }
  public static <T> Set<T> of(final Set<T> ¢) {
    final Set<T> $ = empty.Set();
    $.addAll(¢);
    return $;
  }
  public static <T> Set<T> minus(final Iterable<T> qs1, final Collection<T> sq2) {
    final Set<T> $ = set.of(qs1);
    $.removeAll(sq2);
    return $;
  }
  public static <T> Set<T> intersection(final Collection<T> s1, Collection <T> s2) {
    final Set<T> $ = set.of(s1);
    $.retainAll(s2);
    return $;
  }
  public static <T> Set<T> union(final Collection<T> s1, final Collection<T> s2) {
    final Set<T> $ = set.of(s1);
    $.addAll(s2);
    return $;
  }
  @SafeVarargs public static <T> Set<T> union(final Collection<T> ts, final Collection<T> ...tss) {
    final Set<T> $ = set.of(ts);
    for (Collection<T> s: tss)
      $.addAll(s);
    return $;
  }
}