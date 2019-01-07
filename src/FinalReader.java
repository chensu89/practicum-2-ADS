import java.util.Scanner;

public class FinalReader {
	
	private Scanner scanner;
	private int nrOfStudents;
	private int nrOfQuestions;
	private int[][] zeroScores;
	private int[][] oneScores;
	private int[] totalScores;
	
	public FinalReader(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void readInput() {
		nrOfStudents = scanner.nextInt();
		nrOfQuestions = scanner.nextInt();
		zeroScores = new int[nrOfQuestions][nrOfStudents];
		oneScores = new int[nrOfQuestions][nrOfStudents];
		totalScores = new int[nrOfStudents];

		// Fill matrices
		for (int i = 0; i < nrOfStudents; i++) {
			String answers = scanner.next();
			for (int j = 0; j < nrOfQuestions; j++) {
				int currentNumber = answers.charAt(j) - 48;
				if (currentNumber == 0) {
					zeroScores[j][i] = 1;
					oneScores[j][i] = 0;
				} else {
					zeroScores[j][i] = 0;
					oneScores[j][i] = 1;
				}
			}
			totalScores[i] = scanner.nextInt();
		}		
	}
}
