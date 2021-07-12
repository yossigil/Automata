package finite;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import utils.empty;
import static java.util.stream.Collectors.*;

/** An immutable transition table, complete function from Q x Σ -> Q */
abstract class Δ<Σ> {
  // @formatter:off 
  /** Class summary */ @Override public String toString() { return "\t Δ = " + Δ + " (transition function)\n"; }
  /** Empty constructor */ Δ() { this(empty.Map()); }
  /** Copy constructor */  Δ(final Δ<Σ> that) { this(that.Δ); }
  /** Full constructor */  Δ(final Map<Σ, Map<Q, Q>> Δ) { this.Δ = Δ; }
  /** Inspector: Letters seen */ protected final Set<Σ> Σ()  { return Δ.keySet(); }
  /** Inspector: complete transition function from a state and letter  */
  /** Inspector: transition table of given letter */ Map<Q, Q> δ(final Σ ¢) { init(¢); return Δ.get(¢); }
  /** Inspector: transition table of given letter */ private void init(final Σ ¢) { Δ.putIfAbsent(¢, empty.Map()); }
  /** Inspector: Set of all states for which a transition on a letter is defined */ Set<Q> Q(final Σ ¢) { return δ(¢).keySet(); }
  /** Inspector: Reachable states from a given state */ protected Set<Q> δ(final Q q) { return Σ().stream().map(λ -> δ(q, λ)).collect(toSet()); }
  /** Data: The transition table */ Map<Σ, Map<Q, Q>> Δ;
  /** Data: Wild card letter */ final Σ ANY = null;
  /** Data: Wild card state */ final Q SINK = null;
  // Details: // @formatter:on
  /** Inspector: the transition function */
  protected Q δ(final Q q, final Σ σ) {
    final Q $ = δ(σ).get(q);
    return $ != SINK ? $ : δ(ANY).get(q);
  }
  /** Inspector: set of all states seen */ //@formatter:on
  Set<Q> Q() {
    final Set<Q> $ = empty.Set();
    for (final Σ σ : Σ()) {
      final var qs = Q(σ);
      $.addAll(qs);
      for (final Q q : qs) $.add(δ(σ).get(q));
    }
    return $;
  }
  Set<String> labels(final Q from, final Q to) {
    return Σ().stream().filter(λ -> δ(from, λ) == to).map(λ -> λ == null ? "*" : λ + "").collect(Collectors.toSet());
  }
}

abstract class Algorithms<Σ> extends Implementation<Σ> {
  /** Exerciser: do a DFS search, supplying each state q to a given consumer */
  final void dfs(final Consumer<Q> c) {
    new XDFS<Q>() { //@formatter:off
      @Override public Set<Q> n(final Q ¢) { return δ(¢); }
      @Override public Q v(final Q ¢) { c.accept(¢); return ¢;}
      //@formatter:on
    }.dfs(q0);
  }
  Algorithms() {}
  Algorithms(Implementation<Σ> that) { super(that); }
  Algorithms(finite.Q q0, Set<finite.Q> ζ, Map<Σ, Map<finite.Q, finite.Q>> Δ) { super(q0, ζ, Δ); }
  Algorithms(finite.Q q0, Set<finite.Q> ζ) { super(q0, ζ); }
  Algorithms(Set<finite.Q> ζ, Map<Σ, Map<finite.Q, finite.Q>> Δ) { super(ζ, Δ); }
}

public abstract class FSA<Σ> extends Algorithms<Σ> {
  /** Class summary: // @formatter:on */
  @Override public String toString() {
    return "FSA #" + n + "/" + N + " = ⟨Σ,q0,Q,ζ,Δ⟩ \n" + //
        super.toString() + //
        "\t " + toString("Q'$", QQ()) + " (reachable states)\n"//
    ;
  }
  // @formatter:off
  /** Empty constructor */ FSA() {}
  /** Copy constructor */ FSA(final Implementation<Σ> a) { super(a); }
  /** Partial constructor */  FSA(final Map<Σ, Map<Q, Q>> Δ, final Set<Q> ζ) { super(ζ, Δ); }
  /** Full constructor */ FSA(final Q q0, final Set<Q> ζ, final Map<Σ,Map<Q,Q>> Δ) { super(q0, ζ, Δ); }

  FSA<Σ> ζ(final Q ¢) { ζ.add(¢); return this; }
  FSA<Σ> ζ(final Set<Q> ¢) { ζ.addAll(¢); return this; }
  /** Data: Instance number */  final int n = ++N;
  /** Data: Instance counter */ static int N;
  /** Modifier: Add a new transition */
  @Override FSA<Σ> δ(final Q from, final Σ σ, final Q to) {
    δ(σ).put(from, to);
    return this;
  }
  /** Modifier: Add a full transition table */
  FSA<Σ> δ(final Map<Σ, Map<Q, Q>> Δ) {
    for (final Σ σ : Δ.keySet()) δ(δ(σ), Δ.get(σ));
    return this;
  }
  /** Helper: Copy a transition table of a single letter */
  static void δ(final Map<Q, Q> m1, final Map<Q, Q> m2) {
    for (final Q q : m2.keySet()) m1.put(q, m2.get(q));
  }
  /** Inspector: Set of all reachable states */
  Set<Q> QQ() {
    final Set<Q> $ = empty.Set();
    dfs(λ -> $.add(λ));
    return $;
  }
  public final String TikZ() {
    return new TikZifier() {
    //@formatter:off
      @Override protected String traverse() { enumerate(); dfs(from -> render(from)); return this + ""; }
      void render(Q from) { render(from, δ(from)); }
      void render(Q from, Set<Q> to) { for (final Q q : to) render(from, q); }
      int ordinal;
      void enumerate() { dfs(q -> enumeration.computeIfAbsent(q, __ -> ordinal++)); }
      final Map<Q, Integer> enumeration = empty.Map();
      final Set<Q> elaborated = empty.Set();
      String tikz(final Q ¢) { return sprintf("\"$q_{%s}$\" %s", enumeration.get(¢), elaborate(¢)); }
      String elaborate(final Q from, final Q to) { return to == from ? ",loop" : !edge(to, from) ? "" : ",bend left"; }

      //@formatter:on
      void render(Q from, Q to) {
        printf("\t %s -> [\"%s\"%s] %s;\n", tikz(from), tikz(labels(from, to)), elaborate(from, to), tikz(to));
      }
      String elaborate(final Q ¢) {
        if (!elaborated.add(¢)) return "";
        var $ = "";
        if (¢ == q0) $ += "initial,";
        if (ζ.contains(¢)) $ += "accept";
        return square($);
      }//@formatter:on
      boolean edge(final Q from, final Q to) {
        for (final Σ σ : Σ()) if (δ(from, σ) == to) return true;
        return false;
      }
      String tikz(final Set<String> ss) {
        var $        = new StringBuilder();
        var ordinary = false;
        for (final String s : ss) {
          if (ordinary) $.append(", ");
          else ordinary = true;
          $.append(s);
        }
        return $ + "";
      }
    }.render();
  }
}
