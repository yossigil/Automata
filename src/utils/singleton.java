package utils;

import java.util.Set;

enum singleton {
  ;
  static <T> Set<T> Set(T ¢) {
    Set<T> $ = empty.Set();
    $.add(¢);
    return $;
  }
}