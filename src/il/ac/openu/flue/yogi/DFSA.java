package il.ac.openu.flue.yogi;

import java.util.Map;
import java.util.Set;

public class DFSA extends FSA {

   DFSA(NFSA x) {
    super(x.n());
  }


  public DFSA(Set<Integer> accepting, Map<Character, Integer>[] delta) {
    super(accepting, delta);
  }


  boolean run(String string) {
    return true;
  }

  public boolean accepting(Set<Integer> ss) {
    for (var s : ss)
      if (ζ.contains(s))
        return true;
    return false;
  }



}
