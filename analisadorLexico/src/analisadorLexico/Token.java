package analisadorLexico;

public class Token {
	public static final String TK_NUMBER 		= "NUM";
	public static final String TK_LITERAL		= "LIT";
	public static final String TK_IDENTIFIER 	= "ID";
	public static final String TK_COMENTARIO	= "COMENTARIO";
	public static final String TK_OPERATOR 		= "OPR";
	public static final String TK_ERRO			= "ERRO";
	public static final String TK_RCB			= "RCB";
	public static final String TK_OPRMATEMATICO	= "OPM";
	public static final String TK_AB_P			= "AB_P";
	public static final String TK_FC_P			= "FC_P";
	public static final String TK_PT_V			= "PT_V";
	public static final String TK_VIR			= "VIR";
	public static final String TK_EOF			= "EOF";
	
	private String 	classe;
	private String 	lexema;
	private int 	colunaAtual;
	private String 	tipo;
	
	public String tipoLexema(String classe, String lexema) {
		this.tipo = "Nulo";
		
		if(classe.equals("NUM")) {
			if(lexema.contains(".") || lexema.contains("-")) {
				this.tipo = "flutuante";
			}else {
				this.tipo = "inteiro";
			}
		}
		
		else if(classe.equals("LIT")) {
			tipo = "literal";
		}
		
		else {
			tipo = "Nulo";
		}

		return tipo;
	}
	
	public Token(String classe, String text) {
		super();
		this.classe = classe;
		this.lexema = text;
	}
	
	public Token() {
		super();
	}

	public int getColunaAtual() {
		return colunaAtual;
	}

	public void setColunaAtual(int colunaAtual) {
		this.colunaAtual = colunaAtual;
	}

	public String getClasse() {
		return classe;
	}
	
	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getLexema() {
		return lexema;
	}
	
	public void setLexema(String text) {
		this.lexema = text;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Classe: " + classe + ", Lexema: " + lexema 
				+ ", tipo: " + tipo;
	}
}