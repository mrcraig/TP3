package thesaurus.gui.canvas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainNode extends Node {
	
	protected MainNode(String value, GraphicsContext gc, int x, int y){
		super(value,gc,x,y);
	}
	
	
	protected void draw(){
		getGc().setStroke(Color.BLACK);
		getGc().setFill(Color.WHITE);
		getGc().strokeOval((getX()-50),(getY()-25), 100, 50);
		getGc().fillOval((getX()-49),(getY()-24),98,48);
		getGc().setFill(Color.BLACK);
		getGc().setFont(new Font(20));
		getGc().fillText(getValue(), (getX()-34), (getY()+5));
	}
}
