import java.util.Scanner;

public class Reader {

	private Scanner scanner;
	private int n;
	private int m;
	private int[][] A1;
	private int[][] A2;
	private int[] b;
	private int m1;
	private int m2;

	public int getN() {
		return n;
	}
	
	
	public Reader(Scanner scanner) {
		this.scanner = scanner;
	}
	
	/**
	 * This function reads the input from System.in and converts it two matrices and the correct answer vector.
	 * It also converts every students score to the lowest score possible by inverting the scores if that results
	 * in a lower score. finally it puts the student with the lowest score at the first row of the matrices.
	 *  
	 */

	public void readInput() {
		n = scanner.nextInt();
		m = scanner.nextInt();
		m1 = m / 2; // contains lowest amount of answers in case of split
		m2 = m - m1;

		A1 = new int[n][m1];
		A2 = new int[n][m2];
		b = new int[n];

		// Fill matrices
		for (int i = 0; i < n; i++) {
			String answers = scanner.next();
			for (int j = 0; j < m; j++) {
				if (j < m1) {
					A1[i][j] = answers.charAt(j) - 48;
				} else {
					A2[i][j - m1] = answers.charAt(j) - 48;
				}
			}
			b[i] = scanner.nextInt();
		}
		
		changesToLowestScore();
		lowestStudentFirst(A1, A2, b);
	}
	
	/**
	 * changes the students score to the lowest score possible
	 */

	private void changesToLowestScore() {
		for(int i=0; i < n; i++) {
			if(b[i] > m/2){
				reverseStudentAnswers(A1, A2, b, i);
			}
		}
	}
	
	/**
	 * 
	 * @return the amount of students
	 */

	public int getM() {
		return m;
	}
	
	/**
	 * This function switches the student on top of the matrix with the lowest scoring one
	 * 
	 * @param A1 first half of questions matrix
	 * @param A2 second half of questions matrix
	 * @param b answer vector
	 */

	private void lowestStudentFirst(int[][] A1, int[][] A2, int[] b) {
		int lsi = lowestScoreIndex(b);
		placeStudentOnTop(A1, A2, b, lsi);
	}
	
	/**
	 * This function finds the index in the matrices of the student with the lowest possible score
	 * 
	 * @param b answer vector
	 * @return
	 */

	private int lowestScoreIndex(int[] b) {
		int index = 0;
		int currentScore = m;
		for(int i=0; i < b.length;i++) {
			if(b[i] < currentScore) {
				currentScore = b[i];
				index = i;
			}
		}
		return index;
	}
	
	/**
	 * code for switching a student at a specified index with the student at the first index 
	 * of the matrices
	 * 
	 * @param A1 first half of questions matrix
	 * @param A2 second half of questions matrix
	 * @param b answer vector
	 * @param lsi index which of vector which is going to be switched
	 */

	private void placeStudentOnTop(int[][] A1, int[][] A2, int[] b, int lsi) {
		if (lsi != 0) {
			int[] firstStudentA1 = A1[0];
			int[] firstStudentA2 = A2[0];
			int firstStudentB = b[0];

			A1[0] = A1[lsi];
			A2[0] = A2[lsi];
			b[0] = b[lsi];

			A1[lsi] = firstStudentA1;
			A2[lsi] = firstStudentA2;
			b[lsi] = firstStudentB;
		}
	}
	
	/**
	 * inverts a student score so ones become zeros and the other way around
	 * 
	 * @param A1 first half of questions matrix
	 * @param A2 second half of questions matrix
	 * @param b2 answer vector
	 * @param hds index of student to be switched
	 */

	private void reverseStudentAnswers(int[][] A1, int[][] A2, int[] b2, int hds) {
		for (int i = 0; i < A1[0].length; i++) {
			A1[hds][i] = (A1[hds][i] == 0) ? 1 : 0;
		}

		for (int i = 0; i < A2[0].length; i++) {
			A2[hds][i] = (A2[hds][i] == 0) ? 1 : 0;
		}

		b2[hds] = m - b2[hds];
	}
	
	// a few getters for the matrices answer vector and number of questions per matrix

	public int getM1() {
		return m1;
	}

	public int getM2() {
		return m2;
	}

	public int[] getB() {
		return b;
	}

	public int[][] getA1() {
		return A1;
	}

	public int[][] getA2() {
		return A2;
	}

}
