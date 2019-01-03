import java.util.Scanner;

public class Reader {

	private Scanner scanner;
	private int n;
	private int [][] A1;
	private int [][] A2;
	private int [] b;
	private String output;

	public Reader(Scanner scanner) {
		this.scanner = scanner;
	}

	public void readInput() {
		n = scanner.nextInt();
		int m = scanner.nextInt();
		int m1 = m/2;
		int m2 = m-m1;

		A1 = new int [n][m1];
		A2 = new int [n][m2];
		b = new int [n];

		//Fill matrices
		for(int i = 0; i < n; i++) {
			String answers = scanner.next();
			for(int j = 0; j < m; j++) {
				if (j < m1) {
					A1[i][j] = answers.charAt(j) - 48;
				}
				else {
					A2[i][j-m1] = answers.charAt(j) - 48;
				}
			}
			b[i] = scanner.nextInt();
		}
		output = scanner.next();
	}
}