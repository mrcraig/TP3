package thesaurus.gui.canvas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AntonymNode extends SubNode {

	protected AntonymNode(String value, GraphicsContext gc, int x, int y) {
		super(value, gc, x, y);
	}

	protected void drawConnector(MainNode main) {
		getGc().setStroke(Color.RED);
		getGc().strokeLine(main.getX(), main.getY(), getX(), getY());
		
	}

	protected void draw() {
		getGc().setStroke(Color.RED);
		getGc().setFill(Color.WHITE);
		getGc().strokeOval((getX()-37),(getY()-13), 74, 36);
		getGc().fillOval((getX()-36),(getY()-12),72,34);	//Draw white oval overlapping to hide connector
		getGc().setFill(Color.BLACK);
		getGc().setFont(new Font(14));
		getGc().fillText(getValue(), (getX()-25), (getY()+10));
	}

}
