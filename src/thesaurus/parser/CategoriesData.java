package thesaurus.parser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class CategoriesData implements Serializable {
	
	/**
	 * Hello
	 */
	private static final long serialVersionUID = 2037245769706830936L;
	private HashMap<String, ArrayList<String>> categories;

	public CategoriesData(){
		categories = new HashMap<String, ArrayList<String>>();
	}
	
	public HashMap<String, ArrayList<String>> getCategories() {
		return categories;
	}

	public void setCategories(HashMap<String, ArrayList<String>> categories) {
		this.categories = categories;
	}

	public String toString()
	{
		String s = "";
		for(String c : categories.keySet())
		{
			s += c;
			s += "values are ";
			s += categories.get(c).get(0);
		}
		return s;
	}
		
	
}
