package thesaurus.parser;





/*
 * getAll, getRange return a list of vertices
 * getTableData returns a dictionary of strings of the vertices
 * currently in use
 */


public class Driver 
{
	public static void main(String args[]){
		
		Parser driver = new Parser("/resourcePackage/data.xml");
		driver.getAllNodes();
		System.out.println();
		System.out.println(driver.getTableData());
			
			
		}
}
