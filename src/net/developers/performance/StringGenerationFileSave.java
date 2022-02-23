package net.developers.performance;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Prepares very large data file with randomly generated alpha numeric characters.
 * 
 * @author Igor Artimenko
 *
 */
public class StringGenerationFileSave implements CommonDefinitions{

	public static void main(String[] args) {
		
	StringGenerationFileSave srtFile = new StringGenerationFileSave();
		srtFile.fileSave();
	}

	private void fileSave() {

	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 1_000_000;
	    Random random = new Random();
	    
	    try(PrintWriter pw = new PrintWriter(Files.newBufferedWriter( Paths.get( DATA_FILE ) ) ) ) {
	    	
	    	random.ints(leftLimit, rightLimit + 1)
	   	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97) && i != 84 )
	   	      .limit(targetStringLength)
	   	      .mapToObj( Character::toString )
	   	      .forEach(pw::print);
	    	
	    } catch (IOException e) {
			e.printStackTrace();
		}	    
		
	}
	
}
