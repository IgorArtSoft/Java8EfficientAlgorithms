package net.algorithms.optimized;

import java.util.Arrays;

/**
 * @author Igor Artimenko
 
 Write a function:
class Solution { public int solution(int[] A); }
that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
Given A = [1, 2, 3], the function should return 4.
Given A = [−1, −3], the function should return 1.
Write an efficient algorithm for the following assumptions:
     N is an integer within the range [1..100,000];
     each element of array A is an integer within the range [1,000,000..1,000,000].
Compilation successful.

 */
public class MinimalPositiveInteger {

	public static void main(String[] args) {
		MinimalPositiveInteger test =  new MinimalPositiveInteger();		
		test.performTest();	
	}
	
	private void performTest(){
	
		int[][] testData = { { 1, 3, 6, 4, 1, 2 }, { 1, 2, 3 }, { -1, -3 } };

		for (int i = 0; i < testData.length; i++) {
			System.out.println( this.solution( testData[ i ] ) );			
		}

	}
	
    public int solution(int[] A) {

		int minimalSmallPositive = 1;
		
    	if ( A == null || A.length == 0) {

    	} else {
    		          
            Arrays.parallelSort(A);
            
            int currentSmallestPositive = A[0] > 0 ? A[0] : 1;
            minimalSmallPositive = ( A[A.length-1] + 1) > 0 ? ( A[A.length-1] + 1 ) : 1;
                    
            for (int i = 0; i < A.length; i++) {
            	if( A[i] < 0 ) {
                    continue;
            	}
            	else if( A[i] - currentSmallestPositive > 1 ) {
                	minimalSmallPositive = currentSmallestPositive + 1;
                	break;
                }
                else{
                    currentSmallestPositive = A[i];
                    continue;
                }
    		}

    	}
    	
        return minimalSmallPositive;
    }

}
