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
		/*
		System.out.println("nr of students = " + nrOfStudents);
		System.out.println("nr of Matrices = " + nrOfMatrices);
		System.out.println("nr of questions = " + nrOfQuestions);
		System.out.println("nr of answerModels = " + answerModels.length);
	
		System.out.println("indexList = " + Arrays.toString(indexList));
		*/
		}
	
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

	private boolean toBeAdded(int[] vector) {
		for(int i=0; i< nrOfStudents; i++) {
			if(vector[i] > studentScores[i]) {
				//System.out.println("vector[i] = " + vector[i] + " studentScores[i]= " + studentScores[i]);
				return false;
			}
		}
		return true;
	}

	private int[] createOppositeVector(int[] vector) {
		int [] oppositeVector = new int[vector.length];
		for(int i=0; i<nrOfStudents; i++) {
			oppositeVector[i] = nrOfQuestions - vector[i];
		}
		int binaryNumber = (int) Math.pow(2, (double) nrOfQuestions) - vector[nrOfStudents] - 1;
		oppositeVector[nrOfStudents] = binaryNumber;
		
		return oppositeVector;
	}

	private void createEmptyMatrices() {
		for(int i=0; i < nrOfMatrices; i++){
			int size = (int) combine(nrOfQuestions, i);
			//System.out.println("nrOfQuestion =" +  nrOfQuestions + " i = " + i);
			//System.out.println("size is= " + size );
			vectors[i] = new int[size][];
		}
	}
	
	private int[] createVector(int[][] answerModel, int index) {
		int[] vector = new int[nrOfStudents+1];
		
		for(int i=0; i< nrOfStudents; i++) {
			vector[i] = countCorrectAnswers(studentAnswers[i],answerModel[index]);
		}
		vector[nrOfStudents] = index;
		
		return vector;
	}
	
	private int countCorrectAnswers(int[] a, int[] b) {
		int correctAnswers = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == b[i]) {
				correctAnswers++;
			}
		}
		return correctAnswers;
	}
	
	public static int combinatoricCalc(int n, int k){
		int a = n-k;
		int numerator = 1;
		int denominator = 1;
		
		for(int i = n; i > a; i--){
			numerator *= i;
		}
		
		for(int j=k; j>0; j--) {
			denominator *= j;
		}
		
		return numerator/denominator;	
	}
	
	private static long combine(int total, int select) {
	    long result = 1;
	    select = Math.min(select, total - select);
	    for (int i=0; i<select; i++)
	        result = result * (total-i) / (i+1);
	    return result;
	}
	

}
