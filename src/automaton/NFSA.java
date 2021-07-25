package automaton;

import java.util.Map;
import java.util.Set;

import utils.empty;
import utils.set;

public class NFSA<Σ> extends FSA<Σ> { //@formatter:off
  /** Data: the table of ε transitions */ final Map<Q, Set<Q>> ε = empty.Map();
  /** Data: the initial state */ STATE<Σ> s0;
  public NFSA(automaton.Q q) { super(q); }
  /** Inspector: Set of outgoing transitions */  Set<Q> ε(Q ¢) { return ε.get(¢); } 
  @Override protected Set<Q> neighbours(Q ¢) { return set.union(super.neighbours(¢), ε(¢)); }
  @Override public FSA<Σ> minimal() { return DFSA().minimal(); } 
  public NFSA<Σ> and(NFSA<Σ> a2) { return Thompson.and(this, a2); }
  public NFSA<Σ> many() { return Thompson.many(this); }
  public NFSA<Σ> not() { return Thompson.not(this); }
  public NFSA<Σ> except(NFSA<Σ> a2) { return Thompson.except(this, a2); }
  public NFSA<Σ> or(NFSA<Σ> a2) { return Thompson.or(this, a2); }
  public NFSA<Σ> plenty() { return Thompson.plenty(this); }
  public NFSA<Σ> then(NFSA<Σ> a2) { return Thompson.then(this, a2); }
  public static <Σ> NFSA<Σ>.Builder builder(Q q) { return new NFSA<Σ>(q).new Builder(); }
  abstract class External extends FSA<Σ>.External { NFSA<Σ> This() { return NFSA.this; } }
  //@formatter:on
  /** Factory: recognizer of a single letter */
  public static <Σ> NFSA<Σ> σ(Σ ¢) {
    final Q q0 = new Q(), q1 = new Q();
    return NFSA.<Σ>builder(q0).δ(q0, ¢, q1).ζ(q1).build();
  }
  /** Factory: recognizer of the empty string */
  public static <Σ> NFSA<Σ> ε() {
    final Q q0 = new Q();
    return NFSA.<Σ>builder(q0).ζ(q0).build();
  }
  /** Factory: recognizer of the empty set */
  public static <Σ> NFSA<Σ> Φ() { return NFSA.<Σ>builder(new Q()).build(); }
  /** Factory: recognizer of arbitrary single letter */
  public static <Σ> NFSA<Σ> ʘ() {
    final Q q0 = new Q(), q1 = new Q();
    return NFSA.<Σ>builder(q0).δ(q0, null, q1).ζ(q1).build();
  }
  //@formatter:on
  public boolean run(Iterable<Σ> w) {
    var s = s0;
    for (final var σ : w) s = s.δ(σ);
    return s.ζ();
  }
  @Override Set<String> labels(Q from, Q to) {
    final var $ = super.labels(from, to);
    if (ε(from).contains(to)) $.add("\\varepsilon");
    return $;
  }
  class Builder extends FSA<Σ>.Builder {
    @Override Builder δ(Q from, Σ σ, Q to) { super.δ(from, σ, to); return this; }
    @Override Builder ζ(Q ¢) { super.ζ(¢); return this; }
    @Override Builder ζ(FSA<?> ¢) { super.ζ(¢); return this; }
    @Override Builder Δ(Δ<Σ> ¢) { super.Δ(¢); return this; }
    /** Modifier: add a new empty transition */
    Builder ε(Q from, Q to) { ε(from).add(to); return this; }
    /** Modifier: add a new table of empty transitions */
    Builder ε(NFSA<?> that) {
      that.ε.keySet().stream().forEach(q -> ε(q).addAll(that.ε(q)));
      return this;
    }
    @Override protected Set<Q> Q() {
      final var $ = set.union(super.Q(), ε.keySet());
      for (final Q q : ε.keySet()) $.addAll(ε.get(q));
      return $;
    }
    @Override NFSA<Σ> build() {
      super.build();
      for (Q q : Q) ε.putIfAbsent(q, empty.Set());
      for (Q q : Q) Δ.putIfAbsent(q, empty.Map());
      s0 = new State<Σ>(NFSA.this, q0);
      assert !Σ.contains(null);
      return NFSA.this;
    }
    private Set<Q> ε(Q ¢) {
      ε.putIfAbsent(¢, empty.Set());
      return ε.get(¢);
    }
    public Q q0() { return NFSA.this.q0; }
  }
  @Override public String toString() {
    return "Nodeterministic " + super.toString() + //
        "\t ε = " + ε + " (non-deterministic ε-transitions)\n" //
    ;
  }
  public FSA<Σ> DFSA() { return dfsa.of(this); }
}