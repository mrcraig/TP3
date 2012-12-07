package thesaurus.gui.canvas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AntonymNode extends SubNode {
	int subX;
	int subY;
	int mainX;
	int mainY;

	public AntonymNode(String value, GraphicsContext gc, int x, int y) {
		super(value, gc, x, y);
	}
	
	public void moveConnector(int xOffset, int yOffset){
		subX += xOffset;
		subY += yOffset;
		mainX += xOffset;
		mainY += yOffset;
	}
	
	public void redrawConnector(){
		getGc().setFill(Color.RED);
		getGc().strokeLine(mainX, mainY, subX, subY);
	}

	public void drawConnector(MainNode main) {
		double gradient = (double) main.getWindowHeight() / (double) main.getWindowWidth();
			
		if(getY()<=(main.getWindowHeight()-(gradient*getX()))){
			//top left triangle
			if(getX()>getY()/gradient){
				//bottom of sub to top of main
				mainX = main.getX();
				mainY = main.getY()-25;
				subX = getX();
				subY = getY()+23;
			} else {
				//right of sub to left of main
				mainX = main.getX()-50;
				mainY = main.getY();
				subX = getX()+37;
				subY = getY();
			}
		} else {
			//bottom right of triangle
			if(getX()>getY()/gradient){
				//left of sub to right of main
				mainX = main.getX() +50;
				mainY = main.getY();
				subX = getX()-37;
				subY = getY();
			} else {
				//top of sub to bottom of main
				mainX = main.getX();
				mainY = main.getY()+25;
				subX = getX();
				subY = getY() -13;
			}
		}
	
		getGc().setStroke(Color.RED);
		getGc().strokeLine(mainX, mainY, subX, subY);
		
	}

	public void draw() {
		getGc().setStroke(Color.RED);
		getGc().strokeOval((getX()-37),(getY()-13), 74, 36);
		getGc().setFont(new Font(14));
		getGc().fillText(getValue(), (getX()-25), (getY()+10));
	}

}
