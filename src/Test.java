
public class Test {
	
	public static int countCorrectAnswers(int[] a, int[] b){
		int correctAnswers = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == b[i]) {
				correctAnswers++;
			}
		}
		return correctAnswers;
	}
	
	
		
	public static int combinatoricCalc(int n, int k){
		int a = n-k;
		int numerator = 1;
		int denominator = 1;
		
		for(int i = n; i > a; i--){
			numerator *= i;
		}
		
		for(int i=k; i>0; i--) {
			denominator *= i;
		}
		
		return numerator/denominator;	
	}
	
	

}
