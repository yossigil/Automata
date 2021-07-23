package finite;

import java.util.Collection;

interface Vertex<Self> {
  Collection<? extends Self> neighbours();
}