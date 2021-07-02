package finite;

enum Thompson {;
  //@formatter:off
  static <Σ, Self extends NFSA<Σ, Self>> NFSA<Σ, Self> or(NFSA<Σ, Self> a1, NFSA<Σ, Self> a2) { return null;}
  static <Σ, Self extends NFSA<Σ, Self>> NFSA<Σ, Self> then(NFSA<Σ, Self> a1, NFSA<Σ, Self> a2) { return null;}
  static <Σ, Self extends NFSA<Σ, Self>> NFSA<Σ, Self> and(NFSA<Σ, Self> a1, NFSA<Σ, Self> a2) { return null;}
  static <Σ, Self extends NFSA<Σ, Self>> NFSA<Σ, Self> not(NFSA<Σ, Self> a1) { return null;}
  static <Σ, Self extends NFSA<Σ, Self>> NFSA<Σ, Self> star(NFSA<Σ, Self> a1) { return null;}
  static <Σ, Self extends NFSA<Σ, Self>> NFSA<Σ, Self> plus(NFSA<Σ, Self> a1) { return null;}
  //@formatter::on
 }