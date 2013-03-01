package thesaurus.gui.window;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Popup;


public class PopupFactory {

	Popup currentPopup = null;
	MainWindow referenceWindow;

	public PopupFactory(String inputChoice, MainWindow inputWindow) {

		referenceWindow = inputWindow;
		if (inputChoice.equals("add")) {
			currentPopup = new Popup();
			currentPopup.getContent().add(makeCanvasAdd());
		} else if (inputChoice.equals("edit")) {
			currentPopup = new Popup();
			currentPopup.getContent().add(makeCanvasEdit());
		} else if (inputChoice.equals("remove")) {
			currentPopup = new Popup();
			currentPopup.getContent().add(makeCanvasRemove());
		} else if (inputChoice.equals("fileError")) {
			currentPopup = new Popup();
			currentPopup.getContent().add(makeCanvasFileError());
		} else if (inputChoice.equals("about")) {
			currentPopup = new Popup();
			currentPopup.getContent().add(makeAbout());
		}
	}

	private Pane makeCanvasAdd() {
		Pane canvas = getPane(200,200);
		Text addWordLabel = getText(35,10,"Add Word",2);
		Text promptWordLabel = getText(10,52,"Word: ",1);
		final TextField addWordInput = getTextField(70, 50, 120);
		Text promptSynLabel = getText(10,82,"Synonyms: ",1);
		final TextField addSynInput = getTextField(70, 80, 120);
		Text promptAntLabel = getText(10,112,"Antonyms: ",1);
		final TextField addAntInput = getTextField(70, 110, 120);
		Button confirmButton = new Button();
		confirmButton.setText("Confirm");
		confirmButton.relocate(30, 160);
		confirmButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				referenceWindow.getVisualisationRoot().getCurrentParser().addVertex(addWordInput.getText(), addSynInput.getText(),addAntInput.getText());
				System.out.printf("\n\n=========== %s  ======== %s\n\n",addWordInput.getText(), addSynInput.getText());
				currentPopup.hide();
				currentPopup = null;
				referenceWindow.getVisualisationRoot().doClickSearchGraph(addWordInput.getText());
				referenceWindow.getVisualisationRoot().addCanvas();
				referenceWindow.getVisualisationRoot().addTable();
			}
		});
		Button cancelButton = new Button();
		cancelButton.setText("Cancel");
		cancelButton.relocate(100, 160);
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				currentPopup.hide();
				currentPopup = null;
			}
		});
		canvas.getChildren().addAll(addWordLabel, promptWordLabel,
		                            addWordInput, promptSynLabel, addSynInput, promptAntLabel,
		                            addAntInput, confirmButton, cancelButton);
		canvas.setStyle("	-fx-background-color: #dfdfdf;"
		                + "-fx-border-color: black;" + "-fx-border-width: 1px;" + "-fx-font-family: 'Arial';");
		return canvas;
	}

	private Pane makeCanvasFileError() {
		Pane canvas = getPane(150,80);
		Text confirmLabel = getText(25,10,"File Does Not Exist",1);
		Button confirmButton = new Button();
		confirmButton.setText("Confirm");
		confirmButton.relocate(40, 42);
		confirmButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				currentPopup.hide();
				currentPopup = null;
			}
		});
		canvas.getChildren().addAll(confirmLabel, confirmButton);
		canvas.setStyle("	-fx-background-color: #dfdfdf;"
		                + "-fx-border-color: black;" + "-fx-border-width: 1px;" + "-fx-font-family: 'Arial';");
		return canvas;
	}

	private Pane makeAbout() {
		Pane canvas = getPane(150,80);
		Text one_Label = getText(8,10,"Graphical Thesaurus",1);
		Text two_Label = getText(35,26,"by Team O",1);
		Button confirmButton = new Button();
		confirmButton.setText("OK");
		confirmButton.relocate(55, 45);
		confirmButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				currentPopup.hide();
				currentPopup = null;
			}
		});
		canvas.getChildren().addAll(one_Label, two_Label, confirmButton);
		canvas.setStyle("	-fx-background-color: #dfdfdf;"
		                + "-fx-border-color: black;" + "-fx-border-width: 1px;" + "-fx-font-family: 'Arial';");
		return canvas;
	}

	private Pane makeCanvasEdit() {
		Pane canvas = getPane(200,200);
		Text addWordLabel = getText(35,10,"Edit Word",2);
		Text promptWordLabel = getText(10,52,"Word: ",1);
		final TextField addWordInput = getTextField(70, 50, 120);
		Text promptSynLabel = getText(10,82,"Synonyms: ",1);
		final TextField addSynInput = getTextField(70, 80, 120);
		Text promptAntLabel = getText(10,112,"Antonyms: ",1);
		final TextField addAntInput = getTextField(70, 110, 120);
		Button confirmButton = new Button();
		confirmButton.setText("Confirm");
		confirmButton.relocate(30, 160);
		confirmButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String newWord = addWordInput.getText();
				String oldWord = referenceWindow.getVisualisationRoot().getCurrentVertex().getWord();
				if(!oldWord.equalsIgnoreCase(newWord)) {
					referenceWindow.getVisualisationRoot().getCurrentParser().editVertex(oldWord, newWord);
				}
				referenceWindow.getVisualisationRoot().getCurrentParser().addVertex(newWord, addSynInput.getText(), addAntInput.getText());
				currentPopup.hide();
				currentPopup = null;
			}
		});
		Button cancelButton = new Button();
		cancelButton.setText("Cancel");
		cancelButton.relocate(100, 160);
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				currentPopup.hide();
				currentPopup = null;
			}
		});
		canvas.getChildren().addAll(addWordLabel, promptWordLabel,
		                            addWordInput, promptSynLabel, addSynInput, promptAntLabel,
		                            addAntInput, confirmButton, cancelButton);
		canvas.setStyle("	-fx-background-color: #dfdfdf;"
		                + "-fx-border-color: black;" + "-fx-border-width: 1px;" + "-fx-font-family: 'Arial';");
		return canvas;
	}

	private Pane makeCanvasRemove() {
		Pane canvas = getPane(200,200);
		Text addWordLabel = getText(45,10,"Remove Word",2);
		Text promptWordLabel = getText(10,52,"Word: ",1);
		final TextField addWordInput = getTextField(70, 50, 120);
		Button confirmButton = new Button();
		confirmButton.setText("Confirm");
		confirmButton.relocate(30, 160);
		confirmButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				referenceWindow.getVisualisationRoot().getCurrentParser().removeVertex(addWordInput.getText());
				currentPopup.hide();
				currentPopup = null;
				referenceWindow.getVisualisationRoot().doSearchRefresh();
			}
		});
		Button cancelButton = new Button();
		cancelButton.setText("Cancel");
		cancelButton.relocate(100, 160);
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				currentPopup.hide();
				currentPopup = null;
			}
		});
		canvas.getChildren().addAll(addWordLabel, promptWordLabel,addWordInput, confirmButton, cancelButton);
		canvas.setStyle("	-fx-background-color: #dfdfdf;"+ "-fx-border-color: black;" + "-fx-border-width: 1px;" + "-fx-font-family: 'Arial';");
		return canvas;
	}

	public Popup getPopup() {
		return currentPopup;
	}

	private Pane getPane(int x, int y) {
		Pane currentCanvas = new Pane();
		currentCanvas.setPrefSize(x, y);
		return currentCanvas;
	}

	private Text getText(int x, int y, String inputString, int scale) {
		Text currentWordLabel = new Text();
		currentWordLabel.relocate(x, y);
		currentWordLabel.setText(inputString);
		currentWordLabel.setScaleX(scale);
		currentWordLabel.setScaleY(scale);
		return currentWordLabel;
	}

	private TextField getTextField(int x, int y, int width) {
		TextField currentWordInput = new TextField();
		currentWordInput.setPrefWidth(width);
		currentWordInput.relocate(x, y);
		return currentWordInput;
	}

}
