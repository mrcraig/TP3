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
	
	/**
	 * Constructor Method
	 * 
	 * Provides abstract constructor for all nodes
	 * 
	 * @param value
	 * @param gc
	 * @param x
	 * @param y
	 */
	protected Node(String value, GraphicsContext gc, int x, int y){
		this.value = value;
		this.gc = gc;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Getter Method
	 *
	 * Returns X coordinate of any node
	 * @return
	 */
	protected int getX(){
		return this.x;
	}
	
	/**
	 * Getter Method
	 * 
	 * Returns Y coordinate of any node
	 * @return
	 */
	protected int getY(){
		return this.y;
	}
	
	
	/**
	 * Setter Method
	 * 
	 * Sets the value of Y coordinate for any node
	 * @param x
	 */
	protected void setX(int x){
		this.x = x;
	}
	
	/**
	 * Setter Method
	 * 
	 * Sets the value of Y coordinate for any node
	 * @param y
	 */
	protected void setY(int y){
		this.y = y;
	}
	
	
	/**
	 * Getter Method
	 * 
	 * Returns the value of any Node.
	 * @return
	 */
	protected String getValue(){
		return this.value;
	}
	
	/** 
	 * Getter Method 
	 * 
	 * Returns the GraphicsContext
	 * @return
	 */
	protected GraphicsContext getGc(){
		return this.gc;
	}
	
	/**
	 * Setter Method
	 * 
	 * Sets the value for any Node.
	 * @param value
	 */
	protected void setValue(String value){
		this.value = value;
	}
	
	/**
	 * abstract void draw()
	 * 
	 * Ensures extending classes implement the draw() method.
	 */
	protected abstract void draw();
}
