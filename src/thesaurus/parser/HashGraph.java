package thesaurus.parser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

//use string as index

//use word first then maybe index
public class HashGraph {
	private HashMap<String, Vertex> nodesMap;

	public HashGraph() {
		nodesMap = new HashMap<String, Vertex>();

	}

	void add(Vertex v) {
		nodesMap.put(v.getWord(), v);
	}

	int size() {
		return nodesMap.size();
	}

	Vertex getVertexFromIndex(String index) {
		Iterator<Entry<String, Vertex>> all = nodesMap.entrySet().iterator();
		while (all.hasNext()) {
			Vertex v = all.next().getValue();
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
		return nodesMap.get(word);
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
		Iterator<Entry<String, Vertex>> all = nodesMap.entrySet().iterator();
		LinkedList<RemoveEntry> verticesRemove = new LinkedList<RemoveEntry>();

		while (all.hasNext()) {
			Vertex v = all.next().getValue();
			System.out.println("currently checking " + v.getWord());
			Iterator<Vertex> antonyms = v.getAntonyms().iterator();
			RemoveEntry r = new RemoveEntry();
			r.v = v;
			while (antonyms.hasNext()) {
				Vertex a = antonyms.next();
				if (a.getWord().equals(word)) {
					System.out.println("removing antonym " + word);
					r.la.add(a);
					// nodesMap.remove(a.getWord());
				}
			}

			Iterator<Vertex> synonyms = v.getSynomyns().iterator();
			while (synonyms.hasNext()) {
				Vertex s = synonyms.next();
				if (s.getWord().equals(word)) {

					System.out.println("removing synonym " + word);
					r.ls.add(s);
					// v.removeSynonym(s);
					// nodesMap.remove(s.getWord());
				}
			}

			Iterator<Vertex> groupings = v.getGroupings().iterator();
			while (groupings.hasNext()) {
				Vertex g = groupings.next();
				if (g.getWord().equals(word)) {
					System.out.println("removing grouping " + word);
					// v.removeGrouping(g);
					r.lg.add(g);
					// nodesMap.remove(g.getWord());
				}
			}
			
			verticesRemove.add(r);
			
		}
		
		removeVertices(verticesRemove);
		
	}
	
	void removeVertices(LinkedList<RemoveEntry> entries)
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
