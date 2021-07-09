package finite;

enum Thompson {
  ;
  static <Σ> NFSA<Σ> or(final NFSA<Σ> a1, final NFSA<Σ> a2) {
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
  static <Σ> NFSA<Σ> not(final NFSA<Σ> a1) { return null;}
  static <Σ> NFSA<Σ> plus(final NFSA<Σ> a1) { return null;}
  //@formatter::on
 }