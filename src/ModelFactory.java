

public class ModelFactory {

	
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
