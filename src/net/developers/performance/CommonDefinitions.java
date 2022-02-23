package net.developers.performance;

import java.text.DecimalFormat;

public interface CommonDefinitions {

	public static final String MILLI_SEC = " milliSec";
	public static final String NANO_SEC = " nanoSec"; 
	public static final String TIME_TO_EXECUTE = "Find an actual value took: ";
	public static final DecimalFormat FORMATTER = new DecimalFormat( "###,###" );

	public static final String DATA_FILE = "./data/characters.txt";
	
}
