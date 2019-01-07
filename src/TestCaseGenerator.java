import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TestCaseGenerator {
	
	
	/**
	 * Prints a random test case where only one answer model is correct  
	 * 
	 * @param nrOfStudents
	 * @param nrOfQuestions
	 * 
	 */
	
	Random rnd = new Random();
	
	public void generateTestCase(int nrOfStudents, int nrOfQuestions){
		System.out.println(nrOfStudents + " " + nrOfQuestions);
		int[] correctAnswerModel = correctAnswerModel(nrOfQuestions);
		ArrayList<Integer> indexList = indexList(nrOfQuestions);
		int[][] allAnswerModels = allAnswerModels(correctAnswerModel,nrOfStudents, nrOfQuestions);
		int[] answersCorrect = new int[nrOfStudents];
		
		for(int i=0; i < nrOfStudents; i++) {
			int nrOfWrongQ =  rnd.nextInt(nrOfQuestions) + 1;
			answersCorrect[i] = nrOfQuestions-nrOfWrongQ; 
			allAnswerModels[i] = changeQuestions(indexList, nrOfWrongQ,correctAnswerModel);
		}
		
		int index = rnd.nextInt(nrOfStudents);
		allAnswerModels[index] = correctAnswerModel; 
		
		for(int i=0; i < nrOfStudents; i++) {
			printArray(allAnswerModels[i]);
			System.out.print( " " + answersCorrect[i] + "\n");
		}
		
		System.out.println();
		System.out.print("correct answer model: ");
		printArray(correctAnswerModel);
		
	}
	
	private void printArray(int[] is) {
		for(int x: is) {
			System.out.print(x);
		}
		
	}

	private int[] changeQuestions(ArrayList<Integer> indexList, int nrOfWrongQ, int[] correctAnswerModel) {
		Collections.shuffle(indexList);
		int[] newModel = correctAnswerModel.clone(); 
		for(int i=0; i<nrOfWrongQ; i++) {
			newModel[indexList.get(i)] = (correctAnswerModel[indexList.get(i)] == 0) ? 1 : 0;
		}
		return newModel;
	}

	private int[][] allAnswerModels(int[] correctAnswerModel, int nrOfStudents, int nrOfQuestions) {
		int[][] allAnswerModels = new int[nrOfStudents][];
		for(int i = 0; i<nrOfStudents; i++) {
			allAnswerModels[i] = correctAnswerModel;
		}
		return allAnswerModels;
		
	}

	private ArrayList<Integer> indexList(int nrOfQuestions) {
		ArrayList<Integer> list = new ArrayList<Integer>(nrOfQuestions);
	    for (int i=0 ; i < nrOfQuestions;i++) {
	        list.add(i);
	    }
	    return list;
	}

	public int[] correctAnswerModel(int nrOfQuestions) {
		int[] answerModel = new int[nrOfQuestions];
		for(int i=0; i<nrOfQuestions ; i++) {
			answerModel[i] = rnd.nextInt(2);
		}
		return answerModel;
				
		
	}

}
