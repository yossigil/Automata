package finite;

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
    assert σ != null;
    assert !qs.contains(null);
    return new State<Σ>(NFSA(), qs().map(q -> NFSA().δ(q, σ)).filter(q -> {
      if (q == null) System.out.println("Current = " + this.qs + "SIGMA = " + σ);
      return q != null;
    }).collect(toSet()));
  }
  @Override public Stream<Q> qs() {
    assert !qs.contains(null);
    assert !this.qs.contains(null);
    return qs.stream();
  }
  @Override public STATE<Σ> add(Q ¢) {
    assert !qs.contains(null);
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
  @Override public Set<? extends STATE<Σ>> neighbours() { return This().Σ.stream().map(λ -> δ(λ)).collect(toSet()); }
  NFSA<Σ> NFSA() { return This(); }
  /** Reinstate ε-closure */
  private void ε() {
    new Object() {
      boolean missing(Q ¢) {
        return !qs.contains(¢);
      }
      {
        assert !qs.contains(null);
        Set<finite.Q> more;
        do {
          more = qs().map(NFSA()::ε).flatMap(Set::stream).filter(this::missing).collect(toSet());
        } while (qs.addAll(more));
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
  /* One liners: //@formatter:off */
    /*  Multiple liners //@formatter:on */
  public boolean ζ() {
    return !set.intersection(qs, This().ζ).isEmpty();
  }
}