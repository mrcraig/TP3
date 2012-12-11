package thesaurus.gui.window;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import thesaurus.parser.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import thesaurus.controller.SystemController;
import thesaurus.gui.canvas.ViewGraph;

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
		if (inputChoice.equals("add")) {
			showPopupAdd();
		} else if (inputChoice.equals("edit")) {
			showPopupEdit();
		} else if (inputChoice.equals("remove")) {
			showPopupRemove();
		}
	}

	private void showPopupAdd() {
		Popup popup = new Popup();
		popup.getContent().addAll(makeCanvasAdd(popup));
		popup.show(referenceWindow.getStage());
	}
	
	private void showPopupEdit() {
		Popup popup = new Popup();
		popup.getContent().addAll(makeCanvasEdit(popup));
		popup.show(referenceWindow.getStage());
	}

	private void showPopupRemove() {
		Popup popup = new Popup();
		popup.getContent().addAll(makeCanvasRemove(popup));
		popup.show(referenceWindow.getStage());
	}

	private Pane makeCanvasAdd(final Popup inputPopup) {
		Pane canvas = new Pane();
		canvas.setPrefSize(200, 200);
		Text addWordLabel = new Text();
		addWordLabel.relocate(35, 10);
		addWordLabel.setText("Add Word");
		addWordLabel.setScaleX(2);
		addWordLabel.setScaleY(2);
		Text promptWordLabel = new Text();
		promptWordLabel.relocate(10, 52);
		promptWordLabel.setText("Word: ");
		TextField addWordInput = new TextField();
		addWordInput.setPrefWidth(120);
		addWordInput.relocate(70, 50);
		Text promptSynLabel = new Text();
		promptSynLabel.relocate(10, 82);
		promptSynLabel.setText("Synonyms: ");
		TextField addSynInput = new TextField();
		addSynInput.setPrefWidth(120);
		addSynInput.relocate(70, 80);
		Text promptAntLabel = new Text();
		promptAntLabel.relocate(10, 112);
		promptAntLabel.setText("Antonyms: ");
		TextField addAntInput = new TextField();
		addAntInput.setPrefWidth(120);
		addAntInput.relocate(70, 110);
		Button confirmButton = new Button();
		confirmButton.setText("Confirm");
		confirmButton.relocate(30, 160);
		Button cancelButton = new Button();
		cancelButton.setText("Cancel");
		cancelButton.relocate(100, 160);
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				inputPopup.hide();
			}
		});
		canvas.getChildren().addAll(addWordLabel, promptWordLabel,
				addWordInput, promptSynLabel, addSynInput, promptAntLabel,
				addAntInput, confirmButton, cancelButton);
		canvas.setStyle("	-fx-background-color: #dfdfdf;"
				+ "-fx-border-color: black;" + "-fx-border-width: 1px;");
		return canvas;
	}
	
	private Pane makeCanvasEdit(final Popup inputPopup) {
		Pane canvas = new Pane();
		canvas.setPrefSize(200, 200);
		Text addWordLabel = new Text();
		addWordLabel.relocate(35, 10);
		addWordLabel.setText("Edit Word");
		addWordLabel.setScaleX(2);
		addWordLabel.setScaleY(2);
		Text promptWordLabel = new Text();
		promptWordLabel.relocate(10, 52);
		promptWordLabel.setText("Word: ");
		TextField addWordInput = new TextField();
		addWordInput.setPrefWidth(120);
		addWordInput.relocate(70, 50);
		Text promptSynLabel = new Text();
		promptSynLabel.relocate(10, 82);
		promptSynLabel.setText("Synonyms: ");
		TextField addSynInput = new TextField();
		addSynInput.setPrefWidth(120);
		addSynInput.relocate(70, 80);
		Text promptAntLabel = new Text();
		promptAntLabel.relocate(10, 112);
		promptAntLabel.setText("Antonyms: ");
		TextField addAntInput = new TextField();
		addAntInput.setPrefWidth(120);
		addAntInput.relocate(70, 110);
		Button confirmButton = new Button();
		confirmButton.setText("Confirm");
		confirmButton.relocate(30, 160);
		Button cancelButton = new Button();
		cancelButton.setText("Cancel");
		cancelButton.relocate(100, 160);
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				inputPopup.hide();
			}
		});
		canvas.getChildren().addAll(addWordLabel, promptWordLabel,
				addWordInput, promptSynLabel, addSynInput, promptAntLabel,
				addAntInput, confirmButton, cancelButton);
		canvas.setStyle("	-fx-background-color: #dfdfdf;"
				+ "-fx-border-color: black;" + "-fx-border-width: 1px;");
		return canvas;
	}
	
	private Pane makeCanvasRemove(final Popup inputPopup) {
		Pane canvas = new Pane();
		canvas.setPrefSize(200, 200);
		Text addWordLabel = new Text();
		addWordLabel.relocate(45, 10);
		addWordLabel.setText("Remove Word");
		addWordLabel.setScaleX(2);
		addWordLabel.setScaleY(2);
		Text promptWordLabel = new Text();
		promptWordLabel.relocate(10, 52);
		promptWordLabel.setText("Word: ");
		TextField addWordInput = new TextField();
		addWordInput.setPrefWidth(120);
		addWordInput.relocate(70, 50);
		Button confirmButton = new Button();
		confirmButton.setText("Confirm");
		confirmButton.relocate(30, 160);
		Button cancelButton = new Button();
		cancelButton.setText("Cancel");
		cancelButton.relocate(100, 160);
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				inputPopup.hide();
			}
		});
		canvas.getChildren().addAll(addWordLabel, promptWordLabel,
				addWordInput, confirmButton, cancelButton);
		canvas.setStyle("	-fx-background-color: #dfdfdf;"
				+ "-fx-border-color: black;" + "-fx-border-width: 1px;");
		return canvas;
	}

}
