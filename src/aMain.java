import java.util.Arrays;
import java.util.Scanner;

public class aMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Reader reader = new Reader(scanner);
		reader.readInput();

		int[][] A1 = reader.getA1();
		int[][] A2 = reader.getA2();
		int[] b = reader.getB();

		int totalNrOfQuestions = reader.getM();
		
		

		if (totalNrOfQuestions == 1) {
			if (b.length == 1) {
				OneQuestion oneQ = new OneQuestion(A2, b);
				int a = (b[0] == 1) ? A2[0][0] : oneQ.opposite(A2[0][0]);
				System.out.println(a);
			} else {
				OneQuestion oneQ = new OneQuestion(A2, b);
				System.out.println(oneQ.oneOrZero());
			}
		} else {

			VectorFactory vectorFactory1 = new VectorFactory(A1, b, totalNrOfQuestions);
			int[][][] vectors1 = vectorFactory1.getVectors();
			int[] indexList1 = vectorFactory1.getIndexList();

			VectorFactory vectorFactory2 = new VectorFactory(A2, b, totalNrOfQuestions);
			int[][][] vectors2 = vectorFactory2.getVectors();
			int[] indexList2 = vectorFactory2.getIndexList();

			// System.out.println("nrOfmatrices1 = " + vectors1.length);
			// System.out.println("nrOfmatrices2 = " + vectors2.length);

			// System.out.println("length vector1 =" +vectors1.length );
			// System.out.println("length vector1 =" +vectors2.length );

			/**
			 * for( int i=0 ; i<vectors1.length ;i++) { for( int j=0 ; j<vectors1[i].length
			 * ;j++) { System.out.println(i + " " + j + " = " +
			 * Arrays.toString(vectors1[i][j])); } }
			 * 
			 * 
			 * for( int i=0 ; i<vectors2.length ;i++) { for( int j=0 ; j<vectors2[i].length
			 * ;j++) { System.out.println(i + " = " + Arrays.toString(vectors2[i][j])); } }
			 * 
			 **/

			VectorComparer vectorComparer = new VectorComparer(vectors1, vectors2, indexList1, indexList2, b);
			int solutions = vectorComparer.getNrOfSolutions();
			if (solutions != 1) {
				System.out.println(solutions + " solutions");
			} else {
				int solution1 = vectorComparer.getFirstHalfSolution();
				int solution2 = vectorComparer.getSecondHalfSolution();
				System.out.println(ModelFactory.printSolution(solution1, solution2, A1[0].length, A2[0].length));
			}
		}

	}

	private static void printOppositeArray(int[] is) {
		for (int i = 0; i < is.length; i++) {
			is[i] = (is[i] == 0) ? 1 : 0;
		}
		printArray(is);
	}

	private static void printArray(int[] is) {
		String s = "";
		for (int i = 0; i < is.length; i++) {
			s = s + is[i];
		}
		System.out.println(s);

	}
}
