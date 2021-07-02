package finite;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

enum empty {
  ;
  static<T> Set<T> Set() { return new HashSet<>(); }
  static<F,T> Map<F,T> Map() { return new HashMap<>(); }
}