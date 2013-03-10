package thesaurus.parser;

import java.util.HashMap;
import java.util.LinkedList;

public class Catergories
{
	private HashMap<String,LinkedList<String>> catergories = new HashMap<String,LinkedList<String>>();
	
	
	
	
	
	public Catergories(){}
	
	LinkedList<String> addCatergory(String c)
	{
		LinkedList<String> vertices = new LinkedList<String>();
		catergories.put(c, vertices);
		return vertices;
	}
	
	
	void removeCatergory(String c)
	{
		catergories.remove(c);
	}
	
	LinkedList<String> getCatergory(String c)
	{
		return catergories.get(c);
	}
	void addVertexToCatergory(String c, String word)
	{
		LinkedList<String> catergory = catergories.get(c);
		catergory.add(word);
		catergories.put(c, catergory);
	}
	
	
	
	void removeVertexFromCategory(String c, String word)
	{
		LinkedList<String> catergory = catergories.get(c);
		catergory.remove(word);
		catergories.put(c, catergory);
	}
	
	
}
