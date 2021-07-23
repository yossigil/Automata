package utils;

import java.util.Map;
import java.util.Set;

public enum val {
  ;
  public static <T> String show(final String name, final T t) {
    return name + " = " + t;
  }
  public static <S, T> String show(final String name, final Map<S, T> m) {
    return name + "[[" + m.size() + "]] = " + name + "[{" + m.keySet() + "}]=" + m;
  }
  public static <T> String show(final String name, final Set<T> ts) {
    return head(name, ts) + ts;
  }
  public static <T> String head(final String name, final Set<T> ts) {
    return name + "[[" + ts.size() + "]] = ";
  }
}