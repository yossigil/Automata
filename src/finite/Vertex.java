package finite;

import java.util.Set;

interface Vertex<Self> {
  Set<Self> neighbours();
}