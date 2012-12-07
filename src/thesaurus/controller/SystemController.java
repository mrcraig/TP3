package thesaurus.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import thesaurus.gui.window.MainWindow;
import thesaurus.gui.window.VisualisationRoot;

public class SystemController {
	
    @FXML
    private Pane canvasFullGraph;
    
    @FXML
    private Pane canvasDualGraph;
    
    @FXML
    private TabPane mainTabWindow;
	
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
			saveFile("test", file);
		}

		VisualisationRoot visualisationRootCurrent = new VisualisationRoot(referenceWindow);
		referenceWindow.getStage().setScene(new Scene(visualisationRootCurrent, 800, 600));
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
			Scanner fileScanner = null;
			try {
				fileScanner = new Scanner(file);
			} catch (FileNotFoundException e1) {
				System.out.println();
			}
			if (fileScanner != null) {
				//System.out.println(fileScanner.next());
			}
		}
		
		VisualisationRoot visualisationRootCurrent = new VisualisationRoot(referenceWindow);		
		referenceWindow.getStage().setScene(new Scene(visualisationRootCurrent, 800, 600));
	}
	
	@FXML
	protected void doReturn() {
		referenceWindow.getStage().setScene(referenceWindow.getSplashScene());
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

}
