package analisadorLexico;
//import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Stack;

public class mainScanner {

	public static void main(String[] args) throws IOException {
		//String filename = "C:/Users/lfsil/OneDrive/Documentos/Faculdade/Compiladores/T2/Analisador Sintático 3/AnaliseLexica-main/analisadorLexico/src/analisadorLexico/fonte.alg";
	    
		String filename = "/home/paulo/Documents/AnaliseLexica/analisadorLexico/src/analisadorLexico/fonte.alg";
 		
		
		//contar quantidade de linhas do arquivo
		
		Path file = Paths.get(filename);
		long linhas = Files.lines(file).count();
		//System.out.println(linhas);
		
		/////////////////////////////////////////
		
		
	    BufferedReader conteudoAtualLine = new BufferedReader(new FileReader(filename));
		HashMap<String, String> hm = Scanner.tabelaSimbolos();
		Scanner sc = new Scanner();
		
		Token token = null;
		String line = "";
		String linhaCSV = "";
		
		//int l = 1;
		//int col = 0;
		//String tipo_tabela;
		GeraToken gt = new GeraToken();
		
//inicio sintatico **************************
		ErroSintatico error= new ErroSintatico();
		HashMap<String, String> hmErro = ErroSintatico.erros();

		
		//regras
		String[][] regra = new String[40][3];
		regra[1][0] = "P'";
		regra[1][1] = "P";
		regra[1][2] = "1";
		regra[2][0] = "P";
		regra[2][1] = "inicio V A";
		regra[2][2] = "3";
		regra[3][0] = "V";
		regra[3][1] = "varinicio LV";
		regra[3][2] = "2";
		regra[4][0] = "LV";
		regra[4][1] = "D LV";
		regra[4][2] = "2";
		regra[5][0] = "LV";
		regra[5][1] = "varfim pt_v";
		regra[5][2] = "2";
		regra[6][0] = "D";
		regra[6][1] = "TIPO L pt_v";
		regra[6][2] = "3";
		regra[7][0] = "L";
		regra[7][1] = "id vir L";
		regra[7][2] = "3";
		regra[8][0] = "L";
		regra[8][1] = "id";
		regra[8][2] = "1";
		regra[9][0] = "TIPO";
		regra[9][1] = "inteiro";
		regra[9][2] = "1";
		regra[10][0] = "TIPO";
		regra[10][1] = "real";
		regra[10][2] = "1";
		regra[11][0] = "TIPO";
		regra[11][1] = "literal";
		regra[11][2] = "1";
		regra[12][0] = "A";
		regra[12][1] = "ES A";
		regra[12][2] = "2";
		regra[13][0] = "ES";
		regra[13][1] = "leia id pt_v";
		regra[13][2] = "3";
		regra[14][0] = "ES";
		regra[14][1] = "escreva ARG pt_v";
		regra[14][2] = "3";
		regra[15][0] = "ARG";
		regra[15][1] = "lit";
		regra[15][2] = "1";
		regra[16][0] = "ARG";
		regra[16][1] = "num";
		regra[16][2] = "1";
		regra[17][0] = "ARG";
		regra[17][1] = "id";
		regra[17][2] = "1";
		regra[18][0] = "A";
		regra[18][1] = "CDM A";
		regra[18][2] = "2";
		regra[19][0] = "CMD";
		regra[19][1] = "id rcb LD pt_v";
		regra[19][2] = "4";
		regra[20][0] = "LD";
		regra[20][1] = "OPRD opm OPRD";
		regra[20][2] = "3";
		regra[21][0] = "LD";
		regra[21][1] = "OPRD";
		regra[21][2] = "1";
		regra[22][0] = "OPRD";
		regra[22][1] = "id";
		regra[22][2] = "1";
		regra[23][0] = "OPRD";
		regra[23][1] = "num";
		regra[23][2] = "1";
		regra[24][0] = "A";
		regra[24][1] = "COND A";
		regra[24][2] = "2";
		regra[25][0] = "COND";
		regra[25][1] = "CAB CP";
		regra[25][2] = "2";
		regra[26][0] = "CAB";
		regra[26][1] = "se ab_p EXP_R fc_p então";
		regra[26][2] = "5";
		regra[27][0] = "EXP_R";
		regra[27][1] = "OPRD opr OPRD";
		regra[27][2] = "3";
		regra[28][0] = "CP";
		regra[28][1] = "ES CP";
		regra[28][2] = "2";
		regra[29][0] = "CP";
		regra[29][1] = "CMD CP";
		regra[29][2] = "2";
		regra[30][0] = "CP";
		regra[30][1] = "COND CP";
		regra[30][2] = "2";
		regra[31][0] = "CP";
		regra[31][1] = "fimse";
		regra[31][2] = "1";
		regra[32][0] = "A";
		regra[32][1] = "R A";
		regra[32][2] = "2";
		regra[33][0] = "R";
		regra[33][1] = "CABR CPR";
		regra[33][2] = "2";
		regra[34][0] = "CABR";
		regra[34][1] = "repita ab_p EXP_R fc_p";
		regra[34][2] = "4";
		regra[35][0] = "CPR";
		regra[35][1] = "ES CPR";
		regra[35][2] = "2";
		regra[36][0] = "CPR";
		regra[36][1] = "CMD CPR";
		regra[36][2] = "2";
		regra[37][0] = "CPR";
		regra[37][1] = "COND CPR";
		regra[37][2] = "2";
		regra[38][0] = "CPR";
		regra[38][1] = "fimrepita";
		regra[38][2] = "1";
		regra[39][0] = "A";
		regra[39][1] = "fim";
		regra[39][2] = "1";
		
		// pilha
        Stack<String> s = new Stack();
        s.push("0");
//        System.out.println(s.peek());
        
        // abrir csv
        //String filenameCSV = "C:/Users/lfsil/OneDrive/Documentos/Faculdade/Compiladores/T2/Analisador Sintático 3/AnaliseLexica-main/analisadorLexico/src/analisadorLexico/TabelaTransicoes.csv";
        
        String filenameCSV = "/home/paulo/Documents/AnaliseLexica/analisadorLexico/src/analisadorLexico/TabelaTransicoes.csv";
	    
	    BufferedReader conteudoCsv = new BufferedReader(new FileReader(filenameCSV));
	    String delimiter = ",";
        
        int colCSV = 0;
        int qtdColCSV = 46;
        String simboloCSV;
        String tmp;
        int j;
        int last = 0;
        int isEOF = 0;

// fim sintatico ***************************
        principal:
		while ((line = conteudoAtualLine.readLine()) != null) {
			while(sc.getCol() < line.length()) {
				//** comentario por sintático 
				//pega próximo token (última operação foi shift)
				if(last == 0) {
					token = sc.returnToken(hm, line, gt, sc);
					last = 1;
				}
			//***sintatico
				int flagPosCol = 0;
				int Poscol = 0;
//				int contLine2 = 0;
				//para ficar de acordo com a coluna numérica
//				contLine2 += -1;
				
				String classeToEmpilha = "";
				interno:
				while ((linhaCSV = conteudoCsv.readLine()) != null) {
		        	String[] coluna = linhaCSV.split(delimiter);
		        	operacao:
					while(colCSV < qtdColCSV) {
						simboloCSV = coluna[colCSV];
						String classe = token.getClasse();
				////////////////////////////////////////////////////
						if (isEOF == 2) {
							classe = "EOF";
						}
						
						//verifica se o valor da tabela é igual a classe do token
						if(simboloCSV.equals(classe/*.toLowerCase()*/)) {
							//encontrou a coluna (classe)
							flagPosCol = Poscol;
							classeToEmpilha = coluna[flagPosCol];
							
							System.out.println(classe);

							
							//já encontrou a classe (coluna) na tabela, então passa a procurar o estado (linha)
//							contLine2++;
							colCSV = 0;
							while(true) {
								linhaCSV = conteudoCsv.readLine();
								String[] coluna2 = linhaCSV.split(delimiter);
								if( s.peek().equals(coluna2[0]) ){
									
									//SHIFT
									if(coluna2[flagPosCol].contains("S")){
										//Shift
										String tLine = coluna2[flagPosCol].substring(1);
										s.push(classeToEmpilha);
										s.push(tLine);
										System.out.println(classe);
										System.out.println(s);
										System.out.println(coluna2[flagPosCol]);
										
										//verificar se o próximo é fim de arquivo, se for, vai para EOF fazer as reduções e desvios
										//já está na última coluna e a próxima linha é fim de arquivo
										if((sc.getCol()+1 >= line.length()) && ((sc.getLin() + 1) > linhas ) ){
											isEOF = 2;
//											contLine2 = 0;
											colCSV=0;
											Poscol = 0;
											conteudoCsv.close();
											conteudoCsv = new BufferedReader(new FileReader(filenameCSV));
											break operacao;
										}
										
										//para pegar próximo token
										last = 0;
										
									}
									
									//REDUÇÃO
									if(coluna2[flagPosCol].contains("R")){
										last = 1;
										//o número da regra de redução
										String tLine = coluna2[flagPosCol].substring(1);
										//transforma esse número em inteiro
										j = Integer.parseInt(tLine);
										//imprime a regra
										System.out.println(regra[j][0] + "->" + regra[j][1]);
										//busca a quantidade de elementos que vão ser desempilhados
										j = Integer.parseInt(regra[j][2]);
										//desempilha
										j = 2*j;
										while(j > 0) {
											s.pop();
											j--;
										}
										
										System.out.println(s);
//										
										j = Integer.parseInt(tLine);
										//salva o topo da pilha para procurar o desvio (num)
										tmp = s.peek();
										//empilha o lado esquerdo da regra
										s.push(regra[j][0]);
//										contLine2 = 0;
										colCSV=0;
										Poscol = 0;
										conteudoCsv.close();
										conteudoCsv = new BufferedReader(new FileReader(filenameCSV));
										//procurar o DESVIO
										while ((linhaCSV = conteudoCsv.readLine()) != null) {
											coluna = linhaCSV.split(delimiter);
											while(colCSV < qtdColCSV) {
												simboloCSV = coluna[colCSV];
												classe = regra[j][0]; //classe
												if(simboloCSV.equals(classe/*.toLowerCase()*/)) {
													//encontrou a coluna (classe)
													flagPosCol = Poscol;													
													//já encontrou a classe (coluna) na tabela, então passa a procurar o estado (linha)
//													contLine2++;
													colCSV = 0;
													while(true) {
														linhaCSV = conteudoCsv.readLine();
														coluna2 = linhaCSV.split(delimiter);
														if( tmp.equals(coluna2[0]) ){
															//empilha o desvio
															s.push(coluna2[flagPosCol]);
															System.out.println(s);
//															contLine2 = 0;
															colCSV=0;
															Poscol = 0;
															conteudoCsv.close();
															conteudoCsv = new BufferedReader(new FileReader(filenameCSV));
															break operacao;
														}
//														else {
//															contLine2++;
//														}
													}
													
												}
												
												Poscol++;			
												colCSV++;
											}
//											contLine2 = 0;
											colCSV=0;
											Poscol = 0;
										}
										
//										System.out.println(s);
									}
									//ACEITAÇÃO
									if(coluna2[flagPosCol].contains("acc")) {
										System.out.println("ACC");
										break principal;
									}
									// ERRO
									if(coluna2[flagPosCol].contains("e")) {
										System.out.println(flagPosCol);
										System.out.println(coluna2[flagPosCol]);
										System.out.println(classe);
										//verifica se na tabela de erros do hash tem o erro
										if(hmErro.containsKey(coluna2[flagPosCol])){
											// imprime o valor do erro
											System.out.println(hmErro.get(coluna2[flagPosCol]));
										}
										break principal;
									}

									break interno;
								}
//								else{
//									contLine2++;
//								}
							}
						}
						Poscol++;			
						colCSV++;
					}
//					contLine2 = 0;
					colCSV=0;
					Poscol = 0;
					//break;
		        }
				
				conteudoCsv.close();
				conteudoCsv = new BufferedReader(new FileReader(filenameCSV));
			    delimiter = ",";
		        //System.out.println(sc.getCol());
			}
			gt.setPos(0); //volta pos coluna para inicio, 0
			sc.incrementaLin();
			sc.setCol(0);
		
		}
		//System.out.println("Classe: EOF, Lexema: EOF, Tipo: Nulo");
		//System.out.println(hm);
		
        
        
		
		conteudoAtualLine.close();
	}
}