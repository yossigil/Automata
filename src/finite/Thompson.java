package finite;

enum Thompson {
  ;
  static <Σ> NFSA<Σ> or(NFSA<Σ> a1, NFSA<Σ> a2) {
    final var $ = new NFSA<Σ>();
    $.ζ(a1.ζ);
    $.ζ(a2.ζ);
    $.δ(a1.δ);
    $.δ(a2.δ);
    $.ε(a1.ε);
    $.ε(a2.ε);
    $.ε($.q0, a1.q0);
    $.ε($.q0, a2.q0);
    return $;
  }

  static <Σ> NFSA<Σ> then(NFSA<Σ> a1, NFSA<Σ> a2) {
    final var $ = new NFSA<Σ>();
    $.ε($.q0, a1.q0);
    $.ε(a1.ε);
    $.ε(a2.ε);
    $.δ(a1.δ);
    for (Q q : a1.ζ)
      $.ε(q, a2.q0);
    $.δ(a2.δ);
    $.ζ(a2.ζ);
    return $;
  }

  static <Σ> NFSA<Σ> and(NFSA<Σ> a1, NFSA<Σ> a2) {
    final var $ = new NFSA<Σ>();
    $.ζ(a1.ζ);
    $.ζ(a2.ζ);
    return $.ε($.q0, a1.q0).ε($.q0, a1.q0);
  }

  //@formatter:off
  static <Σ> NFSA<Σ> not(NFSA<Σ> a1) { return null;}
  static <Σ> NFSA<Σ> plus(NFSA<Σ> a1) { return null;}
  //@formatter::on
 }