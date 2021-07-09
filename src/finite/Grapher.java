package finite;

import java.util.Set;

import utils.empty;

abstract class Grapher extends Texter {
  final String render() { return wrap(traverse()); }
  final String wrap(String s) { return sprintf("graph{\n%s}\n" , s); }
  abstract String traverse();
  final Set<Q> elaborated = empty.Set();
  boolean elaborated(Q q) { return elaborated.contains(q); }
}