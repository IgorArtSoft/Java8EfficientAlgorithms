package net.algorithms.optimized;

/**
 * @author Igor Artimenko
 
 
 *
 */
public class Test1 {

	public static void main(String[] args) {
		Test1 test = new Test1();
		test.performTest();
	}

	private void performTest() {
		int[] N = { 14, 10, 99, 1, 100, 110, 111, 499, 500 };

		for (int i = 0; i < N.length; i++) {
			
			System.out.println( "Iteration " + i + ": " + this.solution( N[i] ) );			
		}

	}
	
    public int solution(int N) {      
        
        int result = digitSum( N ) * 2, smallestLargerInteger = N;
        
        while( !(digitSum( ++smallestLargerInteger ) == result ) );
        return smallestLargerInteger;
        
    }
    
    public int digitSum(int N) {
        int result = 0;
        for( int i = N; i > 0; ) {
        	result += i % 10;
        	i = i / 10;
        }
        return result;
    }
    
}
