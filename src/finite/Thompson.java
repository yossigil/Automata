package finite;

enum Thompson {
  ;
  static <Σ, Self extends NFSA<Σ, Self>> NFSA<Σ, Self> or(NFSA<Σ, Self> a1, NFSA<Σ, Self> a2) {
    final var $ = new NFSA<Σ, Self>().ζ(a1.ζ).ζ(a2.ζ);
    return $.ε($.q0, a1.q0).ε($.q0, a1.q0);
  }

  static <Σ, Self extends NFSA<Σ, Self>> NFSA<Σ, Self> then(NFSA<Σ, Self> a1, NFSA<Σ, Self> a2) {
    final var $ = new NFSA<Σ, Self>().ζ(a2.ζ);
    $.ε($.q0, a1.q0);
    for (Q q : a1.ζ)
      $.ε(q, a2.q0);
    return $;
  }

  static <Σ, Self extends NFSA<Σ, Self>> NFSA<Σ, Self> and(NFSA<Σ, Self> a1, NFSA<Σ, Self> a2) {
    final var $ = new NFSA<Σ, Self>().ζ(a1.ζ).ζ(a2.ζ);
    return $.ε($.q0, a1.q0).ε($.q0, a1.q0);
  }

  //@formatter:off
  static <Σ, Self extends NFSA<Σ, Self>> NFSA<Σ, Self> not(NFSA<Σ, Self> a1) { return null;}
  static <Σ, Self extends NFSA<Σ, Self>> NFSA<Σ, Self> star(NFSA<Σ, Self> a1) { return null;}
  static <Σ, Self extends NFSA<Σ, Self>> NFSA<Σ, Self> plus(NFSA<Σ, Self> a1) { return null;}
  //@formatter::on
 }