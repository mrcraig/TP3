package thesaurus.parser;

public class Driver 
{

	public static void main(String[] args)
	{
		XmlRead read = new XmlRead(null);
		System.out.println(read.getSynmsFor("Happy"));
	}
}
