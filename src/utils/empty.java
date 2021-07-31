package utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//@formatter:off
public enum empty { ;
  public static<T> Set<T> Set() { return new LinkedHashSet<>(); }
  public static<F,T> Map<F,T> Map() { return new LinkedHashMap<>(); }
  public static<T> List<T> List() { return new ArrayList<>(); }
}