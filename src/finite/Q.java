package finite;

//@formatter:off
class Q {
  static int count = -1;
  final int n = ++count;
  @Override public String toString() { return "ι" + n; }
}