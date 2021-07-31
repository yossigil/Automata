package utils;

import java.util.List;

@SuppressWarnings("static-method") public class TeXifier extends Tabulator {
  Stack stack = new Stack();
  protected String tt(String ¢) { return "\\text{" + ¢ + "}"; }
  protected String math(String ¢) { return "\\ensuremath{" + ¢ + "}"; }
  void begin(String ¢) { 
    printf("\\begin{%s}", ¢); 
    stack.push(¢);
  }
  void end(String ¢) { 
    printf("\\end{%s}", ¢); 
    }
}

class Stack {
  final List<String> stack = empty.List();
  boolean isEmpty() { return stack.isEmpty(); }
  void push(String value) { stack.add(value); }
  String pop() { return stack.remove(last()); }
  String peek() { return stack.get(last()); }
  private int last() { return stack.size() - 1; }
}