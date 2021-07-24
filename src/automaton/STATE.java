package automaton;

import java.util.Collection;
import java.util.stream.Stream;

interface STATE<Σ> extends Vertex<STATE<Σ>>, Iterable<Q> {//@formatter:off
  /** Transition function */ STATE<Σ> δ(Σ σ);
  /** Is this an accepting state */ boolean ζ();
  /** Modifier: add several states to this super state */ STATE<Σ> add(Collection<Q> ¢);
  /** Modifier: add a state to this super state */ STATE<Σ> add(Q ¢);
  /** Inspector: all included states */ Stream<Q> qs();
}