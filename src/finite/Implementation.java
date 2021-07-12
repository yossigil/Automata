package finite;

import java.util.Map;
import java.util.Set;

import utils.empty;
import utils.set;

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
    return //
    "\t " + toString("Q", Q()) + " (all states)\n" + //
        "\t " + toString("q0", q0) + " (start state)\n" + //
        "\t " + toString("ζ", ζ) + "  (accepting states)\n" + //
        "\t " + toString("Q\\ζ", set.minus(Q(), ζ)) + " (rejecting states)\n" + //
        "\t " + toString("q", q) + " (current state)\n" + //
        "\t " + toString("Σ", Σ()) + " (alphabet)\n" + //
        super.toString()//
    ;
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