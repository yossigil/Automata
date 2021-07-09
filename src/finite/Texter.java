package finite;

import java.util.Formatter;

abstract class Texter { //@formatter:off
  @Override public String toString() { return inner + ""; } 
  void printf(String format, Object... os) { inner.format(format, os); } 
  static String sprintf(String format, Object... os) { return String.format(format, os); } 
  private final Formatter inner = new Formatter();
}