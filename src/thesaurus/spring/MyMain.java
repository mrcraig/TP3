package thesaurus.spring;

import java.util.LinkedList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import thesaurus.parser.*;
public class MyMain extends Application {

	Canvas graph;
	int wid = 1500;
	int len = 1500;
	static Vertex myW;

	static FrSpring fr;

	public static void main(String[] args) {
		mystart();
		launch(args);
	}

	public static void mystart() {
		// TODO Auto-generated method stub
		Vertex v1 = new Vertex("happy");
		//this.myW = v1;
		Vertex v2 = new Vertex("joyfull");
		Vertex v3 = new Vertex("cheerfull");
		Vertex v4 = new Vertex("jubilant");
		Vertex v5 = new Vertex("merry");
		Vertex v6 = new Vertex("upbeat");
		Vertex v7 = new Vertex("peacefull");
		Vertex v8 = new Vertex("friendly");
		Vertex v9 = new Vertex("affable");
		Vertex v10 = new Vertex("affectionate");
		Vertex v11 = new Vertex("amiable");
		Vertex v12 = new Vertex("mature");
		Vertex v13 = new Vertex("fit");
		Vertex v14 = new Vertex("full grown");
		Vertex v15 = new Vertex("settle");
		Vertex v16 = new Vertex("Pal");
		Vertex v17 = new Vertex("Mate");
		Vertex v18 = new Vertex ("Facetious");
		Vertex v19 = new Vertex ("Comic");
		Vertex v20 = new Vertex ("Clown");
		Vertex v21 = new Vertex ("Fool");
		Vertex v22 = new Vertex ("antic");
		Vertex v23 = new Vertex ("dolt");
		Vertex v24 = new Vertex ("Comedian");
		Vertex v25 = new Vertex ("wag");
		Vertex v26 = new Vertex ("Amusing");
		Vertex v27 = new Vertex ("Boffo");
		Vertex v28 = new Vertex ("Campy");
		Vertex v29 = new Vertex ("Screaming");
		Vertex v30 = new Vertex ("Sidesplitting");
		Vertex v31 = new Vertex ("ironic");
		Vertex v32 = new Vertex ("mordant");
		Vertex v33 = new Vertex ("paradoxical");
		Vertex v34 = new Vertex (" goofus");
		Vertex v35 = new Vertex (" gump");
		Vertex v39 = new Vertex (" genuine");
		Vertex v40 = new Vertex (" actulal");
		Vertex v41 = new Vertex (" accurate");
		Vertex v42 = new Vertex (" honest");		 
		Vertex v43 = new Vertex ("unhumorous");
		Vertex v44 = new Vertex (" unsmiling");
		Vertex v45 = new Vertex ("  weighty");
		
		Vertex v36 = new Vertex ("formal");
		Vertex v37 = new Vertex ("serious");
		
		v37.addSynonym(v43);
		v37.addSynonym(v44);
		v37.addSynonym(v45);
		
		v39.addSynonym(v42);
		
		v36.addSynonym(v39);
		v36.addSynonym(v40);
		v36.addSynonym(v41);
		v18.addAntonym(v36);
		v18.addAntonym(v37);
		
		//v36.addAntonym(v18);
		//v37.addAntonym(v18);
		
		v18.addSynonym(v26);
		v18.addSynonym(v31);
		 
		v31.addSynonym(v32);
		v31.addSynonym(v33);
		v31.addSynonym(v34);
		v31.addSynonym(v35);
		
		v32.addSynonym(v31);
		v33.addSynonym(v31);
		v34.addSynonym(v31);
		v35.addSynonym(v31);
		
		v26.addSynonym(v27);
		v26.addSynonym(v28);
		v26.addSynonym(v29);
		v26.addSynonym(v30);
		
		v27.addSynonym(v26);
		v28.addSynonym(v26);
		v29.addSynonym(v26);
		v30.addSynonym(v26);
		
		v1.addSynonym(v2);
		v1.addSynonym(v3);
	    v1.addSynonym(v6);
    	v1.addSynonym(v7);
		v1.addSynonym(v8);
     	

		
		v2.addSynonym(v1);
		v2.addSynonym(v4);
		v2.addSynonym(v5);
		v2.addSynonym(v6);
//		v2.addSynomyn(v14);
		//
         v3.addSynonym(v5);
		 //v3.addToAdjList(v11);
		
		 v3.addSynonym(v1);
		 v3.addSynonym(v6);


		v4.addSynonym(v1);
		v4.addSynonym(v2);
		

		v5.addSynonym(v1);
		v5.addSynonym(v2);
		v5.addSynonym(v3);
		v5.addSynonym(v6);
		v5.addSynonym(v18);
		
		v6.addSynonym(v1);
		v6.addSynonym(v2);
		v6.addSynonym(v3);
		//v6.addToAdjList(v5);
		
		v7.addSynonym(v1);
		v7.addSynonym(v8);
		
		v8.addSynonym(v1);
		v8.addSynonym(v7);
		v8.addSynonym(v16);
		//v8.addSynonym(v15);
		//v8.addSynonym(v10);
		//v8.addSynonym(v11);
		
		v9.addSynonym(v10);
		v9.addSynonym(v11);
		v9.addSynonym(v8);
		
		v12.addSynonym(v13);
		v12.addSynonym(v14);
		v12.addSynonym(v15);
		
		
		
		
		v16.addSynonym(v8);
		v16.addSynonym(v17);
		v17.addSynonym(v16);
		
		
		v18.addSynonym(v5);
		v18.addSynonym(v19);
		v19.addSynonym(v18);
		v19.addSynonym(v20);
		v19.addSynonym(v24);
		v20.addSynonym(v19);
		
		v20.addSynonym(v21);
		v20.addSynonym(v22);
		v20.addSynonym(v23);
		v20.addSynonym(v24);
		v20.addSynonym(v25);
		
		v21.addSynonym(v20);
		v21.addSynonym(v23);
		
		v22.addSynonym(v20);
		v23.addSynonym(v20);
		v23.addSynonym(v21);
		
		v24.addSynonym(v20);
		v24.addSynonym(v25);
		v24.addSynonym(v19);
		
		v25.addSynonym(v20);
		v25.addSynonym(v24);
		
		myW = v18;
		//myW = v16;
		fr = new FrSpring(myW,1,0,0);
		// fr.getPositions();
		// fr.test();

	}

	public void start(Stage primaryStage) {
		
		Group root = new Group();
		
		graph = new Canvas(wid, len);
		final GraphicsContext gc = graph.getGraphicsContext2D();
		// drawShapes(gc, myW);
	//	while ((((double)10000 - fr.temprature) < 5)&& (((double)10000 - fr.temprature) >= 0) ){
		root.getChildren().add(graph);
		primaryStage.setScene(new Scene(root));
		
		primaryStage.show();
		
		
		for (Vertex x : fr.getVertices()) {
			double sX = x.getPos().getX();
			double sY = x.getPos().getY();
			
			for (Vertex y : x.getSynomyns()) {
				if (y.getPos() != null) {
					gc.setStroke(Color.BLACK);
					gc.setLineWidth(1);
					double tX = y.getPos().getX();
					double tY = y.getPos().getY();

					gc.strokeLine(sX, sY, tX, tY);
				} 
			}
			
			for (Vertex y : x.getAntonyms()) {
				if (y.getPos() != null) {
					gc.setStroke(Color.RED);
					gc.setLineWidth(3);
					double atX = y.getPos().getX();
					double atY = y.getPos().getY();
					gc.strokeLine(sX, sY, atX, atY);
				}
			}
		}

		for (Vertex v : fr.getVertices()) {
			
			drawShapes(gc, v);
		}
		
		
		
	}

	public void drawShapes(GraphicsContext gc, Vertex v) {
		gc.setStroke(Color.RED);
		if (v.equals(myW)) {gc.setFill(Color.AQUAMARINE);}
		else {gc.setFill(Color.WHITE);}
		gc.strokeOval((v.getPos().getX() - 37), (v.getPos().getY() - 13), 74,
				36);
		gc.fillOval((v.getPos().getX() - 36), (v.getPos().getY() - 12), 72, 34); 
																					
		gc.setFill(Color.BLACK);
		gc.setFont(new Font(14));
		gc.fillText(v.getID(), (v.getPos().getX() - 25),
				(v.getPos().getY() + 10));
		

	}
	

}
