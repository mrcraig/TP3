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
	
	//Declare synonyms and antonym storage
	private LinkedList<SynonymNode> syn = new LinkedList<SynonymNode>();
	private LinkedList<AntonymNode> ant = new LinkedList<AntonymNode>();
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
	
	private void initialiseObjects(GraphicsContext gc){
		
		//Create main Node
		main = new MainNode(vertex.getWord(),gc,(int) vertex.getPos().getX(),(int) vertex.getPos().getY());
		
		//Create Synonyms
		for(int i=0;i<vertex.getAdjList().size();i++){
			syn.add(new SynonymNode(vertex.getAdjList().get(i).getWord(),gc,(int) vertex.getAdjList().get(i).getPos().getX(),(int) vertex.getAdjList().get(i).getPos().getY()));
			syn.get(i).drawConnector(main);
			syn.get(i).draw();
		}
		main.draw();
	}

	/**
	 * void start()
	 * 
	 * Contains all methods and calls relating to graph drawing and control.
	 */
	private void start() {
		graph = new Canvas(windowWidth,windowHeight);
		final GraphicsContext gc = graph.getGraphicsContext2D();
		initialiseObjects(gc);
		//drawNodes(gc);
	
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
						syn.get(i).setX(syn.get(i).getX()-xOffset);
						syn.get(i).setY(syn.get(i).getY()-yOffset);
						redrawSyn(i);
					}
					
					for(int i=0;i<2;i++){
						ant.get(i).setX(ant.get(i).getX()-xOffset);
						ant.get(i).setY(ant.get(i).getY()-yOffset);
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
						if((e.getX() > syn.get(i).getX()-37) && (e.getX() < syn.get(i).getX()+37)){
							//Matches X
							if((e.getY() > syn.get(i).getY()-13) && (e.getY() < syn.get(i).getY()+13)){
								//Matches Y
								System.out.println(syn.get(i).getValue());
							}
						}
					}
					
					//Check antonyms
					for(int i=0;i<2;i++){
						if((e.getX() > ant.get(i).getX()-37) && (e.getX() < ant.get(i).getX()+37)){
							//Matches X
							if((e.getY() > ant.get(i).getY()-13) && (e.getY() < ant.get(i).getY()+13)){
								//Matches Y
								System.out.println(ant.get(i).getValue());
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
		
		for(int i=0;i<3;i++){
			syn.get(i).drawConnector(main);
			syn.get(i).draw();
		}
		
		for(int i=0;i<2;i++){
			ant.get(i).drawConnector(main);
			ant.get(i).draw();
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
		syn.get(index).drawConnector(main);
		syn.get(index).draw();
	}
	
	/**
	 * void redrawAnt()
	 * 
	 * Draws AntonymNode with index 'index'
	 * @param index
	 */
	private void redrawAnt(int index){
		ant.get(index).drawConnector(main);
		ant.get(index).draw();
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
