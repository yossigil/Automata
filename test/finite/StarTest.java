package finite;

import org.junit.jupiter.api.Test;

public class StarTest {
  final Text aStar = new Text(new Text('a').star());

  @Test void accept0() {
    assert aStar.run("");
  }

  @Test void accept1() {
    assert aStar.run("aa");
  }

  @Test void accept2() {
    assert aStar.run("aa");
  }

  @Test void accept3() {
    assert aStar.run("aaa");
  }

  @Test void reject0() {
    assert !aStar.run("ba");
  }

  @Test void reject1() {
    assert !aStar.run("ba");
  }

  @Test void reject2() {
    assert !aStar.run("babab");
  }

  @Test void reject3() {
    assert !aStar.run("abababa");
  }
}
