package thesaurus.parser;

import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMParser
{
	private Document xml;
	private String path;
	private XPathFactory xfactory = XPathFactory.newInstance();
	private XPath xpath = xfactory.newXPath();
	private Graph nodes = new Graph();
	
	public DOMParser(String path)
	{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try 
		{
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			this.path = getClass().getResource(path).getPath();
			this.xml = docBuilder.parse(getClass().getResource(path).getPath());
		
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		if(!checkEmpty())
		{
			getVertices();
			getEdges();
		}
		
	}
	
	
	private boolean checkEmpty()
	{
		try 
		{
			XPathExpression ml = xpath.compile("//graphml");
			XPathExpression graph = xpath.compile("//graphml/graph");
			XPathExpression nodes = xpath.compile("//graphml/graph/nodes");
			
		  NodeList listML  = (NodeList) ml.evaluate(this.xml, XPathConstants.NODESET);
		  NodeList  listGraph = (NodeList) graph.evaluate(this.xml, XPathConstants.NODESET);
		  NodeList  listNodes = (NodeList) nodes.evaluate(this.xml, XPathConstants.NODESET);
		
		  System.out.println(listNodes);
		  if(listML==null || listGraph==null || listNodes==null ) return true;
		}
		catch (XPathExpressionException e) 
		{
			e.printStackTrace();
		}
	 return false;
	}
	
	public LinkedList<Vertex> getAllNodes()
	{
		return nodes.getNodes();
	}
	
	
	private void getVertices()
	{
		NodeList nodes = null;
		LinkedList<Vertex> vertices = new LinkedList<Vertex>();
		try {
			XPathExpression expr = xpath.compile("//graphml/graph/node");
		    nodes = (NodeList) expr.evaluate(this.xml, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		//its looping through them all
		Node n = null;
		String id=null;
		String word=null;
		Vertex v=null;
		for(int i=0;i<nodes.getLength();i++)
		{
			n = nodes.item(i);
			id = n.getAttributes().getNamedItem("id").getTextContent();
			v = new Vertex(id);
			for(int j=0;j<n.getChildNodes().getLength();j++)
			{
				Node d = n.getChildNodes().item(j);
				if(d.getNodeName().equals("data"))
				{
					word = d.getTextContent();
					v.setWord(word);
				}
			}
			if(!this.nodes.contains(v)) vertices.add(v);
		}
		this.nodes.setNodes(vertices);
	}
	
	
	private void getEdges()
	{
		NodeList edges = null;
		try 
		{
			XPathExpression expr = xpath.compile("//graphml/graph/edge");
		    edges = (NodeList) expr.evaluate(this.xml, XPathConstants.NODESET);
		} catch (XPathExpressionException e) 
		{
			e.printStackTrace();
		}
		
		for(int i=0;i<edges.getLength();i++)
		{
			Node e = edges.item(i);
			String source = e.getAttributes().getNamedItem("source").getTextContent();
			String target = e.getAttributes().getNamedItem("target").getTextContent();
			Vertex v = nodes.getVertexFromIndex(source);
			
			//System.out.println("edge: "+v.getWord());
			v.addToAdjList(nodes.getVertexFromIndex(target));
		}
	}
	
	
	
}


