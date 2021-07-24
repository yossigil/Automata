package automaton;

import java.util.Collection;

interface Vertex<Self> {
  Collection<? extends Self> neighbours();
}