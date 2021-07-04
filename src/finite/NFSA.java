package finite;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class NFSA<Σ> extends FSA<Σ> {
  @Override Set<Q> δ(Q q) {
    var $ = super.δ(q);
    $.addAll(ε(q));
    return $;
  }

  /* One liners: //@formatter:off */
  final Map<Q, Set<Q>> ε = empty.Map();               // Set of all ε transitions
  /** Empty constructor: The empty language */ NFSA() { }
  /** Full constructor */ NFSA(Map<Σ, Map<Q, Q>> Δ, Set<Q> ζ, Map<Q, Set<Q>> ε) { super(Δ, ζ); ε(ε);  }  
  /** Copy constructor */ NFSA(NFSA<Σ> that) { this(that.q0, that.ζ, that.Δ, that.ε);   }
  /** A recognizer of a single letter */ NFSA(Σ σ) { final Q q1 = new Q(); ζ(q1); δ(q0, σ, q1); }
  NFSA<Σ> ε(Q from, Q to) { ε(from).add(to); return this; } // Add an ε transition (fluently)
  NFSA<Σ> ε(Map<Q, Set<Q>> ε) { for (Q q : ε.keySet()) this.ε(q).addAll(ε.get(q)); return this; }
  Set<Q> ε(Q q) { if (ε.get(q) == null) ε.put(q, empty.Set()); return ε.get(q); } // Set of outgoing transitions
  final State s0 = new State(super.q0); // The initial state
  static  <Σ> NFSA<Σ> σ(Σ σ) { return new NFSA<Σ>(σ); }
  static  <Σ> NFSA<Σ> ε(){ var $ = new NFSA<Σ>(); $.ζ($.q0); return $;}
  NFSA<Σ> plus() { return Thompson.plus(this); }
  NFSA<Σ> not() { return Thompson.not(this); }
  NFSA<Σ> or(NFSA<Σ> a2) { return Thompson.or(this, a2); }
  NFSA<Σ> then(NFSA<Σ> a2) { return Thompson.then(this, a2); }
  NFSA<Σ> and(NFSA<Σ> a2) {return Thompson.and(this, a2); }
  //@formatter:on 
  boolean run(Iterable<Σ> w) {
    var s = s0.ε();
    for (var σ : w)
      s = s.δ(σ).ε();
    return s.ζ();
  }


  public NFSA(Q q0, Set<Q> ζ, Map<Σ, Map<Q, Q>> δ, Map<Q, Set<Q>> ε) {
    super(q0, ζ, δ);
    for (Q q : ε(q))
      this.ε.put(q, ε.get(q));
  }





  class State implements V<State>, Iterable<Q> {
    @Override public int hashCode() {
      return qs.hashCode();
    }

    @Override public boolean equals(Object o) {
      if (this == o)
        return true;
      if (o == null)
        return false;
      if (getClass() != o.getClass())
        return false;
      @SuppressWarnings("unchecked") State other = (State) o;
      return qs.equals(other.qs);
    }

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
      final Set<Q> todo = empty.Set();
      for (State $ = new State(qs);; $.add(todo),todo.clear()) {
        for (Q q : $)
          for (Q qε : NFSA.this.ε(q)) {
            if ($.has(qε))
              continue;
            todo.add(qε);
          }
        if (todo.isEmpty())
          return $;
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
      final State ε = ε();
        for (var σ : Σ())
          $.add(ε.δ(σ).ε());
      return $;
    }

    boolean ζ() {
      for (var q : this)
        if (ζ.contains(q))
          return true;
      return false;
    }
  }

  NFSA<Σ> star() {
    final var $ = new NFSA<Σ>(Δ, ζ, ε);
    $.δ(Δ);
    $.ε(ε);
    $.ζ($.q0);
    $.ε($.q0, q0);
    for (Q q : ζ)
      $.ε(q, $.q0);
    return $;
  }

  String TikZ() {
    return new Object() {
// @formatter:off
      String render() { return wrap(traverse()); } 
      String wrap(String s) { return "\\graph{\n" + s + "};\n"; } 
// @formatter:on
      String traverse() {
        dfs(q -> Q(q));
        String $ = "";
        for (Q from : ε.keySet())
          for (Q to : ε.get(from))
            $ += "\t " + Q(from) + " -> [epsilon]" + Q(to) + ";\n";
        for (Σ σ : Σ()) {
          Map<Q, Q> δσ = δ(σ);
          for (Q from : δσ.keySet())
            $ += "\t " + Q(from) + "->[\"" + σ + "\"] " + Q(δσ.get(from)) + ";\n";
        }
        return $;
      }

      int ordinal = 0;
      Map<Q, Integer> n = empty.Map();

      String Q(Q q) {
        if (n.get(q) == null)
          n.put(q, ordinal++);
        return "\"$q_{" + n.get(q) + "}$\"" + properties(q);
      }

      private String properties(Q q) {
        if (q == q0 && ζ.contains(q))
          return "[state,initial,accept]";
        if (q == q0)
          return "[state,initial]";
        if (ζ.contains(q))
          return "[state,accept]";
        return "[]";
      }
    }.render();
  }

  DFSA<Σ> d() {
    return new Object() {
    /* One liners: //@formatter:off */
      DFSA<Σ> go() { return new DFSA<Σ>(q0(), ζ(), δ()); }  
      Q q0() { return code.get(s0); }
      Set<State> ss = new DFS<State>() { @Override public void v(State s) {}}.
          dfs(s0);
      Map<State, Q> code = code();
      Map<State, Q> code() { Map<State,Q> $ = empty.Map(); for (var s: ss) $.put(s, new Q()); return $; }
      Q Q(State s) { return code.get(s); } //@formatter:on */

      Set<Q> ζ() {
        Set<Q> $ = empty.Set();
        for (var s : ss)
          if (s.ζ())
            $.add(Q(s));
        return $;
      }

      Map<Σ, Map<Q, Q>> δ() {
        Map<Σ, Map<Q, Q>> $ = empty.Map();
        for (var σ : Σ())
          $.put(σ, δ(σ));
        return $;
      }

      Map<Q, Q> δ(Σ σ) {
        Map<Q, Q> $ = empty.Map();
        for (var s : ss)
          $.put(Q(s), Q(s.δ(σ)));
        return $;
      }
      /*Auxiliary definitions : //@formatter:off */
    }.go();
  }
  
}