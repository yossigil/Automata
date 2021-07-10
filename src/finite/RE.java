package finite;

import finite.RE.Binary.*;
import finite.RE.Unary.*;
import finite.RE.Nullary.*;

abstract class RE {
  public static final RE ʘ = new ʘ() {
                             @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.ʘ(); }
                             @Override public String toString() { return "ʘ"; }
                           };
  public static final RE ε = new ε() {
                             @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.ε(); }
                             @Override public String toString() { return "ε"; }
                           };
  public static final RE Φ = new Φ() {
                             @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.Φ(); }
                             @Override public String toString() { return "Φ"; }
                           };
  static RE c(final char c) {
    return new Letter(c) {
      @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.σ(c); }
      @Override public String toString() { return c + ""; }
    };
  }
  final RE and(final RE x) {
    return new And(this, x) {
      @Override public <T> T reduce(final Reducer<T> ¢) {
        return ¢.and(x1.reduce(¢), x2.reduce(¢));
      }
      public String toString() { 
        return x1 + "&" + x2;
      }
    };
  }
  final RE dup() {
    return reduce(new Reducer<RE>() {
      @Override public RE and(final RE x1, final RE x2) { return x1.and(x2); }
      @Override public RE not(final RE x) { return x.not(); }
      @Override public RE or(final RE x1, final RE x2) { return x1.or(x2); }
      public RE plus(RE t) { // TODO Auto-generated method stub
        return null;
      }
      @Override public RE star(final RE x) { return x.star(); }
      @Override public RE then(final RE x1, final RE x2) { return x1.then(x2); }
      @Override public RE ʘ() { return RE.ʘ; }
      @Override public RE ε() { return RE.ε; }
      @Override public RE σ(final char ¢) { return RE.c(¢); }
      @Override public RE Φ() { return RE.Φ; }
    });
  }
  final <Σ> NFSA<Σ> fsa() {
    return reduce(new Reducer<NFSA<Σ>>() {
      @Override public NFSA<Σ> and(final NFSA<Σ> a1, final NFSA<Σ> a2) { return a1.and(a2); }
      @Override public NFSA<Σ> not(final NFSA<Σ> ¢) { return ¢.not(); }
      @Override public NFSA<Σ> or(final NFSA<Σ> a1, final NFSA<Σ> a2) { return a1.or(a2); }
      public NFSA<Σ> plus(NFSA<Σ> t) { // TODO Auto-generated method stub
        return null;
      }
      @Override public NFSA<Σ> star(final NFSA<Σ> ¢) { return ¢.star(); }
      @Override public NFSA<Σ> then(final NFSA<Σ> a1, final NFSA<Σ> a2) { return a1.then(a2); }
      @Override public NFSA<Σ> ʘ() { return null; }
      @Override public NFSA<Σ> ε() { return NFSA.ε(); }
      @Override
      @SuppressWarnings("unchecked") public NFSA<Σ> σ(final char σ) { return NFSA.σ((Σ) (Character) σ); }
      @Override public NFSA<Σ> Φ() { return NFSA.Φ(); }
    });
  }
  final Not not() {
    return new Not(this) {
      @Override public <T> T reduce(final Reducer<T> ¢) {
        return ¢.not(x.reduce(¢));
      }
      public String toString() { 
        return x + "!"; 
      }
    };
  }
  final Or or(final RE x) {
    return new Or(this, x) {
      @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.or(x1.reduce(¢), x2.reduce(¢)); }
      public String toString() { 
        return x1 + "|" + x2;
      }
    };
  }
  final RE plus() {
    return new Plus(this) {
      @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.plus(x.reduce(¢)); }
      public String toString() { 
        return x + "+"; 
      }
    };
  }
  abstract <T> T reduce(Reducer<T> r);
  final Star star() {
    return new Star(this) {
      @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.star(x.reduce(¢)); }
      public String toString() { 
        return x + "*";
      }
    };
  }
  final Then then(final RE x) {
    return new Then(this, x) {
      @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.then(x1.reduce(¢), x2.reduce(¢)); }
      public String toString() {
        return x1 + "" + x2;
      }
    };
  }
  public abstract String toString();
  abstract static class Binary extends RE {
    Binary(final RE x1, final RE x2) { this.x1 = x1; this.x2 = x2; }
    final RE x1, x2;
    abstract static class And extends Binary {
      And(final RE x1, final RE x2) { super(x1, x2); }
      public String toString() {
        return x1 + "&" + x2;
      }
    }
    abstract static class Or extends Binary { Or(final RE x1, final RE x2) { super(x1, x2); } }
    abstract static class Then extends Binary { Then(final RE x1, final RE x2) { super(x1, x2); } }
  }
  abstract static class Nullary extends RE {
    abstract static class Letter extends Nullary { Letter(final char c) { this.c = c; } final char c; }
    abstract static class ʘ extends Nullary {}
    abstract static class ε extends Nullary {}
    abstract static class Φ extends Nullary {}
  }
  abstract static class Unary extends RE {
    Unary(final RE x) { this.x = x; }
    final RE x;
    abstract static class Not extends Unary { Not(final RE r) { super(r); } }
    abstract static class Plus extends Unary { Plus(final RE r) { super(r); } }
    abstract static class Star extends Unary { Star(final RE r) { super(r); } }
  }
}

interface Red {
  interface Binary<T> { T and(T t1, T t2); T or(T t1, T t2); T then(T t1, T t2); }
  interface Fixed<T> { T ʘ(); T ε(); T Φ(); }
  interface Informational<T> { T σ(char σ); }
  interface Nullary<T> extends Informational<T>, Fixed<T> {}
  interface Reducer<T> extends Binary<T>, Unary<T>, Nullary<T> {}
  interface Unary<T> { T not(T t); T plus(T t); T star(T t); }
}
//@formatter:off

interface Reducer<T> extends Red.Reducer<T> {}