package thesaurus.parser;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

//use boolean flags to indicate to characters method what it should parse - switch statement

public class Parser {
	
	String path;
	Handler handler;
	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser parser;
	//List<Vertex> currentlyInUse = new LinkedList<Vertex>();
	Graph allNodes = new Graph();
	
	public Parser(String path)
	{
		this.path = getClass().getResource(path).getPath();
		try
		{
			 parser = factory.newSAXParser();
			 this.getAll(); //parse and save nodes
		}
		catch(Exception s) {System.out.println("couldn't create a parser");};
	}
	
	public Parser(File f)
	{
		this.path = f.getPath();
		try
		{
			 parser = factory.newSAXParser();
			 this.getAll(); //save nodes
		}
		catch(Exception s) {System.out.println("couldn't create a parser");};
	}
	
	private void getAll()
	{
		handler = new Handler();
		try 
		{
			parser.parse(path, handler);
		} catch (Exception e) 
		{ System.out.println("parsing went wrong");
		}
		allNodes.setNodes(handler.getNodes());
	}
	

	public LinkedList<Vertex> getAllNodes()
	{
		return allNodes.getList();
	}
	
	
	/* Breadth First Search*/
	public LinkedList<Vertex> getSynmsFor(String s, int max)
	{
		return allNodes.getSynmsFor(s, max);
	}
 	
	public LinkedList<Vertex> getSynmsFor(String s){return getSynmsFor(s, 100);}
		
	//dictionary key is word, value is linkedlist of synomns	
	public HashMap<String, LinkedList<String>> getTableData()
	{
		return allNodes.getTableData();
	}
	
}
