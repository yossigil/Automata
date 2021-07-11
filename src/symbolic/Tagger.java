package symbolic;

/** Classification of nodes, based only on their type (but not their contents),
 * to values of some type */
interface Tagger {
  interface Γ<Tag> extends Γ0<Tag>, Γ1<Tag>, Γ2<Tag> {}
  interface Γ0<Tag> extends Γ00<Tag>, Γ01<Tag> {}
  interface Γ00<Tag> { Tag ʘ(); Tag ε(); Tag Φ(); }
  interface Γ01<Tag> { Tag σ(char c); }
  interface Γ1<Tag> { Tag many(); Tag not(); Tag plenty(); }
  interface Γ2<Tag> { Tag and(); Tag except(); Tag or(); Tag then(); }
}