package minimize;

import org.junit.jupiter.api.Test;

import automaton.FSA;
import automaton.Lexer;
public class Many2 {
  final FSA<Character> dfsa = Lexer.c('a').Then('b').many().DFSA().minimal();
  {
   Sample. many$ab.show();
  }

  @Test  void  accept0()   {  assert  dfsa.run("");          }
  @Test  void  accept1()   {  assert  dfsa.run("ab");        }
  @Test  void  accept2()   {  assert  dfsa.run("abab");      }
  @Test  void  accept3()   {  assert  dfsa.run("ababab");    }
  @Test  void  accept4()   {  assert  dfsa.run("abababab");  }
  @Test  void  reject0()   {  assert  !dfsa.run("a");          }
  @Test  void  reject01()  {  assert  !dfsa.run("b");          }
  @Test  void  reject02()  {  assert  !dfsa.run("aa");         }
  @Test  void  reject03()  {  assert  !dfsa.run("bb");         }
  @Test  void  reject04()  {  assert  !dfsa.run("ba");         }
  @Test  void  reject05()  {  assert  !dfsa.run("aababab");    }
  @Test  void  reject06()  {  assert  !dfsa.run("aab");        }
  @Test  void  reject07()  {  assert  !dfsa.run("abb");        }
  @Test  void  reject08()  {  assert  !dfsa.run("aaa");        }
  @Test  void  reject09()  {  assert  !dfsa.run("bbb");        }
  @Test  void  reject10()  {  assert  !dfsa.run("abababa");    }
}
