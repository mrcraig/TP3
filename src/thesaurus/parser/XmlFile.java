package thesaurus.parser;


import java.io.File;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.*;
public class XmlFile {
	
	private Document xml;
	private String path;
	private XPathFactory xfactory = XPathFactory.newInstance();
	private XPath xpath = xfactory.newXPath();
	
	public XmlFile (String path)
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
	
	public String toString()
	{
		NodeList n = this.xml.getElementsByTagName("data");
		
		for(int i=0;i<n.getLength();i++)
		{
			System.out.println(n.item(i).getTextContent());
			
		}
		return null;
	}
	
	/*
	 * for adding, ndoe needs word and id.
	 * id can get from number of nodes, and increment
	 * go to last child
	 * 
	 * 
	 * add node, add edge method
	 * add vertex method
	 */
	
	
	
	
	public void addVertex(Vertex v)
	{	
		this.addNode(v.word, v.getIndex());
		String source = Integer.toString(v.getIndex());
		String target = null;
		for(Vertex j : v.getAdjList())
		{
			target = Integer.toString(j.getIndex());
			this.addEdge(source, target);
		}
		saveFile();
	}
	
	
	
   private void addEdge(String source, String target)
	{
		//work on ordering later...
		Node lastEdge = null;
		try
		{
			XPathExpression expr = xpath.compile("//graphml/graph/edge");
			lastEdge = (Node) expr.evaluate(this.xml, XPathConstants.NODE);
		}
		catch(XPathExpressionException e) {e.printStackTrace();}
		
		Element edge = this.xml.createElement("edge");
		edge.setAttribute("source",source);
		edge.setAttribute("target", target);
		//try and keep order smooth
		if(lastEdge!=null)lastEdge.appendChild(edge);
		else
			this.xml.getElementsByTagName("graph").item(0).appendChild(edge);
	}
	
	
	public void cleanXml() {}
	
	
	private void addNode(String word, int index)
	{
		String newID = null;
		
		try {
			XPathExpression expr = xpath.compile("//graphml/graph/node[position()=last()]");
		Node result = (Node) expr.evaluate(this.xml,XPathConstants.NODE);
		//String id = result.getAttributes().getNamedItem("id").getTextContent();
		//int tempID = Integer.parseInt(id);
		//tempID++;
		//newID = Integer.toString(tempID);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	
	    newID = Integer.toString(index);
		Element node = this.xml.createElement("node");
		Element data = this.xml.createElement("data");
		node.setAttribute("id", newID);
		data.setAttribute("key","w");
		data.setTextContent(word);
		node.appendChild(data);
		this.xml.getElementsByTagName("graph").item(0).appendChild(node);
		
	}
	
	
	
	private void saveFile()
	{
		
		TransformerFactory transformer = TransformerFactory.newInstance();
		Transformer trans = null;
		try {
		trans = transformer.newTransformer();
		} catch (TransformerConfigurationException e)
		{
			e.printStackTrace();
		}
		DOMSource source = new DOMSource(this.xml);
		StreamResult result = new StreamResult(new File(this.path));
		try 
		{
			trans.transform(source, result);
			//System.out.println("file saved to "+this.path);
		} catch (TransformerException e) 
		{
				e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
