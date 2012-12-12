package thesaurus.parser;

public class Driver 
{

	public static void main(String[] args)
	{
		//File test = new File()
		XmlRead read = new XmlRead(null);
		System.out.println(read.getSynmsForOne("Peaceful"));
		
		
	}
}
