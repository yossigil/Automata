package FSA.complex;

import org.junit.jupiter.api.Test;

import finite.Lexer;

class Demo {
  final Lexer empty  = Lexer.Φ();
  final Lexer a      = Lexer.c('a').or(empty);
  final Lexer b      = Lexer.c('b');
  final Lexer ab     = (a.then(b));
  final Lexer a_b    = (a.or(b));
  final Lexer abStar = (ab.many());
  final Lexer x0     = a_b.then(ab).Then('c').then(a_b).or(a.then(a).then(a).then(a)).Then('c');
  final Lexer x1     = x0.or(abStar.then(a).or(x0));
  final Lexer x2     = x1.many().Or('c').then(abStar.Then('c').Then('a'));
  final Lexer x3     = x2.then(a_b).then(x2).many();
  final Lexer x4     = x3.then(x3).many().or(x2).many().then(x1);

  @Test  void  dfsa_abStarTikZ()   {  show(abStar.DFSA().TikZ());  }
  @Test  void  dfsa_a_bTikZ()      {  show(a_b.DFSA().TikZ());     }
  @Test  void  dfsa_abTikZ()       {  show(ab.DFSA().TikZ());      }
  @Test  void  dfsa_aTikZ()        {  show(a.DFSA().TikZ());       }
  @Test  void  dfsa_bTikZ()        {  show(b.DFSA().TikZ());       }
  @Test  void  dfsa_x0TikZ()        {  show(x0.DFSA().TikZ());      }
  @Test  void  dfsa_x1TikZ()        {  show(x1.DFSA().TikZ());      }
  @Test  void  dfsa_x2TikZ()        {  show(x2.DFSA().TikZ());      }
  @Test  void  dfsa_x3TikZ()        {  show(x3.DFSA().TikZ());      }
  @Test  void  dfsa_x4TikZ()        {  show(x4.DFSA().TikZ());      }
  @Test  void  nfsa_x0Run()          {  x0.run("Abc");               }
  @Test  void  nfsa_x0TikZ()         {  x0.TikZ();                   }
  @Test  void  nfsa_x1Run()          {  x1.run("Abc");               }
  @Test  void  nfsa_x1TikZ()         {  x1.TikZ();                   }
  @Test  void  nfsa_x2Run()          {  x2.run("Abc");               }
  @Test  void  nfsa_x2TikZ()         {  x2.TikZ();                   }
  @Test  void  nfsa_x3Run()          {  x3.run("Abc");               }
  @Test  void  nfsa_x3TikZ()         {  x3.TikZ();                   }
  @Test  void  nfsa_x4Run()          {  x4.run("Abc");               }
  @Test  void  nfsa_x4TikZ()         {  x4.TikZ();                   }
  @Test  void  nfsa_a_b_run()      {  a_b.run("abc");              }
  @Test  void  nfsa_ab_run()       {  ab.run("abc");               }
  @Test  void  nfsa_abStar_run()   {  abStar.run("abc");           }
  @Test  void  nfsa_abStar_TikZ()  {  show(abStar.TikZ());         }
  @Test  void  nfsa_a_b_TikZ()     {  show(a_b.TikZ());            }
  @Test  void  nfsa_ab_TikZ()      {  show(ab.TikZ());             }
  @Test  void  nfsa_a_run()        {  a.run("abc");                }
  @Test  void  nfsa_a_TikZ()       {  show(a.TikZ());              }
  @Test  void  nfsa_b_run()        {  b.run("abc");                }
  @Test  void  nfsa_b_TikZ()       {  show(b.TikZ());              }
  @Test  void  nfsaEmptyrun()      {  empty.run("abc");            }
  @Test  void  nfsaEmptyTikZ()     {  show(empty.TikZ());          }
  @Test  void  nfsa_x0_run()       {  x0.run("abc");               }
  @Test  void  nfsa_x1_run()       {  x1.run("abc");               }
  @Test  void  nfsa_x2_run()       {  x2.run("abc");               }
  @Test  void  nfsa_x3_run()       {  x3.run("abc");               }
  @Test  void  nfsa_x4_run()       {  x4.run("abc");               }

    private static void show(final String graph) {
      print(path(graph));
    }
    @Test void minimized() {
      print(path(x4.DFSA().minimize().TikZ()));
    }
    private static void print(final String ¢) {
      System.out.println(¢);
    }
    private static String path(final String graph) {
      return "\\path " + graph + ";";
    }
  }
