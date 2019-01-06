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

	public Reader(Scanner scanner) {
		this.scanner = scanner;
	}

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

	private void changesToLowestScore() {
		for(int i=0; i < n; i++) {
			if(b[i] > m/2){
				reverseStudentAnswers(A1, A2, b, i);
			}
		}
	}

	public int getM() {
		return m;
	}

	private void lowestStudentFirst(int[][] A1, int[][] A2, int[] b) {
		int lsi = lowestScoreIndex(b);
		placeStudentOnTop(A1, A2, b, lsi);
	}

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

	private void reverseStudentAnswers(int[][] A1, int[][] A2, int[] b2, int hds) {
		for (int i = 0; i < A1[0].length; i++) {
			A1[hds][i] = (A1[hds][i] == 0) ? 1 : 0;
		}

		for (int i = 0; i < A2[0].length; i++) {
			A2[hds][i] = (A2[hds][i] == 0) ? 1 : 0;
		}

		b2[hds] = m - b2[hds];
	}

	private int HighestDeviationScoreIndex(int[] array) {
		int index = 0;
		int currentDeviation = 0;
		for (int i = 0; i < array.length; i++) {
			int deviation = Math.abs(array[i] - m / 2);
			if(deviation > currentDeviation) {
				index = i;
				currentDeviation = deviation;
			}
		}
		return index;
	}

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
