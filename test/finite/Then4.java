package finite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Then4 {
  final Text abcd = new Text(new Text('a').then(new Text('b').then(new Text('c')).then(new Text('d'))));

  @Test void x() {
   System.out.println(abcd.TikZ()); 
   System.out.println(abcd.DFSA().TikZ()); 
  }
  @Test void accept() {
    assertTrue(abcd.run("abcd"));
  }

  @Test void rejectA() {
    assertFalse(abcd.run("a"));
  }

  @Test void rejectB() {
    assertFalse(abcd.run("b"));
  }

  @Test void rejectEmpty() {
    assertFalse(abcd.run(""));
  }

  @Test void rejectBA() {
    assertFalse(abcd.run("ba"));
  }
}
