package finite;

import java.util.Formatter;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import utils.empty;
import utils.set;

//@formatter:off
class Q {
  static int count;
  final int n = count++;
  @Override public String toString() { return "ι" + n; }
}
abstract class Texter {
  @Override public String toString() { return inner + ""; }
  void printf(String format, Object... os) { inner.format(format,os); }
  static String sprintf(String format, Object... os) { return String.format(format,os); }
  private final Formatter inner = new Formatter();
}
abstract class Grapher extends Texter {
  final String render() { return wrap(traverse()); }
  final String wrap(String s) { return sprintf("graph{\n%s}\n" , s); }
  abstract String traverse();
  final Set<Q> elaborated = empty.Set();
  boolean elaborated(Q q) { return elaborated.contains(q); }
}

/** Abstract recognizer of a formal language over Σ */ 
interface Recognizer<Σ> {
  /** Initialize the recognizer */ void q0(); 
  /** Feed the next letter */  void feed(Σ σ);
  /** Is it in an accepting state */ boolean ζ();
}

/** An immutable transition table, complete function from Q x Σ -> Q */
abstract class Δ<Σ> { 
  /** Class summary */
  @Override public String toString() {
    return  //
     "\t Δ = " + Δ +  " (transition function)\n" + //
     "";
  }
  /* Essence: // @formatter:off */
  /** Empty constructor */ Δ() { this(empty.Map()); } 
  /** Copy constructor */  Δ(Δ<Σ> that) { this(that.Δ); } 
  /** Full constructor */  Δ(Map<Σ, Map<Q, Q>> Δ) { this.Δ = Δ; }
  /** Inspector Letters seen */ final Set<Σ> Σ()  { return Δ.keySet(); } 
  /** Data: The transition table */ Map<Σ, Map<Q, Q>> Δ;
  /** Inspector: complete transition function from a state and letter  */ 
  /** Inspector: the transition function */ Q δ(Q q, Σ σ) { return δ(σ).get(q); }
  /** Inspector: Transition table of given letter */ Map<Q, Q> δ(Σ σ) { init(σ); return Δ.get(σ); }
  /** Inspector: Transition table of given letter */  void init(Σ σ) { Δ.putIfAbsent(σ, empty.Map()); }
  // Details: // @formatter:on

  /** Inspector: set of all states seen */ //@formatter:on
  Set<Q> Q() {
    Set<Q> $ = empty.Set();
    for (Σ σ : Σ()) {
      Set<finite.Q> keySet = δ(σ).keySet();
      $.addAll(keySet);
      for (Q q : keySet) 
        $.add(δ(σ).get(q));
    }
    return $;
  }
}

class Implementation<Σ> extends Δ<Σ> implements Recognizer<Σ> {
  static <T> String toString(String name, Set<T> ts) {
    return head(name, ts) + ts;
  }

  private static <T> String head(String name, Set<T> ts) {
    return name + "[[" + ts.size() + "]] = ";
  }

  static <S, T> String toString(String name, Map<S, T> m) {
    return name + "[[" + m.size() + "]] = " + name + "[{" + m.keySet() + "}]=" + m;
  }

  static <T> String toString(String name, T t) { return name + " = " + t; }

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
  /** Partial constructor */  Implementation(Q q0, Set<Q> ζ) { this.q0 = q0; this.ζ = ζ; }
  /** Full constructor */  Implementation(Q q0, Set<Q> ζ,  Map<Σ, Map<Q, Q>> Δ) { super(Δ); this.q0 = q0; this.ζ= ζ; }
  /** Full constructor */  Implementation(Set<Q> ζ,  Map<Σ, Map<Q, Q>> Δ) { super(Δ); this.q0 = new Q(); this.ζ= ζ; }
  /** Copy constructor */  Implementation(Implementation<Σ> that) { this(that.q0,that.ζ,that.Δ); }
  /** Update transition table */  Implementation<Σ>  δ(Q from, Σ σ, Q to) { δ(σ).put(from, to); return this;}
  @Override Set<Q> Q() { return set.union(super.Q(),ζ); }
  @Override public void q0() { q = q0; } 
  @Override public void feed(Σ σ) { q = δ(q,σ); } 
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
  /** Copy constructor */ FSA(FSA<Σ> a) { super(a); }
  /** Partial constructor */  FSA(Map<Σ, Map<Q, Q>> Δ, Set<Q> ζ) { super(ζ, Δ); }
  /** Full constructor */ FSA(Q q0, Set<Q> ζ, Map<Σ,Map<Q,Q>> Δ) { super(q0, ζ, Δ); }

  /** Modifier: Add a new accepting state */ FSA<Σ> ζ(Q q) { ζ.add(q); return this; }
  /** Modifier: Add a set of new accepting state */ FSA<Σ> ζ(Set<Q> qs) { ζ.addAll(qs); return this; }
  /** Data: Instance number */  final int n = ++N; 
  /** Data: Instance counter */ static int N;
  //@formatter:on

  /** Inspector: Set of all reachable state of a give state */
  Set<Q> δ(Q q) {
    Set<Q> $ = empty.Set();
    for (Σ σ : Σ())
      $.add(δ(q, σ));
    return $;
  }

  /** Modifier: Add a new transition */
  FSA<Σ> δ(Q from, Σ σ, Q to) {
    δ(σ).put(from, to);
    return this;
  }

  /** Modifier: Add a full transition table */
  FSA<Σ> δ(Map<Σ, Map<Q, Q>> Δ) {
    for (Σ σ : Δ.keySet())
      δ(δ(σ), Δ.get(σ));
    return this;
  }

  /** Helper: Copy a transition table of a single letter */
  static void δ(Map<Q, Q> m1, Map<Q, Q> m2) {
    for (Q q : m2.keySet())
      m1.put(q, m2.get(q));
  }

  /** Inspector: Set of all reachable states */
  Set<Q> QQ() {
    Set<Q> $ = empty.Set();
    dfs(q -> $.add(q));
    return $;
  }

  /** Exerciser: do a DFS search, supplying each state q to a given consumer */
  final void dfs(Consumer<Q> c) {
    new XDFS<Q>() { //@formatter:off
      @Override public Set<Q> n(Q q) { return δ(q); }
      @Override public Q v(Q q) { c.accept(q); return q;} 
      //@formatter:on
    }.dfs(q0);
  }

  class TikZ extends Grapher { //@formatter:off
    private int ordinal;
    private final Map<Q, Integer> enumeration = empty.Map();
    final Set<Q> elaborated = empty.Set();
    void enumerate() { dfs(q -> enumeration.computeIfAbsent(q, __ -> ordinal++)); }
    String tikz(Q q) { return sprintf("\"$q_{%s$\" [%s]", enumeration.get(q), elaborate(q)); }
    String elaborate(Q from, Q to) { return to == from ? ",loop" : inverse(from, to) ? ",bend left" : ""; }
    String traverse() { enumerate(); dfs(from -> render(from)); return this + ""; } 

    //@formatter:on
    void render(Q from) {
      Set<Σ> σs = empty.Set();
      for (final Σ σ : Σ())
        if (!σs.contains(σ))
          σs.addAll(render(from, δ(from, σ)));
    }

    Set<Σ> render(Q from, final Q to) {
      if (to == null)
        return empty.Set();
      final Set<Σ> $ = unify(from, to);
      printf("\t %s -> [\"%s\"%s] %s\n;", tikz(from), tikz($), elaborate(from, to), tikz(to));
      return $;
    }

    final String elaborate(Q q) { //@formatter:off 
      String $ = "";
      if (elaborated(q)) return $;
      elaborated.add(q);
      if (q == q0) $ += "initial,";
      if (ζ.contains(q)) $ += "accept";
      return $;
    }//@formatter:on 

    boolean inverse(Q from, Q to) {
      for (Σ σ : Σ())
        if (δ(to, σ) == from)
          return true;
      return false;
    }

    private Set<Σ> unify(Q from, Q to) {
      Set<Σ> $ = empty.Set();
      for (Σ σ : Σ())
        if (δ(from, σ) == to)
          $.add(σ);
      return $;
    }

    private String tikz(Set<Σ> σs) {
      String $ = "";
      boolean special = true;
      for (Σ σ : σs) {
        if (special)
          special = false;
        else
          $ += ", ";
        $ += σ == null ? "*" : σ;
      }
      return $;
    }

  }
}
