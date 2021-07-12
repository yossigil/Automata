package finite;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import utils.empty;
import utils.set;

public class NFSA<Σ> extends FSA<Σ> {
  public NFSA<Σ> and(NFSA<Σ> a2) { return Thompson.and(this, a2); }
  public NFSA<Σ> many() { return Thompson.many(this); }
  public NFSA<Σ> not() { return Thompson.not(this); }
  public NFSA<Σ> except(NFSA<Σ> a2) { return Thompson.except(this, a2); }
  public NFSA<Σ> or(NFSA<Σ> a2) { return Thompson.or(this, a2); }
  public NFSA<Σ> plenty() { return Thompson.plenty(this); }
  public NFSA<Σ> then(NFSA<Σ> a2) { return Thompson.then(this, a2); }
  @Override public String toString() {
    return "Nodeterministic " + super.toString() + //
        "\t ε = " + ε + " (non-deterministic ε-transitions)\n" //
    ;
  }
  public DFSA<Σ> DFSA() {
    return new Object() {
      /* One liners: //@formatter:off */
      Map<State, Q> code() { final Map<State, Q> $ = empty.Map(); for (final var s : ss) $.put(s, new Q()); return $; }
      Set<State>    ss   = new DFS<State>() { @Override public void visit(State s) {} }.dfs(s0);
      Map<State, Q> code = code();
      DFSA<Σ> DFSA() { return new DFSA<>(q0(), ζ(), δ()); }
      Q Q(State ¢) { return code.get(¢); } 
      Q q0() { return code.get(s0); }
      //@formatter:on */
      Map<Σ, Map<Q, Q>> δ() {
        final Map<Σ, Map<Q, Q>> $ = empty.Map();
        for (final var σ : Σ()) $.put(σ, δ(σ));
        return $;
      }
      Map<Q, Q> δ(Σ σ) {
        final Map<Q, Q> $ = empty.Map();
        ss.stream().forEach(λ -> $.put(Q(λ), Q(λ.ε().δ(σ).ε())));
        return $;
      }
      Set<Q> ζ() {
        final Set<Q> $ = empty.Set();
        for (final var s : ss) if (s.ζ()) $.add(Q(s));
        return $;
      }
    }.DFSA();
  }
  @Override Set<String> labels(finite.Q from, finite.Q to) {
    final var $ = super.labels(from, to);
    if (ε(from).contains(to)) $.add("\\varepsilon");
    return $;
  }
  @Override Set<Q> Q() {
    final var $ = set.union(super.Q(), ε.keySet());
    for (final Q q : ε.keySet()) $.addAll(ε.get(q));
    return $;
  }
  boolean run(Iterable<Σ> w) {
    var s = s0.ε();
    for (final var σ : w) {
      NFSA<Σ>.State δ2 = s.δ(σ);
      s = δ2.ε();
    }
    return s.ζ();
  }
  @Override protected Set<Q> δ(Q q) {
    super.δ(q);
    final var $ = new State(ε(q)).add(super.δ(q));
    for (final Σ σ : Σ()) $.add(new State().δ(σ).ε().qs);
    return $.qs;
  }
  //@formatter:on
  /** Modifier: add a new table of empty transitions */
  NFSA<Σ> ε(Map<Q, Set<Q>> ε) {
    for (final Q q : ε.keySet()) if (ε.get(q) != null) ε(q).addAll(ε.get(q));
    return this;
  }
  /* One liners: //@formatter:off */
  Set<Q> ε(Q ¢) { if (ε.get(¢) == null) ε.put(¢, empty.Set()); return ε.get(¢); } // Set of outgoing transitions
  /** Data: the table of ε transitions */ final Map<Q, Set<Q>> ε = empty.Map();
  /** Data: the initial set of states */ final State s0 = new State(super.q0); // The initial state
  /** Constructor: Empty */ NFSA() { }
  /** Constructor: Copy */ NFSA(NFSA<Σ> that) { this(that.q0, that.ζ, that.Δ, that.ε); }
  /** Constructor: Entire data */ NFSA(Map<Σ, Map<Q, Q>> Δ, Set<Q> ζ, Map<Q, Set<Q>> ε) { super(Δ, ζ); ε(ε);  }
  /** Modifier: add a new empty transition */ NFSA<Σ> ε(Q from, Q to) { ε(from).add(to); return this; }
  /** Factory: recognizer of a single letter */ public static <Σ> NFSA<Σ> σ(Σ ¢) { return new NFSA<>(¢); }
  /** Factory: recognizer of the empty string */ public static  <Σ> NFSA<Σ> ε(){ final var $ = new NFSA<Σ>(); $.ζ($.q0); return $;}
  /** Factory: recognizer of the empty set */ public static  <Σ> NFSA<Σ> Φ(){ return new NFSA<>(); }
  /** Factory: recognizer of arbitrary single letter */ public static <Σ> NFSA<Σ> ʘ() {
    final var $ = new NFSA<Σ>();
    final var q1 = new Q();
    $.δ($.q0,null,q1).ζ(q1);
    return $;
  }

  public NFSA(Q q0, Set<Q> ζ, Map<Σ, Map<Q, Q>> δ, Map<Q, Set<Q>> ε) {
    super(q0, ζ, δ);
    for (final Q q : ε.keySet()) this.ε.put(q, ε.get(q));
  }

  public NFSA(Σ σ) {
    final var q = new Q();
    δ(q0, σ, q).ζ(q);
  }




  class State implements Vertex<State>, Iterable<Q> {

    @Override public boolean equals(Object o) {
      if (o == this) return true;
      if ((o == null) || (getClass() != o.getClass())) return false;
      @SuppressWarnings("unchecked")
      final var other = (State) o;
      return qs.equals(other.qs);
    }

    /* One liners: //@formatter:off */
    @Override public int hashCode() { return qs.hashCode(); }
    @Override public Iterator<Q> iterator() { return qs.iterator(); }
    @Override public Set<State> neighbours() {
      final Set<State> $  = new HashSet<>();
      final var      ε  = ε();
      final var           σ2 = Σ();
      for (final var σ : σ2) $.add(ε.δ(σ).ε());
      return $;
    }
    @Override public String toString() { return qs + ""; }
    void add(Q ¢) { qs.add(¢); ε(); }
    State add(Set<Q> qs) { for (final Q q: qs) add(q); return this;}

    boolean has(Q ¢) { return qs.contains(¢); }
    State δ(Σ σ) {
      final var $ = new State();
      for (final var q : this) $.add(NFSA.this.δ(q, σ));
      return $;
    }
    //@formatter:on
    State ε() {
      for (final Set<Q> todo = empty.Set();; add(todo), todo.clear()) {
        for (final Q q : this) for (final Q qε : NFSA.this.ε(q)) if (!has(qε)) todo.add(qε);
        if (todo.isEmpty()) return this;
      }
    }
    /*  Multiple liners //@formatter:on */
    boolean ζ() {
      for (final var q : this) if (ζ.contains(q)) return true;
      return false;
    }
    State() {}
    State(Q q) { add(q); }
    State(Set<Q> qs) { add(qs); }
    final Set<Q> qs = new HashSet<>();
  }
}