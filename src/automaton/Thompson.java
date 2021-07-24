package automaton;

enum Thompson {
  ;
  static <Σ> NFSA<Σ> or(NFSA<Σ> a1, final NFSA<Σ> a2) {
    final Q q0 = new Q();
    return NFSA.<Σ>builder(q0)//
        .ε(q0, a1.q0) //
        .ε(q0, a2.q0) //
        .Δ(a1).Δ(a2) //
        .ε(a1).ε(a2) //
        .ζ(a1).ζ(a2) //
        .build();
  }
  static <Σ> NFSA<Σ> and(final NFSA<Σ> a1, final NFSA<Σ> a2) {
    Q $ = new Q();
    return NFSA.<Σ>builder($).ζ(a1).ζ(a2).ε($, a1.q0).ε($, a1.q0).build();
  }
  static <Σ> NFSA<Σ> many(NFSA<Σ> a) {
    final var $ = NFSA.<Σ>builder(a.q0).ε(a.q0, a.q0).ζ(a.q0).Δ(a);
    for (final Q q : a.ζ) $.ε(q, $.q0());
    return $.build();
  }
  static <Σ> NFSA<Σ> then(NFSA<Σ> a1, NFSA<Σ> a2) {
    final var $ = NFSA.<Σ>builder(a1.q0).ε(a1).ε(a2).Δ(a1).Δ(a2).ζ(a2);
    for (final Q q : a1.ζ) $.ε(q, a2.q0);
    return $.build();
  }
//@formatter:off
  static <Σ> NFSA<Σ> not(NFSA<Σ> a1) {    throw new UnsupportedOperationException("11 Jul 2021");}
  static <Σ> NFSA<Σ> plenty(NFSA<Σ> a1) {    throw new UnsupportedOperationException("11 Jul 2021");}
  static <Σ> NFSA<Σ> except(NFSA<Σ> a1, NFSA<Σ> a2) {    throw new UnsupportedOperationException("11 Jul 2021");}
}