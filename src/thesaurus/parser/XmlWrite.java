package thesaurus.parser;


import java.io.File;


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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlWrite {
	
	private Document xml;
	private String path;
	private HashGraph nodes;
	
	public XmlWrite (File f, HashGraph nodes2)
	{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try 
		{
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			path = f.getPath();
			this.nodes = nodes2;
			this.xml = docBuilder.parse(path);
			checkNew();
		} 
		catch (Exception e) 
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
		this.addNode(v.getWord(), v.getID());
		String source = v.getID();
		String target = null;
		for(Vertex j : v.getSynomyns())
		{
			if(j==null) continue;
			target = j.getID();
			System.out.println("adding synonym");
			this.addEdge(source, target, "s");
		}
		for(Vertex i : v.getAntonyms())
		{
			if(i==null) continue;
			target = i.getID();
			this.addEdge(source, target, "a");
		}
		for(Vertex h: v.getGroupings())
		{
			if(h==null) continue;
			target = h.getID();
			this.addEdge(source, target, "g");
		}
		saveFile();
	}
	
	
 void editVertex(String oldWord, String newWord)
	{
		String id = nodes.getVertexFromWord(oldWord).getID();
		NodeList allNodes = this.xml.getElementsByTagName("node");
		for(int i=0;i<allNodes.getLength();i++)
		{
			Node cursor = allNodes.item(i);
			if (cursor.getAttributes().getNamedItem("id").getTextContent().equalsIgnoreCase(id))
			{
				cursor.getChildNodes().item(1).setTextContent(newWord);
			}
		}
		saveFile();
	}
	

 	// s for synonym, a for antonym
   void addEdge(String source, String target, String type)
	{
		Element edge = this.xml.createElement("edge");
		Element data = this.xml.createElement("data");
		edge.setAttribute("source",source);
		edge.setAttribute("target", target);
		data.setAttribute("key", "s");
		data.setTextContent(type);
		edge.appendChild(data);
		this.xml.getElementsByTagName("graph").item(0).appendChild(edge);
	}
	
	void addNode(String word, String ID)
	{
		System.out.println("adding Node..."+word+" "+ID);
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
	
	public void removeVertex(String w)
	{
		Vertex v = nodes.getVertexFromWord(w);
		removeNode(v.getID());
		removeEdge(v.getID());
		nodes.removeVertex(v);
		saveFile();
	}
	
	private void removeNode(String id)
	{
		NodeList allNodes = xml.getElementsByTagName("node");
		Node graph = xml.getElementsByTagName("graph").item(0);
		for(int i=0;i<allNodes.getLength();i++)
		{
			Node cursor = allNodes.item(i);
			if(cursor.getAttributes().getNamedItem("id").getTextContent().equals(id))
			{
				System.out.println("removing node");
				System.out.println(graph.removeChild(cursor).getTextContent());
			}
		}
	}
	
	private void removeEdge(String id)
	{
		NodeList allEdges = xml.getElementsByTagName("edge");
		Node graph = xml.getElementsByTagName("graph").item(0);
		//iterate backwards to avoid order being affected
		for(int i=allEdges.getLength()-1;i>-1;i--)
		{
			Node edge = allEdges.item(i);
			String source = edge.getAttributes().getNamedItem("source").getTextContent();
			String target = edge.getAttributes().getNamedItem("target").getTextContent();
			System.out.println(source);
			System.out.println(target);
			if(source.equalsIgnoreCase(id) || target.equalsIgnoreCase(id))
			{
				System.out.println("removing edge");
				graph.removeChild(edge);
			}
		}
	}
	
	private void saveFile()
	{
		
		TransformerFactory transformer = TransformerFactory.newInstance();
		Transformer trans = null;
		try 
		{
		trans = transformer.newTransformer();
		}
		catch (TransformerConfigurationException e)
		{
			e.printStackTrace();
		}
		DOMSource source = new DOMSource(this.xml);
		StreamResult result = new StreamResult(new File(this.path));
		try 
		{
			trans.transform(source, result);
		} 
		catch (TransformerException e) 
		{
				e.printStackTrace();
		}
	}
}