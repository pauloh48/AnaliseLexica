package analisadorLexico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class mainScanner {

	public static void main(String[] args) throws IOException {
		// palavras reservadas -> tabelahash
		String filename = "/home/paulo/Documents/AnaliseLexica/analisadorLexico/src/analisadorLexico/fonte.alg";
		BufferedReader conteudoAtualLine = new BufferedReader(new FileReader(filename));
		
		Token token = null;
		String line = "";
		
		int l = 1;
		int c = 0;
		int size = 0;
		OScanner sc = new OScanner();

		while ((line = conteudoAtualLine.readLine()) != null) {
			size = line.length();
			while(c < line.length()) {
				token = sc.nextToken(line);
				c = token.getColunaAtual();
				if(token != null) {
					System.out.println(token);
				}
			}
			sc.setPos(0); //volta pos coluna para inicio, 0
			l++;
			c = 0;
		}while(token != null);

	}

}
