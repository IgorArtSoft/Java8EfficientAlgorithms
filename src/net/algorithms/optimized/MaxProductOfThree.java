package net.algorithms.optimized;

/*
 *  @author Igor Artimenko
 *  
 A non-empty array A consisting of N integers is given. 
 The product of triplet (P, Q, R) equates to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).

For example, array A such that:

  A[0] = -3
  A[1] = 1
  A[2] = 2
  A[3] = -2
  A[4] = 5
  A[5] = 6
contains the following example triplets:

(0, 1, 2), product is −3 * 1 * 2 = −6
(1, 2, 4), product is 1 * 2 * 5 = 10
(2, 4, 5), product is 2 * 5 * 6 = 60
Your goal is to find the maximal product of any triplet.

Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty array A, returns the value of the maximal product of any triplet.

For example, given array A such that:

  A[0] = -3
  A[1] = 1
  A[2] = 2
  A[3] = -2
  A[4] = 5
  A[5] = 6
the function should return 60, as the product of triplet (2, 4, 5) is maximal.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [3..100,000];
each element of array A is an integer within the range [−1,000..1,000].

 */
public class MaxProductOfThree {

	public static void main(String[] args) {
		MaxProductOfThree test =  new MaxProductOfThree();
		
		// int abc = 1000 * 999 * 998;
		// System.out.println( abc );
		test.performTest();
		
	}
	
	private void performTest(){
		
		int[][] testData = { { -3, 1, 2, 2, 5, 6 }, { -3, 1, 2, -60, 2, -10, 5, 6 } };
		for (int i = 0; i < testData.length; i++) {
			System.out.println( i + ":" + this.solution( testData[ i ] ) );			
		}

	}
	
    public int solution(int[] A) {
    	int largest3 = 0;
    	
    	// Try to find up to 3 largest positive numbers & up to 3 smallest negative numbers
    	int[] positiveLargest = {0,0,0};
    	int[] negativeSmallest = {0,0,0};
    	
    	for (int i = 0; i < A.length; i++) {
    		if( A[i] > 0 ) {
    			if( A[i] > positiveLargest[2] ) {
    				positiveLargest[0] = positiveLargest[1];
    				positiveLargest[1] = positiveLargest[2];
    				positiveLargest[2] = A[i];
    			} 
    			else {
        			if( A[i] > positiveLargest[1] ) {
        				positiveLargest[0] = positiveLargest[1];
        				positiveLargest[1] = A[i];
        			} 
        			else if( A[i] > positiveLargest[0] ) {
        				positiveLargest[0] = A[i];
        			}    				
    			}

    		}
    		else if( A[i] < 0 ) {
    			if( A[i] < negativeSmallest[2] ) {
    				negativeSmallest[0] = negativeSmallest[1];
    				negativeSmallest[1] = negativeSmallest[2];
    				negativeSmallest[2] = A[i];
    			} 
    			else {
        			if( A[i] < negativeSmallest[1] ) {
        				negativeSmallest[0] = negativeSmallest[1];
        				negativeSmallest[1] = A[i];
        			} 
        			else if( A[i] < negativeSmallest[0] ) {
        				negativeSmallest[0] = A[i];
        			}    				
    			}
    		}
		}
    	

    	// Largest multiplication would be in 2 situations: 
    	// 3 largest positive or one largestOsitive times by 2 smallest negatives
    	// if all 3 positives > 0 then
    	largest3 = positiveLargest[0] * positiveLargest[1] * positiveLargest[2];

    	int negativeSmallest3 = negativeSmallest[1] * negativeSmallest[2] * positiveLargest[2];

    	if( negativeSmallest3 > largest3 ) {
    		largest3 = negativeSmallest3;
    	}
    	
    	return largest3;
    }
}
