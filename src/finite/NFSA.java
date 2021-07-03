package finite;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class NFSA<Σ> extends FSA<Σ> {
  /* One liners: //@formatter:off */
  final Map<Q, Set<Q>> ε = empty.Map();               // Set of all ε transitions
  final State s0 = new State(super.q0); // The initial state
  static  <Σ> NFSA<Σ> σ(Σ σ) { return new NFSA<Σ>(σ); }
  static  <Σ> NFSA<Σ> ε(){ return new NFSA<Σ>(); }

  NFSA<Σ> plus() { return Thompson.plus(this); }
  NFSA<Σ> not() { return Thompson.not(this); }
  NFSA<Σ> or(NFSA<Σ> a2) { return Thompson.or(this, a2); }
  NFSA<Σ> then(NFSA<Σ> a2) { return Thompson.then(this, a2); }
  NFSA<Σ> and(NFSA<Σ> a2) {return Thompson.and(this, a2); }
  NFSA<Σ> ε(Q from, Q to) { ε(from).add(to); return this;}
  //@formatter:on 
  boolean run(Iterable<Σ> w) {
    var s = s0.ε();
    for (var σ : w)
      s = s.δ(σ).ε();
    return s.ζ();
  }

  Set<Q> ε(Q q) {
    if (ε.get(q) == null)
      ε.put(q, empty.Set());
    return ε.get(q);
  }

  NFSA() {
  }

  NFSA(Σ c) {
    this();
    var q1 = Q.make();
    ζ(q1);
    δ(q0, c, q1);
  }

  public NFSA(Q q0, Set<Q> ζ, Map<Σ, Map<Q, Q>> δ, Map<Q, Set<Q>> ε) {
    super(q0, ζ, δ);
    for (Q q : ε.keySet())
      this.ε.put(q, ε.get(q));
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
      final Set<Q> todo = empty.Set();
      for (State $ = new State(qs);; todo.clear()) {
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

  NFSA<Σ> star() {
    final var $ = new NFSA<Σ>();
    $.ζ($.q0);
    $.δ(δ);
    $.ε(ε);
    $.ε($.q0, q0);
    for (Q q : ζ)
      $.ε(q, $.q0);
    return $;
  }

  String TikZ() {
    return new Object() {

      String render() {
        return wrap(traverse());
      }

      String wrap(String s) {
        return "\\graph{\n" + s + "};\n";
      }

      String traverse() {
        Set<Q> sq = new XDFS<Q>() {
          @Override public void v(Q v) {
          }

          @Override public Set<Q> n(Q q) {
            final Set<Q> $ = empty.Set();
            for (Σ σ : Σ())
              if (δ(σ).get(q) != null)
                $.add(δ(σ).get(q));
            if (ε.get(q) != null)
              $.addAll(ε.get(q));
            return $;
          }
        }.dfs(q0);
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
          return "[initial,accept]";
        if (q == q0)
          return "[initial]";
        if (ζ.contains(q))
          return "[accept]";
        return "";
      }
    }.render();
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
      Set<State> ss = new DFS<State>() { @Override public void v(State s) {}}.dfs(s0);
      Map<State, Q> code = code();
      Map<State, Q> code() { Map<State,Q> $ = empty.Map(); for (var s: ss) $.put(s, new Q()); return $; }
      Q Q(State s) { return code.get(s); }
      Q q0() { return code.get(s0); }
    }.go();
  }
  public void ε(Map<Q, Set<Q>> ε) {
    for (Q q : ε.keySet()) 
      ε(q).addAll(ε.get(q));
  }
  
}