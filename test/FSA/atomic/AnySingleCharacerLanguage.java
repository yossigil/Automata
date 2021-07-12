package FSA.atomic;

import org.junit.jupiter.api.Test;

import finite.Lexer;

public class AnySingleCharacerLanguage {
  Lexer any = Lexer.any();
  @Test void print() { System.out.println(any.TikZ()); }
  @Test void accept0() { assert any.run("a"); }
  @Test void accept1() { assert any.run("b"); }
  @Test void accept2() { assert any.run("c"); }
  @Test void accept3() { assert any.run("*"); }
  @Test void reject0() { assert !any.run(""); }
  @Test void reject1() { assert !any.run("an"); }
  @Test void reject2() { assert !any.run("babe"); }
  @Test void reject3() { assert !any.run("**"); }
}