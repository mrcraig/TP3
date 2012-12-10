package thesaurus.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.scene.control.Button;
import thesaurus.gui.window.MainWindow;
import thesaurus.gui.window.VisualisationRoot;

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
	
	MainWindow referenceWindow;

	public SystemController(MainWindow inputWindow){
		referenceWindow = inputWindow;
	}

	@FXML
	protected void doCreate() throws IOException {

		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter graphmlFilter = new FileChooser.ExtensionFilter("GraphML files (*.graphml)", "*.graphml");
		FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(graphmlFilter);
		fileChooser.getExtensionFilters().add(xmlFilter);
		File file = fileChooser.showSaveDialog(referenceWindow.getStage());
		if (file != null) {
			saveFile("", file);
		}
		else{
			return;
		}

		VisualisationRoot visualisationRootCurrent = new VisualisationRoot(referenceWindow);
		referenceWindow.setVisualisationRoot(visualisationRootCurrent);
		referenceWindow.getStage().setScene(new Scene(visualisationRootCurrent, 800, 600));
		visualisationRootCurrent.setCurrentParser(file);
		setVisualisationFileName();
	}

	@FXML
	protected void doImport() throws IOException {

		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter graphmlFilter = new FileChooser.ExtensionFilter("GraphML files (*.graphml)", "*.graphml");
		FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		FileChooser.ExtensionFilter allFilter = new FileChooser.ExtensionFilter("All files", "*");
		fileChooser.getExtensionFilters().add(graphmlFilter);
		fileChooser.getExtensionFilters().add(xmlFilter);
		fileChooser.getExtensionFilters().add(allFilter);

		File file = fileChooser.showOpenDialog(referenceWindow.getStage());
		if (file != null){
			System.out.println(file.getAbsolutePath());
		}
		else{
			return;
		}

		VisualisationRoot visualisationRootCurrent = new VisualisationRoot(referenceWindow);
		referenceWindow.setVisualisationRoot(visualisationRootCurrent);
		referenceWindow.getStage().setScene(new Scene(visualisationRootCurrent, 800, 600));
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
		System.out.println(referenceWindow.getVisualisationRoot().getCurrentParser().getSynmsFor(searchText));
	}

	private void saveFile(String content, File file) {
		try {
			FileWriter fileWriter = null;
			fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException ex) {}

	}
	
 	public Pane getCanvasFullGraph() {
		return canvasFullGraph;
	}

	public Pane getCanvasDualGraph() {
		return canvasDualGraph;
	}
	
	public void setVisualisationFileName(){
		currentFileLabel.setText(referenceWindow.getVisualisationRoot().getCurrentFile().getName());
	}

}
