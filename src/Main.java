
import java.io.FileNotFoundException;
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

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in); 
		//Scanner scanner = new Scanner(new File("test_1"));
		Reader reader = new Reader(scanner);
		reader.readInput();
		int [][] A1 = reader.getA1();
		int [][] A2 = reader.getA2();
		int [] b  = reader.getB(); 
		int [][] binaryList = ModelFactory.createModels(A1[0].length);
		int [][] binaryList2 = ModelFactory.createModels(A2[0].length);
		
		System.out.println("list b" );
		System.out.println(Arrays.toString(b));
		
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
		
		int [][] correctMatrix = numberCorrectMatrix(A1,binaryList, A1[0].length);
		System.out.println("number of correct answers per student in A1");
		for( int i=0 ; i<correctMatrix.length ;i++) {
	    	 	System.out.println(i + " = " + Arrays.toString(correctMatrix[i]));
	    }
		System.out.println();
		
		int [][] correctMatrix2 =  numberCorrectMatrix(A2,binaryList2, A2[0].length);
		System.out.println("number of correct answers per student in A2");
		for( int i=0 ; i<correctMatrix2.length ;i++) {
    	 		System.out.println(i + " = " + Arrays.toString(correctMatrix2[i]));
		}
		System.out.println();
		
		int count = 0;
		
		for(int i=0; i<correctMatrix.length;i++){
			for(int j=0; j<correctMatrix2.length;j++) {
				if(compareScores(correctMatrix[i],correctMatrix2[j],b)) {
					count++;
				};
			}
		}
		
		System.out.println("nrOfSolutions is " + count);
		
	}
	
	public static int [][] numberCorrectMatrix(int [][] matrix, int [][] binaryList, int numberOfQuestions) {
		int [][] correctMatrix = new int [(int) Math.pow(2, (double) numberOfQuestions)][matrix.length];
		for(int i = 0; i < binaryList.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				int correctAnswers = countCorrectAnswers(binaryList[i], matrix[j]);
				correctMatrix[i][j] = correctAnswers;
				correctMatrix[correctMatrix.length-i-1][j] = numberOfQuestions-correctAnswers;
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
