package utils;

import java.util.Set;

enum singleton {
  ;
  static <T> Set<T> Set(final T ¢) {
    final Set<T> $ = empty.Set();
    $.add(¢);
    return $;
  }
}