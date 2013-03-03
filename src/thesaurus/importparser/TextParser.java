package thesaurus.importparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TextParser {


public static void main(String[] args)
{ 
	File f = new File("/users/level3/0805089k/massivechore.txt");
	Scanner fileScan = null;
	try {
		fileScan = new Scanner(f);
		fileScan.useDelimiter("\n");
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	List allTheWords = new LinkedList();
	List wordsWordsWords = null;
	while(fileScan.hasNext()){
			String words = fileScan.next();
			
			if(!words.isEmpty())
			{
				if(words.split(" ").length==1)
				{
					allTheWords.add(wordsWordsWords);
					wordsWordsWords = new LinkedList();
				}
				words = words.replace(",","");
				wordsWordsWords.add(words);
				System.out.println();
				System.out.println(wordsWordsWords);
			}		
		}
			
	}
}