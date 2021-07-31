package automaton;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import utils.TeXifier;
import utils.empty;
import utils.set;

public enum TikZ {
  ;
  static final char[] LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
  static final char[] DIGITS  = "0123456789".toCharArray();
  static <Σ> String of(FSA<Σ> ¢) {
    return ¢.new External() {
      {
        tex.memo.put(null, "\\text{undefined}");
        var letters = new Object() {
                      int i;
                      Character next() { return i >= LETTERS.length ? null : LETTERS[i++]; }
                    };
        var digits  = new Object() {
                      int i;
                      Character next() { return i >= DIGITS.length ? null : DIGITS[i++]; }
                    };
        self().dfs(q -> tex.memo.put(q, letters.next() + ""));
        self().Σ.stream().sorted().forEach(σ -> tex.memo.put(σ, digits.next() + ""));
      }
      @Override public String toString() {
        return String.format("%s \n\t\t\t \\pgfmatrixnextcell \n %s", description(), graph());
      }
      String graph() {
        return new TikZifier() {
          @Override protected String traverse() { self().dfs(q -> render(q)); return this + ""; }
          void render(Q from) { render(from, self().neighbours(from)); }
          void render(Q from, Collection<Q> to) {
            if (to.isEmpty() && !elaborated(from)) {
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
            printf("\t %s -> [\"%s\"%s] %s;\n", tikz(from), labels(from, to), elaborate(from, to), tikz(to));
          }
          String labels(Q from, Q to) {
            Set<?>       a = self().letters(from, to);
            List<String> $ = a.stream().map(x -> x == null ? "*" : tex.info(x)).collect(toList());
            if (self() instanceof NFSA<?> && ((NFSA<?>) self()).ε(from).contains(to)) $.add(0, "\\varepsilon");
            return $.stream().sorted().collect(joining(","));
          }
          String tikz(Q ¢) { return sprintf("\"${%s}$\" %s", tex.memo.get(¢), elaborate(¢)); }
          String elaborate(Q ¢) {
            if (!elaborated.add(¢)) return "";
            var $ = "";
            if (¢ == self().q0) $ += "initial,";
            if (self().ζ.contains(¢)) $ += "accept";
            return square($);
          }
        }.$();
      }
      String description() {
        return String.format("\\node{%s};", new TeXifier() {
          void line(String s1, String s2, String s3) {
            printf(" %s & = \\scriptsize %s & \\scriptsize %s \\\\\n", s1, s3, s2);
          }
          {
            printf("\\begin{tabularx}{0.6\\textwidth}{@{}r@{}XX}\n");
            printf("\\multicolumn3c{FSA \\#$%d$/$%d$ = $\\langle\\Sigma,Q, q_0,\\zeta,\\Delta\\rangle$}\\\\\n",
                self().n, FSA.N);
            line("$\\Sigma$", "alphabet", tex.show(self().Σ));//
            line("$Q$", "set of all states", tex.show(self().Q)); //
            line("$q_0$", "initial state", tex.show(self().q0)); //
            line("$\\zeta$", "accepting states", tex.show(self().ζ)); //
            line("$\\Delta$", "transition table", tex.show(self()));
            if (self() instanceof NFSA)
              line("$\\varepsilon$", "$\\varepsilon$ transitions", tex.showMapSet(((NFSA<Σ>) self()).ε));
            line("$q$", "current state", tex.show(self().q)); //
            line("$Q'$", "reachable states", tex.show(self().QQ())); //
            line("$Q\\setminus Q'$", "unreachable states", tex.show(set.minus(self().Q, self().QQ()))); //
            line("$Q\\setminus \\zeta$", "rejecting states", tex.show(set.minus(self().Q, self().ζ))); //
            printf("\\end{tabularx}\n");
          }
        });
      }
    } + "";
  }
}
