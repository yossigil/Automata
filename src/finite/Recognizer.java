package finite;

/** Abstract recognizer of a formal language over Σ */
interface Recognizer<Σ> { //@formatter:off
  /** Reset the recognizer */ void reset();
  /** Feed the recognizer with the letter */ void feed(Σ σ);
  /** Is the recognizer in an accepting state */ boolean accepting();
  /** Accept a given input by running the automaton//@formatter:on */
  default boolean run(Iterable<Σ> w) {
    reset();
    for (Σ σ : w) feed(σ);
    return accepting();
  }
}