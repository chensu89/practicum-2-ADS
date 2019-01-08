import java.util.Arrays;

public class VectorFactory {
	
	int[][][] vectors;
	int[][] studentAnswers;
	int[] studentScores;
	int[][] answerModels;
	int[] indexList;
	int nrOfMatrices;
	int nrOfQuestions;
	int nrOfStudents;
	int totalNrOfQuestions;
 
	
	public VectorFactory(int[][] studentAnswers, int[] studentScores, int totalNrOfQuestions) {
		this.studentAnswers = studentAnswers;
		this.studentScores = studentScores;
		this.totalNrOfQuestions = totalNrOfQuestions;
		this.nrOfStudents = studentScores.length;
		this.nrOfMatrices = studentScores[0]+1;
		this.nrOfQuestions = studentAnswers[0].length;
		this.answerModels = ModelFactory.createModels(nrOfQuestions);
		this.vectors = new int[nrOfMatrices][][];
		this.indexList = new int[nrOfMatrices];
		createEmptyMatrices();	
		fillVectorMatrix();
		}
	
	/**
	 * This function creates every possible answer vector where none of the values exceed their corresponding value in the students total score.
	 * After an answer vector is created it is placed in on of the matrices based on the score of the first student.
	 */
	
	private void fillVectorMatrix(){
		for(int i=0; i< answerModels.length; i++) {
			int[] vector = createVector(answerModels, i);
			int[] oppositeVector = createOppositeVector(vector);
			
			if(toBeAdded(vector)) {
				addVector(vector);
			}
			
			if(toBeAdded(oppositeVector)) {
				addVector(oppositeVector);
			}
		}
	}
	
	/**
	 * This function adds a vector to one of the matrices in the variable vectors based on the first students score 
	 * e.g. if the first students score is 3 the vector is placed in vector[3][][]. Where in vector[3][][] is tracked 
	 * in the array indexList
	 * 
	 * @param vector the vector to be placed
	 */
	
	private void addVector(int[] vector) {
		int firstStudentScore = vector[0];
		int index = indexList[firstStudentScore];
		vectors[firstStudentScore][index] = vector;
		indexList[firstStudentScore]++;
	}

	public int[] getIndexList() {
		return indexList;
	}

	public int[][][] getVectors() {
		return vectors;
	}
	
	
	/**
	 * This function checks if none of the scores in this vector exceed the total score
	 * 
	 * @param vector the vector which is checked 
	 * @return true or false based on if it should be added or not
	 */
	private boolean toBeAdded(int[] vector) {
		for(int i=0; i< nrOfStudents; i++) {
			if(vector[i] > studentScores[i]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * this function creates a new vector by taking another vector and changing it's values to nrOfQuestions minus the value 
	 * of the original vector
	 *  
	 * @param vector  the original vector
	 * @return		  the opposite vector
	 * 
	 */

	private int[] createOppositeVector(int[] vector) {
		int [] oppositeVector = new int[vector.length];
		for(int i=0; i<nrOfStudents; i++) {
			oppositeVector[i] = nrOfQuestions - vector[i];
		}
		int binaryNumber = (int) Math.pow(2, (double) nrOfQuestions) - vector[nrOfStudents] - 1;
		oppositeVector[nrOfStudents] = binaryNumber;
		
		return oppositeVector;
	}
	
	/**
	 * creates the empty matrices based on the first students score and the amount of possibilities which 
	 * are possible within that score.
	 */

	private void createEmptyMatrices() {
		for(int i=0; i < nrOfMatrices; i++){
			int size = (int) combine(nrOfQuestions, i);
			vectors[i] = new int[size][];
		}
	}
	
	/**
	 * creates a vector based where every value represents the amount of answers correct according to a 
	 * specific answer model.
	 * 
	 * @param answerModel a matrix of different possible answer models
	 * @param index 		 the index of a specific answer model in that matrix
	 * @return a answer vector
	 */
	private int[] createVector(int[][] answerModel, int index) {
		int[] vector = new int[nrOfStudents+1];
		
		for(int i=0; i< nrOfStudents; i++) {
			vector[i] = countCorrectAnswers(studentAnswers[i],answerModel[index]);
		}
		vector[nrOfStudents] = index;
		
		return vector;
	}
	
	/**
	 * used to create the values in createVector()
	 * 
	 * @param a students answer
	 * @param b	answer in answer model
	 * @return number of correct answers for a student 
	 */
	
	private int countCorrectAnswers(int[] a, int[] b) {
		int correctAnswers = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == b[i]) {
				correctAnswers++;
			}
		}
		return correctAnswers;
	}
	
	/**
	 * function to calculate the number of ways you can take k items out of a set of n. Which is used in our 
	 * code to calculate the number of ways a student can have k questions wrong.
	 * 
	 * @param total     n
	 * @param select    k 
	 * @return number of combinations
	 */
	
	private static long combine(int total, int select) {
	    long result = 1;
	    select = Math.min(select, total - select);
	    for (int i=0; i<select; i++)
	        result = result * (total-i) / (i+1);
	    return result;
	}
	

}
