package finite;

enum Thompson {
  ;
  static <Σ> NFSA<Σ> or(NFSA<Σ> a1, final NFSA<Σ> a2) {
    final var $ = new NFSA<Σ>();
    $.ζ(a1.ζ).ζ(a2.ζ);
    $.δ(a1.Δ).δ(a2.Δ);
    return $.ε(a1.ε).ε(a2.ε).ε($.q0, a1.q0).ε($.q0, a2.q0);
  }
  static <Σ> NFSA<Σ> and(final NFSA<Σ> a1, final NFSA<Σ> a2) {
    final var $ = new NFSA<Σ>();
    $.ζ(a1.ζ).ζ(a2.ζ);
    return $.ε($.q0, a1.q0).ε($.q0, a1.q0);
  }
  static <Σ> NFSA<Σ> many(NFSA<Σ> a) {
    final var $ = new NFSA<>(a);
    $.ε($.q0, $.q0).ζ($.q0);
    for (final Q q : $.ζ) $.ε(q, $.q0);
    return $;
  }
  static <Σ> NFSA<Σ> then(NFSA<Σ> a1, NFSA<Σ> a2)   {
    final var $ = new NFSA<Σ>().ε(a1.ε).ε(a2.ε);
    $.δ(a1.Δ).δ(a2.Δ); 
    $.ζ(a2.ζ); 
    for (final Q q : a1.ζ) $.ε(q, a2.q0);
    return $;
  }
//@formatter:off
  static <Σ> NFSA<Σ> not(NFSA<Σ> a1) {    throw new UnsupportedOperationException("11 Jul 2021");}
  static <Σ> NFSA<Σ> plenty(NFSA<Σ> a1) {    throw new UnsupportedOperationException("11 Jul 2021");}
  static <Σ> NFSA<Σ> except(NFSA<Σ> a1, NFSA<Σ> a2) {    throw new UnsupportedOperationException("11 Jul 2021");}

}