/**
 * @author Team O
 * @title Graphical Dictionary
 * @date 4/12/12
 * 
 * Abstract class to represent a Node in the Graph.
 */


package thesaurus.gui.canvas;

import javafx.scene.canvas.GraphicsContext;

public abstract class Node {
	private String value;
	private GraphicsContext gc;
	private int x;
	private int y;
	
	public Node(String value, GraphicsContext gc, int x, int y){
		this.value = value;
		this.gc = gc;
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public GraphicsContext getGc(){
		return this.gc;
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
	public abstract void draw();
}
