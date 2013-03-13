package thesaurus.parser;

import java.util.ArrayList;
import java.util.HashMap;


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
	private ArrayList<Vertex> nodes = new ArrayList<Vertex>();

	 ArrayList<Vertex> getNodes() 
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

	void setNodes(ArrayList<Vertex> nodes)
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
		nodes.get(nodes.size()-1).setWord(word);
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
	    ArrayList<Vertex> getListOfSynomyns(String s, int max)
		{
			ArrayList<Vertex> workQueue = new ArrayList<Vertex>();
			ArrayList<Vertex> results = new ArrayList<Vertex>();
			Vertex start = this.getVertexFromWord(s);
			if(start==null) return null;
			workQueue.add(start);
			while(!workQueue.isEmpty() && results.size()<max)
			{
				Vertex current = workQueue.remove(0);
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
	 
	 public HashMap<String,HashMap<String, ArrayList<String>>> getTableData()
		{	
		 HashMap<String, HashMap<String, ArrayList<String>>> tableData = new HashMap<String, HashMap<String, ArrayList<String>>>();
		 	for(Vertex v : this.getNodes())
		 	{
		 		HashMap<String,ArrayList<String>> words = new HashMap<String, ArrayList<String>>();	
		 		ArrayList<String> syns = new ArrayList<String>();
		 		ArrayList<String> ants = new ArrayList<String>();
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