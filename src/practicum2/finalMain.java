package practicum2;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class finalMain {
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner scanner = new Scanner(System.in); 
		FinalReader reader = new FinalReader(scanner);
		reader.readInput();
		Algorithm alg = new Algorithm(reader.getTotalScores(),reader.getColumnScores(),reader.getNrOfStudents(),reader.getNrOfQuestions());
		alg.runAlgorithm();
		System.out.println(alg.getNrOfSolutions() + " solution");
		//System.out.println(alg.getCurrentSolution());
	}

}
