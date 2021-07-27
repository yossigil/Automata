package automaton;

enum Thompson {
  ;
  static <Σ> NFSA<Σ> or(NFSA<Σ> n1, final NFSA<Σ> n2) {
    NFSA<Σ> a1 = n1.refresh();
    NFSA<Σ> a2 = n2.refresh();
    final Q $ = new Q();
    return NFSA.<Σ>builder($)//
        .ε($, a1.q0) //
        .ε($, a2.q0) //
        .Δ(a1).Δ(a2) //
        .ε(a1).ε(a2) //
        .ζ(a1).ζ(a2) //
        .build();
  }
  static <Σ> NFSA<Σ> and(final NFSA<Σ> a1, final NFSA<Σ> a2) {
    throw new UnsupportedOperationException("26 Jul 2021");
  }
  static <Σ> NFSA<Σ> many(NFSA<Σ> n) {
    NFSA<Σ> a = n.refresh();
    final var $ = NFSA.<Σ>builder(a.q0).ε(a.q0, a.q0).ζ(a.q0).Δ(a).ε(a);
    for (final Q q : a.ζ) $.ε(q, $.q0());
    return $.build();
  }
  static <Σ> NFSA<Σ> then(NFSA<Σ> n1, NFSA<Σ> n2) {
    NFSA<Σ> a1 = n1.refresh();
    NFSA<Σ> a2 = n2.refresh();
    final var $ = NFSA.<Σ>builder(a1.q0).ε(a1).ε(a2).Δ(a1).Δ(a2).ζ(a2);
    for (final Q q : a1.ζ) $.ε(q, a2.q0);
    return $.build();
  }
//@formatter:off
  static <Σ> NFSA<Σ> not(NFSA<Σ> a1) {    throw new UnsupportedOperationException("11 Jul 2021");}
  static <Σ> NFSA<Σ> plenty(NFSA<Σ> a1) {    throw new UnsupportedOperationException("11 Jul 2021");}
  static <Σ> NFSA<Σ> except(NFSA<Σ> a1, NFSA<Σ> a2) {    throw new UnsupportedOperationException("11 Jul 2021");}
}