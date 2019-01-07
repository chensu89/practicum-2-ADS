package practicum2;

import java.util.Stack;

public class Algorithm {

	private Stack<Path> stack;
	int[][][] columnScores;
	private int[] totalScores;
	private String currentSolution;
	private int nrOfSolutions;
	private int nrOfQuestions;
	private int nrOfStudents;
	

	public Algorithm(int[] totalScores,int[][][] columnScores, int nrOfStudents, int nrOfQuestions) {
		this.stack = new Stack<Path>();
		this.totalScores = totalScores;
		this.columnScores = columnScores;
		this.nrOfStudents = nrOfStudents;
		this.nrOfQuestions = nrOfQuestions;
		nrOfSolutions = 0;
	}

	public String getCurrentSolution() {
		return currentSolution;
	}

	public int getNrOfSolutions() {
		return nrOfSolutions;
	}

	public void runAlgorithm(){
		addStartPath();
		while(!stack.isEmpty()) {
			Path path = stack.pop();
			if(path.getIndex() < nrOfQuestions){
				addPathToStack(path,0, path.getIndex());
				addPathToStack(path,1, path.getIndex());
			} else {
				currentSolution = path.getAnswerModel();
				System.out.println(currentSolution);
				nrOfSolutions += 1;
			}
		}
	}

	private void addPathToStack(Path path, int bit, int currentAnswerIndex) {
		int[] newParentArray = newParentArray(path,bit, currentAnswerIndex);
		if(newParentArray[0] != -1) {
			String newString = path.getAnswerModel() + bit;
			stack.push(new Path(newParentArray, newString, currentAnswerIndex+1));
		}
		
	}

	private int[] newParentArray(Path path, int i, int currentAnswer) {
		int [] newParentArray = new int[nrOfStudents];
		int [] oldParentArray = path.getParentArray();
		int [] answers = columnScores[i][currentAnswer];
		for(int j=0; i < nrOfStudents; i++) {
			int newValue = oldParentArray[j] + answers[i];
			if(newValue > totalScores[i]) {
				newParentArray[0] = -1;
				return newParentArray;
			} else {
				newParentArray[i] = newValue;
			}
		}
		return newParentArray;
	}

	private void addStartPath() {
		int[] array1 = new int[nrOfStudents];
		stack.push(new Path(array1, "", 0));
	}

}
