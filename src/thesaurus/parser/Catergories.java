package thesaurus.parser;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Catergories implements Serializable {
	private HashMap<String, ArrayList<String>> categories = new HashMap<String, ArrayList<String>>();
	private String filePath;
	private CategoriesData localCat = new CategoriesData();

	public Catergories() {
		//readFromFile();
	}
	
	public ArrayList<String> getAllCategories()
	{
		ArrayList<String> cats = new ArrayList<String>();
		cats.addAll(localCat.getCategories().keySet());
		return cats;
	}

	ArrayList<String> addCatergory(String c) {
		ArrayList<String> vertices = new ArrayList<String>();
		localCat.getCategories().put(c, vertices);
		//saveToFile();
		return vertices;
	}

	void removeCatergory(String c) {
		localCat.getCategories().remove(c);
		//saveToFile();
	}

	ArrayList<String> getCatergory(String c) {
		return localCat.getCategories().get(c);
	}

	void addVertexToCatergory(String c, String word) {
		localCat.getCategories().get(c).add(word);
		//saveToFile();
	}

	String getCatergories(Vertex v) {
		System.out.println("entering method " + v.getWord() + " wo "
				+ localCat.getCategories().size());
		for(Entry<String, ArrayList<String>> checker: localCat.getCategories().entrySet()){
			System.out.println("checking " + checker.getKey());
			if (checker.getValue().contains(v.getWord()))
				return checker.getKey();
		}
		return null;
	}

	void removeVertexFromCategory(String c, String word) {
		localCat.getCategories().get(c).remove(word);
		//saveToFile();
	}
	
	void saveToFile()
	{
		try{
			 
			FileOutputStream fout = new FileOutputStream("categories.obj");
			ObjectOutputStream oos = new ObjectOutputStream(fout);   
			oos.writeObject(localCat);
			oos.close();
			System.out.println("saving");
			System.out.println(localCat);
	 
		   }catch(Exception ex){
			   ex.printStackTrace();
		   }
	}
	
	void readFromFile()
	{
		try{
			 
			File f = new File("categories.obj");
			if(!f.exists())return;
			   FileInputStream fin = new FileInputStream("categories.obj");
			   ObjectInputStream ois = new ObjectInputStream(fin);
			   localCat =  (CategoriesData) ois.readObject();
			   ois.close();
			   System.out.println("reading");
			   //System.out.println(local);
	 
		   }catch(Exception ex){
			   ex.printStackTrace();
			
		   } 
	}

}
