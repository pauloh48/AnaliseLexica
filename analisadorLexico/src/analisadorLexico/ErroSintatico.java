package analisadorLexico;

import java.util.HashMap;
/**
 * para erro, adicionar
 * 	//inicio sintatico ************************** line 42
		ErroSintatico error= new ErroSintatico();
		HashMap<String, String> hmErro = ErroSintatico.erros();
 * 
 * line 340
   }
									// ERRO
									if(coluna2[flagPosCol].contains("e1")) {
										//verifica se na tabela de erros do hash tem o erro
										if(hmErro.containsKey("e1")){
											// imprime o valor do erro
											System.out.println(hmErro.get("e1"));
										}
										break principal;
									}

 * @author paulo
 *
 */
public class ErroSintatico {
	
	//hash map para mapear erros
	public static HashMap<String, String> erros() {
		// palavras reservadas -> hash map
		HashMap<String, String> hmErro = new HashMap<String, String>();
		//e1 : lexema inesperado
		//e2 : lexema fora do escopo
		hmErro.put("e1", "ERRO: lexema inesperado");
		hmErro.put("e0", "ERRO: programa não inicializado corretamente (talvez falte inicio)");
		hmErro.put("et1", "ERRO: programa não possui varinicio");
		hmErro.put("et29", "ERRO: ID nao encontrado");
		hmErro.put("et3", "ERRO: ';' não encontrado");
		hmErro.put("et4", "ERRO: não encontrado ID, ou NUM, ou LIT");
		hmErro.put("et5", "ERRO: '=' não encontrado");
		hmErro.put("et6", "ERRO: Operador matematico não encontrado (+ - * /)");
		hmErro.put("et7", "ERRO: Operador ou ID ou NUM não encontrado");
		hmErro.put("et8", "ERRO: '(' não encontrado");
		hmErro.put("et9", "ERRO: ')' não encontrado");
		hmErro.put("et10", "ERRO: 'entao' não encontrado");
		hmErro.put("et11", "ERRO: operadores (?) não encontrado");
		hmErro.put("et12", "ERRO: Operadores ou ID ou NUM");
		hmErro.put("et13", "ERRO: ',' não encontrado");
		
		hmErro.put("et14", "ERRO: LIT não encontrado");
		
		
		return hmErro;
	}

}
