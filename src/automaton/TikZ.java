package automaton;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import utils.empty;

public enum TikZ {
  ;
  static <Σ> String of(FSA<Σ> ¢) {
    return ¢.new External() {
      final Map<Q, Integer> enumeration = new Object() {
        int                   ordinal = -1;
        final Map<Q, Integer> $ = empty.Map();
        {
          self().dfs(q -> $.computeIfAbsent(q, x -> ++ordinal));
        }
      }.$;
      int ordinal(Q ¢) { return enumeration.get(¢); }
      String $ = new TikZifier() {
        @Override protected String traverse() { self().dfs(q -> render(q)); return this + ""; }
        void render(Q from) { render(from, self().neighbours(from)); }
        void render(Q from, Collection<Q> to) {
          Set<Q> seen = empty.Set();
          for (final Q q : to) 
            if (seen.add(q))  
              render(from, q);
        }
        boolean edge(Q from, Q to) { return self().Δ.get(from).containsKey(to); }
        final Set<Q> elaborated = empty.Set();
        String elaborate(Q from, Q to) { return to == from ? ",loop" : !edge(to, from) ? "" : ",bend left"; }
        void render(Q from, Q to) {
          printf("\t %s -> [\"%s\"%s] %s;\n", tikz(from), tikz(self().labels(from, to)), elaborate(from, to), tikz(to));
        }
        String tikz(Q ¢) { return sprintf("\"$q_{%s}$\" %s", ordinal(¢), elaborate(¢)); }
        String elaborate(Q ¢) {
          if (!elaborated.add(¢)) return "";
          var $ = "";
          if (¢ == self().q0) $ += "initial,";
          if (self().ζ.contains(¢)) $ += "accept";
          return square($);
        }
        String tikz(final Set<String> ss) {
          var $        = new StringBuilder();
          var ordinary = false;
          for (final String ¢ : ss) {
            if (ordinary) $.append(", ");
            else ordinary = true;
            $.append(¢);
          }
          return $ + "";
        }
      }.$();
    }.$;
  }
}