package utils;

import java.util.Set;

public enum set {
  ;
  @SafeVarargs public static <T> Set<T> of(T... ts) {
    Set<T> $ = empty.Set();
    for (T t : ts)
      $.add(t);
    return $;
  }

  public static <T> T pick(Iterable<T> ts) {
    for (T t : ts)
      return t;
    return null;
  }

  public static <T> Set<T> of(Iterable<T> ts) {
    Set<T> $ = empty.Set();
    for (T t : ts)
      $.add(t);
    return $;
  }

  public static <T> Set<T> minus(Iterable<T> qs1, Set<T> sq2) {
    Set<T> $ = set.of(qs1);
    $.removeAll(sq2);
    return $;
  }

  public static <T> Set<T> intersection(Set<T> s1, Set<T> s2) {
    Set<T> $ = set.of(s1);
    $.retainAll(s2);
    return $;
  }

  public static <T> Set<T> union(Set<T> s1, Set<T> s2) {
    Set<T> $ = set.of(s1);
    $.addAll(s2);
    return $;
  }
}