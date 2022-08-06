package analisadorLexico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

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
		
		//int l = 1;
		//int col = 0;
		//String tipo_tabela;
		GeraToken gt = new GeraToken();

		while ((line = conteudoAtualLine.readLine()) != null) {
			//while(col < line.length()) {
			while(sc.getCol() < line.length()) {
				token = sc.returnToken(hm, line, gt, sc);
				//System.out.println("tk: " + token);
			}
			gt.setPos(0); //volta pos coluna para inicio, 0
			//l++;
			//col = 0;
			sc.incrementaLinErro();
			sc.setCol(0);
		}
		System.out.println("Classe: EOF, Lexema: EOF, Tipo: Nulo");
		//System.out.println(hm);
		
		conteudoAtualLine.close();
	}
}