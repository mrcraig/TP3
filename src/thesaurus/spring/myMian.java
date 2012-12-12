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

public class myMian extends Application {

	Canvas graph;
	int wid = 10000;
	int len = 10000;
	static Vertex myW;

	FrSpring2 fr;

	public static void main(String[] args) {

		launch(args);
	}

	public void mystart() {
		// TODO Auto-generated method stub
		Vertex v1 = new Vertex("big");
		this.myW = v1;
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
		//v1.addToAdjList(v4);
		v1.addToAdjList(v5);
		v1.addToAdjList(v6);
		v1.addToAdjList(v7);
		v1.addToAdjList(v9);

		v2.addToAdjList(v11);
		v2.addToAdjList(v12);
		v2.addToAdjList(v10);
		v2.addToAdjList(v13);
		v2.addToAdjList(v14);
		v2.addToAdjList(v14);
//
//		v3.addToAdjList(v5);
//		v3.addToAdjList(v11);
//		v3.addToAdjList(v12);
//		v3.addToAdjList(v13);
//		v3.addToAdjList(v14);
		v3.addToAdjList(v6);
		v3.addToAdjList(v15);
		v3.addToAdjList(v17);
		v3.addToAdjList(v18);
		v3.addToAdjList(v19);
		v3.addToAdjList(v20);

		v4.addToAdjList(v8);
		v4.addToAdjList(v10);
		v4.addToAdjList(v11);
		
		v5.addToAdjList(v2);
		v5.addToAdjList(v4);
		
		v6.addToAdjList(v8);
		v6.addToAdjList(v10);
		v6.addToAdjList(v16);
		v6.addToAdjList(v21);

		fr = new FrSpring2(v1);
		// fr.getPositions();
		// fr.test();

	}

	public void start(Stage primaryStage) {
		mystart();
		Group root = new Group();
		graph = new Canvas(wid, len);
		final GraphicsContext gc = graph.getGraphicsContext2D();
		//drawShapes(gc, myW);
		
			for (Vertex x : fr.getVertices()) {
				double sX =x.getPos().getX();
				double sY =x.getPos().getY();
				for (Vertex y : x.getAdjList()){
					if (y.getPos() != null){
					 double tX =y.getPos().getX();
					 double tY =y.getPos().getY();
				
				gc.strokeLine(sX, sY, tX, tY);
					}
			}
		}
			for (Vertex v : fr.getVertices()) {
				drawShapes(gc, v);
			}
		// for (Vertex v : fr.getVertices() ){
		// for (Vertex x: v.getAdjList()){
		// if (gc == null)System.out.println("I am null");
		// if ((x != null)|| (v != null)){
		// drawShapes1(gc,v,x);}
		// }
		// }
		// TODO Auto-generated method stub
		root.getChildren().add(graph);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();

	}

	private static void drawShapes(GraphicsContext gc, Vertex v) {
		gc.setStroke(Color.RED);
		gc.setFill(Color.WHITE);
		gc.strokeOval((v.getPos().getX()-37),(v.getPos().getY()-13), 74, 36);
		gc.fillOval((v.getPos().getX()-36),(v.getPos().getY()-12),72,34);	//Draw white oval overlapping to hide connector
		gc.setFill(Color.BLACK);
		gc.setFont(new Font(14));
		gc.fillText(v.getIndex(), (v.getPos().getX()-25), (v.getPos().getY()+10));
//		if (v.equals(myW)) {
//			gc.fillRect(v.getPos().getX(), v.getPos().getY(), 20, 20);
//		} else {
//			gc.fillOval(v.getPos().getX(), v.getPos().getY(), 20, 20);
//		}

	}

	private static void drawShapes1(GraphicsContext gc, Vertex so, Vertex ta) {

		gc.setStroke(Color.BLUE);
		gc.setLineWidth(5);

		gc.strokeLine(so.getPos().getX(), so.getPos().getY(), ta.getPos()
				.getX(), ta.getPos().getY());

	}

}
