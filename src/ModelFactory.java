import java.util.Arrays;

public class ModelFactory {

	// int nrOfQuestions;

	public ModelFactory() {
		// this.nrOfQuestions = nrOfQuestions;
	}

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
	 * int input = 15;
			int binarySize = 8;

		    int[] bits = new int[binarySize];
		    for (int i = 0 ; i < binarySize; i++) {
		        bits[binarySize-1-i] = (input & (1 << i)) != 0 ? 1 : 0;
		    }

		    System.out.println(input + " = " + Arrays.toString(bits));
		    
		    ModelFactory m = new ModelFactory();
		    int[][] poep = m.createModels(20);
		    for( int i=0 ; i<1000000 ;i++) {
		    	 	System.out.println(i + " = " + Arrays.toString(poep[i]));
		    }
		}
	 */

}
