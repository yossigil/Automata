package finite;

import java.util.Formatter;

class Encapsulator {
  final Formatter inner = new Formatter();
  @Override public final String toString() {
    return inner + "";
  }
}

class Tabulator extends Encapsulator { //@formatter:off
  StringBuffer indent = new StringBuffer();
  String indent(String line) { return indent + line; }
  String indent() { indent.append("  "); return ""; }
  String unindent() { indent.delete(0,2);return "";  }
  protected String indentln(String line) { return println(line) + indent(); }
  protected String unindentln(String line) { return unindent() + println(line); }
  protected String println(String line) { inner.format(indent + "%s\n", line); return ""; }
  String printf(final String format, final Object... os) { inner.format(format, os);return "";  }
  static String sprintf(final String format, final Object... os) { return String.format(format, os); }
}

@SuppressWarnings("static-method") class TeXifier extends Tabulator {
  protected String math(String ¢) { return "\\ensuremath{" + ¢ + "}"; }
  protected String tt(String ¢) { return "\\text{" + ¢ + "}"; }
}