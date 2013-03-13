package thesaurus.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;


public class Driver 
{

	public static void main(String[] args)
	{
		File test = new File("/users/level3/0805089k/tempwork/tempgit/testData.xml");
		System.out.println(test.getAbsolutePath());
		InternalRepresentation driver = new InternalRepresentation(test);
		
		
		ArrayList<String> array = driver.parseCsvToList("");
		ArrayList<String> list = driver.parseCsvToList("");
	
		
		for(String s : array)
		{
			System.out.printf("array syn: %s\n",s);
		}
		
		for(String s : list)
		{
			System.out.printf("list syn: %s\n",s);
		}
		//Vertex t = driver.getOneSynomyn("merry");
		//System.out.println(t);
		//driver.addAntonym("Cheerful", "Jubilant");
		//driver.addSynonym("Cheerful","Merry");
		
		//String[] antonym = new String[3];
		//antonym[0] = "happy";
		//antonym[1] = "content";
		//antonym[2] = "joyful";
		
		//driver.addVertex("mathew","Peaceful, Merry", "happy, content");
		
		//driver.removeVertex("hate");
		
		
		//driver.addVertex("helen", "thumb, happy, supervisor", "fun, peaceful");
		
		//driver.removeVertex("Merry");
		
		
		
		

		
		//driver.editVertex("Joyful", "sad");
		//System.out.println(driver.getListOfSynomyns("Jubilant"));
		//System.out.println(driver.getTableData());
		
	}
}