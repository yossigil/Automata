package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//@formatter:off
public enum empty { ;
  public static<T> Set<T> Set() { return new HashSet<>(); }
  public static<F,T> Map<F,T> Map() { return new HashMap<>(); }
  public static<T> List<T> List() { return new ArrayList<>(); }
}