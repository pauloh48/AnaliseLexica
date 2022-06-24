package analisadorLexico;

public class mainScanner {

	public static void main(String[] args) {
		/*
		 	NUMERAL 	-> 
		 	OPM 		-> + | - | * | /
		 	
		 */
		OScanner sc = new OScanner("/home/paulo/eclipse-workspace/analisadorLexico/src/analisadorLexico/fonte.alg");
		Token token = null;
		do {
			token = sc.nextToken();
			if(token != null) {
				System.out.println(token);
			}
		}while(token != null);

	}

}
