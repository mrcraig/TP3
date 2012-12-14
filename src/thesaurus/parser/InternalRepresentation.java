package thesaurus.parser;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

public class InternalRepresentation
{
	private Graph nodes = new Graph();
	private XmlRead read;
	private XmlWrite write;

	
	public InternalRepresentation(File f)
	{
		read = new XmlRead(f);
		nodes.setNodes(read.getAllNodes());
		write = new XmlWrite(f, nodes);
		
	}
	
	public void addVertex(String w, String[] synomyns)
	{
		String index = read.getLastVertexIndex();
		Vertex n = new Vertex(index);
		n.setWord(w);
		for(String s : synomyns)
		{
			Vertex syn = nodes.getVertexFromWord(s);
			n.addToAdjList(syn);
		}
		System.out.println(n.getWord());
		System.out.println("Vertex to be added" + n);
		write.addVertex(n);
	}
	
	/* facade pattern */
	public LinkedList<Vertex> getSynmsFor(String s, int max)
	{
		return nodes.getSynmsFor(s, max);
	}
	
	public Vertex getSynmsForOne(String s)
	{
		if(nodes.getSynmsFor(s,1)!=null) return nodes.getSynmsFor(s, 1).getFirst();
		return null;
	}
 	
	public LinkedList<Vertex> getSynmsFor(String s){return getSynmsFor(s, 100);}
	
	public HashMap<String, LinkedList<String>> getTableData()
	{
		return nodes.getTableData();
	}
	
}
