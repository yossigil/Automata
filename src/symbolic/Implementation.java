package symbolic;

import finite.NFSA;
import finite.TikZifier;
import symbolic.Implementation.Σ0.Letter;
import symbolic.Implementation.Σ0.ʘ;
import symbolic.Implementation.Σ0.Φ;
import symbolic.Implementation.Σ0.ε;
import symbolic.Implementation.Σ1.Many;
import symbolic.Implementation.Σ1.Not;
import symbolic.Implementation.Σ1.Plenty;
import symbolic.Implementation.Σ2.And;
import symbolic.Implementation.Σ2.Except;
import symbolic.Implementation.Σ2.Or;
import symbolic.Implementation.Σ2.Then;

abstract class Algorithms implements RE {
  @Override public final RE dup() {
    return reduce(new Reducer<RE>() {
      @Override public RE and(final RE r1, final RE r2) { return r1.and(r2); }
      public RE except(RE r1, RE r2) { return r1.except(r2); }
      @Override public RE many(final RE ¢) { return ¢.many(); }
      @Override public RE not(final RE ¢) { return ¢.not(); }
      @Override public RE or(final RE r1, final RE r2) { return r1.or(r2); }
      @Override public RE plenty(final RE ¢) { return ¢.plenty(); }
      @Override public RE then(final RE r1, final RE r2) { return r1.then(r2); }
      @Override public RE ʘ() { return Atoms.ʘ; }
      @Override public RE ε() { return Atoms.ε; }
      @Override public RE σ(final char ¢) { return Atomic.c(¢); }
      @Override public RE Φ() { return Atoms.Φ; }
    });
  }
  @Override public final NFSA<Character> fsa() {
    return reduce(new Reducer<NFSA<Character>>() {
      @Override public NFSA<Character> and(final NFSA<Character> a1, final NFSA<Character> a2) { return a1.and(a2); }
      @Override public NFSA<Character> except(final NFSA<Character> a1, final NFSA<Character> a2) {
        return a1.except(a2);
      }
      @Override public NFSA<Character> many(final NFSA<Character> ¢) { return ¢.many(); }
      @Override public NFSA<Character> not(final NFSA<Character> ¢) { return ¢.not(); }
      @Override public NFSA<Character> or(final NFSA<Character> a1, final NFSA<Character> a2) { return a1.or(a2); }
      @Override public NFSA<Character> plenty(final NFSA<Character> ¢) { return ¢.plenty(); }
      @Override public NFSA<Character> then(final NFSA<Character> a1, final NFSA<Character> a2) { return a1.then(a2); }
      @Override public NFSA<Character> ʘ() { return NFSA.ʘ(); }
      @Override public NFSA<Character> ε() { return NFSA.ε(); }
      @Override public NFSA<Character> σ(final char ¢) { return NFSA.σ(¢); }
      @Override public NFSA<Character> Φ() { return NFSA.Φ(); }
    });
  }
  @Override public final String TikZ() {
    return new TikZifier() {
      @Override
      @SuppressWarnings("synthetic-access") protected String traverse() {
        recurse(new Abstracter<String>() {
          @Override public String and() { return "\\wedge"; }
          @Override public String except() { return "\\setminus"; }
          @Override public String many() { return "^*"; }
          @Override public String not() { return ""; }
          @Override public String or() { return "\\vee"; }
          @Override public String plenty() { return "^+"; }
          @Override public String then() { return "\\circ"; }
          @Override public String ʘ() { return math("\\ast"); }
          @Override public void γ0(final String kind) { println(missing(kind) + ";"); }
          @Override public void γ1(final String kind, final RE e) {
            indentln(missing(kind) + " ->  ");
            e.recurse(this);
            unindentln(";");
          }
          @Override public void γ2(final String kind, final RE r1, final RE r2) {
            indentln(missing(kind) + " -> {%%");
            r1.recurse(this);
            r2.recurse(this);
            unindentln("};");
          }
          @Override public String ε() { return math("\\varepsilon"); }
          @Override public String σ(final char ¢) { return tt(¢ + ""); }
          @Override public String Φ() { return math("\\emptyset"); }
        });
        return this + "";
      }
    }.render();
  }
}

abstract class Implementation extends Algorithms {
  @Override public final And and(final RE ¢) { return new And(this, ¢); }
  @Override public final Except except(final RE ¢) { return new Except(this, ¢); }
  @Override public final Many many() { return new Many(this); }
  @Override public final Not not() { return new Not(this); }
  @Override public final Or or(final RE ¢) { return new Or(this, ¢); }
  @Override public final Plenty plenty() { return new Plenty(this); }
  @Override public abstract <T> void recurse(Abstracter<T> ¢);
  @Override public abstract int size();
  @Override public final Then then(final RE ¢) { return new Then(this, ¢); }
  static final ʘ ʘ = new ʘ() {
                     @Override public <T> T kind(final Abstracter<T> ¢) { return ¢.ʘ(); }
                     @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.ʘ(); }
                     @Override public String toString() { return "ʘ"; }
                   };
  static final ε ε = new ε() {
                     @Override public <T> T kind(final Abstracter<T> ¢) { return ¢.ε(); }
                     @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.ε(); }
                     @Override public String toString() { return "ε"; }
                   };
  static final Φ Φ = new Φ() {
                     @Override public <T> T kind(final Abstracter<T> ¢) { return ¢.Φ(); }
                     @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.Φ(); }
                     @Override public String toString() { return "Φ"; }
                   };
  static Implementation c(final char c) {
    return new Letter(c) {
      @Override public <T> T kind(final Abstracter<T> ¢) { return ¢.σ(c); }
      @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.σ(c); }
      @Override public String toString() { return c + ""; }
    };
  }
  abstract static class Σ0 extends Implementation {
    @Override public final int depth() { return 1; }
    @Override public final <T> void recurse(final Abstracter<T> ¢) { ¢.γ0(kind(¢)); }
    @Override public final int size() { return 1; }
    abstract static class Letter extends Σ0 { Letter(final char c) { this.c = c; } final char c; }
    abstract static class ʘ extends Σ0 {}
    abstract static class ε extends Σ0 {}
    abstract static class Φ extends Σ0 {}
  }
  abstract static class Σ1 extends Implementation {
    @Override public final int depth() { return 1 + r.depth(); }
    @Override public final <T> void recurse(final Abstracter<T> ¢) { ¢.γ1(kind(¢), r); }
    @Override public final int size() { return 1 + r.size(); }
    Σ1(final RE r) { this.r = r; }
    final RE r;
    static final class Many extends Σ1 {
      @Override public <T> T kind(final Abstracter<T> ¢) { return ¢.many(); }
      @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.many(r.reduce(¢)); }
      @Override public String toString() { return r + "*"; }
      Many(final RE r) { super(r); }
    }
    static final class Not extends Σ1 {
      @Override public <T> T kind(Abstracter<T> ¢) { return ¢.not(); }
      @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.not(r.reduce(¢)); }
      @Override public String toString() { return r + "!"; }
      Not(final RE r) { super(r); }
    }
    static final class Plenty extends Σ1 {
      @Override public <T> T kind(final Abstracter<T> ¢) { return ¢.plenty(); }
      @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.plenty(r.reduce(¢)); }
      @Override public String toString() { return r + "+"; }
      Plenty(final RE r) { super(r); }
    }
  }
  abstract static class Σ2 extends Implementation {
    @Override public final int depth() { return 1 + Math.max(r1.depth(), r2.depth()); }
    @Override public final <T> void recurse(final Abstracter<T> ¢) { ¢.γ2(kind(¢), r1, r2); }
    @Override public int size() { return 1 + r1.size() + r2.size(); }
    Σ2(final RE r1, final RE r2) { this.r1 = r1; this.r2 = r2; }
    final RE r1, r2;
    static final class And extends Σ2 {
      @Override public <T> T kind(final Abstracter<T> ¢) { return ¢.and(); }
      @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.and(r1.reduce(¢), r2.reduce(¢)); }
      @Override public String toString() { return r1 + "&" + r2; }
      And(final RE r1, final RE r2) { super(r1, r2); }
    }
    static final class Except extends Σ2 {
      @Override public <T> T kind(final Abstracter<T> ¢) { return ¢.except(); }
      @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.except(r1.reduce(¢), r2.reduce(¢)); }
      @Override public String toString() { return r1 + "&" + r2; }
      Except(final RE r1, final RE r2) { super(r1, r2); }
    }
    static final class Or extends Σ2 {
      @Override public <T> T kind(final Abstracter<T> ¢) { return ¢.or(); }
      @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.or(r1.reduce(¢), r2.reduce(¢)); }
      @Override public String toString() { return r1 + "|" + r2; }
      Or(final RE r1, final RE r2) { super(r1, r2); }
    }
    static final class Then extends Σ2 {
      @Override public <T> T kind(final Abstracter<T> ¢) { return ¢.or(); }
      @Override public <T> T reduce(final Reducer<T> ¢) { return ¢.and(r1.reduce(¢), r2.reduce(¢)); }
      @Override public String toString() { return r1 + "" + r2; }
      Then(final RE r1, final RE r2) { super(r1, r2); }
    }
  }
}
