package thesaurus.parser;

import java.util.HashMap;
import java.util.LinkedList;


/*
 * one vertex at a time
 * hashmap of string - vertex
 * 
 * getNodes() - returns entries
 * 
 * clear() - same
 * 
 * craig doesnt touch the word in vertex - can use value set or similar
 */

public class Graph 
{
	private LinkedList<Vertex> nodes = new LinkedList<Vertex>();

	 LinkedList<Vertex> getNodes() 
	{
		return nodes;
	}
	 
	 public void debug()
	 {
	
		 for(Vertex v : nodes)
		 {
			 System.out.println(v);
		 }
	 }
	 
	 //should probably iterate backwards? not helping.
	 void removeVertex(Vertex v)
	 {
		 String w = v.getWord();
		 for(int i=nodes.size()-1;i>-1;i--)
		 {
			 Vertex x = nodes.get(i);
			 for(Vertex a : x.getAntonyms())
			 {
				 if(a.getWord().equals(w)) x.getAntonyms().remove(a);
			 }
			 
			 for(Vertex s : x.getSynomyns())
			 {
				 if(s.getWord().equals(w)) x.getSynomyns().remove(s);
			 }
			 if(x.getWord().equals(w)) 
			 {
				 System.out.println("removed");
				 nodes.remove(i);
			 }
		 }
		 //System.out.println("current nodes are\n"+nodes);
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
		nodes.getLast().setWord(word);
	}
	
	boolean contains(Vertex v)
	{
		for(Vertex x : nodes)
		{
			if(v.getID().equals(x.getID()))
			{
				return true;
			}
		}
		return false;
	}
	
	 Vertex getVertexFromIndex(String index)
	{
		for(Vertex v : nodes)
		{
			if(v.getID().equals(index)) return v;
		}
		return null;
	}
	
	
	 Vertex getVertexFromWord(String word)
	{
		 //System.out.println("word looking for "+word);
		for(Vertex v : nodes)
		{
			System.out.println(v.getWord());
			if(v.getWord().equalsIgnoreCase(word)) return v;
		}
		return null;
	}
	 
	 
	 /* Breadth First Search*/
	    LinkedList<Vertex> getListOfSynomyns(String s, int max)
		{
			LinkedList<Vertex> workQueue = new LinkedList<Vertex>();
			LinkedList<Vertex> results = new LinkedList<Vertex>();
			Vertex start = this.getVertexFromWord(s);
			if(start==null) return null;
			workQueue.add(start);
			while(!workQueue.isEmpty() && results.size()<max)
			{
				Vertex current = workQueue.remove();
				results.add(current);
				for(Vertex child : current.getSynomyns())
				{
					if(!workQueue.contains(child) && !results.contains(child))
					{
						workQueue.add(child);
					}
				}
			}
			return results;
		}
	 
	 public HashMap<String,HashMap<String, LinkedList<String>>> getTableData()
		{	
		 HashMap<String, HashMap<String, LinkedList<String>>> tableData = new HashMap<String, HashMap<String, LinkedList<String>>>();
		 	for(Vertex v : this.getNodes())
		 	{
		 		HashMap<String,LinkedList<String>> words = new HashMap<String, LinkedList<String>>();	
		 		LinkedList<String> syns = new LinkedList<String>();
		 		LinkedList<String> ants = new LinkedList<String>();
 		 		for(Vertex s : v.getSynomyns())
		 		{
 		 			if (s==null) continue;
		 			syns.add(s.getWord());
		 		}
		 		words.put("synomyns", syns);
		 		for(Vertex a : v.getAntonyms())
		 		{
		 			if(a==null) continue;
		 			ants.add(a.getWord());
		 		}
		 		words.put("antonyms", ants);
		 	tableData.put(v.getWord(), words);
		 	}
			return tableData;
		}	
	
}