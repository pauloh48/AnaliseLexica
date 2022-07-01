package analisadorLexico;

import java.util.Hashtable;
import java.util.HashMap;

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
	
	private String 	type;
	private String 	text;
	private int colunaAtual;
	private String tipo;
	
	public String typeLexema(String type, String text) {
	
		
		this.tipo = "Nulo";
		if(type.equals("NUM")) {
			if(text.contains(".") || text.contains("E") || text.contains("e")) {
				this.tipo = "flutuante";
			}else {
				this.tipo = "inteiro";
			}
		}
		
		if(type.equals("LIT")) {
			tipo = "literal";
		}
		
		/*if(type.equals("ID")) {
			///Verificar na hash table se é uma palavra reservada ou se já está preenchido na hash table
			//Se não estiver preenchido, inserir e retornar com o tipo = Nulo 
			tipo = hm.get(text);
			if(tipo == null) {
				hm.put(text, "ID");
			}
			else {
				tipo = hm.get(text);
				type = text;
			}
		}
		*/
		if(type.equals("COMENTARIO")) {
			tipo = "Nulo";
		}
		
		if(type.equals("OPR")) {
			tipo = "Nulo";
		}
		
		if(type.equals("ERRO")) {
			tipo = "Nulo";
		}
		
		if(type.equals("RCB")) {
			tipo = "Nulo";
		}
		
		if(type.equals("OPM")) {
			tipo = "Nulo";
		}
		
		if(type.equals("AB_P")) {
			tipo = "Nulo";
		}
		
		if(type.equals("FC_P")) {
			tipo = "Nulo";
		}
		
		if(type.equals("PT_V")) {
			tipo = "Nulo";
		}
		
		if(type.equals("VIR")) {
			tipo = "Nulo";
		}
		
		if(type.equals("EOF")) {
			tipo = "Nulo";
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Classe: " + type + ", Lexema: " + text + ", tipo: " + typeLexema(type, text);
	}
}