package automaton;

import static java.util.stream.Collectors.toSet;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

import utils.empty;

/** An immutable transition table, complete function from {@code Q} x {@code Σ}
 * to {@code Q}. The {@code null} value of the type {@code Q} (states) denotes a
 * designated sink state, i.e., a non-accepting state from which all edges lead
 * to itself. The {@code null} value of the {@code Σ} (letters) denotes a the
 * wild card letter. */
abstract class Δ<Σ> { // @formatter:off 
  /** Data: Transition table */ public final Map<Q, Map<Σ, Q>> Δ = empty.Map();
  /** Data: Wild card letter */ final Σ ANY = null;
  /** Data: Sink state */ static final Q SINK = null;
  /** Data: All states used, SINK excluded */  public final Set<Q> Q = empty.Set();
  /** Data: All letters used, ANY excluded */  public final Set<Σ> Σ = empty.Set();
  public Stream<Entry<Q,Entry<Σ,Q>>> δ() { return stream.mapOfMaps(this.Δ); }
  /** Inspector: the main transition function */ // @formatter:on 
  protected Q δ(final Q q, final Σ σ) {
    if (q == SINK) return SINK; // Starting at the sink, must end in the sink
    final Q $ = Δ.get(q).get(σ); // Wild card magic
    assert σ != ANY; // But no magic on wild card
    return $ != SINK ? $ : Δ.get(q).get(ANY);
  }
  class Builder {
    /** Modifier: Add a single transition */
    Builder δ(final Q from, final Σ σ, final Q to) {
      assert from != SINK;
      assert to != SINK;
      Δ.putIfAbsent(from, empty.Map());
      Δ.get(from).put(σ, to);
      return this;
    }
    // TODO: BUG HERE; why two versions? One at least must be wrong
    /** Modifier: copy a full transition table */
    Builder Δ(final Δ<Σ> Δ) {
      for (Q q : Δ.Q) //
        if (q != SINK) //
          for (final Σ σ : Δ.σ(q)) //
            δ(q, σ, Δ.δ(q, σ));
      return this;
    }
    /** Modifier: copy a full transition table */
    Builder Δ(final Map<Q, Map<Σ, Q>> m) {
      stream.map(m)//
          .forEach(e -> stream.map(e.getValue())//
              .forEach(ee -> δ(e.getKey(), ee.getKey(), ee.getValue())));
      return this;
    }
    Δ<Σ> build() { return build(Q(), Σ()); }
    Set<Σ> Σ() { // Initial approximation of the set of letters
      assert !Δ.values().stream().flatMap(x -> x.keySet().stream()).filter(x -> x != ANY).collect(toSet())
          .contains(null);
      return Δ.values().stream().flatMap(x -> x.keySet().stream()).filter(x -> x != ANY).collect(toSet());
    }
    Set<Q> Q() { // Initial approximation of the set of states; builders of subclasses may refine
      final Set<Q> $ = empty.Set();
      $.addAll(Δ.keySet());
      for (Q q : Δ.keySet()) $.addAll(Δ.get(q).values());
      return $;
    }
    final Δ<Σ> build(Set<Q> qs, Set<Σ> σs) {
      for (Q q : qs) Δ.putIfAbsent(q, empty.Map());
      Δ.this.Q.addAll(qs);
      assert !Σ.contains(null);
      Δ.this.Σ.addAll(σs);
      assert !Σ.contains(null);
      return Δ.this;
    }
  } // @formatter:off 
  /** Class summary */ @Override public String toString() { return "\t Δ = " + Δ + " (transition function)\n"; }
  /** Inspector: Set of all states for which a transition on a letter is defined */ 

  // Details: // @formatter:on
  /** Inspector: Collection of states reachable from a given state */
  protected Collection<Q> neighbours(final Q ¢) {
    assert ¢ != null;
    assert !Δ.get(¢).values().contains(null);
    return Δ.get(¢).values();
  }
  final Set<Σ> letters(final Q from, final Q to) {
    return stream.map(Δ.get(from))//
        .filter(e -> e.getValue() == to)//
        .map(e -> e.getKey()).collect(toSet());
  }
  /** Inspector: get all letters */
  Collection<Σ> σ(Q ¢) { return Δ.get(¢).keySet(); }
}