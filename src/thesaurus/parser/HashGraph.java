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

    //
    //remove key and value
    void removeVertex(Vertex old)
    {
    	String word = old.getWord();
    	System.out.println("word to be deleted "+word);
    	nodesMap.remove(word);
    	Iterator<Entry<String,Vertex>> all = nodesMap.entrySet().iterator();
    	System.out.println("nodes at start " + nodesMap);
    	while(all.hasNext())
    	{
    		Vertex v = all.next().getValue();
    		System.out.println("currently checking "+v.getWord());
    		Iterator<Vertex> antonyms = v.getAntonyms().iterator();
    		while(antonyms.hasNext())
			 {
    			 Vertex a = antonyms.next();
				 if(a.getWord().equals(word))
					 {
					 	System.out.println("removing antonym "+word);
					 	//v.removeAntonym(a);
					 	nodesMap.remove(a.getWord());
					 }
			 }

    		Iterator<Vertex> synonyms = v.getSynomyns().iterator();
			 while(synonyms.hasNext())
			 {
				 Vertex s = synonyms.next();
				 if(s.getWord().equals(word))
					 {
					
					 System.out.println("removing synonym "+word);
					 	//v.removeSynonym(s);
					 	nodesMap.remove(s.getWord());
					 }
			 }
			 
			 Iterator<Vertex> groupings = v.getGroupings().iterator();
			 while(groupings.hasNext())
			 {
				 Vertex g = groupings.next();
				 if(g.getWord().equals(word))
				 {
					 System.out.println("removing grouping "+word);
					 //v.removeGrouping(g);
					 nodesMap.remove(g.getWord());
				 }
			 }
    	}
    	System.out.println("nodes at end " + nodesMap);
    }

    
}
