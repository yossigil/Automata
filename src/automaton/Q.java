package automaton;

public class Q {
  static int count = -1;
  final int  n     = ++count;
  @Override public String toString() { return "q" + n; }
}