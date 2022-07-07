package analisadorLexico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class mainScanner {

	public static void main(String[] args) throws IOException {
		// palavras reservadas -> hash map
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("inicio","inicio");
		hm.put("varinicio","varinicio");
		hm.put("varfim","varfim");
		hm.put("escreva","escreva");
		hm.put("leia","leia");
		hm.put("se","se");
		hm.put("entao","entao");
		hm.put("fimse","fimse");
		hm.put("Repita","Repita");
		hm.put("fimRepita","fimRepita");
		hm.put("fim","fim");
		hm.put("inteiro","inteiro");
		hm.put("literal","literal");
		hm.put("real","real");
		
		/*String filename = "C:\\Users\\lfsil\\OneDrive\\Documentos\\Faculdade\\Compiladores\\"
				+ "T1\\Analisador Léxico 3\\AnaliseLexica-main\\analisadorLexico\\src\\"
				+ "analisadorLexico\\fonte.alg";
		 */String filename = "/home/paulo/Documents/AnaliseLexica-main/analisadorLexico/src"
		 		+ "/analisadorLexico/fonte.alg";
		 
		BufferedReader conteudoAtualLine = new BufferedReader(new FileReader(filename));
		Token token = null;
		String line = "";
		
		int l = 1;
		int c = 0;
		String tipo_tabela;
		OScanner sc = new OScanner();

		while ((line = conteudoAtualLine.readLine()) != null) {
			while(c < line.length()) {
				token = sc.nextToken(line);
				if(token == null) 
					break;
				
				c = token.getColunaAtual();
				
				if(token != null) {
					if(token.getClasse().equals("ID")) {
						
						/*Verificar na hash table se é uma palavra reservada ou 
						 * se já está preenchido na hash table*/
						/*Se não estiver preenchido, inserir e retornar com o tipo = Nulo */
						tipo_tabela = hm.get(token.getLexema());
						
						if(tipo_tabela == null) {
							hm.put(token.getLexema(), "ID");
							tipo_tabela = "Nulo";
						}
						else {
							//verifica se é palavra reservada ou ID
							if(hm.get(token.getLexema()) == "ID") {
								tipo_tabela = "Nulo";
							}
							else {
								tipo_tabela = hm.get(token.getLexema());
							}
						}
						System.out.println("Classe: " + hm.get(token.getLexema()) + 
											", Lexema: " + token.getLexema() + ", tipo: " 
											+ tipo_tabela);
					}
					else System.out.println(token);
					if(token.getClasse().equals("ERRO")) 
						System.out.println("\tERRO lexico – Caractere invalido na\n"
											+ "\tlinguagem. Linha " + l + ", coluna " 
											+ sc.getPosErro() + ".");
				}
			}
			sc.setPos(0); //volta pos coluna para inicio, 0
			l++;
			c = 0;
		}
		System.out.println("Classe: EOF, Lexema: EOF, Tipo: Nulo");
		//System.out.println(hm);
		
		conteudoAtualLine.close();
	}

}