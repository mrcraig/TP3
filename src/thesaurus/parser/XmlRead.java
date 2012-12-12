package thesaurus.parser;

import java.io.File;
import java.util.HashMap;
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

public class XmlRead
{
	private Document xml;
	private XPathFactory xfactory = XPathFactory.newInstance();
	private XPath xpath = xfactory.newXPath();
	private Graph nodes = new Graph();
	
	public XmlRead(File f)
	{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try 
		{
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			//this.xml = docBuilder.parse("/home/james/GIT/myWorkspace/TP3/data.xml");
			this.xml = docBuilder.parse(getClass().getResource(f.getPath()).getPath());
		
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
		try
		{
			XPathExpression expr = xpath.compile("//graphml/graph/node");
		    nodes = (NodeList) expr.evaluate(this.xml, XPathConstants.NODESET);
		} catch (XPathExpressionException e)
		{
			e.printStackTrace();
		}
		
		for(int i=0;i<nodes.getLength();i++)
		{
			Node n = nodes.item(i);
			String id = n.getAttributes().getNamedItem("id").getTextContent();
			Vertex v = new Vertex(id);
			for(int j=0;j<n.getChildNodes().getLength();j++)
			{
				Node d = n.getChildNodes().item(j);
				if(d.getNodeName().equals("data"))
				{
					String word = d.getTextContent();
					v.setWord(word);
				}
			}
			if(!this.nodes.contains(v)) vertices.add(v);
		}
		this.nodes.setNodes(vertices);
	}	
	
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
			v.addToAdjList(nodes.getVertexFromIndex(target));
		}
	}
	
	
	
}


