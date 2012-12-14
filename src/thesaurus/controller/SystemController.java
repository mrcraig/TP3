package thesaurus.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import thesaurus.gui.window.MainWindow;
import thesaurus.gui.window.TutorialRoot;
import thesaurus.gui.window.VisualisationRoot;
import thesaurus.parser.Vertex;

public class SystemController {

	@FXML
	private Pane canvasFullGraph;

	@FXML
	private Pane canvasDualGraph;

	@FXML
	private TabPane mainTabWindow;

	@FXML
	private Button goButtonGraph;

	@FXML
	private TextField searchBoxGraph;

	@FXML
	private Text currentFileLabel;
	
	@FXML
	private Text searchStatusLabel;

	MainWindow referenceWindow;

	public SystemController(MainWindow inputWindow) {
		referenceWindow = inputWindow;
	}

	@FXML
	protected void doCreate() throws IOException {

		FileChooser currentFileChooser = new FileChooser();
		FileChooser.ExtensionFilter graphmlFilter = new FileChooser.ExtensionFilter(
				"GraphML files (*.graphml)", "*.graphml");
		FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		currentFileChooser.getExtensionFilters().add(graphmlFilter);
		currentFileChooser.getExtensionFilters().add(xmlFilter);
		File currentFile = currentFileChooser.showSaveDialog(referenceWindow.getStage());

		if (currentFile != null) {
			if (currentFile.getName().endsWith(".graphml")
					|| currentFile.getName().endsWith(".xml")) {
				saveFile("", currentFile);
			} else {
				System.out.println("Incorrect! ");
				saveFile("Woot", currentFile);
			}
		} else {
			return;
		}

		VisualisationRoot visualisationRootCurrent = new VisualisationRoot(
				referenceWindow);
		referenceWindow.setVisualisationRoot(visualisationRootCurrent);
		referenceWindow.getStage().setScene(
				new Scene(visualisationRootCurrent, 800, 600));
		visualisationRootCurrent.setCurrentParser(currentFile);
		setVisualisationFileName();

	}

	@FXML
	protected void doImport() throws IOException {

		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter graphmlFilter = new FileChooser.ExtensionFilter(
				"GraphML files (*.graphml)", "*.graphml");
		FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		FileChooser.ExtensionFilter allFilter = new FileChooser.ExtensionFilter(
				"All files", "*");
		fileChooser.getExtensionFilters().add(graphmlFilter);
		fileChooser.getExtensionFilters().add(xmlFilter);
		fileChooser.getExtensionFilters().add(allFilter);

		File file = fileChooser.showOpenDialog(referenceWindow.getStage());
		if (file != null) {
			System.out.println(file.getAbsolutePath());
		} else {
			return;
		}

		VisualisationRoot visualisationRootCurrent = new VisualisationRoot(
				referenceWindow);
		referenceWindow.setVisualisationRoot(visualisationRootCurrent);
		referenceWindow.getStage().setScene(
				new Scene(visualisationRootCurrent, 800, 600));
		visualisationRootCurrent.setCurrentParser(file);
		setVisualisationFileName();

	}

	@FXML
	protected void doReturn() {
		referenceWindow.getStage().setScene(referenceWindow.getSplashScene());
	}

	@FXML
	protected void doSearchGraph() {
		String searchText = searchBoxGraph.getText();
		Vertex currentVertex = referenceWindow.getVisualisationRoot().getCurrentParser().getSynmsForOne(searchText);
		if (currentVertex == null){System.out.println("bleh");return;}
		referenceWindow.getVisualisationRoot().setCurrentVertex(
				referenceWindow.getVisualisationRoot().runSpringOnVertex(
						currentVertex));
		referenceWindow.getVisualisationRoot().addCanvas();
	}

	@FXML
	protected void runTutorial() {
		TutorialRoot tutorialRootCurrent = new TutorialRoot(
				referenceWindow);
		referenceWindow.setTutorialRootCurrent(tutorialRootCurrent);
		referenceWindow.getStage().setScene(
				new Scene(tutorialRootCurrent, 800, 600));
	}

	@FXML
	protected void doAddWord() {
		referenceWindow.getVisualisationRoot().showPopup("add");
	}

	@FXML
	protected void doEditWord() {
		referenceWindow.getVisualisationRoot().showPopup("edit");
	}

	@FXML
	protected void doRemoveWord() {
		referenceWindow.getVisualisationRoot().showPopup("remove");
	}

	private void saveFile(String content, File file) {
		try {
			OutputStreamWriter fileWriter;
			fileWriter = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException ex) {
		}

	}

	public Pane getCanvasFullGraph() {
		return canvasFullGraph;
	}

	public Pane getCanvasDualGraph() {
		return canvasDualGraph;
	}

	public void setVisualisationFileName() {
		currentFileLabel.setText(referenceWindow.getVisualisationRoot()
				.getCurrentFile().getName());
	}

}
