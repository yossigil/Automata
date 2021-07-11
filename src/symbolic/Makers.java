package symbolic;

public interface Makers {
  interface All extends Γ1, Γ2 {}
  interface Γ1 {
    RE many();
    RE not();
    RE plenty();
  }
  interface Γ2 {
    RE and(RE ¢);
    RE except(RE ¢);
    RE or(RE ¢);
    RE then(RE ¢);
  }
}