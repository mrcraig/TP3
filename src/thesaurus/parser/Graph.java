package thesaurus.parser;

import java.util.LinkedList;
import java.util.List;

/*
 * parser has methods for retrieving data, and thats all
 * user needs.
 * 
 * Parser calls handler and stores all of the information 
 * in graph
 * 
 * graph organizes and provides methods to to parser
 * 
 * most handler methods can be moved to graph
 * 
 * 
 */

public class Graph 
{
	private LinkedList<Vertex> nodes = new LinkedList<Vertex>();

	 LinkedList<Vertex> getNodes() 
	{
		return nodes;
	}

	void setNodes(LinkedList<Vertex> nodes)
	{
		this.nodes = nodes;
	}
	
	 void clear()
	{
		nodes.clear();
	}
	
	 void add(Vertex v)
	{
		nodes.add(v);
	}
	
	int size()
	{
		return nodes.size();
	}
	
	void setLastWord(String word)
	{
		//copy from handler
		//finish moving methods from handler to graph
		nodes.getLast().setWord(word);
	}
	
	
	/* if the xml is in the wrong order, this method will still work */
	 Vertex getVertex(int index)
	{
		for(Vertex v : nodes)
		{
			if(v.getIndex()==index) return v;
		}
		return null;
	}
	
	 LinkedList<Vertex> getList()
	{
		return nodes;
	}
	 Vertex getVertex(String word)
	{
		for(Vertex v : nodes)
		{
			if(v.getWord().equalsIgnoreCase(word)) return v;
		}
		return null;
	}
	
}
