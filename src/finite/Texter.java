package finite;

import java.util.Formatter;

abstract class Texter { //@formatter:off
  @Override public String toString() { return inner + ""; }
  void printf(final String format, final Object... os) { inner.format(format, os); }
  static String sprintf(final String format, final Object... os) { return String.format(format, os); }
  private final Formatter inner = new Formatter();
}