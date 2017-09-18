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
	
	/**
	 * The main method to start the reading and writing of the play
	 * @param args
	 */
	public static void main(String []args){
		String thePlay = PlayJSONReader.readPlay(input);
		writePlay(thePlay, output);
		
	}
	/**
	 * responsible for handling the writing of the play by calling helper methods 
	 * that use a "top-down" approach. Takes the the Play as a string, writes the output 
	 * to a text file
	 * @param thePlay
	 * @param outputFile
	 */
	public static void writePlay(String thePlay, String outputFile) {
		
		
		try {
			writer = new BufferedWriter (new FileWriter(output));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//takes the string of the play and parses it as a jsonObject
		JsonElement jelement = new JsonParser().parse(thePlay);
		JsonObject thePlayJsonObject = (JsonObject)jelement;
		
		writePlayTitleAndYear(thePlayJsonObject);
		writeActs(thePlayJsonObject);
		
		
		
	}
	/**
	 * Takes the jsonObject of the entire play and writes the title and year to the 
	 * output file
	 * @param thePlayJson
	 */
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
	/**
	 * Takes the play as a jsonObject and extracts the acts as plain text
	 * Then it iterates through each act calls the helper 
	 * method writeAct to write the text in the act
	 * @param thePlayJson
	 */
	private static void writeActs(JsonObject thePlayJson) {
		
		ArrayList<String> acts = AccessorUtils.getActsOfPlayByName(thePlayJson);
		for (int i = 0; i < acts.size(); i++) {
			
			//passes the act as a jsonObject to the writeAct function, also passes the name of the act for writing
			writeAct(AccessorUtils.getActOfPlay(thePlayJson, acts.get(i)), acts.get(i));
		}
		
	}
	
	/**
	 * Takes the actNameString and prints it at the beginning of each act, then
	 * passes the actName JsonObject to the writeScenesOfAct helper function to 
	 * write each scene of the act
	 * @param actName
	 * @param actNameString
	 */
	private static void writeAct(JsonObject actName, String actNameString) {
		
		try {
			writer.write((actNameString) + "\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writeScenesOfAct(actName, actNameString);

	}
	
	/**
	 * Takes the actName as a JsonObject and iterates through each scene to print the header
	 * for each scene and passes the scene JsonObject to the writeSceneOfAct helper function
	 * @param actName
	 * @param actNameString
	 */
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
	
	/**
	 * takes the JsonObject of the scene and iterates through each paragraph to call the 
	 * writeSceneParagraphs helper function to write the text of each paragraph
	 * @param scene
	 */
	private static void writeSceneOfAct(JsonObject scene) {
		
		JsonArray paragraphs = AccessorUtils.getSceneParagraphs(scene);
//		System.out.println(paragraphs.size());
		for (int i = 0; i < paragraphs.size(); i++) {
			writeSceneParagraphs(AccessorUtils.getParagraphFromParagraphs(paragraphs, i));
		}
	}

	/**
	 * Takes the paragraph as a JsonObject and writes the text from each paragraph to the output file
	 * @param paragraph
	 */
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
