package finite;

import java.util.Set;

import utils.empty;

public abstract class TikZifier extends TeXifier {
  public final String render() { return wrap(traverse()); }
  private static String wrap(final String ¢) { return sprintf("\\graph{%%\n\t%s\n};", ¢); }
  protected abstract String traverse();
  final Set<Q> elaborated = empty.Set();
  boolean elaborated(final Q ¢) { return elaborated.contains(¢); }
  static String square(String $) { return $.isBlank() ? "" : "[" + $ + "]"; }
  protected String missing(final String kind) { return missing + "/" + math(kind); }
  private final String missing = "\"\"";
}