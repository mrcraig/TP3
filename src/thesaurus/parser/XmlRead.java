package thesaurus.parser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlRead
{
	private Document xml;
	private XPathFactory xfactory = XPathFactory.newInstance();
	private XPath xpath = xfactory.newXPath();
	private HashGraph nodes = new HashGraph();
	boolean emptyFile = false;
	private int lastIndex;
	
	public XmlRead(File f)
	{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try 
		{
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			this.xml = docBuilder.parse(f.getPath());
		} catch (IOException e) 
		{
			System.out.println("bad file");
		}
		catch(SAXException s)
		{
			System.out.println("Invalid file");
		}
		catch(IllegalArgumentException a)
		{
			System.out.println("illegal Argument");
		}
		catch(ParserConfigurationException p)
		{
			p.printStackTrace();
		}
		lastIndex = Integer.parseInt(getLastVertexIndex());
		getVertices();
		getEdges();
	}
	
	//boolean checkEmpty()
	//{
	//	XPathExpression expr = xpath.compile("//graphml)
	//}
	
	String getLastVertexIndex() 
	{
		String newID = null;
		try
		{
			XPathExpression expr = xpath.compile("//graphml/graph/node[position()=last()]");
			Node result = (Node) expr.evaluate(this.xml,XPathConstants.NODE);
			String id = result.getAttributes().getNamedItem("id").getTextContent();
			int tempID = Integer.parseInt(id);
			tempID++;
			newID = Integer.toString(tempID);
		} 
		catch (XPathExpressionException e)
		{
			e.printStackTrace();
		}
		//empty file.
		catch (NullPointerException n)
		{
			this.emptyFile = true;
			return "0";
		}
		return newID;
	}
	
	String getIndex()
	{
		return Integer.toString(this.lastIndex++);
	}
	
	
	public HashMap<String,Vertex> getAllNodes()
	{
		return nodes.getNodes();
	}
	
	
	private void getVertices()
	{
		NodeList nodes = null;
		//ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		HashMap<String,Vertex> vertices = new HashMap<String,Vertex>();
		
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
			if(!this.nodes.contains(v))
				{
				vertices.put(v.getWord(), v);
				}
		}
		//vertices should be added
		this.nodes.setNodes(vertices);
	}	
	
	
	private void getEdges()
	{
		NodeList edges = null;
		char type = 0;
		try 
		{
			XPathExpression expr = xpath.compile("//graphml/graph/edge");
		    edges = (NodeList) expr.evaluate(this.xml, XPathConstants.NODESET);
		} 
		catch (XPathExpressionException e) 
		{
			e.printStackTrace();
		}
		
		for(int i=0;i<edges.getLength();i++)
		{
			Node e = edges.item(i);
			//loop in case e has several child nodes
			for(int j=0;j<e.getChildNodes().getLength();j++)
			{
				Node d = e.getChildNodes().item(j);
				if(d==null)
				{
					System.out.println("skipping dodgy edge");
					continue;
				}
				if(d.getNodeName().equals("data"))
						{
							if(d.getTextContent().equals("s"))
							{
								type = 's';
							}
							else if(d.getTextContent().equals("a"))
							{
								type = 'a';
							}
							else if(d.getTextContent().equals("g"))
							{
								type = 'g';
							}
						}
			}
			String source = e.getAttributes().getNamedItem("source").getTextContent();
			String target = e.getAttributes().getNamedItem("target").getTextContent();
			Vertex s = nodes.getVertexFromIndex(source);
			Vertex t = nodes.getVertexFromIndex(target); 
			//vertex with 5 is null
			switch(type)
			{
				case 's': if(!s.getSynomyns().contains(t)) s.addSynonym(t);
					  	  break;				
			
				case 'a': if(!s.getAntonyms().contains(t)) s.addAntonym(t);
				          break;
				          
				case 'g': if(!s.getGroupings().contains(t)) s.addGrouping(t);
			}
		}
	}	
}

