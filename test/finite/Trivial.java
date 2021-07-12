package finite;

import org.junit.jupiter.api.Test;
@SuppressWarnings("static-method") class FSAExists {
  @Test void fsa() {
    FSA<Character> fsa = new FSA<Character>() {};
    fsa.hashCode();
    (fsa + "").hashCode();
  }
}
