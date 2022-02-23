package net.algorithms.optimized;

/**
 * @author Igor Artimenko
 
 
 *
 */
public class Test3 {

	public static void main(String[] args) {
		Test3 test = new Test3();
		test.performTest();
	}

	private void performTest() {
		int[][] testData = { { 1, 3, 6, 4, 1, 2 }, { 1, 2, 3 } };

		for (int i = 0; i < testData.length; i++) {
			System.out.println( "Iteration " + i + ": " + this.solution( testData[ i ] ) );			
		}

	}
	
    public int solution(int[] A) {
    	int result = 0;
    	
    	return result;
    }
}
