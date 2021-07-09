package finite;

import java.util.Set;

import utils.empty;

abstract class Renderer extends Texter { //@formatter:off
  final String render() { return wrap(traverse()); }
  final String wrap(String ¢) { return sprintf("graph{\n%s}\n", ¢); }
  abstract String traverse();
  final Set<Q> elaborated = empty.Set();
  boolean elaborated(Q ¢) { return elaborated.contains(¢); }
}