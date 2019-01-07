
public class OneQuestion {
	
	int[][] A2;
	int[] b;

	public OneQuestion(int[][] a2, int[] b) {
		this.A2 = a2;
		this.b = b;
	}
	
	public String oneOrZero() {
		boolean same = A2[0][0] == b[0];
		for(int i = 1; i < b.length; i++) {
			if((A2[i][0] == b[i]) != same) {
				return "0 solutions";
			}
		}
	    return solution();
	}

	private String solution() {
		int solution = (b[0] == 0) ? opposite(A2[0][0]) : A2[0][0];
		return Integer.toString(solution);
	}
	
	public int opposite(int i) {
		if(i == 0) {
			return 1;
		} else {
			return 0;
		}
		
	}
	
	
	

}
