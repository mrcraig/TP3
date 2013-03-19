package thesaurus.importparser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import thesaurus.parser.InternalRepresentation;
import thesaurus.parser.Vertex;

public class TestCases {
	
	TextParser testParser;
	InternalRepresentation testInternal;

	@Before
	public void setUp() throws Exception {
		//Open thesaurus source with some known values
		testParser = new TextParser("./src/resourcePackage/importparsermedium.txt","out.graphml");
		testInternal = new InternalRepresentation(new File("out.graphml"));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try {
			testParser.run();
		} catch (IOException e) {
			System.out.println("Failed due to IO Exception");
		}
		Vertex v = testInternal.getVertexFromWord("education");
		assertTrue(v != null);
	}

}
