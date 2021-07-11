package finite;

enum Thompson {
  ;
  static <Σ> NFSA<Σ> or(NFSA<Σ> a1, final NFSA<Σ> a2) {
    final var $ = new NFSA<Σ>();
    $.ζ(a1.ζ).ζ(a2.ζ).δ(a1.Δ).δ(a2.Δ);
    return $.ε(a1.ε).ε(a2.ε).ε($.q0, a1.q0).ε($.q0, a2.q0);
  }
  static <Σ> NFSA<Σ> and(final NFSA<Σ> a1, final NFSA<Σ> a2) {
    final var $ = new NFSA<Σ>();
    $.ζ(a1.ζ).ζ(a2.ζ);
    return $.ε($.q0, a1.q0).ε($.q0, a1.q0);
  }
  //@formatter:off
  static <Σ> NFSA<Σ> not(NFSA<Σ> a1) {    throw new UnsupportedOperationException("11 Jul 2021");}
  static <Σ> NFSA<Σ> plenty(NFSA<Σ> a1) {    throw new UnsupportedOperationException("11 Jul 2021");}
  static <Σ> NFSA<Σ> except(NFSA<Σ> a1, NFSA<Σ> a2) {    throw new UnsupportedOperationException("11 Jul 2021");}
  //@formatter::on
 }