package thesaurus.parser;





/*
 * getAll, getRange return a list of vertices
 * getTableData returns a dictionary of strings of the vertices
 * currently in use
 */


public class Driver 
{
	public static void main(String args[]){
		
		DOMParser read = new DOMParser("/resourcePackage/data.xml");
		XmlFile add = new XmlFile("/resourcePackage/data.xml");
		//Vertex test = driver.getSynmsForOne("Happy");
		Vertex one = new Vertex("101");
		Vertex two = new Vertex("102");
		Vertex three = new Vertex("103");
		System.out.println(read.getAllNodes());
		
		/*
		one.setWord("hot");
		two.setWord("cold");
		three.setWord("warm");
		
		one.addToAdjList(two);
		one.addToAdjList(three);
		
		two.addToAdjList(one);
	
		add.addVertex(one);
		add.addVertex(two);
		add.addVertex(three);
		
		*/
		//System.out.println(driver.getSynmsForOne("Happy"));
		//System.out.println(driver.getTableData());
			
			
		}
}
