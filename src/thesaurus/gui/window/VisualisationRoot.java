package thesaurus.gui.window;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import thesaurus.controller.SystemController;
import thesaurus.gui.canvas.ViewGraph;
import thesaurus.parser.*;
import thesaurus.spring.FrSpring2;

/**
 * This class is an extension of AnchorPane and defines how the visualisation
 * pages looks and what the buttons do via mouse handlers.
 */
public class VisualisationRoot extends AnchorPane {

	private MainWindow referenceWindow;
	private SystemController currentController;
	private InternalRepresentation currentParser;
	private File currentFile;
	private Popup currentPopup;
	private Vertex currentVertex;
	private FrSpring2 currentSpring;

	public VisualisationRoot(MainWindow inputWindow) throws IOException {

		referenceWindow = inputWindow;

		currentController = referenceWindow.getCurrentController();

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resourcePackage/visualisationLayout.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(currentController);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

	}

	public void addCanvas() {
		ViewGraph displayGraphFull = new ViewGraph(750, 376,currentVertex);
		ViewGraph displayGraphDual = new ViewGraph(354, 362,currentVertex);
		currentController.getCanvasFullGraph().getChildren().add(displayGraphFull.returnGraph());
		currentController.getCanvasDualGraph().getChildren().add(displayGraphDual.returnGraph());
	}

	public void setCurrentParser(File inputFile) {
		setCurrentFile(inputFile);
		currentParser = new InternalRepresentation(inputFile);
		System.out.println(inputFile.getAbsolutePath());
	}

	public File getCurrentFile() {
		return currentFile;
	}

	public void setCurrentFile(File currentFileInput) {
		currentFile = currentFileInput;
	}
	
	public Vertex getCurrentVertex() {
		return currentVertex;
	}

	public void setCurrentVertex(Vertex currentVertexInput) {
		currentVertex = currentVertexInput;
	}

	public InternalRepresentation getCurrentParser() {
		return currentParser;
	}

	public void showPopup(String inputChoice) {
		if(currentPopup != null){
			currentPopup.hide();
			currentPopup = null;
		}
		PopupFactory currentPopupFactory = new PopupFactory(inputChoice);
		currentPopup = currentPopupFactory.getPopup();
		currentPopup.show(referenceWindow.getStage());
		currentPopup.setY(currentPopup.getY()+15);
	}

	public FrSpring2 getCurrentSpring() {
		return currentSpring;
	}

	public void setCurrentSpring(FrSpring2 currentSpringInput) {
		currentSpring = currentSpringInput;
	}
	
	public Vertex runSpringOnVertex(Vertex inputVertex){
		currentSpring = new FrSpring2(inputVertex);
		return currentSpring.getCoordinates();
	}

}
