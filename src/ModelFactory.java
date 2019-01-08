

public class ModelFactory {
	
	
	/**
	 * This function creates every possible answer model given a number of questions
	 * 
	 * @param nrOfQ the number of questions in a matrix
	 * @return every possible answer model in the order of binary numbers
	 * e.g. 000 , 010 , 011 etc
	 */

	
	public static int[][] createModels(int nrOfQ){
		
		int nrOfModels = (int) Math.pow(2, (double) nrOfQ)/2;
		int[][] models = new int[nrOfModels][nrOfQ];
		
		for(int input = 0; input < nrOfModels; input++) {
		    for (int i = 0 ; i < nrOfQ; i++){
		        models[input][nrOfQ-1-i] = (input & (1 << i)) != 0 ? 1 : 0;
		    } 
		}
		return models;
	}
	
	/**
	 * this function turns two integers into two binary numbers and adds a padding of zeros
	 * according to the number of questions.
	 * 
	 * @param model1 number 1 to be converted to binary
	 * @param model2 number 2 to be converted to binary
	 * @param nrOfQuestions1 
	 * @param nrOfQuestions2
	 * 
	 * @return an answer model
	 */
	
	public static String printSolution(int model1, int model2, int nrOfQuestions1, int nrOfQuestions2) {
		String zeros1 ="";
		String binary1 = Integer.toBinaryString(model1);
		
		for(int i=0; i< (nrOfQuestions1 - binary1.length()); i++) {
			zeros1 = zeros1 + "0";
		}
		
		String zeros2 ="";
		String binary2 = Integer.toBinaryString(model2);
		
		for(int i=0; i< (nrOfQuestions2 - binary2.length()); i++) {
			zeros2 = zeros2 + "0";
		}
		
		return zeros1+binary1+zeros2+binary2;	
	}
	

}
