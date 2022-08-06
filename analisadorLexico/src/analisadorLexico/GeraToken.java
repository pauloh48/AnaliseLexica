package analisadorLexico;

public class GeraToken {
	private char[] 	content;
	private int 	estado;
	private int		pos;
	private int 	posErro = 0;
	
	public int getPosErro() {
		return posErro;
	}

	public void setPosErro(int posErro) {
		this.posErro = posErro;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	//automato 
	public Token nextToken(String contenLine) {
		char currentChar;
		char charAnterior = '\0';
		char temp = 0;
		content = contenLine.toCharArray();
		Token token;
		String term = "";
		String msgErro = "";
		estado = 0;

		while(true) {	
			currentChar = nextchar();
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
				//OPR =
				}else if(isOPRIgual(currentChar)) {
					term += currentChar;
					estado = 13;
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
				else if (isPT_V(currentChar)) {
					term += currentChar;
					estado = 21;
				}
				// VIR
				else if (isVIR(currentChar)) {
					term += currentChar;
					estado = 22;
				}
				// ESPAÇO
				else if (isSpace(currentChar)) {
					estado = 0;
				}
				// EOF
				else if(isEOF() && currentChar == '\0') {
					estado = 26;
					return null;
				}
				// ERRO
				else {
					term+= currentChar;
					setPosErro(pos);
					estado = 28;
					msgErro = "palavra invalida";
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
					charAnterior = currentChar;
					estado = 3;
				}else if(isE_e(currentChar)) {
					term += currentChar;
					estado = 4;
				}else if(isSpace(currentChar)) {
					//ignora espaco
					estado = 5;
				}
				else if(isVIR(currentChar) || isPT_V(currentChar) || 
						isFC_P(currentChar) || isAbreColchete(currentChar)) {
					back();
					estado = 5;
				}
				else if(currentChar == '\0') {
					estado = 5;
				}
				//ERRO
				else {
					temp+= currentChar;
					estado = 29;
					setPosErro(pos);
					msgErro = "numeoro Invalido";
				}
				break;
			case 3:
				if(isDigit(currentChar)) {
					term += currentChar;
					estado = 3;
				}else if(isE_e(currentChar)) {
					term += currentChar;
					estado = 4;
				}else if(isSpace(currentChar)) {
					//ignora espaco
					if(charAnterior == '.') {
						estado = 24; //erro
						posErro++;
						msgErro = "numeoro incompleto";
					}
					else
						estado = 5;
				}
				else if(isVIR(currentChar) || isPT_V(currentChar) || isFC_P(currentChar)) {
					back();
					estado = 5;
				}
				else if(currentChar == '\0') {
					estado = 5;
				}
				//ERRO
				else {
					temp+= currentChar;
					estado = 29;
					setPosErro(pos);
					msgErro = "numeoro Invalido";
				}
				break;
			case 4:
				if(ispositivo_negativo(currentChar)) {
					term += currentChar;
					estado = 3;
				}
				else if(isDigit(currentChar)) {
					term += currentChar;
					estado = 3;
				}
				else if(isVIR(currentChar) || isPT_V(currentChar) || isFC_P(currentChar)) {
					back();
					estado = 5;
				}
				else if(currentChar == '\0') {
					estado = 5;
				}
				//ERRO
				else {
					setPosErro(pos);
					term+= currentChar;
					estado = 29;
					msgErro = "numeoro Invalido";
				}
				break;
			case 5:
				token = new Token();
				token.setClasse(Token.TK_NUMBER);
				token.setLexema(term);
				updatePosCol(currentChar, token);
				return token;
				
		// NUM ******************************
				
		// LITERAL **************************
			case 6:
				if(isQuote(currentChar)) {
					term += currentChar;
					estado = 7;
				}else if(currentChar != '\0'){
					term += currentChar;
					estado = 6;
				}
				//ERRO
				if(currentChar == '\0'){
					term+= currentChar;
					estado = 24;
					setPosErro(pos);
					msgErro = "nao fechou aspas duplas '\"'";
				}
				break;
			case 7:
				token = new Token();
				token.setClasse(Token.TK_LITERAL);
				token.setLexema(term);
				updatePosCol(currentChar, token);
				return token;
		// LITERAL **************************
		
		// ID *******************************
			case 8:
				if(isChar(currentChar) || isDigit(currentChar) || isUnderline(currentChar)) {
					term += currentChar;
					estado = 8;
				}
				//ERRO
				else if(!isChar(currentChar) && !isDigit(currentChar) 
						&& !isUnderline(currentChar) && !isSpace(currentChar) 
						&& /*!isEOF() &&*/ !isVIR(currentChar) && !isPT_V(currentChar)
						&& !isOPRMaior(currentChar) && !isOPRMenor(currentChar)
						&& !isOPRIgual(currentChar) && !isRCBTraco(currentChar)
						&& !isOPRMatematico(currentChar) && !isFC_P(currentChar)
						&& !isAB_P(currentChar)	&& !isQuote(currentChar) && currentChar != '\0') {
					//temporário para verificar se realmente é erro
					temp = currentChar;
					estado = 23;
					setPosErro(pos);
				}
				else {
					token = new Token();
					token.setClasse(Token.TK_IDENTIFIER);
					token.setLexema(term);
					updatePosCol(currentChar, token);
					return token;
				}
				break;
		// ID *******************************
				
		// COMENTARIO ***********************
			case 9:
				if(isFechaColchete(currentChar)) {
					term += currentChar;
					estado = 10;
				}else if(currentChar != '\0'){
					term += currentChar;
					estado = 9;
				}
				//ERRO
				if(currentChar == '\0'){
					term+= currentChar;
					estado = 24;
					setPosErro(pos);
					msgErro = "nao fechou colchetes '}'";
				}
				break;
			case 10:
				token = new Token();
				token.setClasse(Token.TK_COMENTARIO);
				token.setLexema(term);
				updatePosCol(currentChar, token);
				return token;
		// COMENTARIO ***********************
				
		// OPR >=      ***********************
			case 12:
				// >=
				if(isOPRIgual(currentChar)) {
					term += currentChar;
					estado = 13;
				}
				// >
				else {
					estado = 13;
					if(currentChar != '\0') {
						back();
					}
				}
				break;
			case 13:
				token = new Token();
				token.setClasse(Token.TK_OPERATOR);
				token.setLexema(term);
				updatePosCol(currentChar, token);
				return token;
		// OPR >=		***********************
				
		// OPR e RCB <  ***********************
			case 15:
				// OPR <=
				if(isOPRIgual(currentChar)) {
					term += currentChar;
					estado = 13;
				// OPR <>
				}else if(isOPRMaior(currentChar)) {
					term += currentChar;
					estado = 13;
				// <-
				}else if(isRCBTraco(currentChar)) {
					term += currentChar;
					estado = 17;
				}
				// <
				else {
					estado = 13;
					if(currentChar != '\0') {
						back();
					}
				}
				break;
			// OPR e RCB <  ***********************
			case 17:
				token = new Token();
				token.setClasse(Token.TK_RCB);
				token.setLexema(term);
				updatePosCol(currentChar, token);
				return token;
				
			//OPRMATERMATICO ***********************
			case 18:
				token = new Token();
				token.setClasse(Token.TK_OPRMATEMATICO);
				token.setLexema(term);
				updatePosCol(currentChar, token);
				return token;
				
			// AB_P ********************************
			case 19:
				token = new Token();
				token.setClasse(Token.TK_AB_P);
				token.setLexema(term);
				updatePosCol(currentChar, token);
				return token;
			// FC_P ********************************
			case 20:
				token = new Token();
				token.setClasse(Token.TK_FC_P);
				token.setLexema(term);
				updatePosCol(currentChar, token);
				return token;
			// PT_V ********************************
			case 21:
				token = new Token();
				token.setClasse(Token.TK_PT_V);
				token.setLexema(term);
				updatePosCol(currentChar, token);
				return token;
			// VIR *********************************
			case 22:
				token = new Token();
				token.setClasse(Token.TK_VIR);
				token.setLexema(term);
				updatePosCol(currentChar, token);
				return token;
			// ERRO ID ******************************
			case 23:
				//contaColErro++;
				if(isVIR(currentChar) || isPT_V(currentChar) || isSpace(currentChar)) {
					estado = 27;
					back();
					back();
				}
				else if(currentChar == '\0') {
					estado = 27;
				}//ERRO
				else {
					term += temp;
					term += currentChar;
					estado = 28;
					msgErro = "identificador Invalido";
				}
				break;
			
			case 24:
				token = new Token();
				token.setClasse(Token.TK_ERRO);
				token.setLexema(term);
				token.setColunaAtual(pos);
				token.setMsgErro(msgErro);
				updatePosCol(currentChar, token);
				return token;
			/*case 25:
				token = new Token();
				token.setClasse(Token.TK_ERRO);
				token.setLexema(term);
				return token;
			*/case 26:
				token = new Token();
				token.setClasse(Token.TK_EOF);
				token.setLexema(term);
				return token;
			
		// ERRO ID ******************************	
			case 27:
				token = new Token();
				token.setClasse(Token.TK_IDENTIFIER);
				token.setLexema(term);
				pos = pos - 1;
				token.setColunaAtual(pos);
				return token;
		
		// ERRO     *****************************
			case 28:
				if(isVIR(currentChar) || isPT_V(currentChar)) {
					back();
					estado = 24;
				}
				else if(currentChar =='\0' || isSpace(currentChar)) {
					estado = 24;
				}
				else {
					term += currentChar;
					estado = 28;
				}
				break;
				
			case 29:
				if(isVIR(currentChar) || isPT_V(currentChar) || isSpace(currentChar)) {
					estado = 30;
					back();
					back();
				}
				else if(currentChar == '\0') {
					estado = 30;
				}
				else {
					term += temp;
					term += currentChar;
					estado = 28;
				}
				break;
				
			case 30:
				token = new Token();
				token.setClasse(Token.TK_NUMBER);
				token.setLexema(term);
				pos = pos - 1;
				token.setColunaAtual(pos);
				return token;
			}
		}
	}

	private void updatePosCol(char currentChar, Token token) {
		if(currentChar == '\0') {
			token.setColunaAtual(pos);
			back();
		}
		else {
			back();
			token.setColunaAtual(pos);
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
	/*
	private boolean isOtherSymbolsNoCloseQuote(char c) {	// remove "
		String symbols = ",;:.!?\\*+-/(){}[]+<>='";
		String cAux = Character.toString(c);
		return symbols.contains(cAux);
	}
	
	private boolean isOtherSymbolsNoCloseBracket(char c) { //remove }
		String symbols = ",;:.!?\\*+-/(){[]+<>='\"";
		String cAux = Character.toString(c);
		return symbols.contains(cAux);
	}
	*/
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
	/*private boolean isOperator(char c) {
		return c == '>' || c == '<' || c == '=' || c == '!'; 
	}*/
	private boolean isSpace(char c) {
		return c == ' ' || c == '\t' || c == '\n' || c == '\r'; 
	}
	private boolean isEOF() {
		return pos >= content.length;
	}
	
	private char nextchar() {
		if (isEOF()) {
			return '\0';
		}
		return content[pos++];
	}
	private void back() {
		pos--;
	}
	
	/*private boolean isEOF(char c) {
    	return c == '\0';
    }*/
}