package thesaurus.gui.canvas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class MainNode extends Node {
	private int windowWidth;
	private int windowHeight;
	
	public MainNode(String value, GraphicsContext gc, int x, int y, int ww, int wh){
		super(value,gc,x,y);
		this.windowWidth = ww;
		this.windowHeight = wh;
	}
	
	public int getWindowWidth(){
		return this.windowWidth;
	}
	
	public int getWindowHeight(){
		return this.windowHeight;
	}
	
	public void draw(){
		setX(windowWidth/2);
		setY(windowHeight/2);
		
		redraw();
	}
	
	public void redraw(){
		getGc().strokeOval((getX()-50),(getY()-25), 100, 50);
		getGc().setFont(new Font(20));
		getGc().fillText(getValue(), (getX()-34), (getY()+5));
	}
}
