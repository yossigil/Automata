package finite;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

enum singleton {
  ;
  static <T> Set<T> Set(T t) {
    Set<T> $ = empty.Set();
    $.add(t);
    return $;
  }
}

class NFSA<Σ> extends FSA<Σ> {
  /* One liners: //@formatter:off */
 final State s0 = new State(super.q0); // The initial state
 final Map<Q, Set<Q>> ε;               // Set of all ε transitions
 static  <Σ> NFSA<Σ> σ(Σ σ) { return new NFSA<>(σ); }
 static  <Σ> NFSA<Σ> ε(){ return new NFSA<>(); }
 NFSA<Σ> star() { return Thompson.star(this); }
 NFSA<Σ> plus() { return Thompson.plus(this); }
 NFSA<Σ> not() { return Thompson.not(this); }
 NFSA<Σ> or(NFSA<Σ> a2) { return Thompson.or(this, a2); }
 NFSA<Σ> then(NFSA<Σ> a2) { return Thompson.then(this, a2); }
 NFSA<Σ> and(NFSA<Σ> a2) {return Thompson.and(this, a2); }
 NFSA<Σ> ε(Q from, Q to) { ε(from).add(to); return this; }
 
 /*  Multiple liners //@formatter:on */
  boolean run(Iterable<Σ> w) {
    var s = s0;
    for (var σ : w)
      s = s.δ(σ);
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
    ε = empty.Map();
  }

  NFSA(Σ c) {
    this();
    var q1 = Q.make();
    ζ.add(q1);
    δ(q0, c, q1);
  }

  class State implements V<State>, Iterable<Q> {
    /* One liners: //@formatter:off */
    final Set<Q> qs = new HashSet<>();
    State() {}
    State(Q q) { add(q); }
    State(Set<Q> qs) { add(qs);}
    Q add(Q $) { if (!qs.contains($)) qs.addAll(ε($)); return $; }
    Set<Q> ε(Q q) { return NFSA.this.ε(q); }
    void add(Set<Q> qs) { for (Q q: qs) add(q); }
    @Override public Iterator<Q> iterator() { return qs.iterator(); }
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

  @SuppressWarnings("static-method") Map<State, Q> encode(Set<State> ss) {
    Map<State, Q> $ = new HashMap<>();
    for (var s : ss)
      $.put(s, new Q());
    return $;
  }

  DFSA<Σ> d() {

    return new Object() {
      /* One liners: //@formatter:off */
      DFSA<Σ> go() { return new DFSA<Σ>(q0(), ζ(), δ()); }
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