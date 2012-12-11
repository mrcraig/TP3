package thesaurus.gui.window;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import thesaurus.controller.SystemController;
import thesaurus.gui.canvas.ViewGraph;
import thesaurus.parser.Parser;
import thesaurus.parser.Vertex;

/**
 * This class is an extension of AnchorPane and defines how the visualisation
 * pages looks and what the buttons do via mouse handlers.
 */
public class VisualisationRoot extends AnchorPane {

	private MainWindow referenceWindow;
	private SystemController currentController;
	private Parser currentParser;
	private LinkedList<Vertex> currentResults;
	private File currentFile;

	public VisualisationRoot(MainWindow inputWindow) throws IOException {

		referenceWindow = inputWindow;

		currentController = referenceWindow.getCurrentController();

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"/resourcePackage/visualisationLayout.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(currentController);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		addCanvas();

	}

	private void addCanvas() {
		ViewGraph displayGraphFull = new ViewGraph(750, 376);
		ViewGraph displayGraphDual = new ViewGraph(354, 362);
		currentController.getCanvasFullGraph().getChildren()
				.add(displayGraphFull.returnGraph());
		currentController.getCanvasDualGraph().getChildren()
				.add(displayGraphDual.returnGraph());
	}

	public void setCurrentParser(File inputFile) {
		setCurrentFile(inputFile);
		currentParser = new Parser(inputFile);
	}

	public LinkedList<Vertex> getCurrentResults() {
		return currentResults;
	}

	public void setCurrentResults(LinkedList<Vertex> currentResultsInput) {
		currentResults = currentResultsInput;
	}

	public File getCurrentFile() {
		return currentFile;
	}

	public void setCurrentFile(File currentFileInput) {
		currentFile = currentFileInput;
	}

	public Parser getCurrentParser() {
		return currentParser;
	}

	public void showPopup(String inputChoice) {
		PopupFactory currentPopupFactory = new PopupFactory(inputChoice);
		Popup currentPopup = currentPopupFactory.getPopup();
		currentPopup.show(referenceWindow.getStage());
		currentPopup.setY(currentPopup.getY()+10);
	}

}
