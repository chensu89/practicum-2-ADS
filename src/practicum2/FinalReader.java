package practicum2;
import java.util.Scanner;

public class FinalReader {
	
	private Scanner scanner;
	private int nrOfStudents;
	private int nrOfQuestions;
	private int [][][] columnScores;
	private int[][] zeroScores;
	private int[][] oneScores;
	private int[] totalScores;
	
	public FinalReader(Scanner scanner) {
		this.scanner = scanner;
		this.columnScores = new int[2][][];
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
			columnScores[0] = zeroScores;
			columnScores[1] = oneScores;
			changeToLowestScore();
			totalScores[i] = scanner.nextInt();
		}		
	}
	
	private void changeToLowestScore() {
		for(int i=0; i < nrOfStudents; i++) {
			if(totalScores[i] > nrOfQuestions/2){
				reverseStudentAnswers(i);
			}
		}
	}

	private void reverseStudentAnswers(int i) {
		totalScores[i] = nrOfQuestions - totalScores[i];
		for(int j = 0; j < nrOfQuestions; j++) {
			zeroScores[j][i] = (zeroScores[j][i]==0) ? 1 : 0;
			oneScores[j][i] = (oneScores[j][i]==0) ? 1 : 0;
		}
		
		
	}

	public int[][] getOneScores() {
		return oneScores;
	}

	public void setOneScores(int[][] oneScores) {
		this.oneScores = oneScores;
	}

	public int[] getTotalScores() {
		return totalScores;
	}

	public int getNrOfStudents() {
		return nrOfStudents;
	}

	public int getNrOfQuestions() {
		return nrOfQuestions;
	}

	public int[][][] getColumnScores() {
		return columnScores;
	}
}
