
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author Fulco Taen, Noud Emonts 
 *
 * Things to do:
 * 
 * - Make it so that the output is the solution when there is only correct answer model
 * - Try to organize code more into classes
 * - optimize where possible e.g
 * 		- use threads to create A1 and A2 simultaneously
 * 		- if one of the vectors already exceeds b somewhere exclude it as it will never create a valid solution
 * 		- see if there are options for sorting at the beginning when the matrix is small
 * 		- maybe exclude an answer when all students have it the same (this one seems hard)
 * 		- see if taking the opposite of the solution and the score can be used elsewhere
 */

public class Main {
	
	static int currentSolutionA1;
	static int currentSolutionA2;
	static int nrRemoved;
	static int[] b;
	static ArrayList<Integer> indexRemoved1  = new ArrayList<Integer>(50000);
	static ArrayList<Integer> indexRemoved2  = new ArrayList<Integer>(50000);
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in); 
		Reader reader = new Reader(scanner);
		reader.readInput();
		int [][] A1 = reader.getA1();
		int [][] A2 = reader.getA2();
		b  = reader.getB(); 
		int [][] binaryList = ModelFactory.createModels(A1[0].length);
		int [][] binaryList2 = ModelFactory.createModels(A2[0].length);
		
		
	/**	
		System.out.println("list b" );
		System.out.println(Arrays.toString(b));
		System.out.println();
		
		System.out.println("Matrix A1 with length " + A1.length);
		for( int i=0 ; i<A1.length ;i++) {
    	 	System.out.println(i + " = " + Arrays.toString(A1[i]));
		}
		System.out.println();
		
		System.out.println("binaryMatrix with length " + binaryList.length);
		for( int i=0 ; i<binaryList.length ;i++) {
    	 	System.out.println(i + " = " + Arrays.toString(binaryList[i]));
		}
		System.out.println();
		**/
		int [][] correctMatrix = numberCorrectMatrix(A1,binaryList, A1[0].length);
		int[][] prunedMatrix = prune(correctMatrix);
	/**	System.out.println("number of correct answers per student in A1");
		for( int i=0 ; i<correctMatrix.length ;i++) {
	    	 	System.out.println(i + " = " + Arrays.toString(correctMatrix[i]));
		}
		System.out.println();
	**/	
		
		nrRemoved = -1;
		
		
		int [][] correctMatrix2 =  numberCorrectMatrix(A2,binaryList2, A2[0].length);
		int[][] prunedMatrix2 = prune(correctMatrix2);
	/**	System.out.println("number of correct answers per student in A2");
		for( int i=0 ; i<correctMatrix2.length ;i++) {
    	 		System.out.println(i + " = " + Arrays.toString(correctMatrix2[i]));
		}
	**/	System.out.println();
		System.out.println(nrRemoved);
		int count = 0;
		
		for(int i=0; i<prunedMatrix.length;i++){
			for(int j=0; j<prunedMatrix2.length;j++) {
				if(compareScores(prunedMatrix[i],prunedMatrix2[j],b)) {
					currentSolutionA1 = i;
					currentSolutionA2 = j;
					count++;
				};
			}
		}
		if(count != 1) {
			System.out.println(count +" solutions");
		} else {
			ModelFactory.printSolution(currentSolutionA1, currentSolutionA2, reader.getM1(), reader.getM2());
		}
			
	}
	
	private static int[][] prune(int[][] correctMatrix) {
		int[][] prunedMatrix = new int[correctMatrix.length - nrRemoved][correctMatrix[0].length]; 
		int index = 0;
		for(int i=0; i < correctMatrix.length; i++) {
			if(correctMatrix[i][0] != -1) {
				prunedMatrix[index] = correctMatrix[i];
				index++;
			}
		}
		return prunedMatrix;
	}

	public static int [][] numberCorrectMatrix(int [][] matrix, int [][] binaryList, int numberOfQuestions) {
		int [][] correctMatrix = new int [(int) Math.pow(2, (double) numberOfQuestions)][matrix.length];
		for(int i = 0; i < binaryList.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				int correctAnswer = countCorrectAnswers(binaryList[i], matrix[j]);
				correctMatrix[i][j] = correctAnswer;
				correctMatrix[correctMatrix.length-i-1][j] = numberOfQuestions-correctAnswer;
				
				if(correctAnswer > b[j]) {
					correctMatrix[i][0] = -1;
					indexRemoved1.add(i);
					nrRemoved++;
					
				} else if(numberOfQuestions-correctAnswer > b[j]){
					correctMatrix[correctMatrix.length-i-1][0] = -1;
					indexRemoved2.add(correctMatrix.length-i-1);
					nrRemoved++;
				}
				
				
			}
		}
		return correctMatrix;
	}

	
	/**
	 * Applies elementwise ifandonlyif of 2 vectors, then returns the sum of the resulting vector
	 * @param a vector a
	 * @param b vector b
	 * @return int
	 */
	public static int countCorrectAnswers(int[] a, int[] b) {
		int correctAnswers = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == b[i]) {
				correctAnswers++;
			}
		}
		return correctAnswers;
	}
	
	public static Boolean compareScores(int[] a1, int[] a2, int[] b) {
		for(int i=0; i< a1.length; i++) {
			if(a1[i]+a2[i] != b[i]) {
				return false;
			}
		}
		return true;
	}

}
