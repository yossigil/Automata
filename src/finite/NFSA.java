package finite;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

class NFSA<Σ, Self extends NFSA<Σ, Self>> extends FSA<Σ, Self> {
  /* One liners: //@formatter:off */
 final Map<Q, Set<Q>> ε = empty.Map();               // Set of all ε transitions
 final State s0 = new State(super.q0); // The initial state
 @SuppressWarnings("unchecked") Self selfie() { return (Self) this; }
static  <Σ, Self extends NFSA<Σ, Self>> Self σ(Σ σ) { return new NFSA<Σ, Self>(σ).selfie(); }
 static  <Σ, Self extends NFSA<Σ, Self>> Self ε(){ return new NFSA<Σ, Self>().selfie(); }
 Self star() { return Thompson.star(this).selfie(); }
 Self plus() { return Thompson.plus(this).selfie(); }
 Self not() { return Thompson.not(this).selfie(); }
 Self or(NFSA<Σ, Self> a2) { return Thompson.or(this, a2).selfie(); }
 Self then(NFSA<Σ, Self> a2) { return Thompson.then(this, a2).selfie(); }
 Self and(NFSA<Σ, Self> a2) {return Thompson.and(this, a2).selfie(); }
 Self ε(Q from, Q to) { ε(from).add(to); return this.selfie();}
//@formatter:on 
  boolean run(Iterable<Σ> w) {
    var s = s0;
    for (var σ : w)
      s = s.ε().δ(σ).ε();
    return s.ζ();
  }

  Set<Q> ε(Q q) {
    if (ε.get(q) == null)
      ε.put(q, empty.Set());
    ε.get(q).addAll(ε(ε.get(q)));
    return ε.get(q);
  }

  Set<Q> ε(Set<Q> s) {
    Set<Q> $ = new LinkedHashSet<>();
    for (;;) {
      final Set<Q> todo = empty.Set();
      for (var q : $)
        for (var qε : ε(q)) {
          if ($.contains(qε))
            continue;
          $.add(qε);
          todo.add(qε);
        }
      if (todo.isEmpty())
        return $;
    }
  }

  NFSA() {
  }

  NFSA(Σ c) {
    this();
    var q1 = Q.make();
    ζ(q1);
    δ(q0, c, q1);
  }

  class State implements V<State>, Iterable<Q> {
    /* One liners: //@formatter:off */
    final Set<Q> qs = new HashSet<>();
    boolean has(Q q) { return qs.contains(q); }
    State() {}
    State(Q q) { add(q); }
    State(Set<Q> qs) { add(qs);}
    void add(Q q) { qs.add(q); }
    void add(Set<Q> qs) { for (Q q: qs) add(q); }
    @Override public Iterator<Q> iterator() { return qs.iterator(); }
    //@formatter:on 
    State ε() {
      for (State $ = new State(qs);;) {
        final Set<Q> todo = empty.Set();
        for (Q q : $)
          for (Q qε : NFSA.this.ε(q)) {
            if ($.has(qε))
              continue;
            todo.add(qε);
          }
        if (todo.isEmpty())
          return $;
        $.add(todo);
      }
    }
    /*  Multiple liners //@formatter:on */

    State δ(Σ σ) {
      final var $ = new State();
      for (var q : this)
        $.add(NFSA.this.δ(q, σ));
      return $;
    }

    @Override public Iterable<State> neighbours() {
      final Set<State> $ = new HashSet<>();
      for (var σ : δ.keySet())
        $.add(δ(σ));
      return $;
    }

    boolean ζ() {
      for (var q : this)
        if (ζ.contains(q))
          return true;
      return false;
    }
  }

  xDFSA<Σ> d() {
    return new Object() {
      /* One liners: //@formatter:off */
      xDFSA<Σ> go() { return new xDFSA<Σ>(q0(), ζ(), δ()); }
      /* Multi liners: //@formatter:on */
      Set<Q> ζ() {
        Set<Q> $ = empty.Set();
        for (var s : ss)
          if (s.ζ())
            $.add(Q(s));
        return $;
      }

      Map<Σ, Map<Q, Q>> δ() {
        Map<Σ, Map<Q, Q>> $ = empty.Map();
        for (var σ : Σ()) {
          $.put(σ, δ(σ));
        }
        return $;
      }

      Map<Q, Q> δ(Σ σ) {
        Map<Q, Q> $ = empty.Map();
        for (var s : ss)
          $.put(Q(s), Q(s.δ(σ)));
        return $;
      }

      /*Auxiliary definitions : //@formatter:off */
      Set<State> ss = new DFS<State>() { @Override public void v(State s) {}}.dfs(s0);
      Map<State, Q> code = code();
      Map<State, Q> code() { Map<State,Q> $ = empty.Map(); for (var s: ss) $.put(s, new Q()); return $; }
      Q Q(State s) { return code.get(s); }
      Q q0() { return code.get(s0); }
      
    }.go();
  }
}