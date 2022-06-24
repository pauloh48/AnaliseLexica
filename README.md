# AnaliseLexica

## ARQUIVOS
* [**fonte.alg*]([https://github.com/pauloh48/AnaliseLexica/blob/main/analisadorLexico/src/analisadorLexico/mainScanner.java](https://github.com/pauloh48/AnaliseLexica/blob/main/analisadorLexico/src/analisadorLexico/fonte.alg)): arquivo fonte de teste, usado para ver o funcionamento do Scanner;
* [**mainScanner.java**](https://github.com/pauloh48/AnaliseLexica/blob/main/analisadorLexico/src/analisadorLexico/mainScanner.java): arquivo main, usa a função Scanner, favor alterar o caminho em que está o fonte, linha 11;
* [**Token.java**](https://github.com/pauloh48/AnaliseLexica/blob/main/analisadorLexico/src/analisadorLexico/Token.java): Identifica o tipo de token e imprime na tela;
* [**OScanner.java**](https://github.com/pauloh48/AnaliseLexica/blob/main/analisadorLexico/src/analisadorLexico/OScanner.java): Programa principal, implementa o dfa com seguintes estados:
+ 0: linhas 43 - 95;              (estado inicial)
+ 1, 3, 4, 5: linhas 98 - 138     (Numeral)
+ 6, 7: linhas  141 - 159         (Literal)
+ 8: linhas 162 - 173             (id)
+ 
+ 
2. 
