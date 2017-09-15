package edu.txstate.cs3320.claggett;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PlayWriter {
	
	
	private static String path = "./iofiles/MidsummerNightsDream.json";
	
	public static void main(String []args){
		String thePlay = PlayJSONReader.readPlay(path);
		writePlay(thePlay, "something");
		
	}
	
	public static void writePlay(String thePlay, String outputFile) {
		
		JsonElement jelement = new JsonParser().parse(thePlay);
		JsonObject thePlayJsonObject = (JsonObject)jelement;
		writePlayTitleAndYear(thePlayJsonObject);
		
		
	}
	
	private static void writePlayTitleAndYear(JsonObject thePlayJson) {
		
		System.out.println(AccessorUtils.getPlayTitle(thePlayJson));
		System.out.println(AccessorUtils.getPlayYear(thePlayJson));
		
		
		
	}

}
