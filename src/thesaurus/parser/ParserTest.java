package thesaurus.parser;


import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class ParserTest {

	@Before
	public void setUp() throws Exception {
	}
	@Test
	public void testVertex(){
		Vertex myV = new  Vertex("0");
		myV.setWord("happy");
		 assertEquals("happy",myV.getWord());
	}
	@Test
	public void testVertexIsVisited(){
		Vertex myV = new  Vertex("0");
		assertEquals(false,myV.isVisited());
	}
	@Test
	public void testInternalRepresentation(){
		File myfile = new File("./dataSmall.graphml");
		InternalRepresentation myV = new  InternalRepresentation(myfile);
		assertEquals(false,myV.isEmptyFile());
	}
	
	@Test
	public void testHashMap(){
		//File myfile = new File("./dataSmall.graphml");
		//InternalRepresentation myIr = new  InternalRepresentation(myfile);
		Vertex myV = new  Vertex("1");
		myV.setWord("happy");
		HashGraph myGraph = new HashGraph();
		myGraph.add(myV);
		int mysize = 1;
		assertEquals(mysize,myGraph.size());
	}

}
