package finite;

import java.util.Set;

enum singleton {
  ;
  static <T> Set<T> Set(T t) {
    Set<T> $ = empty.Set();
    $.add(t);
    return $;
  }
}