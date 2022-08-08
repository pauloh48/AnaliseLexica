package analisadorLexico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

public class mainScanner {

	public static void main(String[] args) throws IOException {
		/*String filename = "C:\\Users\\lfsil\\OneDrive\\Documentos\\Faculdade\\Compiladores\\"
		+ "T1\\Analisador Léxico 3\\AnaliseLexica-main\\analisadorLexico\\src\\"
		+ "analisadorLexico\\fonte.alg";
	    */String filename = "/home/paulo/Documents/AnaliseLexica/analisadorLexico/src/"
 		+ "analisadorLexico/fonte.alg";
		
	    BufferedReader conteudoAtualLine = new BufferedReader(new FileReader(filename));
		HashMap<String, String> hm = Scanner.tabelaSimbolos();
		Scanner sc = new Scanner();
		
		Token token = null;
		String line = "";
		String l2 = "";
		
		//int l = 1;
		//int col = 0;
		//String tipo_tabela;
		GeraToken gt = new GeraToken();
		
//inicio sintatico **************************
		// pilha
        Stack s = new Stack();
        /*for(int i=0;i<5;i++){
            s.push(i);
            s.push('a');
        }*/
        s.push(0);
        System.out.println(s.peek());
        
        // abrir csv
        String filenameCSV = "/home/paulo/Documents/AnaliseLexica/analisadorLexico/src/analisadorLexico/TabelaTransicoes.csv";
	    BufferedReader conteudoCsv = new BufferedReader(new FileReader(filenameCSV));
	    String delimiter = ",";
	   // String[] coluna = line.split(delimiter);

        
        int colCSV = 0;
        int qtdColCSV = 44;
        String t;
        int et = line.length();
        // ler csv
/*        while ((line = conteudoCsv.readLine()) != null) {
        	String[] coluna = line.split(delimiter);
			while(colCSV < qtdColCSV) {
				t = coluna[colCSV];
				if(coluna[colCSV].contains("S") && colCSV == 20)
					System.out.println(coluna[colCSV]);
				/*if(colCSV == 1)
					System.out.println(coluna[colCSV]);
				*//*colCSV++;
			}
			colCSV=0;
        }
*/

// fim sintatico ***************************

		while ((line = conteudoAtualLine.readLine()) != null) {
			//while(col < line.length()) {
			while(sc.getCol() < line.length()) {
				//** comentriao por sintatico token = sc.returnToken(hm, line, gt, sc);
			
			//***sintatico
				//primeiro token
				if(sc.getCol() == 0 && sc.getLin() == 1) {
					token = sc.returnToken(hm, line, gt, sc);
				}
				int flagPosCol = 0;
				int Poscol = 0;
				int contLine2 = 0;
				//para ficar de acordo com a coluna numerica
				contLine2 += -1;
				
				String classeToEmpilha = "";
				while ((l2 = conteudoCsv.readLine()) != null) {
		        	String[] coluna = l2.split(delimiter);
					while(colCSV < qtdColCSV) {
						t = coluna[colCSV];
						String t2 = token.getClasse();
						int t3 = (int) s.peek();
						String t4 = coluna[Poscol];
						
						//verifica se o valor da tabela é igual a classe do token
						if(t.equals(t2.toLowerCase())) {
							flagPosCol = Poscol;
							classeToEmpilha = coluna[flagPosCol];
							
							
							/*if(colCSV == 1)
								System.out.println(coluna[colCSV]);
							 */
						}
						Poscol++;
						// verifica se contem S e a ultima posicção da pilha
						//if (ACTION [s,a] = shift t )
						if(/*token.getClasse()*/coluna[flagPosCol].contains("S") && contLine2 == (int) s.peek()) {
							char tLine = coluna[flagPosCol].charAt(1);
							s.push(classeToEmpilha);
							s.push(tLine);
							
							System.out.println(s);
							System.out.println(coluna[flagPosCol]);
							
							
							flagPosCol = 0;
						}else if(coluna[flagPosCol].contains("R") && contLine2 == (int) s.peek()) {
							
						}
						
						colCSV++;
						
					}
					contLine2++;
					colCSV=0;
					Poscol = 0;
		        }
				
			//*** sintatico
				//System.out.println("tk: " + token);
			}
			gt.setPos(0); //volta pos coluna para inicio, 0
			//l++;
			//col = 0;
			sc.incrementaLin();
			sc.setCol(0);
		}
		System.out.println("Classe: EOF, Lexema: EOF, Tipo: Nulo");
		//System.out.println(hm);
		
		conteudoAtualLine.close();
	}
}