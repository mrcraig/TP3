package thesaurus.spring;

import thesaurus.parser.Vertex;

public class TestData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Vertex v1 = new Vertex("big");
			Vertex v2 = new Vertex("giant");
			Vertex v3 = new Vertex("grand");
			Vertex v4 = new Vertex("gargantuan");
			Vertex v5 = new Vertex("large");
			
			v1.addToAdjList(v2);
			v1.addToAdjList(v3);
			v1.addToAdjList(v4);
			v1.addToAdjList(v5);
			
			FrSpring2 fr = new FrSpring2(v1);
	}

}
