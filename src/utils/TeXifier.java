package utils;

@SuppressWarnings("static-method")
public class TeXifier extends Tabulator {
  protected String math(String ¢) { return "\\ensuremath{" + ¢ + "}"; }
  protected String tt(String ¢) { return "\\text{" + ¢ + "}"; }
}