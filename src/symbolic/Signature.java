package symbolic;

public interface Signature {
  interface Γ<T> extends Γ0<T>, Γ1<T>, Γ2<T> {}
  interface Γ0<T> extends Γ00<T>, Γ01<T> {}
  interface Γ00<T> { T ʘ(); T ε(); T Φ(); }
  interface Γ01<T> { T σ(char σ); }
  interface Γ1<T> { T many(T t); T not(T t); T plenty(T t); }
  interface Γ2<T> { T and(T t1, T t2); T except(T t1, T t2); T or(T t1, T t2); T then(T t1, T t2); }
}