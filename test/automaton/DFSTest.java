package automaton;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

import org.junit.Test;


@SuppressWarnings("static-method")public class DFSTest {
  static class V implements Vertex<V> {
    int value;
    public V(int value) { this.value = value; }
    public int hashCode() { return value; }
    public boolean equals(Object ¢) {
      if (¢ == this) return true;
      if (¢ == null) return false;
      if (getClass() != ¢.getClass()) return false;
      return Objects.equals(value, ((V) ¢).value);
    }
    public Collection<? extends V> neighbours() {
      return Stream//
          .iterate(1, i -> i + 1)//
          .limit(value) //
          .filter(d -> value % d == 0)//
          .map(V::new)//
          .collect(toSet());
    }
  }
  @Test public void n0() { assertEquals((new V(0)).neighbours().size(), 0); }
  @Test public void n1() { assertEquals((new V(1)).neighbours().size(), 1); }
  @Test public void n2() { assertEquals((new V(2)).neighbours().size(), 2); }
  @Test public void n3() { assertEquals((new V(3)).neighbours().size(), 2); }
  @Test public void n4() { assertEquals((new V(4)).neighbours().size(), 3); }
  @Test public void n5() { assertEquals((new V(5)).neighbours().size(), 2); }
  @Test public void n6() { assertEquals((new V(6)).neighbours().size(), 4); }
  @Test public void exists0() { new DFS<V>() {}.dfs(new V(0)); }
  @Test public void exists1() { new DFS<V>() {}.dfs(new V(1)); }
  @Test public void exists120() { new DFS<V>() {}.dfs(new V(120)); }
}
