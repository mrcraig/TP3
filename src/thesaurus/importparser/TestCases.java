/*package thesaurus.importparser;

import java.io.File;
import java.io.IOException;

import thesaurus.parser.InternalRepresentation;
import thesaurus.parser.Vertex;

public class TestCases {
	
	TextParser testParser;
	InternalRepresentation testInternal;

	@Before
	public void setUp() throws Exception {
		//Open thesaurus source with some known values
		testParser = new TextParser("./src/resourcePackage/importparsersmall.txt","output.graphml");
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
*/