package analisadorLexico;
//import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Stack;

public class mainScanner {

	public static void main(String[] args) throws IOException {
		
		// Abre fonte
		//String filename = "C:/Users/lfsil/OneDrive/Documentos/Faculdade/Compiladores/T2/Analisador Sintático 3/AnaliseLexica-main/analisadorLexico/src/analisadorLexico/fonte.alg";
		String filename = "/home/paulo/Documents/AnaliseLexica/analisadorLexico/src/analisadorLexico/fonte.alg";				
	    BufferedReader conteudoAtualLine = new BufferedReader(new FileReader(filename));
        
	    // abre CSV
        //String filenameCSV = "C:/Users/lfsil/OneDrive/Documentos/Faculdade/Compiladores/T2/Analisador Sintático 3/AnaliseLexica-main/analisadorLexico/src/analisadorLexico/TabelaTransicoes.csv";
        String filenameCSV = "/home/paulo/Documents/AnaliseLexica/analisadorLexico/src/analisadorLexico/TabelaTransicoes.csv";
	    BufferedReader conteudoCsv = new BufferedReader(new FileReader(filenameCSV));
        
	    // Analise Sintatica
        Parser ps = new Parser();
        
        ps.inicilizaRegras();
        ps.parser(filename, conteudoAtualLine, filenameCSV, conteudoCsv);
        //System.out.println("Classe: EOF, Lexema: EOF, Tipo: Nulo");
		//System.out.println(hm);
		
		conteudoAtualLine.close();
	}
}