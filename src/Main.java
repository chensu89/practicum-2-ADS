import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Reader reader = new Reader(scanner);
		reader.readInput();
	}
	
	public int [][] numberCorrectMatrix(int [][] matrix, int [][] binaryList, int numberOfQuestions) {
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
	public int countCorrectAnswers(int[] a, int[] b) {
		int correctAnswers = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == b[i]) {
				correctAnswers++;
			}
		}
		return correctAnswers;
	}

}
