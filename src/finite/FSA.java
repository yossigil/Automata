package finite;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import utils.empty;
import utils.set;

/** An immutable transition table, complete function from Q x Σ -> Q */
abstract class Δ<Σ> {
  /** Class summary */
  @Override public String toString() {
    return "\t Δ = " + Δ + " (transition function)\n";
  }

  /* Essence: // @formatter:off */
  /** Empty constructor */ Δ() { this(empty.Map()); }
  /** Copy constructor */  Δ(final Δ<Σ> that) { this(that.Δ); }
  /** Full constructor */  Δ(final Map<Σ, Map<Q, Q>> Δ) { this.Δ = Δ; }
  /** Inspector Letters seen */ final Set<Σ> Σ()  { return Δ.keySet(); }
  /** Data: The transition table */ Map<Σ, Map<Q, Q>> Δ;
  /** Inspector: complete transition function from a state and letter  */
  /** Inspector: the transition function */ Q δ(final Q q, final Σ σ) { return δ(σ).get(q); }
  /** Inspector: Transition table of given letter */ Map<Q, Q> δ(final Σ ¢) { init(¢); return Δ.get(¢); }
  /** Inspector: Transition table of given letter */  void init(final Σ ¢) { Δ.putIfAbsent(¢, empty.Map()); }
  /** Inspector: Set of all states for which a transition on a letter is defined */ Set<Q> Q(final Σ σ) { return δ(σ).keySet(); }
  // Details: // @formatter:on

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

}

class Implementation<Σ> extends Δ<Σ> implements Recognizer<Σ> {
  static <T> String toString(final String name, final Set<T> ts) {
    return head(name, ts) + ts;
  }

  private static <T> String head(final String name, final Set<T> ts) {
    return name + "[[" + ts.size() + "]] = ";
  }

  static <S, T> String toString(final String name, final Map<S, T> m) {
    return name + "[[" + m.size() + "]] = " + name + "[{" + m.keySet() + "}]=" + m;
  }

  static <T> String toString(final String name, final T t) { return name + " = " + t; }

  @Override public String toString() {
    return "" + //
        "\t " + toString("Q", Q()) + " (all states)\n" + //
        "\t " + toString("q0", q0) + " (start state)\n" + //
        "\t " + toString("ζ", ζ) + "  (accepting states)\n" + //
        "\t " + toString("Q\\ζ", set.minus(Q(), ζ)) + " (rejecting states)\n" + //
        "\t " + toString("q", q) + " (current state)\n" + //
        "\t " + toString("Σ", Σ()) + " (alphabet)\n" + //
        super.toString() + //
        "";
  }

  //@formatter:off
  /** Data: Initial state */ final Q q0;
  /** Data: Set of accepting states */ final Set<Q> ζ;
  /** Data: Current state  */ Q q;
  /** Empty constructor */ Implementation()  { this(new Q(), empty.Set()); }
  /** Partial constructor */  Implementation(final Q q0, final Set<Q> ζ) { this.q0 = q0; this.ζ = ζ; }
  /** Full constructor */  Implementation(final Q q0, final Set<Q> ζ,  final Map<Σ, Map<Q, Q>> Δ) { super(Δ); this.q0 = q0; this.ζ= ζ; }
  /** Full constructor */  Implementation(final Set<Q> ζ,  final Map<Σ, Map<Q, Q>> Δ) { super(Δ); this.q0 = new Q(); this.ζ= ζ; }
  /** Copy constructor */  Implementation(final Implementation<Σ> that) { this(that.q0,that.ζ,that.Δ); }
  /** Update transition table */  Implementation<Σ>  δ(final Q from, final Σ σ, final Q to) { δ(σ).put(from, to); return this;}
  @Override Set<Q> Q() { return set.union(super.Q(),ζ); }
  @Override public void q0() { q = q0; }
  @Override public void feed(final Σ ¢) { q = δ(q,¢); }
  @Override public boolean ζ() { return ζ.contains(q); }
}

abstract class FSA<Σ> extends Implementation<Σ> {
  /** Class summary: // @formatter:on */
  @Override public String toString() {
    return "FSA #" + n + "/" + N + " = ⟨Σ,q0,Q,ζ,Δ⟩ \n" + //
        super.toString() + //
        "\t " + toString("Q'$", QQ()) + " (reachable states)\n" + //
        "";
  }

  // @formatter:off
  /** Empty constructor */ FSA() {}
  /** Copy constructor */ FSA(final FSA<Σ> a) { super(a); }
  /** Partial constructor */  FSA(final Map<Σ, Map<Q, Q>> Δ, final Set<Q> ζ) { super(ζ, Δ); }
  /** Full constructor */ FSA(final Q q0, final Set<Q> ζ, final Map<Σ,Map<Q,Q>> Δ) { super(q0, ζ, Δ); }

  /** Modifier: Add a new accepting state */ FSA<Σ> ζ(final Q ¢) { ζ.add(¢); return this; }
  /** Modifier: Add a set of new accepting state */ FSA<Σ> ζ(final Set<Q> ¢) { ζ.addAll(¢); return this; }
  /** Data: Instance number */  final int n = ++N;
  /** Data: Instance counter */ static int N;
  //@formatter:on

  /** Inspector: Set of all reachable state of a give state */
  Set<Q> δ(final Q q) {
    final Set<Q> $ = empty.Set();
    for (final Σ σ : Σ()) $.add(δ(q, σ));
    return $;
  }

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

  /** Exerciser: do a DFS search, supplying each state q to a given consumer */
  final void dfs(final Consumer<Q> c) {
    new XDFS<Q>() { //@formatter:off
      @Override public Set<Q> n(final Q ¢) { return δ(¢); }
      @Override public Q v(final Q ¢) { c.accept(¢); return ¢;}
      //@formatter:on
    }.dfs(q0);
  }

  class TikZ extends Renderer { //@formatter:off
    private int ordinal;
    private final Map<Q, Integer> enumeration = empty.Map();
    final Set<Q> elaborated = empty.Set();
    void enumerate() { dfs(q -> enumeration.computeIfAbsent(q, __ -> ordinal++)); }
    String tikz(final Q ¢) { return sprintf("\"$q_{%s}$\" %s", enumeration.get(¢), elaborate(¢)); }
    String elaborate(final Q from, final Q to) { return to == from ? ",loop" : !edge(to, from) ? "" : ",bend left"; }
    @Override String traverse() { enumerate(); dfs(from -> render(from)); return this + ""; }

    //@formatter:on
    void render(final Q from) {
      final Set<Σ> σs = empty.Set();
      for (final Σ σ : Σ()) if (!σs.contains(σ)) σs.addAll(render(from, δ(from, σ)));
    }

    Set<Σ> render(final Q from, final Q to) {
      if (to == null) return empty.Set();
      final var $ = unify(from, to);
      printf("\t %s -> [\"%s\"%s] %s;\n", tikz(from), tikz($), elaborate(from, to), tikz(to));
      return $;
    }

    final String elaborate(final Q ¢) { //@formatter:on
      if (!elaborated.add(¢)) return "";
      String $ = "";
      if (¢ == q0) $ += "initial,";
      if (ζ.contains(¢)) $ += "accept";
      return square($);
    }//@formatter:on

    boolean edge(final Q from, final Q to) {
      for (final Σ σ : Σ()) if (δ(from, σ) == to) return true;
      return false;
    }

    private Set<Σ> unify(final Q from, final Q to) {
      final Set<Σ> $ = empty.Set();
      for (final Σ σ : Σ()) if (δ(from, σ) == to) $.add(σ);
      return $;
    }

    private String tikz(final Set<Σ> σs) {
      var $        = new StringBuilder();
      var ordinary = false;
      for (final Σ σ : σs) {
        if (ordinary) $.append(", ");
        else ordinary = true;
        $.append(σ != null ? σ : "*");
      }
      return $ + "";
    }

  }
}
