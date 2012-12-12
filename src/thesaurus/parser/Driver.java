package thesaurus.parser;

public class Driver 
{

	public static void main(String[] args)
	{
		XmlRead read = new XmlRead("/resourcePackage/testAgain.xml");
		System.out.println(read.getSynmsFor("Happy"));
	}
}
