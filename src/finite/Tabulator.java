package finite;

import java.util.Formatter;

class Tabulator { //@formatter:off
  StringBuffer indent = new StringBuffer(); 
  String indent(String line) { return indent + line; }
  String indent() { indent.append("  "); return ""; }
  String unindent() { indent.delete(0,2);return "";  }
  String indentln(String line) { return println(line) + indent(); }
  String unindentln(String line) { return unindent() + println(line); }
  String println(String line) { inner.format(indent + "%s\n", line); return ""; }
  String printf(final String format, final Object... os) { inner.format(format, os);return "";  }
  @Override public String toString() { return inner + ""; }
  private final Formatter inner = new Formatter();
  static String sprintf(final String format, final Object... os) { return String.format(format, os); }
}
@SuppressWarnings("static-method") 
class TeXifier extends Tabulator {
  String math(String ¢) { return "\\ensuremath{" + ¢ + "}"; }
  String tt(String ¢) { return "\\text{" + ¢ + "}"; }
}