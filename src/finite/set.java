package finite;

import java.util.Set;

enum set {
  ;
  @SafeVarargs static <T> Set<T> of(T... ts) {
    Set<T> $ = empty.Set();
    for (T t : ts)
      $.add(t);
    return $;
  }

  static <T> T pick(Iterable<T> ts) {
    for (T t : ts)
      return t;
    return null;
  }

  static <T> Set<T> of(Iterable<T> ts) {
    Set<T> $ = empty.Set();
    for (T t : ts)
      $.add(t);
    return $;
  }

  static <T> Set<T> minus(Iterable<T> qs1, Set<T> sq2) {
    Set<T> $ = set.of(qs1);
    $.removeAll(sq2);
    return $;
  }

  static <T> Set<T> intersection(Set<T> s1, Set<T> s2) {
    Set<T> $ = set.of(s1);
    $.retainAll(s2);
    return $;
  }

  static <T> Set<T> union(Set<T> s1, Set<T> s2) {
    Set<T> $ = set.of(s1);
    $.addAll(s2);
    return $;
  }
}