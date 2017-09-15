package edu.txstate.cs3320.claggett;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class PlayWriter {
	
	
	private static String path = "./iofiles/MidsummerNightsDream.json";
	
	public static void writePlay() {
		
		
		
	}
	
	private static void writePlayTitleAndYear() {
		
		String thePlay = PlayJSONReader.readPlay(path);
		
		JsonElement jelement = new JsonParser().parse(thePlay);
		
	}

}
