package thesaurus.gui.canvas;

import javafx.scene.canvas.GraphicsContext;

public abstract class SubNode extends Node {
	
	protected SubNode(String value, GraphicsContext gc, int x, int y) {
		super(value, gc,x,y);
	}
	
	
	protected abstract void drawConnector(MainNode main);
}
