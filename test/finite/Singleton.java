package finite;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Singleton {

  final Text a = new Text('a');
  final Text b = new Text('b');

  @Test void aOK() {
    assert a.run("a");
  }

  @Test void aOther() {
    assert !a.run("b");
  }

  @Test void aEmpty() {
    assert !a.run("");
  }

  @Test void bOK() {
    assert b.run("b");
  }
  
  @Test void singletonSize() {
    assertEquals(a.Δ.keySet().size(), 1);
  }
}