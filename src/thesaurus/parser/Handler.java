package thesaurus.parser;

import java.util.LinkedList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class Handler extends DefaultHandler
{
	
	int edges=0;
	boolean getWord=false;
	Graph nodes = new Graph();
	
	public Handler(){}
	
	public void startElement(String uri, String localname, String qname, Attributes attributes) throws SAXException
	{
		if(qname.equalsIgnoreCase("node"))
			{		
				for(int i=0;i<attributes.getLength();i++)
				{
					if(attributes.getQName(i).equalsIgnoreCase("id"))
					{
						String id = attributes.getValue(i);
						 Vertex v = new Vertex(id);
						 nodes.add(v);			 
					}
				}
			}
		
		//need the nodes check to stop it trying to add to vertexs that don't exist
		if(qname.equalsIgnoreCase("edge"))
		{
			int source = 0;
			int target = 0;
			for(int i=0;i<attributes.getLength();i++)
			{
				if(attributes.getQName(i).equalsIgnoreCase("source"))
				{
					source = Integer.parseInt(attributes.getValue(i));
				}
				if(attributes.getQName(i).equalsIgnoreCase("target"))
				{
					target = Integer.parseInt(attributes.getValue(i));
				}
			}
			//check to see vertexs exist
			if (nodes.getVertex(source)!=null && nodes.getVertex(target)!=null)
			{
				Vertex v = nodes.getVertex(source);
				v.addToAdjList(target);
			}
		}
		if(qname.equalsIgnoreCase("data")) getWord = true;
	}
	
	public void endElement(String uri, String localName, String qname) throws SAXException{
		//System.out.println("leaving"+qname);
	}
	
	 LinkedList<Vertex> getNodes()
	{
		return nodes.getNodes();
	}
	


	
	public void characters(char ch[], int start, int length)
	{
		if(getWord)
		{
			String word = new String(ch,start,length);
			nodes.setLastWord(word);
			getWord=false;
		}
	}
}
