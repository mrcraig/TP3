package thesaurus.parser;

import java.io.File;


public class Driver 
{

	public static void main(String[] args)
	{
		File test = new File("/users/level3/0805089k/data.graphml");
		System.out.println(test.getAbsolutePath());
		InternalRepresentation driver = new InternalRepresentation(test);
		
	
		driver.addAntonym("Cheerful", "Jubilant");
		driver.addSynonym("Cheerful","Merry");
		
		//String[] antonym = new String[3];
		//antonym[0] = "happy";
		//antonym[1] = "content";
		//antonym[2] = "joyful";
		
		//driver.addVertex("mathew","Peaceful, Merry", "happy, content");
		
		//driver.removeVertex("hate");
		
		
		//driver.addVertex("helen", "thumb, happy, supervisor", "fun, peaceful");
		
		//driver.removeVertex("Merry");
		
		
		
		
		driver.debug();
		
		//driver.editVertex("Joyful", "sad");
		//System.out.println(driver.getListOfSynomyns("Jubilant"));
		//System.out.println(driver.getTableData());
		
	}
}
