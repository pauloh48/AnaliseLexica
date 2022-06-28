package analisadorLexico;

public class Token {
	public static final String TK_NUMBER 		= "NUM";
	public static final String TK_LITERAL		= "LIT";
	public static final String TK_IDENTIFIER 	= "ID";
	public static final String TK_COMENTARIO	= "COMENTARIO";
	public static final String TK_OPERATOR 		= "OPR";
	public static final String TK_ERRO			= "0";
	public static final String TK_RCB			= "RCB";
	public static final String TK_OPRMATEMATICO	= "OPM";
	public static final String TK_AB_P			= "AB_P";
	public static final String TK_FC_P			= "FC_P";
	public static final String TK_PT_V			= "PT_V";
	public static final String TK_VIR			= "VIR";
	public static final String TK_EOF			= "EOF";
	
	private String 	type;
	private String 	text;
	private int colunaAtual;
	
	public String typeLexema(String type, String text) {
		String tipo = "";
		if(type.equals("NUM")) {
			if(text.contains(".") || text.contains("E") || text.contains("e")) {
				tipo = "flutuante";
			}else {
				tipo = "inteiro";
			}
		}
		return tipo;
	}
	
	public Token(String type, String text) {
		super();
		this.type = type;
		this.text = text;
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

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Classe: " + type + ", Lexema: " + text + ", tipo: " + typeLexema(type, text);
	}
}
