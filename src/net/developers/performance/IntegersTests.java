package net.developers.performance;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.SplittableRandom;

public abstract class IntegersTests {

	public static final int N = 10_000_000;
	public static final int MIN = -1_000_000_000;
	public static final String MILLI_SEC = " milliSec";
	public static final String NANO_SEC = " nanoSec"; 
	public static final String FIND_VALUE_TIME = "Find an actual value took: ";
	public static final String SEQUENTIAL_SORTED_TIME = "sequential sorted() took: ";
	public static final String PARALLEL_SORT_TIME = "parallelSort(A) took: ";
	public static final DecimalFormat FORMATTER = new DecimalFormat( "###,###" );
	
	abstract void runTest( String solutionName, int[] randomNumbers );
	
	int[] initArray() {
		return new SplittableRandom().ints( N, MIN, -1 * MIN ).parallel().toArray();
	}
	
	int[] mixWithNonRandomArray( int[] originalArr ) {
		int[] nonRandomArray = new int[ 8 ];		
		Arrays.fill( nonRandomArray, MIN/100 );
		
		int[] newArray = Arrays.copyOf(originalArr, originalArr.length + nonRandomArray.length);
		System.arraycopy(nonRandomArray, 0, newArray, originalArr.length, nonRandomArray.length);
		
		return newArray;
	}
	
	boolean binarySearch( int a, int[] b ) {
		boolean pairFound = false;

		int positionFound = Arrays.binarySearch( b, a);
		
		if( positionFound >= 0 ) {
			pairFound = true;
		}
		
		return pairFound;
	}
		
}
