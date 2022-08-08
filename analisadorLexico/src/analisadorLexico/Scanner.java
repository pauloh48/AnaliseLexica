package analisadorLexico;

import java.util.HashMap;

public class Scanner {
	private int col;
	private int lin = 1;
	
	public static HashMap<String, String> tabelaSimbolos() {
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
		return hm;
	}
	
	public Token returnToken(HashMap<String, String> hm, String line, GeraToken gt, Scanner sc) {
		Token token = null;
		//int col;
		String tipo_tabela;
		
		token = gt.nextToken(line);
		if(token == null) {
			return null;
		}
		
		//col = token.getColunaAtual();
		sc.setCol(token.getColunaAtual());
		
		if(token != null) {
			if(token.getClasse().equals("ID")) {
				
				/*Verificar na hash table se é uma palavra reservada ou 
				 * se já está preenchido na hash table*/
				/*Se não estiver preenchido, inserir e retornar com o tipo = Nulo */
				tipo_tabela = hm.get(token.getLexema());
				
				if(tipo_tabela == null) {
					hm.put(token.getLexema(), "ID");
					tipo_tabela = "Nulo";
					token.setTipo("Nulo");
				}
				else {
					//verifica se é palavra reservada ou ID
					if(hm.get(token.getLexema()) == "ID") {
						tipo_tabela = "Nulo";
						token.setTipo("Nulo");
					}
					else {
						tipo_tabela = hm.get(token.getLexema());
						token.setTipo(hm.get(token.getLexema()));
						token.setClasse(hm.get(token.getLexema()));
						
					}
				}
			//	System.out.println("Classe: " + hm.get(token.getLexema()) + 
			//						", Lexema: " + token.getLexema() + ", tipo: " 
			//						+ tipo_tabela);
				System.out.println(token);
			}
			else { 
				tipo_tabela = token.tipoLexema(token.getClasse(), token.getLexema());
				token.setTipo(token.tipoLexema(token.getClasse(), token.getLexema()));
				System.out.println(token);
			}
			if(token.getClasse().equals("ERRO")) 
				System.out.println("\tERRO lexico – Caractere invalido na\n"
									+ "\tlinguagem. Linha " + lin + ", coluna " 
									+ gt.getPosErro() + "\n\tTipo: " + token.getMsgErro() 
									+ ".");
		}
		return token;
	}
	
	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getLin() {
		return lin;
	}

	public void setLin(int lin) {
		this.lin = lin;
	}
	public void incrementaLin() {
		lin += 1;
		setLin(lin);
	}
}