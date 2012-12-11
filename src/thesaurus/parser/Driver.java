package thesaurus.parser;





/*
 * getAll, getRange return a list of vertices
 * getTableData returns a dictionary of strings of the vertices
 * currently in use
 */


public class Driver 
{
	public static void main(String args[]){
		
		DOMParser driver = new DOMParser("/resourcePackage/data.xml");
		driver.getVertices();
		//XmlFile x = new XmlFile("/resourcePackage/data.xml");
		//Vertex test = driver.getSynmsForOne("Happy");
		//x.addVertex(test);
	
		//System.out.println(driver.getSynmsForOne("Happy"));
		//System.out.println(driver.getTableData());
			
			
		}
}
