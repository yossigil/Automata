package finite;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

//@formatter:off
class Q {
  static int count = 0;
  final int n = count++;
  @Override public String toString() { return "q" + n; }
}

/** Abstract recognizer of a formal language over Σ */ 
interface Recognizer<Σ> {
  /** Initialize the recognizer */ void q0(); 
  /** Feed the next letter */  void feed(Σ σ);
  /** Is it in an accepting state */ boolean ζ();
}

/** An immutable transition table, complete function from Q x Σ -> Q */
abstract class Δ<Σ> { //@formatter:off
  /** Empty constructor */ Δ() { this(empty.Map()); } 
  /** Copy constructor */  Δ(Δ<Σ> that) { this(that.Δ); } 
  /** Full constructor */  Δ(Map<Σ, Map<Q, Q>> Δ) { this.Δ = Δ; }
  /** Inspector Letters seen */ final Set<Σ> Σ()  { return Δ.keySet(); } 
  /** Inspector: Transition table of given letter */  Map<Q,Q> δ(Σ σ) { if (Δ.get(σ) == null) Δ.put(σ, empty.Map()); return Δ.get(σ); } 
  /** Data: The transition table */ Map<Σ, Map<Q, Q>> Δ;
  /** Data: Sink state use to automaton complete */ private Q q$ = new Q();
  /** Inspector: complete transition function from a state and letter  */ //@formatter:on
  Q δ(Q q, Σ σ) {
    if (q == q$)
      return q$;
    final Q $ = δ(σ).get(q);
    if ($ == null)
      return q$;
    return $;
  }

  /** Inspector: set of all states seen */ //@formatter:on
  Set<Q> Q() {
    Set<Q> $ = empty.Set();
    for (Σ σ : Σ())
      for (Q q : δ(σ).keySet()) {
        $.add(q);
        $.add(δ(σ).get(q));
      }
    return $;
  }
}

class Implementation<Σ> extends Δ<Σ> implements Recognizer<Σ> { //@formatter:off
  /** Initial state */ final Q q0; 
  /** Sink state: makes the automaton complete */ final Q q$ = new Q(); 
  /** Set of accepting states */ final Set<Q> ζ; 
  /** Empty constructor */ Implementation()  { this(new Q(), empty.Set()); }
  /** Partial constructor */  Implementation(Q q0, Set<Q> ζ) { this.q0 = q0; this.ζ = ζ; }
  /** Full constructor */  Implementation(Q q0, Set<Q> ζ,  Map<Σ, Map<Q, Q>> Δ) { super(Δ); this.q0 = q0; this.ζ= ζ; }
  /** Full constructor */  Implementation(Set<Q> ζ,  Map<Σ, Map<Q, Q>> Δ) { super(Δ); this.q0 = new Q(); this.ζ= ζ; }
  /** Copy constructor */  Implementation(Implementation<Σ> that) { this(that.q0,that.ζ,that.Δ); }
  /** Current state  */ Q q = new Q();
  /** Update transition table */  Implementation<Σ>  δ(Q from, Σ σ, Q to) { δ(σ).put(from, to); return this;}
  @Override public void q0() { q = q0; } 
  @Override public void feed(Σ σ) { q = δ(q,σ); } 
  @Override public boolean ζ() { return ζ.contains(q); }
}

abstract class FSA<Σ> extends Implementation<Σ> {
// @formatter:off
//  { δ(σ).put(from,to); return this;  }
  /** Empty constructor */ FSA() {}
  /** Copy constructor */ FSA(FSA<Σ> a) { super(a); }
  /** Partial constructor */  FSA(Map<Σ, Map<Q, Q>> Δ, Set<Q> ζ) { super(ζ, Δ); }
  /** Full constructor */ FSA(Q q0, Set<Q> ζ, Map<Σ,Map<Q,Q>> Δ) { super(q0, ζ, Δ); }
  /** Helper: Copy a transition table of a single letter */  static void δ(Map<Q, Q> m1, Map<Q, Q> m2) { for (Q q : m2.keySet()) m1.put(q, m2.get(q)); } /** Inspector: Set of reachable states */ 
  /** Modifier: Add a new accepting state */ FSA<Σ> ζ(Q q) { ζ.add(q); return this; }
  /** Modifier: Add a set of new accepting state */ FSA<Σ> ζ(Set<Q> qs) { ζ.addAll(qs); return this; }
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
    return this;
  }

  /** Modifier: Add a full transition table 
   * @return */
  FSA<Σ> δ(Map<Σ, Map<Q, Q>> Δ) {
    for (Σ σ : Σ())
      δ(δ(σ), Δ.get(σ));
    return this;
  }

  /** Inspector: Set of all reachable states */
  Set<Q> q() {
    Set<Q> $ = empty.Set();
    dfs(q -> $.add(q));
    return $;
  }

  /** Exerciser: do a DFS search, supplying each state q to a consumer of */
  final void dfs(Consumer<Q> c) {
    new XDFS<Q>() {
      //@formatter:off
      @Override public Set<Q> n(Q q) { return δ(q); }
      @Override public void v(Q q) { c.accept(q); } //@formatter:on
    }.dfs(q0);
  }
}