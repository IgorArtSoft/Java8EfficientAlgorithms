package net.algorithms.optimized;

/**
 * @author Igor Artimenko
 
 
 *
 */
public class Test2 {

	public static void main(String[] args) {
		Test2 test = new Test2();
		test.performTest();
	}

	private void performTest() {
		int[] N = { 14, 10, 99, 1, 100, 110, 111, 499, 500 };

		for (int i = 0; i < N.length; i++) {
			
			System.out.println( "Iteration " + i + ": " + this.solution( N[i] ) );			
		}

	}
	public int solution(int N) {
		int lookingFor2TimesBigger = digitSum(N) * 2, nearestToN = N;
		while (!( digitSum( ++nearestToN ) == lookingFor2TimesBigger ) ) {
			if( nearestToN <= 0 ) {
				return -1;
			}
		}
		return nearestToN;
	}

	public int digitSum(int N) {
		int sum = 0;
		for ( int i = N; i > 0; i = i / 10 ) {
			sum += i % 10;
		}
		return sum;
	}
}
