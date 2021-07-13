package FSA.compound;

import org.junit.jupiter.api.Test;

import finite.Lexer;

public class Many1 {
  final Lexer a$many = new Lexer(new Lexer('a').many());
  @Test void accept0() { assert a$many.run(""); }
  @Test void accept1() { assert a$many.run("aa"); }
  @Test void accept2() { assert a$many.run("aa"); }
  @Test void accept3() { assert a$many.run("aaa"); }
  @Test void reject0() { assert !a$many.run("ba"); }
  @Test void reject1() { assert !a$many.run("ba"); }
  @Test void reject2() { assert !a$many.run("babab"); }
  @Test void reject3() { assert !a$many.run("abababa"); }
}
