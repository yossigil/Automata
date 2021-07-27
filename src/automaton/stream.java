package automaton;

import java.util.Map;
import java.util.Set;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.stream.Stream;

enum stream {
  ;
  static <K, V> Stream<Entry<K, V>> map(Map<K, V> ¢) { return ¢.entrySet().stream(); }
  static <K1, K2> Stream<Entry<K1, K2>> mapOfSets(Map<K1, Set<K2>> m) {
    return stream.map(m).flatMap(//
        e -> e.getValue().stream()//
            .map(ee -> new SimpleEntry<>(e.getKey(), ee)));
  }
  static <K1, K2, V> Stream<Entry<K1, Entry<K2, V>>> mapOfMaps(Map<K1, Map<K2, V>> m) {
    return stream.map(m).flatMap(//
        e -> stream.map(e.getValue())//
            .map(ee -> new SimpleEntry<>(e.getKey(), //
                new SimpleEntry<>(ee.getKey(), ee.getValue()))));
  }
}