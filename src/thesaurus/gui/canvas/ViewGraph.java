/**
 * @author Team O
 * @title Graphical Thesaurus
 * @date 3/12/12
 */

package thesaurus.gui.canvas;

import java.util.LinkedList;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import thesaurus.parser.Vertex;


public class ViewGraph {
	private int windowWidth = 700;
	private int windowHeight = 316;
	Vertex vertex;
	
	//Create 5 snyonym nodes for testing.
	private SynonymNode syn[] = new SynonymNode[3];
	private AntonymNode ant[] = new AntonymNode[2];
	private MainNode main;
	private Canvas graph;
	
	/** 
	 * Instantiates new instance of Graph
	 * 
	 * @param width
	 * @param height
	 */
	public ViewGraph(int width, int height, Vertex vertex){
		windowWidth = width;
		windowHeight = height;
		this.vertex = vertex;
		start();
	}

	/**
	 * void start()
	 * 
	 * Contains all methods and calls relating to graph drawing and control.
	 */
	private void start() {
		graph = new Canvas(windowWidth,windowHeight);
		final GraphicsContext gc = graph.getGraphicsContext2D();
		drawNodes(gc);
	
		final LinkedList<Integer> curX = new LinkedList<Integer>();
		final LinkedList<Integer> curY = new LinkedList<Integer>();
		
		/**
		 * Action Method
		 * 
		 * Updates coordinates of every element in graph based on mouse displacement.
		 * Calls redraw functions for all items in graph.
		 * (NOTE: Connector must be redrawn before the node.)
		 */
		graph.addEventHandler(MouseEvent.MOUSE_DRAGGED,
				new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				
				curX.add((int)e.getX());
				curY.add((int)e.getY());
				
				
				if(curX.size()>1){
					//two positions added, move nodes.
					gc.setFill(Color.WHITE);
					gc.fillRect(0, 0, windowWidth, windowHeight);
					gc.setFill(Color.BLACK);
					
					int xOffset = curX.get(0)-curX.get(1);
					int yOffset = curY.get(0)-curY.get(1);
					
					for(int i=0;i<3;i++){
						syn[i].setX(syn[i].getX()-xOffset);
						syn[i].setY(syn[i].getY()-yOffset);
						redrawSyn(i);
					}
					
					for(int i=0;i<2;i++){
						ant[i].setX(ant[i].getX()-xOffset);
						ant[i].setY(ant[i].getY()-yOffset);
						redrawAnt(i);
					}
					
					main.setX(main.getX()-xOffset);
					main.setY(main.getY()-yOffset);
					
					main.draw();

					curX.remove();
					curY.remove();
				}
			}
		});
		
		/**
		 * Action Method
		 * 
		 * Resets cursor position on mouse release to prevent graph snapping when graph is next clicked
		 */
		graph.addEventHandler(MouseEvent.MOUSE_RELEASED, 
				new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				/** Reset mouse storage */
				curX.clear();
				curY.clear();
			}
		});
		
		/**
		 * Action Method
		 * 
		 * Used to detect nodes that have been double clicked. Will output node to redrawGraph function.
		 */
		graph.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				if(e.getClickCount()>1){
					//Something has been double clicked
					//Check synonyms
					for(int i=0;i<3;i++){
						if((e.getX() > syn[i].getX()-37) && (e.getX() < syn[i].getX()+37)){
							//Matches X
							if((e.getY() > syn[i].getY()-13) && (e.getY() < syn[i].getY()+13)){
								//Matches Y
								System.out.println(syn[i].getValue());
							}
						}
					}
					
					//Check antonyms
					for(int i=0;i<2;i++){
						if((e.getX() > ant[i].getX()-37) && (e.getX() < ant[i].getX()+37)){
							//Matches X
							if((e.getY() > ant[i].getY()-13) && (e.getY() < ant[i].getY()+13)){
								//Matches Y
								System.out.println(ant[i].getValue());
							}
						}
					}
				}
			}
		});
		
	}
	
	/**
	 * void drawNodes()
	 * 
	 * Initialises SynonymNode, AntonymNode and MainNode variables, and calls draw methods to display.
	 * (NOTE: Connectors must be displayed before nodes are displayed.)
	 * 
	 * @param gc
	 */
	private void drawNodes(GraphicsContext gc){
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(3);
		
		/** Main Node Code */
		main = new MainNode("Matthew",gc,(windowWidth/2),(windowHeight/2));
		
		syn[0] = new SynonymNode("Warm",gc,100,200);
		syn[1] = new SynonymNode("Roasting",gc,300,100);
		syn[2] = new SynonymNode("Boiling",gc,50,50);
		
		ant[0] = new AntonymNode("Cold",gc,500,150);
		ant[1] = new AntonymNode("Freezing",gc,600,50);
		
		for(int i=0;i<3;i++){
			syn[i].drawConnector(main);
			syn[i].draw();
		}
		
		for(int i=0;i<2;i++){
			ant[i].drawConnector(main);
			ant[i].draw();
		}
		
		main.draw();
	}
	
	/**
	 * void redrawSyn()
	 * 
	 * Draws SynonymNode with index 'index'
	 * @param index
	 */
	private void redrawSyn(int index){
		syn[index].drawConnector(main);
		syn[index].draw();
	}
	
	/**
	 * void redrawAnt()
	 * 
	 * Draws AntonymNode with index 'index'
	 * @param index
	 */
	private void redrawAnt(int index){
		ant[index].drawConnector(main);
		ant[index].draw();
	}
	
	/**
	 * Canvas returnGraph()
	 * 
	 * Returns Canvas object 'graph'
	 * @return graph
	 */
	public Canvas returnGraph(){
		return graph;
	}
	
}
