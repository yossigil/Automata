package symbolic;

/** A processor of an RE, operation on node based on its tag (encoding both
 * number of children and node kind) and values of (unprocessed children)
 * children */
public interface Abstracter<Tag> extends Tagger.Γ<Tag> { // @formatter:off
  /** Process a leaf */ void γ0(Tag t);
  /** Process a internal unary node */ void γ1(Tag t, RE r);
  /** Process a internal unary node */ void γ2(Tag t, RE r1, RE r2);
}