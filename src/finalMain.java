import java.io.FileNotFoundException;
import java.util.Scanner;

public class finalMain {
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner scanner = new Scanner(System.in); 
		Reader reader = new Reader(scanner);
		reader.readInput();
	}

}
