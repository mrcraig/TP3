package thesaurus.parser;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

public class CategoriesData implements Serializable {
	
	/**
	 * Hello
	 */
	private static final long serialVersionUID = 2037245769706830936L;
	private HashMap<String, LinkedList<String>> categories;

	public CategoriesData(){
		categories = new HashMap<String, LinkedList<String>>();
	}
	
	public HashMap<String, LinkedList<String>> getCategories() {
		return categories;
	}

	public void setCategories(HashMap<String, LinkedList<String>> categories) {
		this.categories = categories;
	}

	public String toString()
	{
		String s = "";
		for(String c : categories.keySet())
		{
			s += c;
			s += "values are ";
			s += categories.get(c).getFirst();
		}
		return s;
	}
		
	
}
