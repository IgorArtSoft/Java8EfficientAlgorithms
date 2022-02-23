package net.developers.performance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.OptionalInt;
import java.util.Set;

/**
 * This programs take long String and identifies all matching ( upper case & low case ) pairs of english letters ( A, B, ... ).
 * Input string is read from input file.
 * The file itself is being generated by another program
 * 
 * Real purpose of this program is to compare performance of 3 different algorithms.
 * Execution time 
 * @author Igor Artimenko
 *
 */
public class StringPerformance implements CommonDefinitions {

	public static void main(String[] args) {
		StringPerformance strPerf = new StringPerformance();
		String content = readAllBytes();

		System.out.println( content.length() );
		strPerf.runTest("hashSetSolution", content );
		strPerf.runTest("findHighCharPairFast", content );
		strPerf.runTest("findCharPairSet", content );		

	}

	public void runTest(String solutionName, String longString ) {

		System.out.println("\n~~~\t" + solutionName);

		long start = System.currentTimeMillis();

		switch (solutionName) {
		case "hashSetSolution":
			this.hashSetSolution(longString);
			break;
		case "findHighCharPairFast":
			this.findHighCharPairFast(longString);
			break;
		case "findCharPairSet":
			this.findCharPairSet(longString);
			break;
		}	

		long executionTime = System.currentTimeMillis() - start;
		System.out.println(", executionTime= " + FORMATTER.format(executionTime) + MILLI_SEC);

		System.gc();
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void hashSetSolution(String longString ) {

		HashSet<Character> upperCaseLetters = new HashSet(26);
		HashSet<Character> lowCaseLetters = new HashSet(26);
		HashSet<Character> commonChars = new HashSet(26);

		long start = System.currentTimeMillis();
		
		longString.chars().parallel()
		.filter(i -> (65 <= i && i <= 90) || (97 <= i && i <= 122)) // Only letters
		.forEach(currentChar -> { // For now value contains int representation of current character
			if (65 <= currentChar && currentChar <= 90) {
				upperCaseLetters.add(new Character((char) currentChar));
			} else if (97 <= currentChar && currentChar <= 122) {
				lowCaseLetters.add(new Character((char) currentChar));
			}
		});

		long executionTime = System.currentTimeMillis() - start;
		System.out.println(", executionTime= " + FORMATTER.format(executionTime) + MILLI_SEC);
		
		start = System.currentTimeMillis();
		for (Character upperChar : upperCaseLetters) {
			if (lowCaseLetters.contains(Character.toLowerCase(upperChar))) {
				commonChars.add(upperChar);
			}

		}
		executionTime = System.currentTimeMillis() - start;
		//System.out.println(", executionTime= " + FORMATTER.format(executionTime) + MILLI_SEC);
		
		for (Character character : commonChars) {
			System.out.print(character + ", " );
		}
		// System.out.println( "Pairs detected: " + commonChars.size() );
	}

	private String findHighCharPairFast(String longString) {

		boolean[] charset = new boolean[1024];

		OptionalInt max = longString.chars()
	              .parallel()
	              .filter(c->charset[c]?false:(charset[c]=true)?charset[Character.toLowerCase(c)]&&charset[Character.toUpperCase(c)]:false)
	              .map(c->Character.toLowerCase(c))
	              .max();

		String strR = max.isPresent() ? String.valueOf(Character.toUpperCase((char) max.getAsInt())) : "NO";
		//System.out.println( strR );
		return strR;

	}

	public Set<Character> findCharPairSet(String longString) {

        boolean[] bset = new boolean[123];

        Set<Character> set = new HashSet<>(64);

        longString.chars()
            .parallel()            
            .filter(i -> (65 <= i && i <= 90) || (97 <= i && i <= 122)) 
            .filter(c->bset[c]?false:(bset[c]=true)?bset[Character.toLowerCase(c)]&&bset[Character.toUpperCase(c)]:false)
	        .forEach(c->set.add((char) c));

        return set;

   }
	
	private static String readAllBytes() {
		String content = "";

		try {
			content = new String(Files.readString(Paths.get(DATA_FILE)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content;
	}
	
}
