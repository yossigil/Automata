package symbolic;

import automaton.NFSA;

interface Converters {
  NFSA<Character> fsa();
  <T> T reduce(Reducer<T> ¢);
  <T> void recurse(Abstracter<T> ¢);
  <T> T kind(Abstracter<T> ¢);
  String TikZ();
  RE dup();
}