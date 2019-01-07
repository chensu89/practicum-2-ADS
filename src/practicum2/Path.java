package practicum2;

public class Path {
	

	private int[] parentArray;
	private String answerModel;
	int index;
	
	public Path(int[] parentArray, String answerModel, int index) {
		this.parentArray = parentArray;
		this.answerModel = answerModel;
		this.index = index;
	}


	public int getIndex() {
		return index;
	}



	public int[] getParentArray() {
		return parentArray;
	}



	public String getAnswerModel() {
		return answerModel;
	}

}
