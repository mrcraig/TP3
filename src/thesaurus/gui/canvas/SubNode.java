package thesaurus.gui.canvas;

import javafx.scene.canvas.GraphicsContext;

public abstract class SubNode extends Node {
	
	/**
	 * Constructor Method
	 * 
	 * Provides abstract constructor for a subNode.
	 * 
	 * @param value
	 * @param gc
	 * @param x
	 * @param y
	 */
	protected SubNode(String value, GraphicsContext gc, int x, int y) {
		super(value, gc,x,y);
	}
	
	/**
	 * abstract void drawConnector()
	 * 
	 * Ensures inherited classes contain a drawConnector method.
	 * @param main
	 */
	protected abstract void drawConnector(MainNode main);
}
