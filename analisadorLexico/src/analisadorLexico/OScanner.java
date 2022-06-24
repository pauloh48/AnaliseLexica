package analisadorLexico;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.management.RuntimeErrorException;

public class OScanner {
	private char[] 	content;
	private int 	estado;
	private int		pos;
	
	public OScanner(String filename) {
		try {
			//le arquivo como um unico vetor
			String txtConteudo;
			txtConteudo = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
			
			content = txtConteudo.toCharArray();
			pos = 0;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//automato 
	public Token nextToken() {
		char currentChar;
		Token token;
		String term = "";
		if(isEOF()) {
			return null;
		}
		estado = 0;
		while(true) {
			currentChar = nextchar();
			//term += currentChar;
			
			switch(estado) {
			case 0:
				// NUM ******************************
				if(isDigit(currentChar)) {
					term += currentChar;
					estado = 1;
				}
				// LITERAL
				else if(isQuote(currentChar)) {
					term += currentChar;
					estado = 6;
				// ID
				}else if(isChar(currentChar)) {
					term += currentChar;
					estado = 8;
				// COMENTARIO
				}else if(isAbreColchete(currentChar)) {
					term += currentChar;
					estado = 9;
				// OPR >
				}else if(isOPRMaior(currentChar)) {
					term += currentChar;
					estado = 12;
				// OPR e RCB
				}else if(isOPRMenor(currentChar)) {
					term += currentChar;
					estado = 15;
				}
				// OPRMATEMATICO
				else if (isOPRMatematico(currentChar)) {
					term += currentChar;
					estado = 18;
				}
				// AB_P
				else if (isAB_P(currentChar)) {
					term += currentChar;
					estado = 19;
				}
				// FC_P
				else if (isFC_P(currentChar)) {
					term += currentChar;
					estado = 20;
				}
				// PT_V
				else if (isFC_P(currentChar)) {
					term += currentChar;
					estado = 21;
				}
				// VIR
				else if (isFC_P(currentChar)) {
					term += currentChar;
					estado = 22;
				}
				break;
		// NUM ******************************
			case 1:
				if(isDigit(currentChar)) {
					term += currentChar;
					estado = 1;
				}
				else if(isPonto(currentChar)) {
					term += currentChar;
					estado = 3;
				}else if(isE_e(currentChar)) {
					term += currentChar;
					estado = 4;
				}else if(isSpace(currentChar)) {
					//term += currentChar; ignora espco
					estado = 5;
					back();
				}
				break;
			case 3:
				if(isDigit(currentChar)) {
					term += currentChar;
					estado = 3;
				}
				else if(isSpace(currentChar)) {
					//term += currentChar; ignora espco
					estado = 5;
				}
				else {
					throw new RuntimeException("palavra invalida");
				}
				break;
			case 4:
				if(ispositivo_negativo(currentChar)) {
					term += currentChar;
					estado = 3;
				}
				break;
			case 5:
				token = new Token();
				token.setType(token.TK_NUMBER);
				token.setText(term);
				back();
				return token;
		// NUM ******************************
		// LITERAL ******************
			case 6:
				if(isDigit(currentChar) || isChar(currentChar) || isOtherSymbols(currentChar)) {
					term += currentChar;
					estado = 6;
				}
				else if(isQuote(currentChar)) {
					term += currentChar;
					estado = 7;
				}
				else {
					System.out.println("incompleto");
				}
				break;
			case 7:
				token = new Token();
				token.setType(token.TK_LITERAL);
				token.setText(term);
				back();
				return token;
		// LITERAL ******************
		// ID
			case 8:
				if(isChar(currentChar) || isDigit(currentChar) || isUnderline(currentChar)) {
					term += currentChar;
					estado = 8;
				}else {
					token = new Token();
					token.setType(token.TK_IDENTIFIER);
					token.setText(term);
					back();
					return token;
				}
				break;
		// ID
		// COMENTARIO
			case 9:
				if(isDigit(currentChar) || isChar(currentChar) || isOtherSymbolsQuote(currentChar)) {
					term += currentChar;
					estado = 9;
				}
				else if(isFechaColchete(currentChar)) {
					term += currentChar;
					estado = 10;
				}
				else {
					System.out.println("incompleto");
				}
				break;
			case 10:
				token = new Token();
				token.setType(token.TK_COMENTARIO);
				token.setText(term);
				back();
				return token;
		// COMENTARIO		
		// OPR >= 
			case 12:
				if(isOPRIgual(currentChar)) {
					term += currentChar;
					estado = 13;
				}
				else {
					System.out.println("Invalido");
				}
				break;
			case 13:
				token = new Token();
				token.setType(token.TK_OPERATOR);
				token.setText(term);
				back();
				return token;
		// OPR >=
		// OPR e RCB <
			case 15:
				// OPR <=
				if(isOPRIgual(currentChar)) {
					term += currentChar;
					estado = 13;
				// OPR <>
				}else if(isOPRMaior(currentChar)) {
					term += currentChar;
					estado = 13;
				}else if(isRCBTraco(currentChar)) {
					term += currentChar;
					estado = 17;
				}else {
					System.out.println("Invalido");
				}
				break;
			// RCB
			case 17:
				token = new Token();
				token.setType(token.TK_RCB);
				token.setText(term);
				back();
				return token;
			//OPRMATERMATICO
			case 18:
				token = new Token();
				token.setType(token.TK_OPRMATEMATICO);
				token.setText(term);
				back();
				return token;
			// AB_P
			case 19:
				token = new Token();
				token.setType(token.TK_AB_P);
				token.setText(term);
				back();
				return token;
			// FC_P
			case 20:
				token = new Token();
				token.setType(token.TK_FC_P);
				token.setText(term);
				back();
				return token;
			// PT_V
			case 21:
				token = new Token();
				token.setType(token.TK_PT_V);
				token.setText(term);
				back();
				return token;
			// VIR
			case 22:
				token = new Token();
				token.setType(token.TK_VIR);
				token.setText(term);
				back();
				return token;
			}
		}
	}
	//num
	private boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}
	private boolean isPonto(char c) {
		return c == '.';
	}
	private boolean isE_e(char c) {
		return c == 'E' || c == 'e';
	}
	private boolean ispositivo_negativo(char c) {
		return c == '+' || c == '-';
	}
	
	// Literal
	private boolean isQuote(char c) {
		return c == '"';
	}
	private boolean isOtherSymbols(char c) {	// remove "
		String symbols = ",;:.!?\\*+-/(){}[]+<>='";
		String cAux = Character.toString(c);
		return symbols.contains(cAux);
	}
	private boolean isOtherSymbolsQuote(char c) { //remove }
		String symbols = ",;:.!?\\*+-/(){[]+<>='\"";
		String cAux = Character.toString(c);
		return symbols.contains(cAux);
	}
	private boolean isUnderline(char c) {
		return c == '_';
	}
	private boolean isAbreColchete(char c) {
		return c == '{';
	}
	private boolean isFechaColchete(char c) {
		return c == '}';
	}
	private boolean isOPRMaior(char c) {
		return c == '>';
	}
	private boolean isOPRMenor(char c) {
		return c == '<';
	}
	private boolean isOPRIgual(char c) {
		return c == '=';
	}
	private boolean isRCBTraco(char c) {
		return c == '-';
	}
	private boolean isOPRMatematico(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}
	private boolean isAB_P(char c) {
		return c == '(';
	}
	private boolean isFC_P(char c) {
		return c == ')';
	}
	private boolean isPT_V(char c) {
		return c == ';';
	}
	private boolean isVIR(char c) {
		return c == ',';
	}
	
	private boolean isChar(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'); 
	}
	private boolean isOperator(char c) {
		return c == '>' || c == '<' || c == '=' || c == '!'; 
	}
	private boolean isSpace(char c) {
		return c == ' ' || c == '\t' || c == '\n' || c == '\r'; 
	}
	private boolean isEOF() {
		return pos == content.length;
	}
	
	private char nextchar() {
		return content[pos++];
	}
	private void back() {
		pos--;
	}
}
