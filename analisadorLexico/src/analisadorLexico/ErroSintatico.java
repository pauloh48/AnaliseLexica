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
		hmErro.put("e1", "lexema inesperado");
		hmErro.put("e0", "programa não inicializado corretamente (talvez falte inicio)");
		hmErro.put("et1", "programa não possui varinicio");
		hmErro.put("et29", "ID nao encontrado");
		hmErro.put("et3", "';' não encontrado");
		hmErro.put("et4", "não encontrado ID, ou NUM, ou LIT");
		hmErro.put("et5", "'<-' não encontrado");
		hmErro.put("et6", "Operador matematico não encontrado (+ - * /)");
		hmErro.put("et7", "Operador ou ID ou NUM não encontrado");
		hmErro.put("et8", "'(' não encontrado");
		hmErro.put("et9", "')' não encontrado");
		hmErro.put("et10", "'entao' não encontrado");
		hmErro.put("et11", "operadores (?) não encontrado");
		hmErro.put("et12", "Operadores ou ID ou NUM");
		hmErro.put("et13", "',' não encontrado");
		
		hmErro.put("et14", "LIT não encontrado");
		hmErro.put("et17", "NUM não encontrado");
		hmErro.put("et18", "ID não encontrado");
		
		hmErro.put("et15", "leitura declarada incorretamente");
		hmErro.put("et16", "escrita declarada incorretamente");
		
		hmErro.put("et19", "TIPO de dado inexistente, use inteiro, literal ou real");
		hmErro.put("et20", "inteiro declarado incorretamente");
		hmErro.put("et21", "real declarado incorretamente");
		hmErro.put("et22", "literal declarado incorretamente");
		hmErro.put("et23", "aguarda virgula ou identificador");
		
		
		
		
		return hmErro;
	}

}
