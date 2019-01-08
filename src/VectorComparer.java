
public class VectorComparer {
	private int[][][] vectorList1;
	private int[][][] vectorList2;
	private int[] indexList1;
	private int[] indexList2;
	private int[] scores;
	int firstHalfSolution;
	int secondHalfSolution;
	int nrOfSolutions;
	
	/**
	 * This class takes two vector lists and the students total scores and combines them to see if there are any that result 
	 * in the same vector as the total scores.
	 * 
	 * @param vectorList1
	 * @param vectorList2
	 * @param indexList1
	 * @param indexList2
	 * @param scores
	 */
	
	public VectorComparer(int[][][] vectorList1, int[][][] vectorList2, int[] indexList1, int[] indexList2, int[] scores) {
		this.vectorList1 = vectorList1;
		this.vectorList2 = vectorList2;
		this.indexList1 = indexList1;
		this.indexList2 = indexList2;
		this.scores = scores;
		nrOfSolutions = 0;
		totalCompare();
	}
	
	public int getNrOfSolutions() {
		return nrOfSolutions;
	}
	
	/**
	 * this function starts the comparing of vectors
	 */

	private void totalCompare() {
		int nrOfMatrices = vectorList1.length;
		for(int i=0; i < nrOfMatrices;i++) {
			if(vectorList1[i][0] != null  && vectorList2[nrOfMatrices-i-1][0] != null) { 
				matrixCompare(vectorList1[i],vectorList2[nrOfMatrices-i-1],indexList1[i],indexList2[nrOfMatrices-1-i]);
			} 
		}
	}
	
	/**
	 * The vector lists are of type int[][][] so an array of matrices. this function compares the vectors of a matrix of both vector lists
	 * 
	 * @param matrix1
	 * @param matrix2
	 * @param maxIndex1
	 * @param maxIndex2
	 */
	
	private void matrixCompare(int[][] matrix1, int[][] matrix2, int maxIndex1, int maxIndex2) {
		for(int i=0; i< maxIndex1; i++) {
			for(int j=0; j< maxIndex2;j++) { 
				if(compareVector(matrix1[i],matrix2[j])) {
					nrOfSolutions++;
				}
			}
		}
	}
	
	/**
	 * This function compares two vectors and looks if added together they create the score vector. if they don't
	 * it returns false if they do it returns true.
	 * 
	 * @param vector1
	 * @param vector2
	 * @return
	 */

	private boolean compareVector(int[] vector1, int[] vector2) {
		for(int i=1; i<vector1.length-1;i++) {		
			if(vector1[i] + vector2[i] != scores[i]) {
				return false;
			}
		}
		firstHalfSolution = vector1[vector1.length-1];
		secondHalfSolution = vector2[vector2.length-1];
		return true;
	}

	public int getFirstHalfSolution() {
		return firstHalfSolution;
	}

	public int getSecondHalfSolution() {
		return secondHalfSolution;
	}
	
	
}
