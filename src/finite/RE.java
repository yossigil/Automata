package finite;

import finite.RE.Binary.And;
import finite.RE.Binary.Or;
import finite.RE.Binary.Then;
import finite.RE.Nullary.Letter;
import finite.RE.Nullary.ʘ;
import finite.RE.Nullary.Φ;
import finite.RE.Nullary.ε;
import finite.RE.Unary.Not;
import finite.RE.Unary.Plus;
import finite.RE.Unary.Star;

interface Abstracter<S> {
  S and();
  S and(And a);
  void binary(S kind, RE r1, RE r2);
  S kind(char c);
  S not();
  void nullary(S kind);
  S or();
  S plus();
  S star();
  S then();
  void unary(S kind, RE e);
  S ʘ();
  S ε();
  S σ(char c);
  S Φ();
}
//@formatter:off

interface Arity {
  int height();
  int length();
  int size();
  interface All<T> extends Binary<T>, Unary<T>, Nullary<T>, Arity {}
  interface Binary<T> extends Arity { T and(T t1, T t2); T or(T t1, T t2); T then(T t1, T t2); }
  interface Fixed<T> extends Arity { T ʘ(); T ε(); T Φ(); }
  interface Informational<T> extends Arity { T σ(char σ); }
  interface Nullary<T> extends Informational<T>, Fixed<T>, Arity {}
  interface Unary<T> extends Arity { T not(T t); T plus(T t); T star(T t); }
}

 interface R<T> extends Arity.All<T> {}

abstract class RE {
  final RE and(final RE ¢) { return new And(this, ¢); }
  abstract int depth();

    final RE dup() {
      return ρ(new R<RE>() {
        @Override public RE and(final RE r1, final RE r2) { return r1.and(r2); }
        @Override public int height() { throw new UnsupportedOperationException("10 Jul 2021"); }
        @Override public int length() { throw new UnsupportedOperationException("10 Jul 2021"); }
        @Override public RE not(final RE ¢) { return ¢.not(); }
        @Override public RE or(final RE r1, final RE r2) { return r1.or(r2); }
        @Override public RE plus(final RE t) { throw new UnsupportedOperationException("10 Jul 2021"); }
        @Override public int size() { throw new UnsupportedOperationException("10 Jul 2021"); }
        @Override public RE star(final RE ¢) { return ¢.star(); }
        @Override public RE then(final RE r1, final RE r2) { return r1.then(r2); }
        @Override public RE ʘ() { return RE.ʘ; }
        @Override public RE ε() { return RE.ε; }
        @Override public RE σ(final char ¢) { return RE.c(¢); }
        @Override public RE Φ() { return RE.Φ; }
      });
    }
    final <Σ> NFSA<Σ> fsa() {
      return ρ(new R<NFSA<Σ>>() {
        @Override public NFSA<Σ> and(final NFSA<Σ> a1, final NFSA<Σ> a2) { return a1.and(a2); }
        @Override public int height() { throw new UnsupportedOperationException("10 Jul 2021"); }
        @Override public int length() { throw new UnsupportedOperationException("10 Jul 2021"); }
        @Override public NFSA<Σ> not(final NFSA<Σ> ¢) { return ¢.not(); }
        @Override public NFSA<Σ> or(final NFSA<Σ> a1, final NFSA<Σ> a2) { return a1.or(a2); }
        @Override public NFSA<Σ> plus(final NFSA<Σ> t) { throw new UnsupportedOperationException("10 Jul 2021"); }
        @Override public int size() { throw new UnsupportedOperationException("10 Jul 2021"); }
        @Override public NFSA<Σ> star(final NFSA<Σ> ¢) { return ¢.star(); }
        @Override public NFSA<Σ> then(final NFSA<Σ> a1, final NFSA<Σ> a2) { return a1.then(a2); }
        @Override public NFSA<Σ> ʘ() { return null; }
        @Override public NFSA<Σ> ε() { return NFSA.ε(); }
        @Override @SuppressWarnings("unchecked") public NFSA<Σ> σ(final char σ) { return NFSA.σ((Σ) (Character) σ); }
        @Override public NFSA<Σ> Φ() { return NFSA.Φ(); }
      });
    }
    abstract <T> T kind(Abstracter<T> ¢);
    final Not not() { return new Not(this); }
    final Or or(final RE ¢) { return new Or(this, ¢); }
    final Plus plus() { return new Plus(this); }
    abstract int size();
    final Star star() { return new Star(this); }
    final Then then(final RE ¢) { return new Then(this, ¢);   } final String TikZ() {
      return new TeXifier() {
        {
          ρ(new Abstracter<String>() {
            @Override public String and() { return ""; }
            @Override public String and(final And a) { return "\\bigwedge"; }
            @Override public void binary(final String kind, final RE r1, final RE r2) {
              indentln(missing(math(kind)) + " -> { ");
              r1.ρ(this);
              r2.ρ(this);
              unindentln("}");
            }
            @Override public String kind(final char c) { return ""; }
            String missing(final String kind) { return missing + "/" + math(kind); }
            @Override public String not() { return ""; }
            @Override public void nullary(final String kind) { println(missing(kind) + ";"); }
            @Override public String or() { return "\\bigvee"; }
            @Override public String plus() { return "^+"; }
            @Override public String star() { return "^*"; }
            @Override public String then() { return ""; }
            @Override public void unary(final String kind, final RE e) {
              println(missing(math(kind)) + " -> { ");
              indent();
              e.ρ(this);
              unindent();
              println("}");
            }
            @Override public String ʘ() { return math("\\ast"); }
            @Override public String ε() { return math("\\varepsilon"); }
            @Override public String σ(final char ¢) { return tt(¢ + ""); }
            @Override public String Φ() { return math("\\emptyset"); }
            String missing = "\"\"";
          });
        }
      } + "";
    }
    @Override public abstract String toString();
    abstract <T> void ρ(Abstracter<T> ¢);
    abstract <T> T ρ(R<T> r);
    static final RE ʘ = new ʘ() {
                        @Override <T> T kind(final Abstracter<T> ¢) { return ¢.ʘ(); }
                        @Override public String toString() { return "ʘ"; }
                        @Override public <T> T ρ(final R<T> ¢) { return ¢.ʘ(); }
                      };
    static final RE ε = new ε() {
                        @Override <T> T kind(final Abstracter<T> ¢) { return ¢.ε(); }
                        @Override public String toString() { return "ε"; }
                        @Override public <T> T ρ(final R<T> ¢) { return ¢.ε(); }
                      };
    static final RE Φ = new Φ() {
                        @Override <T> T kind(final Abstracter<T> ¢) { return ¢.Φ(); }
                        @Override public String toString() { return "Φ"; }
                        @Override public <T> T ρ(final R<T> ¢) { return ¢.Φ(); }
                      };

    static RE c(final char c) {
      return new Letter(c) {
        @Override <T> T kind(final Abstracter<T> ¢) { return ¢.σ(c); }
        @Override public String toString() { return c + ""; }
        @Override public <T> T ρ(final R<T> ¢) { return ¢.σ(c); }
      };
    }

    abstract static class Binary extends RE {
      @Override final int depth() { return 1 + Math.max(r1.depth(), r2.depth()); }
      @Override int size() { return 1 + r1.size() + r2.size(); }
      @Override final <T> void ρ(final Abstracter<T> ¢) { ¢.binary(kind(¢), r1, r2); }
      Binary(final RE r1, final RE r2) { this.r1 = r1; this.r2 = r2; }
      final RE r1, r2;
      static final class And extends Binary {
        @Override <T> T kind(final Abstracter<T> ¢) { return ¢.and(this); }
        @Override public String toString() { return r1 + "&" + r2; }
        @Override public <T> T ρ(final R<T> ¢) { return ¢.and(r1.ρ(¢), r2.ρ(¢)); }
        And(final RE r1, final RE r2) { super(r1, r2); }
      }
      static final class Or extends Binary {
        @Override <T> T kind(final Abstracter<T> ¢) { return ¢.or(); }
        @Override public String toString() { return r1 + "|" + r2; }
        @Override <T> T ρ(final R<T> ¢) { return ¢.or(r1.ρ(¢), r2.ρ(¢)); }
        Or(final RE r1, final RE r2) { super(r1, r2); }
      }
      static final class Then extends Binary {
        @Override
        <T> T kind(final Abstracter<T> ¢) { return ¢.or(); }
        @Override
        public String toString() { return r1 + "" + r2; }
        @Override
        <T> T ρ(final R<T> ¢) { return ¢.and(r1.ρ(¢), r2.ρ(¢)); }
        Then(final RE r1, final RE r2) { super(r1, r2); }
      }
    }

    abstract static class Nullary extends RE {
      @Override
      final int depth() { return 1; }
      @Override final int size() { return 1; }
      @Override public final <T> void ρ(final Abstracter<T> ¢) { ¢.nullary(kind(¢)); }
      abstract static class Letter extends Nullary { Letter(final char c) { this.c = c; } final char c; }
      abstract static class ʘ extends Nullary {}
      abstract static class ε extends Nullary {}
      abstract static class Φ extends Nullary {}
    }

abstract static class Unary extends RE {
  final <T> void ρ(final Abstracter<T> ¢) { ¢.unary(kind(¢), r); }
  @Override final int depth() { return 1 + r.depth(); }
  @Override final int size() { return 1 + r.size(); }
  Unary(final RE r) { this.r = r; }
  final RE r;
  static final class Not extends Unary { 
    @Override <T> T kind(final Abstracter<T> ¢) { return ¢.not();  }
    @Override public String toString() { return  r + "!"; } 
    @Override <T> T ρ(final R<T> ¢) {    return ¢.not(r.ρ(¢)); }
    Not(final RE r) { super(r); } 
    }
  static final class Plus extends Unary { 
    @Override <T> T kind(final Abstracter<T> ¢) {    return ¢.plus();  }
    @Override public String toString() { return r + "+"; } 
    @Override <T> T ρ(final R<T> ¢) { return ¢.plus(r.ρ(¢)); }
    Plus(final RE r) { super(r); } 
  }

  static final class Star extends Unary { @Override
  <T> T kind(final Abstracter<T> ¢) { return ¢.star(); }
  @Override public String toString() { return r + "*"; } 
  @Override <T> T ρ(final R<T> ¢) { return  ¢.star(r.ρ(¢));  } 
  Star(final RE r) { super(r); } }
}
}