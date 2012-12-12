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
public class XmlWrite {
	
	private Document xml;
	private String path;
	
	public XmlWrite (String path)
	{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try 
		{
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			this.path = getClass().getResource(path).getPath();
			this.xml = docBuilder.parse(getClass().getResource(path).getPath());
			checkNew();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void print()
	{
		NodeList n = this.xml.getElementsByTagName("data");
		for(int i=0;i<n.getLength();i++)
		{
			System.out.println(n.item(i).getTextContent());
			
		}
	}

	public void addVertex(Vertex v)
	{	
		this.addNode(v.word, v.getIndex());
		String source = v.getIndex();
		String target = null;
		for(Vertex j : v.getAdjList())
		{
			target = j.getIndex();
			this.addEdge(source, target);
		}
		saveFile();
	}
	
	
   private void addEdge(String source, String target)
	{
		Element edge = this.xml.createElement("edge");
		edge.setAttribute("source",source);
		edge.setAttribute("target", target);
		this.xml.getElementsByTagName("graph").item(0).appendChild(edge);
	}
	
	public void cleanXml() {}
	
	private void addNode(String word, String ID)
	{
		Element node = this.xml.createElement("node");
		Element data = this.xml.createElement("data");
		node.setAttribute("id", ID);
		data.setAttribute("key","w");
		data.setTextContent(word);
		node.appendChild(data);
		this.xml.getElementsByTagName("graph").item(0).appendChild(node);
	}

	private void checkNew()
	{
		if(this.xml.getElementsByTagName("graphml").getLength()==0)
		{
			Element node = this.xml.createElement("graphml");
			this.xml.appendChild(node);
		}
		if(this.xml.getElementsByTagName("graph").getLength()==0)
		{
			Element node = this.xml.createElement("graph");
			xml.getElementsByTagName("graphml").item(0).appendChild(node);
		}
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
		} catch (TransformerException e) 
		{
				e.printStackTrace();
		}
	}
	


}
