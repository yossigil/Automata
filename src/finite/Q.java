package finite;

//@formatter:off
class Q {
  static int count;
  final int n = count++;
  @Override public String toString() { return "ι" + n; }
}