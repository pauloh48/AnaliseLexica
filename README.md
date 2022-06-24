# AnaliseLexica

## ARQUIVOS
1. [**fonte.alg*]([https://github.com/pauloh48/AnaliseLexica/blob/main/analisadorLexico/src/analisadorLexico/mainScanner.java](https://github.com/pauloh48/AnaliseLexica/blob/main/analisadorLexico/src/analisadorLexico/fonte.alg)): arquivo fonte de teste, usado para ver o funcionamento do Scanner;
2. [**mainScanner.java**](https://github.com/pauloh48/AnaliseLexica/blob/main/analisadorLexico/src/analisadorLexico/mainScanner.java): arquivo main, usa a função Scanner, favor alterar o caminho em que está o fonte, linha 11;
3. [**Token.java**](https://github.com/pauloh48/AnaliseLexica/blob/main/analisadorLexico/src/analisadorLexico/Token.java): Identifica o tipo de token e imprime na tela;
4. [**OScanner.java**](https://github.com/pauloh48/AnaliseLexica/blob/main/analisadorLexico/src/analisadorLexico/OScanner.java): Programa principal, implementa o dfa com seguintes estados iniciais de transição:

+ 0:          linhas 043 - 095    (estado origem);
+ 1, 3, 4, 5: linhas 098 - 138    (Numeral);
+ 6, 7:       linhas 141 - 159    (Literal);
+ 8:          linhas 162 - 173    (id);
+ 9, 10:      linhas 176 - 194    (comentarios);
+ 12,13:      linhas 197 - 211    (OPR >=);
+ 15:         linhas 214 - 229    (OPR <> e RCB <=);
+ 17:         linhas 231 - 236    (RCB);
+ 18:         linhas 238 - 243    (OPRMATERMATICO);
+ 19:         linhas 245 - 250    (AB_P);
+ 20:         linhas 252 - 257    (FC_P);
+ 21:         linhas 259 - 264    (PT_V);
+ 22:         linhas 266 - 271    (VIR);

As demais linhas são responsaveid por:
+ linhas 276 - 351: contem as funções que alteram os estados do DFA;
+ linhas 253 - 258: contem função que percorre os caracteres e outra que retrocede um unico caracater.
