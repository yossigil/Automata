package finite;

/** Abstract recognizer of a formal language over Σ */ 
interface Recognizer<Σ> {
  /** Initialize the recognizer */ void q0(); 
  /** Feed the next letter */  void feed(Σ σ);
  /** Is it in an accepting state */ boolean ζ();
}