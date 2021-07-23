package finite;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import utils.empty;
import utils.set;
import utils.val;

public class FSA<Σ> extends Δ<Σ> implements Recognizer<Σ> { //@formatter:off

 /** Data: Set of accepting states */ final Set<Q> ζ = empty.Set();
  FSA() { }
  /** Data: Initial state */ public Q q0 = new Q(); 
  /** Data: Current state  */ Q q;
  /** Data: Instance number */ final int n = ++N;
  /** Data: Instance counter */ static int N;
  @Override public void reset() { q = q0; }
  @Override public void feed(final Σ ¢) { q = δ(q, ¢); }
  @Override public boolean accepting() { return ζ.contains(q); }
  /** Class summary: // @formatter:on */
  @Override public String toString() {
    return "FSA #" + n + "/" + N + " = ⟨Σ,q0,Q,ζ,Δ⟩ \n" + //
        "\t " + val.show("Σ", Σ) + " (alphabet)\n" + //
        "\t " + val.show("q0", q0) + " (initial state)\n" + //
        "\t " + val.show("Q", Q) + " (all states)\n" + //
        "\t " + val.show("Q'$", QQ()) + " (reachable states)\n" + //
        "\t " + val.show("Q\\Q'$", set.minus(Q,QQ())) + " (unreachable states)\n" + //
        "\t " + val.show("ζ", ζ) + "  (accepting states)\n" + //
        "\t " + val.show("Q\\ζ", set.minus(Q, ζ)) + " (rejecting states)\n" + //
        "\t " + val.show("q", q) + " (current state)\n" + //
        super.toString()//
    ;
  }
  /** Factory: creates an immutable instance */
  public static <Σ> FSA<Σ>.Builder builder() {
    return new FSA<Σ>().new Builder();
  }
  class Builder extends Δ<Σ>.Builder {
    FSA<Σ> build() { super.build(); if (q0 == null) q0 = new Q();
      assert !Σ.contains(null);
    return FSA.this; }
    Builder δ(Q from, Σ σ, Q to) { super.δ(from, σ, to); return this; }
    Builder ζ(final Q ¢) { ζ.add(¢); return this; }
    Builder ζ(Set<? extends Q> ¢) { ζ.addAll(¢); return this; }
    Builder ζ(final FSA<?> ¢) { ζ.addAll(¢.ζ); return this; }
    Builder q0(Q ¢) { q0 = ¢; return this; }
    @Override Set<Q> Q() { return set.union(super.Q(), ζ, Set.of(q0)); }
    @Override Builder Δ(Δ<Σ> ¢) { super.Δ(¢); return this; }
    @Override Builder Δ(Map<Q, Map<Σ, Q>> ¢) { super.Δ(¢); return this; }
  }
  /** Exerciser: do a DFS search, supplying each state q to a given consumer
   * 
   * @return the set of states, in the order they were encountered */
  protected final Set<Q> dfs(final Consumer<Q> c) {
    assert q0 != null;
    return new XDFS<Q>() {
      @Override public Collection<Q> neighbours(final Q ¢) { return FSA.this.neighbours(¢); }
      @Override public Q visit(final Q ¢) { c.accept(¢); return ¢; }
    }.dfs(q0);
  }
  /** Inspector: Set of all reachable states */
  Set<Q> QQ() {
    final Set<Q> $ = new LinkedHashSet<>();
    dfs(λ -> $.add(λ));
    return $;
  }
  abstract class External { FSA<Σ> self() {return FSA.this; } }
  public String TikZ() {
    return TikZ.of(this);
  }
  public FSA<Σ> minimal() {
    return minimal.FSA(this);
  }
  public boolean run(final String ¢) { return run(¢.toCharArray()); }
  boolean run(final char[] cs) {
    return run(() -> new Iterator<>() {
      int i;
      @Override public boolean hasNext() { return i < cs.length; }
      @Override
      @SuppressWarnings("unchecked") public Σ next() { return (Σ) (Character) cs[i++]; }
    });
  }
}
