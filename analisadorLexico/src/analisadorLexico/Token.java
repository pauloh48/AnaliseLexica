package analisadorLexico;

public class Token {
	public static final int TK_NUMBER 		= 0;
	public static final int TK_LITERAL		= 1;
	public static final int TK_IDENTIFIER 	= 2;
	public static final int TK_COMENTARIO	= 3;
	public static final int TK_OPERATOR 	= 4;
	public static final int TK_ERRO			= 5;
	public static final int TK_RCB			= 6;
	public static final int TK_OPRMATEMATICO= 7;
	public static final int TK_AB_P			= 8;
	public static final int TK_FC_P			= 9;
	public static final int TK_PT_V			= 10;
	public static final int TK_VIR			= 11;	
	
	private int 	type;
	private String 	text;
	
	public Token(int type, String text) {
		super();
		this.type = type;
		this.text = text;
	}
	
	public Token() {
		super();
	}
	

	public int getType() {
		return type;
	}
	
	public void setType(int type) {
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
		return "Token [type=" + type + ", text=" + text + "]";
	}
	
	
	
}
