package thesaurus.parser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;


//use string as index
public class HashGraph
{
	private HashMap<String,Vertex> nodesMap;
	private Set<Entry<String, Vertex>> nodesSet;



	public HashGraph()
	{
		nodesMap = new HashMap<String, Vertex>();
		nodesSet = nodesMap.entrySet();
	}

	void addVertex(Vertex v)
	{
		nodesMap.put(v.getWord(), v);
	}

	int size()
	{
		return nodesMap.size();
	}

	Vertex getRandVertex()
	{

		Vertex[] arrayNodes = (Vertex[]) nodesSet.toArray();
		return arrayNodes[0];
	}

	boolean contains(Vertex v) {return nodesSet.contains(v);}


    HashMap<String,Vertex> getNodes()
	{
		return nodesMap;
	}

    //go through all vertices, and check to see
    //if synomyns or antonyms match the vertex being removed
    void removeVertex(String word)
    {
    	nodesMap.remove(word);
    	Iterator<Entry<String,Vertex>> all = nodesSet.iterator();
    	while(all.hasNext())
    	{
    		Vertex v = all.next().getValue();
    		for(Vertex a : v.getAntonyms())
			 {
				 if(a.getWord().equals(word)) nodesSet.remove(a);
			 }

			 for(Vertex s : v.getSynomyns())
			 {
				 if(s.getWord().equals(word)) nodesSet.remove(s);
			 }
			 for(Vertex g : v.getGroupings())
			 {
				 if(g.getWord().equals(word)) nodesSet.remove(g);
			 }
    	}
    }

    
}
