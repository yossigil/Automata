package finite;

import java.util.Set;

import utils.empty;

abstract class TikZifier extends TeXifier { 
  final String render() { return wrap(traverse()); }
  static String wrap(final String ¢) { return sprintf("graph{\n%s}\n", ¢); }
  abstract String traverse();
  final Set<Q> elaborated = empty.Set();
  boolean elaborated(final Q ¢) { return elaborated.contains(¢); }
  static String square(String $) { return $.isBlank() ? "" : "[" + $ + "]"; }
}