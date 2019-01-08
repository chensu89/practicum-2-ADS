package practicum2;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FinalMain {
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner scanner = new Scanner(System.in); 
		FinalReader reader = new FinalReader(scanner);
		reader.readInput();
		RecAlgorithm alg = new RecAlgorithm(reader.getTotalScores(),reader.getColumnScores(),reader.getNrOfStudents(),reader.getNrOfQuestions());
		alg.runAlgorithm();
		
		int nrOfSolutions = alg.getNrOfSolutions();
		
		if(nrOfSolutions == 1) {
			System.out.println(alg.getCurrentSolution());
		} else {
			System.out.println(alg.getNrOfSolutions() + " solutions");
		}
		
	}
}
