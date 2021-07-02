package finite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SuppressWarnings("static-method") class DFSATest {
  final Text empty = new Text();
  final Text a = new Text('a');
  final Text b = new Text('b');
  final Text ab = new Text(a.then(b));
  final Text a_b = new Text(a.or(b));
  final Text abStar = new Text(ab.star());
  final Text x = new Text(a_b.then(ab).then(a_b).or(a.then(a).then(a).then(a)));
}
