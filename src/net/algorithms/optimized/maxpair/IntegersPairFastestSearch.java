package net.algorithms.optimized.maxpair;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.SplittableRandom;

/**
 * 
 * This algorithm searches for the largest (by module) negative integer which has it's matching positive integer 
 * among large array of integers. Each integer element is randomly generated. 
 * i.e. it searches for value a== -983123 that has matching b== +983123.
 * 
 * There are 3 implementations of this algorithm, measuring and comparing execution time on each step.
 * 
 * I left slower methods implementations here on purpose.
 * Lessons learned:
 * 1. parallelSort method is about 25% faster than sequential sorted() one.
 * 2. I love lambdas, but they perform 20-100 times slower on my test cases compare to my custom search. 
 *    It's due to that lambdas process an entire large collection. 
 *    My custom algorithm stops after the 1st pair it found.
 *   
 * I specifically use Java 8 SE features only without any extra 3rd party library dependencies for clean tests performance benchmarks.
 * 
 * This algorithm initialises a large array of randomly generated integers.
 * 
 * @author Igor Artimenko
 *
 */
public class IntegersPairFastestSearch {

	public static final int N = 10_000_000;
	public static final int MIN = -1_000_000_000;
	public static final String MILLI_SEC = " milliSec";
	public static final String NANO_SEC = " nanoSec"; 
	public static final String FIND_VALUE_TIME = "Find an actual value took: ";
	public static final String SEQUENTIAL_SORTED_TIME = "sequential sorted() took: ";
	public static final String PARALLEL_SORT_TIME = "parallelSort(A) took: ";
	public static final DecimalFormat FORMATTER = new DecimalFormat( "###,###" );
	
	
	public static void main(String[] args) {
		IntegersPairFastestSearch algorithm4 = new IntegersPairFastestSearch();
		
		long start = System.currentTimeMillis();
		int[] randomNumbers = algorithm4.initArray();
		long executionTime = System.currentTimeMillis() - start;
		System.out.println( "Array Initialization took: " + FORMATTER.format( executionTime ) + MILLI_SEC );

		algorithm4.runTest( "sequentialSortLambdaSearch", randomNumbers);
		algorithm4.runTest( "sequentialSortCustomSearch", randomNumbers);
		algorithm4.runTest( "fastestSearch", randomNumbers);
		
	}
	
	/**
	 * 	Algorithm is efficient. It does not have an overhead of creation of large number of Integer objects.
	 *  Array of primitives It's memory efficient to hold .
	 *  It's fast to compare.
	 *  
	 * @param A
	 * @return 
	 */
	private int fastestSearch(int[] A) {
		
		int maxPairValue  = 0;
		
		long start = System.currentTimeMillis();
		Arrays.parallelSort(A);
		long time = System.currentTimeMillis() - start;
		System.out.println( PARALLEL_SORT_TIME + FORMATTER.format( time ) + MILLI_SEC );
		
		start = System.nanoTime();
		for (int i = 0; i < A.length; i++) {
			if( A[i] < 0 ) {
				int currentInt = A[i];
				if( binarySearch( -1 * currentInt, A ) ) {
					maxPairValue = currentInt;
					break;
				}
			} 
			else { break; } // If we don't have negative any more

		}
		time = System.nanoTime() - start;
		System.out.println( FIND_VALUE_TIME + FORMATTER.format( time ) + NANO_SEC );
		
		return maxPairValue;
	}
	
	int sequentialSortCustomSearch(int[] A) {
		int maxPairValue = 0;
		
		long start = System.currentTimeMillis();
		int[] orderedIntegers = Arrays.stream(A).sorted().toArray();
		long time = System.currentTimeMillis() - start;
		System.out.println( SEQUENTIAL_SORTED_TIME + FORMATTER.format( time ) + MILLI_SEC );
		
		start = System.nanoTime();
		for (int i = 0; i < orderedIntegers.length; i++) {
			if( orderedIntegers[i] < 0 ) {
				int currentInt = orderedIntegers[i];
				if( binarySearch( -1 * currentInt, orderedIntegers ) ) {
					maxPairValue = currentInt;
					break;
				}
			} 
			else { break; } // If we don't have negative any more

		}
		time = System.nanoTime() - start;
		System.out.println( FIND_VALUE_TIME + FORMATTER.format( time )  + NANO_SEC );
		
		return maxPairValue;
	}
	
	
	private int sequentialSortLambdaSearch(int[] A) {
		
		long start = System.currentTimeMillis();

		int[] B = Arrays.stream(A).sorted().toArray();
		long time = System.currentTimeMillis() - start;
		System.out.println( SEQUENTIAL_SORTED_TIME + FORMATTER.format( time ) + MILLI_SEC );
		
		start = System.nanoTime();
		int maxPairValue = Arrays.stream(B)
				.filter(e->e > 0? true: Arrays.binarySearch(B, -e) >=0)
				.map(e->e > 0? 0: -e)
				.findFirst()
				.orElse(0);
		time = System.nanoTime() - start;
		System.out.println( FIND_VALUE_TIME + FORMATTER.format( time ) + NANO_SEC + " Stream, Lambda" );
		
		return maxPairValue;
	}
	
	private void runTest( String solutionName, int[] randomNumbers ) {
		
		System.out.println( "\n~~~\t" + solutionName );
		
		long start = System.currentTimeMillis();		
		int solutionValue = 0;
		
		switch (solutionName) {
		case "sequentialSortCustomSearch":
			solutionValue = this.sequentialSortCustomSearch(randomNumbers);
			break;
		case "sequentialSortLambdaSearch":
			solutionValue = this.sequentialSortLambdaSearch(randomNumbers);
			break;		
		case "fastestSearch":
			solutionValue = this.fastestSearch(randomNumbers);
			break;			
		}
		
		long executionTime = System.currentTimeMillis() - start;
		System.out.println( "MaxValue calculated = " + solutionValue + ", executionTime= " + FORMATTER.format( executionTime ) + MILLI_SEC );
		
		System.gc(); 
		try {
			Thread.sleep( 1000L );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private boolean binarySearch( int a, int[] b ) {
		boolean pairFound = false;

		int positionFound = Arrays.binarySearch( b, a);
		
		if( positionFound >= 0 ) {
			pairFound = true;
		}
		
		return pairFound;
	}
	
	public int[] initArray() {
		return new SplittableRandom().ints( N, MIN, -1 * MIN ).parallel().toArray();
	}
	
}
