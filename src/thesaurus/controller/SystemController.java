package thesaurus.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import thesaurus.gui.window.MainWindow;
import thesaurus.gui.window.TutorialRoot;
import thesaurus.gui.window.VisualisationRoot;
import thesaurus.parser.Vertex;

/*Controller class takes all user interaction with
 * the program and updates the model and the view
 * accordingly.
 */
public class SystemController {

	@FXML
	private Pane canvasFullGraph;

	@FXML
	private Pane canvasDualGraph;

	@FXML
	private Pane tableFullGraph;

	@FXML
	private Pane tableDualGraph;
	
	@FXML
	private Pane tut1pane;

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

	@FXML
	private ChoiceBox<String> selectionBoxGraph;

	@FXML
	private ChoiceBox<String> selectionBoxTable;

	@FXML
	private ChoiceBox<String> selectionBoxDual;
	
	@FXML
	private ChoiceBox<String> groupingGraph;

	@FXML
	private ChoiceBox<String> groupingTable;

	@FXML
	private ChoiceBox<String> groupingDual;

	@FXML
	private Label statusLabelGraph;

	@FXML
	private Label statusLabelTable;

	@FXML
	private Label statusLabelDual;

	@FXML
	private MenuBar menuBar;

	@FXML
	private ChoiceBox<String> sliderGraph;

	@FXML
	private ChoiceBox<String> sliderTable;

	@FXML
	private ChoiceBox<String> sliderDual;

	MainWindow referenceWindow;

	HashMap<String, Label> lookupHashMap;

	public SystemController(MainWindow inputWindow) {
		referenceWindow = inputWindow;
	}

	/*Used to create new files*/
	@FXML
	protected void doCreate() throws IOException {

		FileChooser currentFileChooser = getFileChooser();
		File currentFile = currentFileChooser.showSaveDialog(referenceWindow
				.getStage());

		if (currentFile != null) {
			if (currentFile.getName().endsWith(".graphml")) {
				saveFile("Woot", currentFile);
			} else {
				String appendedString = currentFile.getAbsolutePath() + ".graphml";
				currentFile = new File(appendedString);
				saveFile("Woot", currentFile);
			}
		} else {
			return;
		}
		
		if (referenceWindow.getCurrentRecentArray().contains(
				currentFile.getAbsolutePath())) {
			referenceWindow.getCurrentRecentArray().remove(
					currentFile.getAbsolutePath());
		}
		
		OutputStreamWriter fileWriter;
		fileWriter = new OutputStreamWriter(new FileOutputStream(currentFile),"UTF-8");
		fileWriter.write("<graphml><graph></graph></graphml>");
		fileWriter.close();
		referenceWindow.getCurrentRecentArray().add(currentFile.getAbsolutePath());
		referenceWindow.getSplashRoot().writeToRecentFile();

		VisualisationRoot visualisationRootCurrent = new VisualisationRoot(
				referenceWindow);
		referenceWindow.setVisualisationRoot(visualisationRootCurrent);
		referenceWindow.getStage()
				.setScene(new Scene(visualisationRootCurrent));
		referenceWindow.setCurrentScene(referenceWindow.getStage().getScene());
		visualisationRootCurrent.setCurrentParser(currentFile);
		setVisualisationFileName();
		
		setEnterKeyEvents();

		setSelectionBoxDefault();

		setUserFeedbackEvents();
		
		setSelectionBoxHandlers();
		
		setSliderHandlers();
		
		mainTabWindow.getSelectionModel().select(2);

	}

	/*Used to import existing files*/
	@FXML
	protected void doImport() throws IOException {

		FileChooser currentFileChooser = getFileChooser();
		File file = currentFileChooser.showOpenDialog(referenceWindow
				.getStage());
		if (file != null) {
			System.out.println("");
		} else {
			return;
		}
		if (referenceWindow.getCurrentRecentArray().contains(
				file.getAbsolutePath())) {
			referenceWindow.getCurrentRecentArray().remove(
					file.getAbsolutePath());
		}
		referenceWindow.getCurrentRecentArray().add(file.getAbsolutePath());
		referenceWindow.getSplashRoot().writeToRecentFile();

		VisualisationRoot visualisationRootCurrent = new VisualisationRoot(
				referenceWindow);
		referenceWindow.setVisualisationRoot(visualisationRootCurrent);
		referenceWindow.getStage()
				.setScene(new Scene(visualisationRootCurrent));
		referenceWindow.setCurrentScene(referenceWindow.getStage().getScene());
		visualisationRootCurrent.setCurrentParser(file);
		setVisualisationFileName();

		setEnterKeyEvents();

		setSelectionBoxDefault();

		setUserFeedbackEvents();
		
		setSelectionBoxHandlers();
		
		setSliderHandlers();
		
		mainTabWindow.getSelectionModel().select(2);
		
		referenceWindow.getVisualisationRoot().initialSearch();

	}

	/*Used to open recent files from main window*/
	private void doOpenRecent(int index) throws IOException {
		File file = new File(referenceWindow.getCurrentRecentArray().get(
				reverseIndex(index + 1) - 1));
		if(!file.exists()){
			referenceWindow.getCurrentRecentArray().remove(reverseIndex(index + 1) - 1);
			populateList();
			referenceWindow.getSplashRoot().writeToRecentFile();
			referenceWindow.getSplashRoot().showPopup("fileError");
			return;
		}
		if (referenceWindow.getCurrentRecentArray().contains(
				file.getAbsolutePath())) {
			referenceWindow.getCurrentRecentArray().remove(
					file.getAbsolutePath());
		}
		referenceWindow.getCurrentRecentArray().add(file.getAbsolutePath());
		referenceWindow.getSplashRoot().writeToRecentFile();

		VisualisationRoot visualisationRootCurrent = new VisualisationRoot(
				referenceWindow);
		referenceWindow.setVisualisationRoot(visualisationRootCurrent);
		referenceWindow.getStage()
				.setScene(new Scene(visualisationRootCurrent));
		referenceWindow.setCurrentScene(referenceWindow.getStage().getScene());
		visualisationRootCurrent.setCurrentParser(file);
		setVisualisationFileName();

		setEnterKeyEvents();

		setSelectionBoxDefault();

		setUserFeedbackEvents();
		
		setSelectionBoxHandlers();

		setSliderHandlers();
		
		mainTabWindow.getSelectionModel().select(2);
		
		referenceWindow.getVisualisationRoot().initialSearch();

	}

	private void setUserFeedbackEvents() {
		lookupHashMap = new HashMap<String, Label>();
		lookupHashMap.put("graph", statusLabelGraph);
		lookupHashMap.put("table", statusLabelTable);
		lookupHashMap.put("dual", statusLabelDual);
	}

	/*Set default user buttons, drop down menus and such*/
	private void setSelectionBoxDefault() {
		selectionBoxGraph.getSelectionModel().select(0);
		selectionBoxTable.getSelectionModel().select(0);
		selectionBoxDual.getSelectionModel().select(0);
		groupingGraph.getSelectionModel().select(1);
		groupingTable.getSelectionModel().select(1);
		groupingDual.getSelectionModel().select(1);
		sliderGraph.getSelectionModel().select(4);
		sliderTable.getSelectionModel().select(4);
		sliderDual.getSelectionModel().select(4);
	}

	/*Add event handlers to GUI*/
	private void setSelectionBoxHandlers(){
		
		

		selectionBoxGraph.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue ov, Number value,
							Number new_value) {
						selectionBoxDual.getSelectionModel().select(selectionBoxGraph.getSelectionModel().getSelectedIndex());
						selectionBoxTable.getSelectionModel().select(selectionBoxGraph.getSelectionModel().getSelectedIndex());
						if(selectionBoxGraph.getSelectionModel().getSelectedIndex() == 1){
							referenceWindow.getVisualisationRoot().getStateArray().set(0, 1);
							referenceWindow.getVisualisationRoot().getStateArray().set(1, 0);
						}
						else if(selectionBoxGraph.getSelectionModel().getSelectedIndex() == 2){
							referenceWindow.getVisualisationRoot().getStateArray().set(0, 0);
							referenceWindow.getVisualisationRoot().getStateArray().set(1, 1);
						}
						else{
							referenceWindow.getVisualisationRoot().getStateArray().set(0, 1);
							referenceWindow.getVisualisationRoot().getStateArray().set(1, 1);
						}
						referenceWindow.getVisualisationRoot().doSearchRefresh();
					}
				});
		
		selectionBoxDual.getSelectionModel().selectedIndexProperty()
		.addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value,
					Number new_value) {
				selectionBoxGraph.getSelectionModel().select(selectionBoxDual.getSelectionModel().getSelectedIndex());
				selectionBoxTable.getSelectionModel().select(selectionBoxDual.getSelectionModel().getSelectedIndex());
				if(selectionBoxGraph.getSelectionModel().getSelectedIndex() == 1){
					referenceWindow.getVisualisationRoot().getStateArray().set(0, 1);
					referenceWindow.getVisualisationRoot().getStateArray().set(1, 0);
				}
				else if(selectionBoxGraph.getSelectionModel().getSelectedIndex() == 2){
					referenceWindow.getVisualisationRoot().getStateArray().set(0, 0);
					referenceWindow.getVisualisationRoot().getStateArray().set(1, 1);
				}
				else{
					referenceWindow.getVisualisationRoot().getStateArray().set(0, 1);
					referenceWindow.getVisualisationRoot().getStateArray().set(1, 1);
				}
				referenceWindow.getVisualisationRoot().doSearchRefresh();
			}
		});
		
		selectionBoxTable.getSelectionModel().selectedIndexProperty()
		.addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value,
					Number new_value) {
				selectionBoxGraph.getSelectionModel().select(selectionBoxTable.getSelectionModel().getSelectedIndex());
				selectionBoxTable.getSelectionModel().select(selectionBoxTable.getSelectionModel().getSelectedIndex());
				if(selectionBoxGraph.getSelectionModel().getSelectedIndex() == 1){
					referenceWindow.getVisualisationRoot().getStateArray().set(0, 1);
					referenceWindow.getVisualisationRoot().getStateArray().set(1, 0);
				}
				else if(selectionBoxGraph.getSelectionModel().getSelectedIndex() == 2){
					referenceWindow.getVisualisationRoot().getStateArray().set(0, 0);
					referenceWindow.getVisualisationRoot().getStateArray().set(1, 1);
				}
				else{
					referenceWindow.getVisualisationRoot().getStateArray().set(0, 1);
					referenceWindow.getVisualisationRoot().getStateArray().set(1, 1);
				}
				referenceWindow.getVisualisationRoot().doSearchRefresh();
			}
		});
		
		groupingGraph.getSelectionModel().selectedIndexProperty()
		.addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value,
					Number new_value) {
				groupingDual.getSelectionModel().select(groupingGraph.getSelectionModel().getSelectedIndex());
				groupingTable.getSelectionModel().select(groupingGraph.getSelectionModel().getSelectedIndex());
				if(groupingGraph.getSelectionModel().getSelectedIndex() == 1){
					referenceWindow.getVisualisationRoot().getStateArray().set(2, 0);
				}
				else{
					referenceWindow.getVisualisationRoot().getStateArray().set(2, 1);
				}
				referenceWindow.getVisualisationRoot().doSearchRefresh();
			}
		});
		
		groupingDual.getSelectionModel().selectedIndexProperty()
		.addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value,
					Number new_value) {
				groupingGraph.getSelectionModel().select(groupingDual.getSelectionModel().getSelectedIndex());
				groupingTable.getSelectionModel().select(groupingDual.getSelectionModel().getSelectedIndex());
				if(groupingDual.getSelectionModel().getSelectedIndex() == 1){
					referenceWindow.getVisualisationRoot().getStateArray().set(2, 0);
				}
				else{
					referenceWindow.getVisualisationRoot().getStateArray().set(2, 1);
				}
				referenceWindow.getVisualisationRoot().doSearchRefresh();
			}
		});
		
		groupingTable.getSelectionModel().selectedIndexProperty()
		.addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value,
					Number new_value) {
				groupingGraph.getSelectionModel().select(groupingTable.getSelectionModel().getSelectedIndex());
				groupingTable.getSelectionModel().select(groupingTable.getSelectionModel().getSelectedIndex());
				if(groupingTable.getSelectionModel().getSelectedIndex() == 1){
					referenceWindow.getVisualisationRoot().getStateArray().set(2, 0);
				}
				else{
					referenceWindow.getVisualisationRoot().getStateArray().set(2, 1);
				}
				referenceWindow.getVisualisationRoot().doSearchRefresh();
			}
		});
		

	}

	/*Add event handlers*/
	private void setSliderHandlers() {
		
		sliderGraph.getSelectionModel().selectedIndexProperty()
		.addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value,
					Number new_value) {
				referenceWindow.getVisualisationRoot().getStateArray().set(3, (sliderGraph.getSelectionModel().getSelectedIndex()+1));
				referenceWindow.getVisualisationRoot().doSearchRefresh();
				sliderTable.getSelectionModel().select(sliderGraph.getSelectionModel().getSelectedIndex());
				sliderDual.getSelectionModel().select(sliderGraph.getSelectionModel().getSelectedIndex());
			}
		});
		
		sliderTable.getSelectionModel().selectedIndexProperty()
		.addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value,
					Number new_value) {
				referenceWindow.getVisualisationRoot().getStateArray().set(3, (sliderGraph.getSelectionModel().getSelectedIndex()+1));
				referenceWindow.getVisualisationRoot().doSearchRefresh();
				sliderGraph.getSelectionModel().select(sliderTable.getSelectionModel().getSelectedIndex());
				sliderDual.getSelectionModel().select(sliderTable.getSelectionModel().getSelectedIndex());
			}
		});
		
		sliderDual.getSelectionModel().selectedIndexProperty()
		.addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value,
					Number new_value) {
				referenceWindow.getVisualisationRoot().getStateArray().set(3, (sliderGraph.getSelectionModel().getSelectedIndex()+1));
				referenceWindow.getVisualisationRoot().doSearchRefresh();
				sliderTable.getSelectionModel().select(sliderDual.getSelectionModel().getSelectedIndex());
				sliderGraph.getSelectionModel().select(sliderDual.getSelectionModel().getSelectedIndex());
			}
		});
		
	}

	/*Helper function to reverse index for recent files*/
	private int reverseIndex(int currentIndex) {
		return (referenceWindow.getCurrentRecentArray().size() + 1)
				- currentIndex;
	}

	/*Add event handlers*/
	private void setEnterKeyEvents() {

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

	/*Search graph based on user interaction*/
	private void doSearchGraph(String choiceString) {
		String searchText = null;
		if (choiceString.equals("graph")) {
			searchText = searchBoxGraph.getText();
		} else if (choiceString.equals("table")) {
			searchText = searchBoxTable.getText();
		} else if (choiceString.equals("dual")) {
			searchText = searchBoxDual.getText();
		}
		Vertex currentVertex = referenceWindow.getVisualisationRoot()
				.getCurrentParser().getVertexFromWord(searchText);
		if (currentVertex == null) {
			lookupHashMap.get(choiceString).setText(
					String.format("Can't find \"%s\"", searchText));
			return;
		}
		lookupHashMap.get(choiceString).setText(
				String.format("The word \"%s\" has been found", searchText));
		referenceWindow.getVisualisationRoot().setCurrentVertex(
				referenceWindow.getVisualisationRoot().runSpringOnVertex(
						currentVertex));
		referenceWindow.getVisualisationRoot().addCanvas();
		referenceWindow.getVisualisationRoot().addTable();
	}

	/*Return to main menu*/
	@FXML
	protected void doReturn() throws IOException {
		referenceWindow.getStage().setScene(referenceWindow.getSplashScene());
		referenceWindow.setCurrentScene(referenceWindow.getStage().getScene());
		populateList();
	}

	@FXML
	protected void doRunTutorial() {
		TutorialRoot tutorialRootCurrent = new TutorialRoot(referenceWindow);
		referenceWindow.setTutorialRootCurrent(tutorialRootCurrent);
		referenceWindow.getStage().setScene(new Scene(tutorialRootCurrent));
	}
	
	@FXML
	protected void doRefreshGraph() {
		referenceWindow.getVisualisationRoot().doSearchRefresh();
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
	
	@FXML
	protected void doFullScreen() {
		referenceWindow.getVisualisationRoot().fullScreen();
	}

	/*Save file function*/
	private void saveFile(String content, File file) {
		try {
			OutputStreamWriter fileWriter;
			fileWriter = new OutputStreamWriter(new FileOutputStream(file),
					"UTF-8");
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

	public Pane getTableFullGraph() {
		return tableFullGraph;
	}

	public Pane getTableDualGraph() {
		return tableDualGraph;
	}
	
	public Pane getTutOnePane() {
		return tut1pane;
	}

	private void setVisualisationFileName() {
		currentFileLabel.setText(referenceWindow.getVisualisationRoot()
				.getCurrentFile().getName());
	}

	/*File chooser for GUI is generated to reduce code overlap*/
	private FileChooser getFileChooser() {
		FileChooser myFileChooser = new FileChooser();
		FileChooser.ExtensionFilter graphmlFilter = new FileChooser.ExtensionFilter(
				"GraphML files (*.graphml)", "*.graphml");
		myFileChooser.getExtensionFilters().add(graphmlFilter);
		return myFileChooser;
	}

	/*Populate recent array list*/
	public void populateList() {
		Collections.reverse(referenceWindow.getCurrentRecentArray());
		ArrayList<String> parsedArray = new ArrayList<String>();
		for (String s : referenceWindow.getCurrentRecentArray()) {
			File temp = new File(s);
			parsedArray.add(temp.getName());
		}
		ObservableList<String> parsedList = FXCollections
				.observableList(parsedArray);
		currentListView.setEditable(true);
		currentListView.setItems(parsedList);
		Collections.reverse(referenceWindow.getCurrentRecentArray());
		referenceWindow.setCurrentRecentList(parsedList);
	}

	/*Add event handlers*/
	public void addListenerListView() {

		currentListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						try {
							doOpenRecent(currentListView.getSelectionModel()
									.getSelectedIndex());
						} catch (IOException e) {
						}
					}
				}
			}
		});

	}
	
	@FXML
	private void handleCreateAction(final ActionEvent event) throws IOException {
		doCreate();
	}
	
	@FXML
	private void handleImportAction(final ActionEvent event) throws IOException {
		doImport();
	}
	
	@FXML
	private void handleAboutAction(final ActionEvent event) {
		Popup currentPopup = referenceWindow.getPopupFactory().getPopup("about");
		currentPopup.show(referenceWindow.getStage());
		currentPopup.setY(currentPopup.getY()+15);
	}

	@FXML
	private void handleExitAction(final ActionEvent event) {
		System.exit(0);
	}
	
	/*Used for colour blind toggling*/
	@FXML
	private void handleColourScheme(final ActionEvent event) {
		ArrayList<Integer> referenceArray = referenceWindow.getVisualisationRoot().getStateArray();
		referenceArray.set(4, (1-referenceArray.get(4)));
		referenceWindow.getVisualisationRoot().doSearchRefresh();
	}
	
	@FXML
	private void tutorialNext (final ActionEvent event) {
		referenceWindow.getTutorialRootCurrent().next();
	}
	
	@FXML
	private void tutorialPrev (final ActionEvent event) {
		referenceWindow.getTutorialRootCurrent().previous();
	}
	
	@FXML
	private void tutorialExit (final ActionEvent event) {
		referenceWindow.getTutorialRootCurrent().exit();
	}
	
	@FXML
	private void runTutorial (final ActionEvent event) {
		doRunTutorial();
	}
}
