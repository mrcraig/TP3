package thesaurus.gui.canvas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainNode extends Node {
	private int windowWidth;
	private int windowHeight;
	
	protected MainNode(String value, GraphicsContext gc, int x, int y, int ww, int wh){
		super(value,gc,x,y);
		this.windowWidth = ww;
		this.windowHeight = wh;
	}
	
	protected int getWindowWidth(){
		return this.windowWidth;
	}
	
	protected int getWindowHeight(){
		return this.windowHeight;
	}
	
	protected void draw(){
		setX(windowWidth/2);
		setY(windowHeight/2);
		
		redraw();
	}
	
	protected void redraw(){
		getGc().setStroke(Color.BLACK);
		getGc().strokeOval((getX()-50),(getY()-25), 100, 50);
		getGc().setFont(new Font(20));
		getGc().fillText(getValue(), (getX()-34), (getY()+5));
	}
}
