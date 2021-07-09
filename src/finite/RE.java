package finite;

public abstract class RE {
//@formatter:off
  abstract static class Nullary extends RE {}
  abstract static class Unary extends RE { final RE x; Unary(final RE x) { this.x = x; } }
  abstract static class Binary extends RE { final RE x1,x2; Binary(final RE x1, final RE x2) { this.x1 = x1; this.x2 = x2; } }
  abstract static class Φ extends Nullary{ }
  abstract static class ε extends Nullary{}
  abstract static class Char extends Nullary { public final char c; public Char(final char c) { this.c = c; } }
  abstract static class Star extends Unary{ public Star(final RE r) { super(r);}}
  abstract static class Plus extends Unary{ public Plus(final RE r) { super(r);}}
  abstract static class Not extends Unary{ public Not(final RE r) { super(r);}}
  abstract static class And extends RE.Binary{public And(final RE x1, final RE x2) { super(x1, x2); } }
  abstract static class Or extends RE.Binary{ public Or(final RE x1, final RE x2) { super(x1, x2); }}
  abstract static class Then extends RE.Binary{ Then(final RE x1, final RE x2) {super (x1,x2); }}
  abstract <T> T reduce(Reducer<T> r);
  //@formatter:off
  interface Reducer<T> {
    T ε();
    T Φ();
    T σ(char σ);
    T star(T t);
    T not(T t);
    T then(T t1, T t2);
    T or(T t1, T t2);
    T and(T t1, T t2);
  }

  // Factories
  public static RE ε() {
    return new ε() { @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.ε(); } };
  }

  public static RE Φ() {
    return new Φ() { @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.Φ(); } };
  }

  public static RE c(final char c) {
    return new Char(c) { @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.σ(c); } };
  }

  public final Star star() {
    return new Star(this) { @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.star(x.reduce(¢)); } };
  }

  public final Not not() {
    return new Not(this) {
      @Override
      public <T> T reduce(final Reducer<T> ¢) {
        return ¢.not(x.reduce(¢));
      }
    };
  }

  public final Or or(final RE x) {
    return new Or(this, x) {
      @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.or(x1.reduce(¢), x2.reduce(¢)); }
    };
  }

  public final Then then(final RE x) {
    return new Then(this, x) { 
      @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.then(x1.reduce(¢), x2.reduce(¢)); } 
    };
  }

  public final And and(final RE x) {
    return new And(this, x) {
      @Override public <T> T reduce(final Reducer<T> ¢) {
        return ¢.and(x1.reduce(¢), x2.reduce(¢));
      }
    };
  }

//@formatter:off
  public final RE dup() {
    return reduce(new Reducer<RE>() {
      @Override public RE ε() { return RE.ε(); }
      @Override public RE Φ() { return RE.Φ(); }
      @Override public RE σ(final char ¢) { return RE.c(¢); }
      @Override public RE not(final RE x) { return x.not(); }
      @Override public RE star(final RE x) { return x.star(); }
      @Override public RE then(final RE x1, final RE x2) { return x1.then(x2); }
      @Override public RE or(final RE x1, final RE x2) { return x1.or(x2); }
      @Override public RE and(final RE x1, final RE x2) { return x1.and(x2); }
    });
  }

  public final <Σ> NFSA<Σ> fsa() {
    return reduce(new Reducer<NFSA<Σ>>() {
      @Override public NFSA<Σ> Φ() { return NFSA.Φ(); }
      @Override public NFSA<Σ> ε() { return NFSA.ε(); }
      @Override @SuppressWarnings("unchecked") public NFSA<Σ> σ(final char σ) { return NFSA.σ((Σ) (Character) σ); }
      @Override public NFSA<Σ> star(final NFSA<Σ> ¢) { return ¢.star(); }
      @Override public NFSA<Σ> not(final NFSA<Σ> ¢) { return ¢.not(); }
      @Override public NFSA<Σ> then(final NFSA<Σ> a1, final NFSA<Σ> a2) { return a1.then(a2); }
      @Override public NFSA<Σ> or(final NFSA<Σ> a1, final NFSA<Σ> a2) { return a1.or(a2); }
      @Override public NFSA<Σ> and(final NFSA<Σ> a1, final NFSA<Σ> a2) { return a1.and(a2); }
    });
  }
}
