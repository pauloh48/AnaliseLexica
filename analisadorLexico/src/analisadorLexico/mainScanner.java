package analisadorLexico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Hashtable;

public class mainScanner {

	public static void main(String[] args) throws IOException {
		// palavras reservadas -> tabelahash
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
		
		String filename = "/home/paulo/Documents/AnaliseLexica-main/analisadorLexico/src/analisadorLexico/fonte.alg";
		BufferedReader conteudoAtualLine = new BufferedReader(new FileReader(filename));
		
		Token token = null;
		String line = "";
		
		int l = 1;
		int c = 0;
		int size = 0;
		String tipo_tabela;
		OScanner sc = new OScanner();

		while ((line = conteudoAtualLine.readLine()) != null) {
			size = line.length();
			while(c < line.length()) {
				token = sc.nextToken(line);
				c = token.getColunaAtual();
				if(token != null) {
					if(token.getType().equals("ID")) {
						/*Verificar na hash table se é uma palavra reservada ou se já está preenchido na hash table*/
						/*Se não estiver preenchido, inserir e retornar com o tipo = Nulo */
						tipo_tabela = hm.get(token.getText());
						if(tipo_tabela == null) {
							hm.put(token.getText(), "ID");
						}
						else {
							tipo_tabela = hm.get(token.getText());
							token.setText(tipo_tabela);
							token.setType(token.getText());
							token.setTipo(tipo_tabela);
						}
						if(tipo_tabela == null) tipo_tabela = "Nulo";
						System.out.println("Classe: " + hm.get(token.getText()) + ", Lexema: " + token.getText() + ", tipo: " + tipo_tabela);
					}
					else System.out.println(token);
					if(token.getType().equals("ERRO")) 
						System.out.println("\tERRO lexico – Caractere invalido na\n"
								+ "\tlinguagem. Linha " + l + ", coluna " + c + ".");
				}
				//System.out.println(hm);
			}
			sc.setPos(0); //volta pos coluna para inicio, 0
			l++;
			c = 0;
		}
		System.out.println("Classe: EOF, Lexema: EOF, Tipo:Nulo ");
		
		conteudoAtualLine.close();
	}

}