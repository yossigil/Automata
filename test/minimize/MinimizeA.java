package minimize;

import org.junit.jupiter.api.Test;

import finite.FSA;

public class MinimizeA {
  final FSA<Character> dfsa = Sample.a.minimal();
  {
    Sample.a.show();
  }
  @Test void accept0() { assert dfsa.run("a"); }
  @Test void reject1() { assert !dfsa.run(""); }
  @Test void reject2() { assert !dfsa.run("c"); }
  @Test void reject3() { assert !dfsa.run("b"); }
  @Test void reject4() { assert !dfsa.run("ab"); }
  @Test void reject0() { assert !dfsa.run("cab"); }
  @Test void reject01() { assert !dfsa.run("c"); }
  @Test void reject02() { assert !dfsa.run("ac"); }
  @Test void reject03() { assert !dfsa.run("cb"); }
  @Test void reject04() { assert !dfsa.run("ba"); }
  @Test void reject05() { assert !dfsa.run("aababab"); }
  @Test void reject06() { assert !dfsa.run("aab"); }
  @Test void reject07() { assert !dfsa.run("abb"); }
  @Test void reject08() { assert !dfsa.run("aaa"); }
  @Test void reject09() { assert !dfsa.run("bbb"); }
  @Test void reject10() { assert !dfsa.run("abababa"); }
}
