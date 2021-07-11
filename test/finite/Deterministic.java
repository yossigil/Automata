package finite;

import org.junit.jupiter.api.Test;

class Deterministic {
  final Text a = new Text('a');
  @Test void exists() {
    a.DFSA();
  }
}
