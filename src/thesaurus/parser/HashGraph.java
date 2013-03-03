package thesaurus.parser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;


//use string as index

//use word first then maybe index
public class HashGraph
{
	private HashMap<String,Vertex> nodesMap;
	



	public HashGraph()
	{
		nodesMap = new HashMap<String, Vertex>();
		
	}

	void add(Vertex v)
	{
		nodesMap.put(v.getWord(), v);
	}

	int size()
	{
		return nodesMap.size();
	}

	Vertex getVertexFromIndex(String index)
	{
		Iterator<Entry<String,Vertex>> all = nodesMap.entrySet().iterator();
		while(all.hasNext())
		{
			Vertex v = all.next().getValue();
			if(v.getID().equals(index))
			{
				return v;
			}
		}
		System.out.println("null");
		return null;
	}
	
	void setNodes(HashMap<String,Vertex> nodes)
	{
		this.nodesMap = nodes;
	}
	
	Vertex getVertexFromWord(String word)
	{
		return nodesMap.get(word);
	}
	
	Vertex getRandVertex()
	{
		return nodesMap.entrySet().iterator().next().getValue();
		
	}

	boolean contains(Vertex v) 
	{
		return nodesMap.containsValue(v);
	}


    HashMap<String,Vertex> getNodes()
	{
		return nodesMap;
	}

    //go through all vertices, and check to see
    //if synomyns or antonyms match the vertex being removed
    void removeVertex(Vertex old)
    {
    	String word = old.getWord();
    	nodesMap.remove(word);
    	Iterator<Entry<String,Vertex>> all = nodesMap.entrySet().iterator();
    	while(all.hasNext())
    	{
    		Vertex v = all.next().getValue();
    		for(Vertex a : v.getAntonyms())
			 {
				 if(a.getWord().equals(word)) nodesMap.remove(a);
			 }

			 for(Vertex s : v.getSynomyns())
			 {
				 if(s.getWord().equals(word)) nodesMap.remove(s);
			 }
			 for(Vertex g : v.getGroupings())
			 {
				 if(g.getWord().equals(word)) nodesMap.remove(g);
			 }
    	}
    }

    
}
