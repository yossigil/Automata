package utils;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Map.Entry;

public enum map {
    ;
    public static <T1,T2> Map<T1,T2> transpose(Map<T2,T1> ¢) {
    return ¢.entrySet().stream().collect(toMap(Entry::getValue, Entry::getKey));
  }
}