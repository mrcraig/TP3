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
	}
	
	public LinkedList<Vertex> getVertices()
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
			
			word = n.getFirstChild().getTextContent();
			System.out.println(word);
			//v.setWord(word);
			//vertices.add(v);
		}
		return vertices;
	}
	
	
	
}


