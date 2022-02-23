package net.algorithms.optimized.maxpair;

import java.util.Arrays;
import java.util.SplittableRandom;

public class Solution2 {
	public static final int N = 100_000;
	public static final int MIN = -1000_000;
	
	public static void main(String[] args) {
		Solution2 sol2 = new Solution2();
		
		long start = System.currentTimeMillis();
		int[] a = sol2.initArray();
		long time = System.currentTimeMillis() - start;
		System.out.println( "Initialization:" + time + " millisec");
		
//		System.out.println( "~~~" );
//		
//		start = System.currentTimeMillis();
//		int solutionResult1 = sol2.solution1(a);
//		time = System.currentTimeMillis() - start;
//		System.out.println( "solution1 execution: " + time + " millisec solutionResult1: " + solutionResult1);

		System.out.println( "~~~" );
		
		start = System.currentTimeMillis();
		int solutionResult2 = sol2.solution2(a);
		time = System.currentTimeMillis() - start;
		System.out.println( "solution2 execution: " + time + " millisec solutionResult2: " + solutionResult2 );
		
		System.out.println( "~~~" );
		start = System.currentTimeMillis();
		int solutionResult3 = sol2.solution22(a);
		time = System.currentTimeMillis() - start;
		System.out.println( "solution3 execution: " + time + " millisec solution3: " + solutionResult3 );
		
//		start = System.currentTimeMillis();
//		int solutionResult3 = sol2.solution3(a);
//		time = System.currentTimeMillis() - start;
//		System.out.println( "solution3 execution: " + time + " millisec solution3: " + solutionResult3 );
		
		
	}
	
	private int solution3(int[] A) {
		
		long start = System.currentTimeMillis();
		Arrays.parallelSort(A);
		long time = System.currentTimeMillis() - start;
		System.out.println( "Parallel sort took " + time + " millisec" );
		
		start = System.currentTimeMillis();
		int value = Arrays.stream(A)
				.filter(e->e > 0? true: Arrays.binarySearch(A, -e) >=0)
				.map(e->e > 0? 0: -e)
				.findFirst()
				.orElse(0);
		time = System.currentTimeMillis() - start;
		System.out.println( "Find actual value took " + time + " millisec" );
		
		return value;
	}
	
	int solution2(int[] A) {
		int maxPairValue = 0;
		int posFound = 0;
		// Algorithm is efficient. It does not create Integer object.
		// It's fast to compare, sort primitives.
		// It's memory efficient to hold array of primitives.
		int[] orderedIntegers = Arrays.stream(A).sorted().toArray();
		
		for (int i = 0; i < orderedIntegers.length; i++) {
			if( orderedIntegers[i] < 0 ) {
				int currentInt = orderedIntegers[i];
				if( binarySearch( -1 * currentInt, orderedIntegers ) ) {
					maxPairValue = currentInt;
					posFound = i;
					break;
				}
			} 
			else { break; } // If we don't have negative any more

		}
		
		try {
			if( maxPairValue != 0 ) {
				System.out.println( "orderedIntegers.length: " + orderedIntegers.length );
				System.out.println( "Negative number[" +  posFound + "] " + orderedIntegers[ posFound ] );
				int numberFound = orderedIntegers[ posFound ];
				int positiveNumberOrder = Arrays.binarySearch( orderedIntegers, -1 * numberFound);
				System.out.println( "Matching Positive number[" + positiveNumberOrder + "] " + orderedIntegers[ positiveNumberOrder ] );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return maxPairValue;
	}
	
	int solution22(int[] orderedIntegers) {
		int maxPairValue = 0;
		int posFound = 0;
		// Algorithm is efficient. It does not create Integer object.
		// It's fast to compare, sort primitives.
		// It's memory efficient to hold array of primitives.
		Arrays.parallelSort(orderedIntegers);
		
		for (int i = 0; i < orderedIntegers.length; i++) {
			if( orderedIntegers[i] < 0 ) {
				int currentInt = orderedIntegers[i];
				if( binarySearch( -1 * currentInt, orderedIntegers ) ) {
					maxPairValue = currentInt;
					posFound = i;
					break;
				}
			} 
			else { break; } // If we don't have negative any more

		}
		
		try {
			if( maxPairValue != 0 ) {
				System.out.println( "orderedIntegers.length: " + orderedIntegers.length );
				System.out.println( "Negative number[" +  posFound + "] " + orderedIntegers[ posFound ] );
				int numberFound = orderedIntegers[ posFound ];
				int positiveNumberOrder = Arrays.binarySearch( orderedIntegers, -1 * numberFound);
				System.out.println( "Matching Positive number[" + positiveNumberOrder + "] " + orderedIntegers[ positiveNumberOrder ] );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return maxPairValue;
	}
	
	
//	int solution1(int[] A) {
//		int maxPairValue = 0;
//		
//		// Algorithm is efficient. It does not create Integer object.
//		// It's fast to compare, sort primitives.
//		// It's memory efficient to hold array of primitives.
//		int[] negativeOnly = Arrays.stream(A).filter( v-> v < 0).sorted().distinct().toArray();
//		int[] positiveOnly = Arrays.stream(A).filter( v-> v > 0).sorted().distinct().toArray();
//		
//		for (int i = 0; i < negativeOnly.length; i++) {
//			int currentPositive = -1 * negativeOnly[i];
//			if( binarySearch( currentPositive, positiveOnly ) ) {
//				maxPairValue = negativeOnly[i];
//				break;
//			}
//		}
//		
//		return maxPairValue;
//	}
	
	private boolean binarySearch( int a, int[] b ) {
		boolean pairFound = false;

		int positionFound = Arrays.binarySearch( b, a);
		
		if( positionFound >= 0 ) {
			pairFound = true;
		}
		
		return pairFound;
	}
	
	private int[] initArray() {

		int[] arr = new SplittableRandom().ints( N, MIN, -1 * MIN ).parallel().toArray();
		
		// Arrays.stream(arr).limit(1000).forEach(System.out::println);
		
		return arr;
	}
}
