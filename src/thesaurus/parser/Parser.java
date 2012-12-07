package thesaurus.parser;

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
		//System.out.println(path);
		//System.out.println(getClass().getResource(path).getPath());
		this.path = getClass().getResource(path).getPath();
		try
		{
			 parser = factory.newSAXParser();
			 this.getAll(); //parse and save nodes
		}
		catch(Exception s) {System.out.println("couldn't create a parser");};
	}
	
	public Parser(URL f)
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
	public List<Vertex> getSynmsFor(String s, int max)
	{
		LinkedList<Vertex> workQueue = new LinkedList<Vertex>();
		List<Vertex> results = new LinkedList<Vertex>();
		this.getAll();
		Vertex start = allNodes.getVertex(s);
		workQueue.add(start);
		
		while(!workQueue.isEmpty() && results.size()<max)
		{
			Vertex current = workQueue.remove();
			results.add(current);
			for(AdjListNode n : current.getAdjList())
			{
				Vertex child = allNodes.getVertex(n.getVertexNumber());
				if(!workQueue.contains(child) && !results.contains(child))
				{
					workQueue.add(child);
				}
			}
		}
		return results;
	}
 	
	public List<Vertex> getSynmsFor(String s){return getSynmsFor(s, 100);}
		
	//dictionary key is word, value is linkedlist of synomns	
	public HashMap<String, List<String>> getTableData()
	{	
		HashMap<String,List<String>> tableData = new HashMap<String, List<String>>();
		for(Vertex v : allNodes.getList())
		{
			String k = v.getWord();
			LinkedList<String> synms = new LinkedList<String>();
			for(AdjListNode n : v.getAdjList())
			{
				synms.add(allNodes.getVertex(n.getVertexNumber()).word);
			}
			tableData.put(k,synms);
		}	
		return tableData;
	}	
	
}
