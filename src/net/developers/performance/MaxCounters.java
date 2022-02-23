package net.developers.performance;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Find largest number of the same integer
 * 
 * @author Igor Artimenko
 *
 */
public class MaxCounters extends IntegersTests{
	
	public static void main(String[] args) {
		MaxCounters algorithm5 = new MaxCounters();
		
		long start = System.currentTimeMillis();
		int[] randomNumbersR = algorithm5.initArray();
		int[] mixedArray = algorithm5.mixWithNonRandomArray(randomNumbersR);
		long executionTime = System.currentTimeMillis() - start;
		System.out.println( "Array Initialization took: " + FORMATTER.format( executionTime ) + MILLI_SEC );
		System.out.println( "mixedArray.length " + FORMATTER.format( mixedArray.length ) );
		
		algorithm5.runTest( "lambdaFilter", mixedArray);
		algorithm5.runTest( "fastestSearch", mixedArray);
		
	}
	
	/**
	 * 
	 * Sort an array
	 * Iterate through each element an collect counter for the same range
	 * @param A
	 * @return
	 */
	private int fastestSearch(int[] A) {
		
		int maxCounter  = 0;
		long start = System.currentTimeMillis();
		Arrays.parallelSort(A);
		long time = System.currentTimeMillis() - start;
		System.out.println( PARALLEL_SORT_TIME + FORMATTER.format( time ) + MILLI_SEC );
		
		int rangeIntValue = A[0];
		int rangeCounter = 0; 
					
		start = System.nanoTime();
		for (int i = 1; i < A.length; i++) {
			if( rangeIntValue == A[i] ) {
				rangeCounter++;
			}
			else {
				if( rangeCounter > maxCounter ) {
					maxCounter = rangeCounter;
				}
				rangeIntValue = A[i];
				rangeCounter = 1;
			}
		}
		time = System.nanoTime() - start;
		System.out.println( FIND_VALUE_TIME + FORMATTER.format( time ) + NANO_SEC );
		
		return maxCounter;
	}
	
	int lambdaFilter(int[] A) {
				
		long start = System.nanoTime();
		int maxCounter = 0;
		
		Optional<Long> lng = Arrays.stream( A )
	           .boxed()
	           .collect(Collectors.groupingBy(i->i,Collectors.counting()))
	           .values()
	           .stream()
	           .max(Comparator.comparingLong(i->i))
	           ;

		Long value = lng.get();
		long time = System.nanoTime() - start;		
		System.out.println( FIND_VALUE_TIME + FORMATTER.format( time )  + NANO_SEC );
		System.out.println( "value using streams: " + value );
		
		return maxCounter;
	}

	@Override
	public void runTest( String solutionName, int[] randomNumbers ) {
		
		System.out.println( "\n~~~\t" + solutionName );
		
		long start = System.currentTimeMillis();		
		int solutionValue = 0;
		
		switch (solutionName) {
		case "lambdaFilter":
			solutionValue = this.lambdaFilter(randomNumbers);
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
	
}
