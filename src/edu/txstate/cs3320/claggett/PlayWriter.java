package edu.txstate.cs3320.claggett;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PlayWriter {
	
	
	private static String input = "./iofiles/MidsummerNightsDream.json";
	private static String output = "./iofiles/MidsummerNightsDream.txt";
	
	public static void main(String []args){
		String thePlay = PlayJSONReader.readPlay(input);
		writePlay(thePlay, output);
		
	}
	
	public static void writePlay(String thePlay, String outputFile) {
		
		JsonElement jelement = new JsonParser().parse(thePlay);
		JsonObject thePlayJsonObject = (JsonObject)jelement;
		writePlayTitleAndYear(thePlayJsonObject);
		writeActs(thePlayJsonObject);
		
		
	}
	
	private static void writePlayTitleAndYear(JsonObject thePlayJson) {
		
		System.out.println(AccessorUtils.getPlayTitle(thePlayJson));
		System.out.println(AccessorUtils.getPlayYear(thePlayJson));
		
		
		
	}
	
	private static void writeActs(JsonObject thePlayJson) {
		
		ArrayList<String> acts = AccessorUtils.getActsOfPlayByName(thePlayJson);
		for (int i = 0; i < acts.size(); i++) {
			System.out.println(acts.get(i));
			writeAct(AccessorUtils.getActOfPlay(thePlayJson, acts.get(i)), acts.get(i));
		}
		
	}
	
	
	private static void writeAct(JsonObject actName, String actNameString) {
		
		writeScenesOfAct(actName, actNameString);

	}
	
	
	private static void writeScenesOfAct(JsonObject actName, String actNameString) {
		
		ArrayList<String> scenes = AccessorUtils.getScenesOfActByName(actName);
		for (int i = 0; i < scenes.size(); i++) {
			System.out.println(actNameString + ", " + scenes.get(i));
			writeSceneOfAct(AccessorUtils.getSceneOfAct(actName, scenes.get(i)));
			
		}
		
		
	}
	
	
	private static void writeSceneOfAct(JsonObject scene) {
		
		JsonArray paragraphs = AccessorUtils.getSceneParagraphs(scene);
//		System.out.println(paragraphs.size());
		for (int i = 0; i < paragraphs.size(); i++) {
			writeSceneParagraphs(AccessorUtils.getParagraphFromParagraphs(paragraphs, i));
		}
	}

	
	private static void writeSceneParagraphs(JsonObject paragraph) {
		System.out.println(AccessorUtils.getCharacterFromParagraph(paragraph));
		System.out.println(AccessorUtils.getTextFromParagraph(paragraph));
	}
	
	
}
