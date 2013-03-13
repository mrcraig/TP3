package thesaurus.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;


public class HashGraph {
	private HashMap<String, Vertex> nodesMap;

	public HashGraph() {
		nodesMap = new HashMap<String, Vertex>();

	}

	void add(Vertex v) {
		nodesMap.put(v.getWord().toLowerCase().trim(), v);
	}

	int size() {
		return nodesMap.size();
	}

	Vertex getVertexFromIndex(String index) {
		for(Entry<String, Vertex> vv: nodesMap.entrySet()){
			Vertex v = vv.getValue();
			if (v.getID().equals(index)) {
				return v;
			}
		}
		System.out.println("null");
		return null;
	}

	void setNodes(HashMap<String, Vertex> nodes) {
		this.nodesMap = nodes;
	}

	Vertex getVertexFromWord(String word) {
		return nodesMap.get(word.trim().toLowerCase());
	}

	Vertex getRandVertex() {
		return nodesMap.entrySet().iterator().next().getValue();

	}

	boolean contains(Vertex v) {
		return nodesMap.containsValue(v);
	}

	HashMap<String, Vertex> getNodes() {
		return nodesMap;
	}

	void removeVertex(Vertex old) {
		String word = old.getWord();
		System.out.println("word to be deleted " + word);
		nodesMap.remove(word);

		ArrayList<RemoveEntry> verticesRemove = new ArrayList<RemoveEntry>();
		for(Entry<String, Vertex> all: nodesMap.entrySet()){
			Vertex v = all.getValue();
			System.out.println("currently checking " + v.getWord());
			RemoveEntry r = new RemoveEntry();
			r.v = v;
			for(Vertex a:v.getAntonyms()){
				if (a.getWord().equals(word)) {
					System.out.println("removing antonym " + word);
					r.la.add(a);
				}
			}

			for(Vertex s:v.getSynomyns()){
				if (s.getWord().equals(word)) {

					System.out.println("removing synonym " + word);
					r.ls.add(s);
				}
			}
			for(Vertex g: v.getGroupings()){
				if (g.getWord().equals(word)) {
					System.out.println("removing grouping " + word);
					r.lg.add(g);
				}
			}
			
			verticesRemove.add(r);
			
		}
		
		removeVertices(verticesRemove);
		
	}
	
	void removeVertices(ArrayList<RemoveEntry> entries)
	{
		for(RemoveEntry e : entries)
		{
			for(Vertex va: e.la){
				e.v.removeAntonym(va);
			}
			
			for(Vertex vs: e.ls){
				e.v.removeSynonym(vs);
			}
			
			for(Vertex vg: e.lg){
				e.v.removeGrouping(vg);
			}
			
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
