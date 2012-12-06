package thesaurus.gui.canvas;

import javafx.scene.canvas.GraphicsContext;

public abstract class SubNode extends Node {
	
	public SubNode(String value, GraphicsContext gc, int x, int y) {
		super(value, gc,x,y);
	}
	
	
	public abstract void drawConnector(MainNode main);
}
