package thesaurus.gui.window;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Screen;
import thesaurus.controller.SystemController;
import thesaurus.gui.canvas.ViewGraph;
import thesaurus.gui.table.ViewTable;
import thesaurus.parser.*;
import thesaurus.spring.FrSpring;

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
	private ViewGraph displayGraphFull;
	private ViewGraph displayGraphDual;
	private ArrayList<Integer> state;
	private Popup fullScreenPopup;
	Rectangle2D screenBounds;
	int popupWidth;
	int popupHeight;
	StackPane displayPane;
    ScrollPane returnGraph;
    Label escapePopupLabel;
    boolean fullScreen;

	public VisualisationRoot(MainWindow inputWindow) throws IOException {
		
		state = new ArrayList<Integer>();
		state.add(1);
		state.add(1);
		state.add(0);
		state.add(5);
		state.add(0);
		
		fullScreenPopup = new Popup();
		screenBounds = Screen.getPrimary().getVisualBounds();
		popupWidth = (int)screenBounds.getWidth() - 100;
		popupHeight = (int)screenBounds.getHeight() - 100;
		displayPane = new StackPane();
	    displayPane.setStyle("-fx-background-color: black;");
	    displayPane.setPrefSize(popupWidth+50, popupHeight+50);
	    setLabel();
	    
	    currentVertex = null;

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

	private void setLabel() {
	    escapePopupLabel = new Label("Press Escape Or Click Here To Close Window");
	    escapePopupLabel.setTextFill(Color.web("#ffffff"));
	    escapePopupLabel.relocate((popupWidth-200)/2, 3);
	    escapePopupLabel.setFont(Font.font("Arial", 14));
	    escapePopupLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, 
                new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						displayPane.getChildren().removeAll(displayPane.getChildren());
						fullScreenPopup.getContent().removeAll(fullScreenPopup.getContent());
						fullScreenPopup.hide();
						fullScreen = false;
					}
                    
                });		
	}

	public void addCanvas() {
		currentController.getCanvasFullGraph().getChildren().removeAll(currentController.getCanvasFullGraph().getChildren());
		currentController.getCanvasDualGraph().getChildren().removeAll(currentController.getCanvasDualGraph().getChildren());
		displayGraphFull = new ViewGraph(757, 375,currentVertex, referenceWindow.getVisualisationRoot(),state.get(0),state.get(1),state.get(4));
		displayGraphDual = new ViewGraph(354, 362,currentVertex, referenceWindow.getVisualisationRoot(),state.get(0),state.get(1),state.get(4));
		currentController.getCanvasFullGraph().getChildren().add(displayGraphFull.returnGraph());
		currentController.getCanvasDualGraph().getChildren().add(displayGraphDual.returnGraph());
	}

	public void addTable() {
		currentController.getTableFullGraph().getChildren().removeAll(currentController.getTableFullGraph().getChildren());
		currentController.getTableDualGraph().getChildren().removeAll(currentController.getTableDualGraph().getChildren());
		ViewTable displayTableFull = new ViewTable(728, 366,currentVertex, referenceWindow.getVisualisationRoot());
		ViewTable displayTableDual = new ViewTable(354, 362,currentVertex, referenceWindow.getVisualisationRoot());
		currentController.getTableFullGraph().getChildren().add(displayTableFull.getTable());
		currentController.getTableDualGraph().getChildren().add(displayTableDual.getTable());
	}

	public void setCurrentParser(File inputFile) {
		setCurrentFile(inputFile);
		currentParser = new InternalRepresentation(inputFile);
	}

	public File getCurrentFile() {
		return currentFile;
	}

	public void setCurrentFile(File currentFileInput) {
		currentFile = currentFileInput;
	}

	public void setCurrentVertex(Vertex currentVertexInput) {
		currentVertex = currentVertexInput;
	}

	public InternalRepresentation getCurrentParser() {
		return currentParser;
	}

	public void showPopup(String inputChoice) {
		if(currentPopup != null) {
			currentPopup.hide();
			currentPopup = null;
		}
		PopupFactory currentPopupFactory = new PopupFactory(inputChoice, referenceWindow);
		currentPopup = currentPopupFactory.getPopup();
		currentPopup.show(referenceWindow.getStage());
		currentPopup.setY(currentPopup.getY()+15);
	}

	public Vertex runSpringOnVertex(Vertex inputVertex) {
		FrSpring currentSpring = new FrSpring(inputVertex,state.get(0),state.get(1),state.get(2),state.get(3));
		return currentSpring.getCoordinates();
	}

	public void initialSearch() {
		setCurrentVertex(currentParser.getFirst());
		if (currentVertex == null) {
			return;
		}
		setCurrentVertex(referenceWindow.getVisualisationRoot().runSpringOnVertex(currentVertex));
		referenceWindow.getVisualisationRoot().addCanvas();
		referenceWindow.getVisualisationRoot().addTable();
	}

	public void doClickSearchGraph(String inputString) {
		setCurrentVertex(getCurrentParser().getOneSynomyn(inputString));
		if (currentVertex == null) {
			return;
		}
		setCurrentVertex(referenceWindow.getVisualisationRoot().runSpringOnVertex(currentVertex));
		referenceWindow.getVisualisationRoot().addCanvas();
		referenceWindow.getVisualisationRoot().addTable();
		if(fullScreen){
			fullScreen();
		}
	}

	public void doSearchRefresh() {
		if(currentVertex == null){
			return;
		}
		Vertex replacementVertex = getCurrentParser().getOneSynomyn(currentVertex.getWord());
		setCurrentVertex(referenceWindow.getVisualisationRoot().runSpringOnVertex(replacementVertex));
		referenceWindow.getVisualisationRoot().addCanvas();
		referenceWindow.getVisualisationRoot().addTable();
	}
	
	public ArrayList<Integer> getStateArray(){
		return state;
	}

	public Vertex getCurrentVertex() {
		return currentVertex;
	}

	public ViewGraph getFullGraph() {
		return displayGraphFull;
	}

	public ViewGraph getDualGraph() {
		return displayGraphDual;
	}
	
	public void fullScreen(){
		if(currentVertex == null){
			return;
		}
		if(fullScreen){
			displayPane.getChildren().removeAll(displayPane.getChildren());
			fullScreenPopup.getContent().removeAll(fullScreenPopup.getContent());
		}
		ViewGraph tempC = new ViewGraph(popupWidth, popupHeight,currentVertex, referenceWindow.getVisualisationRoot(),state.get(0),state.get(1),state.get(2));
	    returnGraph = tempC.returnGraph();
	    displayPane.getChildren().add(returnGraph);
		fullScreenPopup.getContent().add(displayPane);
		fullScreenPopup.getContent().add(escapePopupLabel);
		fullScreenPopup.show(referenceWindow.getStage());
		fullScreen = true;
	}
	
}
