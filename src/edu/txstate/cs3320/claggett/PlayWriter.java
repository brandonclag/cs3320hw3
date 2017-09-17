package edu.txstate.cs3320.claggett;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PlayWriter {
	
	
	private static String input = "./iofiles/MidsummerNightsDream.json";
	private static String output = "./iofiles/MidsummerNightsDream.txt";
	private static BufferedWriter  writer = null;
	
	public static void main(String []args){
		String thePlay = PlayJSONReader.readPlay(input);
		writePlay(thePlay, output);
		
	}
	
	public static void writePlay(String thePlay, String outputFile) {
		
		
		try {
			writer = new BufferedWriter (new FileWriter(output));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		JsonElement jelement = new JsonParser().parse(thePlay);
		JsonObject thePlayJsonObject = (JsonObject)jelement;
		writePlayTitleAndYear(thePlayJsonObject);
		writeActs(thePlayJsonObject);
		
		
		
	}
	
	private static void writePlayTitleAndYear(JsonObject thePlayJson) {
		
		try {
			writer.write((AccessorUtils.getPlayTitle(thePlayJson)) + "\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			writer.write(AccessorUtils.getPlayYear(thePlayJson) + "\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	private static void writeActs(JsonObject thePlayJson) {
		
		ArrayList<String> acts = AccessorUtils.getActsOfPlayByName(thePlayJson);
		for (int i = 0; i < acts.size(); i++) {
			try {
				writer.write((acts.get(i)) + "\n");
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writeAct(AccessorUtils.getActOfPlay(thePlayJson, acts.get(i)), acts.get(i));
		}
		
	}
	
	
	private static void writeAct(JsonObject actName, String actNameString) {
		
		writeScenesOfAct(actName, actNameString);

	}
	
	
	private static void writeScenesOfAct(JsonObject actName, String actNameString) {
		
		ArrayList<String> scenes = AccessorUtils.getScenesOfActByName(actName);
		for (int i = 0; i < scenes.size(); i++) {
			try {
				writer.write(actNameString + ", " + scenes.get(i) + "\n");
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		try {
			writer.write(AccessorUtils.getCharacterFromParagraph(paragraph) + "\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			writer.write(AccessorUtils.getTextFromParagraph(paragraph) + "\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
