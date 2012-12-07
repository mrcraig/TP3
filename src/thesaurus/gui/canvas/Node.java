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
	
	protected Node(String value, GraphicsContext gc, int x, int y){
		this.value = value;
		this.gc = gc;
		this.x = x;
		this.y = y;
	}
	
	protected int getX(){
		return this.x;
	}
	
	protected int getY(){
		return this.y;
	}
	
	protected void setX(int x){
		this.x = x;
	}
	
	protected void setY(int y){
		this.y = y;
	}
	
	protected String getValue(){
		return this.value;
	}
	
	protected GraphicsContext getGc(){
		return this.gc;
	}
	
	protected void setValue(String value){
		this.value = value;
	}
	
	protected abstract void draw();
}
