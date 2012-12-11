package thesaurus.spring;

//import thesaurus.parser.Vertex;

public class TestData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Vertex v1 = new Vertex("big");
			Vertex v2 = new Vertex("adult");
			Vertex v3 = new Vertex("elder");
			Vertex v4 = new Vertex("full-grown");
			Vertex v5 = new Vertex("grownup");
			Vertex v6 = new Vertex("mature");
			Vertex v7 = new Vertex("tall");
			Vertex v8 = new Vertex("complete");
			Vertex v9 = new Vertex("full-grown");
			Vertex v10 = new Vertex("fit");
			Vertex v11 = new Vertex("developed");
			Vertex v12 = new Vertex("grown");
			Vertex v13 = new Vertex("of age");
			Vertex v14 = new Vertex("ripe");
			Vertex v15 = new Vertex("ancient");
			Vertex v16 = new Vertex("full grown");
			Vertex v17 = new Vertex("first-born");
			Vertex v18 = new Vertex("more mature");
			Vertex v19 = new Vertex("older");
			Vertex v20 = new Vertex("senior");
			Vertex v21 = new Vertex("settle");
			
			v1.addToAdjList(v2);
			v1.addToAdjList(v3);
			v1.addToAdjList(v4);
			v1.addToAdjList(v5);
			v1.addToAdjList(v6);
			v1.addToAdjList(v7);
			v1.addToAdjList(v9);
			
			v2.addToAdjList(v11);
			v2.addToAdjList(v12);
			v2.addToAdjList(v5);
			v2.addToAdjList(v13);
			v2.addToAdjList(v14);
			v2.addToAdjList(v6);
			
			
			v3.addToAdjList(v5);
			v3.addToAdjList(v11);
			v3.addToAdjList(v12);
			v3.addToAdjList(v13);
			v3.addToAdjList(v14);
			v3.addToAdjList(v6);
			v3.addToAdjList(v15);
			v3.addToAdjList(v17);
			v3.addToAdjList(v18);
			v3.addToAdjList(v19);
			v3.addToAdjList(v20);
			
			v6.addToAdjList(v8);
			v6.addToAdjList(v10);
			v6.addToAdjList(v16);
			v6.addToAdjList(v21);
			
			FrSpring2 fr = new FrSpring2(v1);
			
			Vertex myTest = fr.getCoordinates();
			fr.getVertices();
			
	}

}
