package finite;

public abstract class RE {
//@formatter:off
  abstract static class Nullary extends RE {}
  abstract static class Unary extends RE {
	  public final RE x;
	  public Unary(RE x) { this.x = x; }
  }
  abstract static class Binary extends RE {
	  public final RE x1,x2;
	  public Binary(RE x1, RE x2) { this.x1 = x1; this.x2 = x2; }
  }
  abstract static class Φ extends Nullary{ }
  abstract static class ε extends Nullary{}
  abstract static class Char extends Nullary { public final char c; public Char(char c) { this.c = c; } }
  abstract static class Star extends Unary{ public Star(RE r) { super(r);}}
  abstract static class Plus extends Unary{ public Plus(RE r) { super(r);}}
  abstract static class Not extends Unary{ public Not(RE r) { super(r);}}
  abstract static class And extends RE.Binary{public And(RE x1, RE x2) { super(x1, x2); } }
  abstract static class Or extends RE.Binary{ public Or(RE x1, RE x2) { super(x1, x2); }}
  abstract static class Then extends RE.Binary{ Then(RE x1, RE x2) {super (x1,x2); }}
  abstract <T> T reduce(Reducer<T> r);
  //@formatter:on
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

  public static RE ε() {
    return new ε() {
      public <T> T reduce(Reducer<T> r) {
        return r.ε();
      }
    };
  }

  public static RE Φ() {
    return new Φ() {
      public <T> T reduce(Reducer<T> r) {
        return r.Φ();
      }
    };
  }

  public static RE c(char c) {
    return new Char(c) {
      public <T> T reduce(Reducer<T> r) {
        return r.σ(c);
      }
    };
  }

  public final Star star() {
    return new Star(RE.this) {
      public <T> T reduce(Reducer<T> r) {
        return r.star(x.reduce(r));
      }
    };
  }

  public final Not not() {
    return new Not(RE.this) {
      public <T> T reduce(Reducer<T> r) {
        return r.not(x.reduce(r));
      }
    };
  }

  public final Or or(RE x) {
    return new Or(RE.this, x) {
      public <T> T reduce(Reducer<T> r) {
        return r.or(x1.reduce(r), x2.reduce(r));
      }
    };
  }

  public final Then then(RE x) {
    return new Then(RE.this, x) {
      public <T> T reduce(Reducer<T> r) {
        return r.then(x1.reduce(r), x2.reduce(r));
      }
    };
  }

  public final And and(RE x) {
    return new And(RE.this, x) {
      public <T> T reduce(Reducer<T> r) {
        return r.and(x1.reduce(r), x2.reduce(r));
      }
    };
  }

//@formatter:off
  public final RE dup() {
    return reduce(new Reducer<RE>() {
      public RE ε() { return RE.ε(); }
      public RE Φ() { return RE.Φ(); }
      public RE σ(char c) { return RE.c(c); }
      public RE not(RE x) { return x.not(); }
      public RE star(RE x) { return x.star(); }
      public RE then(RE x1, RE x2) { return x1.then(x2); }
      public RE or(RE x1, RE x2) { return x1.or(x2); }
      public RE and(RE x1, RE x2) { return x1.and(x2); }
    });
  }

  public final <Σ> NFSA<Σ> fsa() {
    return reduce(new Reducer<NFSA<Σ>>() {
      @Override public NFSA<Σ> 
      Φ() { return NFSA.Φ(); }
      @Override public NFSA<Σ> ε() { return NFSA.ε(); }
      @SuppressWarnings("unchecked") @Override public NFSA<Σ> σ(char σ) { return NFSA.σ((Σ)(Character) σ); }
      @Override public NFSA<Σ> star(NFSA<Σ> a) { return a.star(); }
      @Override public NFSA<Σ> not(NFSA<Σ> a) { return a.not(); }
      @Override public NFSA<Σ> then(NFSA<Σ> a1, NFSA<Σ> a2) { return a1.then(a2); }
      @Override public NFSA<Σ> or(NFSA<Σ> a1, NFSA<Σ> a2) { return a1.or(a2); }
      @Override public NFSA<Σ> and(NFSA<Σ> a1, NFSA<Σ> a2) { return a1.and(a2); }
    });
  }

}