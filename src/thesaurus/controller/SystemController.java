package thesaurus.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
	private TextField searchBoxTable;
	
	@FXML
	private TextField searchBoxDual;

	@FXML
	private Text currentFileLabel;

	@FXML
	private Text searchStatusLabel;
	
	@FXML
	private ListView<String> currentListView;

	MainWindow referenceWindow;

	public SystemController(MainWindow inputWindow) {
		referenceWindow = inputWindow;
	}

	@FXML
	protected void doCreate() throws IOException {

		FileChooser currentFileChooser = getFileChooser();
		File currentFile = currentFileChooser.showSaveDialog(referenceWindow.getStage());

		if (currentFile != null) {
			if (currentFile.getName().endsWith(".graphml") || currentFile.getName().endsWith(".xml")) {
				saveFile("Woot", currentFile);
			} else {
				System.out.println("Incorrect!");
				saveFile("Woot", currentFile);
			}
		} else {
			return;
		}

		VisualisationRoot visualisationRootCurrent = new VisualisationRoot(referenceWindow);
		referenceWindow.setVisualisationRoot(visualisationRootCurrent);
		referenceWindow.getStage().setScene(new Scene(visualisationRootCurrent, 800, 600));
		visualisationRootCurrent.setCurrentParser(currentFile);
		setVisualisationFileName();
		
		setSearchBoxEvents();

	}

	@FXML
	protected void doImport() throws IOException {

		FileChooser currentFileChooser = getFileChooser();
		File file = currentFileChooser.showOpenDialog(referenceWindow.getStage());
		if (file != null) {
			//System.out.println(file.getAbsolutePath());
		} else {
			return;
		}
		if(referenceWindow.getCurrentRecentArray().contains(file.getAbsolutePath())){
			referenceWindow.getCurrentRecentArray().remove(file.getAbsolutePath());
		}
		referenceWindow.getCurrentRecentArray().add(file.getAbsolutePath());

		VisualisationRoot visualisationRootCurrent = new VisualisationRoot(referenceWindow);
		referenceWindow.setVisualisationRoot(visualisationRootCurrent);
		referenceWindow.getStage().setScene(new Scene(visualisationRootCurrent, 800, 600));
		visualisationRootCurrent.setCurrentParser(file);
		setVisualisationFileName();
		
		setSearchBoxEvents();

	}
	
	private void doOpenRecent(int index) throws IOException {

		File file = new File(referenceWindow.getCurrentRecentArray().get(reverseIndex(index+1)-1));
		if(referenceWindow.getCurrentRecentArray().contains(file.getAbsolutePath())){
			referenceWindow.getCurrentRecentArray().remove(file.getAbsolutePath());
		}
		referenceWindow.getCurrentRecentArray().add(file.getAbsolutePath());

		VisualisationRoot visualisationRootCurrent = new VisualisationRoot(referenceWindow);
		referenceWindow.setVisualisationRoot(visualisationRootCurrent);
		referenceWindow.getStage().setScene(new Scene(visualisationRootCurrent, 800, 600));
		visualisationRootCurrent.setCurrentParser(file);
		setVisualisationFileName();
		
		setSearchBoxEvents();

	}
	
	private int reverseIndex(int currentIndex){
		return (referenceWindow.getCurrentRecentArray().size()+1)-currentIndex;
	}

	private void setSearchBoxEvents() {

		searchBoxGraph.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyInput) {
				if (keyInput.getCode().equals(KeyCode.ENTER)) {
					doSearchGraph("graph");
				}
			}
		});
		
		searchBoxTable.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyInput) {
				if (keyInput.getCode().equals(KeyCode.ENTER)) {
					doSearchGraph("table");
				}
			}
		});
		
		searchBoxDual.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyInput) {
				if (keyInput.getCode().equals(KeyCode.ENTER)) {
					doSearchGraph("dual");
				}
			}
		});
		
	}
	
	@FXML
	protected void doSearchGraphG() {
		doSearchGraph("graph");
	}
	
	@FXML
	protected void doSearchGraphT() {
		doSearchGraph("table");
	}
	
	@FXML
	protected void doSearchGraphD() {
		doSearchGraph("dual");
	}

	private void doSearchGraph(String choiceString) {
		String searchText = null;
		if(choiceString.equals("graph")){
			searchText = searchBoxGraph.getText();
		}
		else if(choiceString.equals("table")){
			searchText = searchBoxTable.getText();
		}
		else if(choiceString.equals("dual")){
			searchText = searchBoxDual.getText();
		}
		Vertex currentVertex = referenceWindow.getVisualisationRoot().getCurrentParser().getOneSynomyn(searchText);
		if (currentVertex == null) {
			return;
		}
		referenceWindow.getVisualisationRoot().setCurrentVertex(referenceWindow.getVisualisationRoot().runSpringOnVertex(currentVertex));
		referenceWindow.getVisualisationRoot().addCanvas();
	}
	
	@FXML
	protected void doReturn() throws IOException {
		referenceWindow.getStage().setScene(referenceWindow.getSplashScene());
		populateList();
		referenceWindow.getSplashRoot().writeToRecentFile();
	}

	@FXML
	protected void doRunTutorial() {
		TutorialRoot tutorialRootCurrent = new TutorialRoot(referenceWindow);
		referenceWindow.setTutorialRootCurrent(tutorialRootCurrent);
		referenceWindow.getStage().setScene(new Scene(tutorialRootCurrent, 800, 600));
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

	private void setVisualisationFileName() {
		currentFileLabel.setText(referenceWindow.getVisualisationRoot().getCurrentFile().getName());
	}
	
	private FileChooser getFileChooser(){
		FileChooser myFileChooser = new FileChooser();
		FileChooser.ExtensionFilter graphmlFilter = new FileChooser.ExtensionFilter("GraphML files (*.graphml)", "*.graphml");
		FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		myFileChooser.getExtensionFilters().add(graphmlFilter);
		myFileChooser.getExtensionFilters().add(xmlFilter);
		return myFileChooser;
	}
	
	public void populateList(){
		Collections.reverse(referenceWindow.getCurrentRecentArray());
		ArrayList<String> parsedArray = new ArrayList<String>();
		for(String s: referenceWindow.getCurrentRecentArray()){
			File temp = new File(s);
			parsedArray.add(temp.getName());
		}
		ObservableList<String> parsedList = FXCollections.observableList(parsedArray);
		currentListView.setEditable(true);
		currentListView.setItems(parsedList);
		Collections.reverse(referenceWindow.getCurrentRecentArray());
		referenceWindow.setCurrentRecentList(parsedList);
	}
	
	public void addListenerListView(){
		
		currentListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
		            if(mouseEvent.getClickCount() == 2){
		            	try {doOpenRecent(currentListView.getSelectionModel().getSelectedIndex());} catch (IOException e) {}
		            }
		        }
		    }
		});
		
	}

}
