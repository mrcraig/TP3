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
	
	public PopupFactory(String inputChoice){
		if (inputChoice.equals("add")) {
			currentPopup = new Popup();
			currentPopup.getContent().add(makeCanvasAdd());
		} else if (inputChoice.equals("edit")) {
			currentPopup = new Popup();
			currentPopup.getContent().add(makeCanvasEdit());
		} else if (inputChoice.equals("remove")) {
			currentPopup = new Popup();
			currentPopup.getContent().add(makeCanvasRemove());
		}
	}
	
	private Pane makeCanvasAdd() {
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
				currentPopup.hide();
			}
		});
		canvas.getChildren().addAll(addWordLabel, promptWordLabel,
				addWordInput, promptSynLabel, addSynInput, promptAntLabel,
				addAntInput, confirmButton, cancelButton);
		canvas.setStyle("	-fx-background-color: #dfdfdf;"
				+ "-fx-border-color: black;" + "-fx-border-width: 1px;" + "-fx-font-family: 'Georgia';");
		return canvas;
	}
	
	private Pane makeCanvasEdit() {
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
				currentPopup.hide();
			}
		});
		canvas.getChildren().addAll(addWordLabel, promptWordLabel,
				addWordInput, promptSynLabel, addSynInput, promptAntLabel,
				addAntInput, confirmButton, cancelButton);
		canvas.setStyle("	-fx-background-color: #dfdfdf;"
				+ "-fx-border-color: black;" + "-fx-border-width: 1px;" + "-fx-font-family: 'Georgia';");
		return canvas;
	}
	
	private Pane makeCanvasRemove() {
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
				currentPopup.hide();
			}
		});
		canvas.getChildren().addAll(addWordLabel, promptWordLabel,
				addWordInput, confirmButton, cancelButton);
		canvas.setStyle("	-fx-background-color: #dfdfdf;"
				+ "-fx-border-color: black;" + "-fx-border-width: 1px;" + "-fx-font-family: 'Georgia';");
		return canvas;
	}

	public Popup getPopup() {
		return currentPopup;
	}

}
