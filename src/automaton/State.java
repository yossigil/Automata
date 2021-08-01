package automaton;

import static java.util.stream.Collectors.toSet;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

import utils.empty;
import utils.set;

class State<Σ> extends NFSA<Σ>.External implements STATE<Σ> {
  /** Data: an ε-closed set of contained plain states */
  final Set<Q> qs = empty.Set();
  /** Constructor: from single plain state */
  State(NFSA<Σ> a, Q q) { a.super(); add(q); }
  /** Constructor: from set of plain states */
  State(NFSA<Σ> a, Collection<Q> qs) {
    a.super();
    assert !qs.contains(null);
    assert !this.qs.contains(null);
    add(qs);
    assert !this.qs.contains(null);
  }
  @Override public STATE<Σ> δ(Σ σ) {
    assert !qs.contains(null);
    return new State<Σ>(NFSA(), qs().flatMap(q -> NFSA().Δ.get(q).values().stream()).filter(q -> q != Δ.REJECT).collect(toSet()));
  }
  @Override public Stream<Q> qs() {
    assert !qs.contains(null);
    assert !this.qs.contains(null);
    return qs.stream();
  }
  @Override public STATE<Σ> add(Q ¢) {
    assert !qs.contains(null);
    assert ¢ != null; 
    assert !this.qs.contains(null);
    if (qs.add(¢)) ε();
    assert !qs.contains(null);
    assert !this.qs.contains(null);
    return this;
  }
  @Override public STATE<Σ> add(Collection<Q> ¢) { if (this.qs.addAll(¢)) ε(); return this; }
  @Override public int hashCode() { return qs.hashCode(); }
  @Override public Iterator<Q> iterator() { return qs.iterator(); }
  @Override public String toString() { return qs + ""; }
  @Override public Set<? extends STATE<Σ>> neighbours() { return my().Σ.stream().map(λ -> δ(λ)).filter(s->s.valid()).collect(toSet()); }
  NFSA<Σ> NFSA() { return my(); }
  /** Reinstate ε-closure */
  private void ε() {
    new Object() {
      boolean missing(Q ¢) {
        return !qs.contains(¢);
      }
      {
        assert !qs.contains(null);
        Set<automaton.Q> more;
        do //
        more = qs().map(NFSA()::ε).flatMap(Set::stream).filter(this::missing).collect(toSet());//
        while (qs.addAll(more));
      }
    };
  }
  //@formatter:on
  @Override public boolean equals(Object o) {
    if (o == this) return true;
    if (o == null || getClass() != o.getClass()) return false;
    @SuppressWarnings("unchecked") final var other = (State<Σ>) o;
    return qs.equals(other.qs);
  }
  public boolean ζ() {
    return !set.intersection(qs, my().ζ).isEmpty();
  }
}