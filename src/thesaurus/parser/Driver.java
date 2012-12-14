package thesaurus.parser;

import java.io.File;
import java.util.LinkedList;

public class Driver 
{

	public static void main(String[] args)
	{
		File test = new File("/home/james/GIT/myWorkspace/TP3/data.xml");
		System.out.println(test.getAbsolutePath());
		XmlRead read = new XmlRead(test);
		XmlWrite write = new XmlWrite(test);
		System.out.println(read.getSynmsForOne("content"));
		
		
		Vertex v = new Vertex("8");
		v.setWord("Content");
		
		Graph nodes = new Graph();
		nodes.setNodes(read.getAllNodes());
		Vertex p = nodes.getVertexFromWord("Peaceful");
		Vertex h = nodes.getVertexFromWord("happy");
		v.addToAdjList(p);
		v.addToAdjList(h);
		
		//write.addVertex(v);
		
		
		
		
	}
}
