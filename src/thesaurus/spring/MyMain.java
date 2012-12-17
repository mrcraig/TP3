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
	int wid = 1000;
	int len = 1000;
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
//		v3.addToAdjList(v6);
//		v3.addToAdjList(v15);
//		v3.addToAdjList(v17);
//		v3.addToAdjList(v18);
//		v3.addToAdjList(v19);
//		v3.addToAdjList(v20);

		v4.addSynonym(v1);
		v4.addSynonym(v2);
		

		v5.addSynonym(v1);
		v5.addSynonym(v2);
		v5.addSynonym(v3);
		//v5.addToAdjList(v6);
		
		v6.addSynonym(v1);
		v6.addSynonym(v2);
		v6.addSynonym(v3);
		//v6.addToAdjList(v5);
		
		v7.addSynonym(v1);
		v7.addSynonym(v8);
		
		v8.addSynonym(v1);
		v8.addSynonym(v7);
		v8.addSynonym(v9);
		v8.addSynonym(v10);
		v8.addSynonym(v11);
		
		v9.addSynonym(v10);
		v9.addSynonym(v11);
		
		v12.addSynonym(v13);
		v12.addSynonym(v14);
		v12.addSynonym(v15);
		//v5.addToAdjList(v6);
		
		fr = new FrSpring(v1);
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
					double tX = y.getPos().getX();
					double tY = y.getPos().getY();

					gc.strokeLine(sX, sY, tX, tY);
				}
			}
		}

		for (Vertex v : fr.getVertices()) {
			
			drawShapes(gc, v);
		}
		
		
		
	}

	public void drawShapes(GraphicsContext gc, Vertex v) {
		gc.setStroke(Color.RED);
		gc.setFill(Color.WHITE);
		gc.strokeOval((v.getPos().getX() - 37), (v.getPos().getY() - 13), 74,
				36);
		gc.fillOval((v.getPos().getX() - 36), (v.getPos().getY() - 12), 72, 34); // Draw
																					// white
																					// oval
																					// overlapping
																					// to
																					// hide
																					// connector
		gc.setFill(Color.BLACK);
		gc.setFont(new Font(14));
		gc.fillText(v.getIndex(), (v.getPos().getX() - 25),
				(v.getPos().getY() + 10));
		// if (v.equals(myW)) {
		// gc.fillRect(v.getPos().getX(), v.getPos().getY(), 20, 20);
		// } else {
		// gc.fillOval(v.getPos().getX(), v.getPos().getY(), 20, 20);
		// }

	}
	

}
