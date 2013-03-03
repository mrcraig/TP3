package thesaurus.importparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import thesaurus.parser.InternalRepresentation;
import thesaurus.parser.Vertex;

import javafx.stage.FileChooser;

public class TextParser {

	public static void main(String[] args) throws IOException {
		File recentFile = new File("./src/resourcePackage/importparser.txt");
		Scanner fileScan = null;
		try {
			fileScan = new Scanner(recentFile);
			fileScan.useDelimiter("\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<ArrayList<String>> entryArray = new ArrayList<ArrayList<String>>();
		int index = 0;
		int entryindex = 0;
		while (fileScan.hasNext()) {
			String words = fileScan.next();
			if (!words.isEmpty()) {
				if (index == 0) {
					ArrayList<String> currentEntry = new ArrayList<String>();
					entryArray.add(entryindex, currentEntry);
					currentEntry.add(0, words);
					currentEntry.add(1, "");
					currentEntry.add(2, "");
					index++;
				}
				else {
				entryArray.get(entryindex).add(index, words);
				index++;
				}
			} else {
				index = 0;
				entryindex++;
			}


		}

		for (ArrayList<String> s : entryArray) {
			for (String st : s) {
				System.out.println(st);
			}
		}
		
		OutputStreamWriter fileWriter;
		fileWriter = new OutputStreamWriter(new FileOutputStream("smile.graphml"),"UTF-8");
		fileWriter.write("<graphml><graph></graph></graphml>");
		fileWriter.close();
		
		File inputFile = new File("smile.graphml");
		
		InternalRepresentation currentParser = new InternalRepresentation(inputFile);
		
		for (ArrayList<String> s : entryArray) {
			for(String st: s){
				//System.out.println(st);
			}
			//System.in.read();
			currentParser.addVertex(s.get(0), s.get(1),s.get(2),"");
		}
		
		

	}
	
}