package thesaurus.parser;





/*
 * need to parse vertices first before edge
 */


public class Driver 
{
	public static void main(String args[]){
		
		DOMParser read = new DOMParser("/resourcePackage/testAgain.xml");
		//Parser read = new Parser("/resourcePackage/data.xml");
		XmlWrite add = new XmlWrite("/resourcePackage/testAgain.xml");
		
		//System.out.println(read.getSynmsForOne("Happy"));
		
		
		/*
		Vertex blue = new Vertex("0");
		Vertex red = new Vertex("1");
		Vertex green = new Vertex("2");
		
		blue.setWord("blue");
		red.setWord("red");
		green.setWord("green");
		
		blue.addToAdjList(blue);
		red.addToAdjList(blue); red.addToAdjList(green);
		green.addToAdjList(green); green.addToAdjList(blue); green.addToAdjList(red);
		
		add.addVertex(blue);
		add.addVertex(red);
		add.addVertex(green);  */
		
		
		//need to get all vertices before edges?
		System.out.println(read.getAllNodes());	
			
		}
}
