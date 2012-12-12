package thesaurus.parser;

import java.io.File;

public class Driver 
{

	public static void main(String[] args)
	{
		File test = new File("/home/james/GIT/myWorkspace/TP3/data.xml");
		System.out.println(test.getAbsolutePath());
		XmlRead read = new XmlRead(test);
		System.out.println(read.getSynmsForOne("Peaceful"));
		
		
	}
}
