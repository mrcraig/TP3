package thesaurus.importparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TextParser {

	public static void main(String[] args) {
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
					index++;
				} else {
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

	}
}