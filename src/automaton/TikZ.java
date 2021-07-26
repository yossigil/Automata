package automaton;

import utils.set;
import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import utils.TeXifier;
import utils.empty;

public enum TikZ {
  ;
  static <Σ> String of(FSA<Σ> ¢) {
    return ¢.new External() {
      {
        tex.memo.put(null, "\\text{undefined}");
        new Object() {
          int ordinal = -1;
          String next() {
            return !letters.isEmpty() ? letters.remove(0) + "" : String.format("q_{%d}", ++ordinal);
          }
          List<Character> letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".chars()
              .mapToObj(e -> (char) e).collect(toList());
          {
            self().dfs(q -> tex.memo.put(q, next()));
          }
        };
        new Object() {
          String next() {
            return digits.isEmpty() ? null : digits.remove(0) + "";
          }
          List<Character> digits = "0123456789".chars().mapToObj(e -> (char) e).collect(toList());
          {
            self().Σ.stream().forEach(σ -> tex.memo.put(σ, next()));
          }
        };
      }
      @Override public String toString() {
        return String.format("%s \n\t\t\t \\pgfmatrixnextcell \n %s", description(), graph());
      }
      String graph() {
        return new TikZifier() {
          @Override protected String traverse() { self().dfs(q -> render(q)); return this + ""; }
          void render(Q from) { render(from, self().neighbours(from)); }
          void render(Q from, Collection<Q> to) {
            if (to.isEmpty()) {
              printf("\t %s; %% lonely node \n", tikz(from));
              return;
            }
            Set<Q> seen = empty.Set();
            for (final Q q : to) if (seen.add(q)) render(from, q);
          }
          boolean edge(Q from, Q to) { return self().Δ.get(from).values().contains(to); }
          final Set<Q> elaborated = empty.Set();
          String elaborate(Q from, Q to) { return to == from ? ",loop" : !edge(to, from) ? "" : ",bend left"; }
          void render(Q from, Q to) {
            printf("\t %s -> [\"%s\"%s] %s;\n", tikz(from), tikz(self().labels(from, to)), elaborate(from, to),
                tikz(to));
          }
          String tikz(Q ¢) { return sprintf("\"${%s}$\" %s", tex.memo.get(¢), elaborate(¢)); }
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
      }
      String description() {
        return String.format("\\node{%s};", new TeXifier() {
          void line(String s1, String s2, String s3) {
            printf(" %s & = \\scriptsize %s & \\scriptsize %s \\\\\n", s1, s3, s2);
          }
          {
            printf("\\begin{tabularx}{0.6\\textwidth}{lXX}\n");
            printf("\\multicolumn3c{FSA \\#$%d$/$%d$ = $\\langle\\Sigma,Q, q_0,\\zeta,\\Delta\\rangle$}\\\\\n",
                self().n, FSA.N);
            line("$\\Sigma$", "alphabet", tex.show(self().Σ));//
            line("$Q$", "set of all states", tex.show(self().Q)); //
            line("$q_0$", "initial state", tex.show(self().q0)); //
            line("$\\zeta$", "accepting states", tex.show(self().ζ)); //
            line("$\\Delta$", "transition table", tex.showMap(self()));
            line("$q$", "current state", tex.show(self().q)); //
            line("$Q'$", "reachable states", tex.show(self().QQ())); //
            line("$Q\\setminus Q'$", "unreachable states", tex.show(set.minus(self().Q, self().QQ()))); //
            line("$Q\\setminus \\zeta$", "rejecting states", tex.show(set.minus(self().Q, self().ζ))); //
            if (self() instanceof NFSA)
              line("$\\varepsilon$", "$\\varepsilon$ transitions", tex.showMapSet(((NFSA<Σ>) self()).ε));
            printf("\\end{tabularx}\n");
          }
        });
      }
    } + "";
  }
}
