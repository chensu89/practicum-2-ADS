import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Reader reader = new Reader(scanner);
		reader.readInput();
	}
	
	public int ifAndIf(int a, int b) {
		return a == b ? 1 : 0;
	}

}
