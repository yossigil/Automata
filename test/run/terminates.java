package run;

import org.junit.jupiter.api.Test;

import automaton.NFSA;

@SuppressWarnings("static-method") public class terminates {
  static class Empty {
    @Test void or() {
      NFSA.<Character>σ('a').or(NFSA.<Character>σ('a')).run("");
    }
    @Test void many() {
      NFSA.<Character>σ('a').many().run("");
    }
    @Test void then() {
      NFSA.<Character>σ('a').then(NFSA.<Character>σ('a')).run("");
    }
    @Test void σ() {
      NFSA.<Character>σ('a').run("");
    }
    @Test void ε() {
      NFSA.<Character>ε().run("");
    }
    @Test void ʘ() {
      NFSA.<Character>ʘ().run("");
    }
    @Test void Φ() {
      NFSA.<Character>Φ().run("");
    }
  }
  static class onA {
    @Test void or() {
      NFSA.<Character>σ('a').or(NFSA.<Character>σ('a')).run("a");
    }
    @Test void many() {
      NFSA.<Character>σ('a').many().run("a");
    }
    @Test void then() {
      NFSA.<Character>σ('a').then(NFSA.<Character>σ('a')).run("a");
    }
    @Test void σ() {
      NFSA.<Character>σ('a').run("a");
    }
    @Test void ε() {
      NFSA.<Character>ε().run("a");
    }
    @Test void ʘ() {
      NFSA.<Character>ʘ().run("a");
    }
    @Test void Φ() {
      NFSA.<Character>Φ().run("a");
    }
  }
  static class onB {
    @Test void or() {
      NFSA.<Character>σ('a').or(NFSA.<Character>σ('a')).run("b");
    }
    @Test void many() {
      NFSA.<Character>σ('a').many().run("b");
    }
    @Test void then() {
      NFSA.<Character>σ('a').then(NFSA.<Character>σ('a')).run("b");
    }
    @Test void σ() {
      NFSA.<Character>σ('a').run("b");
    }
    @Test void ε() {
      NFSA.<Character>ε().run("b");
    }
    @Test void ʘ() {
      NFSA.<Character>ʘ().run("b");
    }
    @Test void Φ() {
      NFSA.<Character>Φ().run("b");
    }
  }
  static class onAB {
    @Test void or() {
      NFSA.<Character>σ('a').or(NFSA.<Character>σ('a')).run("ab");
    }
    @Test void many() {
      NFSA.<Character>σ('a').many().run("ab");
    }
    @Test void then() {
      NFSA.<Character>σ('a').then(NFSA.<Character>σ('a')).run("ab");
    }
    @Test void σ() {
      NFSA.<Character>σ('a').run("ab");
    }
    @Test void ε() {
      NFSA.<Character>ε().run("ab");
    }
    @Test void ʘ() {
      NFSA.<Character>ʘ().run("ab");
    }
    @Test void Φ() {
      NFSA.<Character>Φ().run("ab");
    }
  }
}