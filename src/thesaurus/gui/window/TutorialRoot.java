package thesaurus.gui.window;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import thesaurus.controller.SystemController;

/**
 * This class is an extension of JavaFX2 node AnchorPane
 * and literally defines what is shown on the screen.
 * It also has mouseHandlers doCreate and doImport.
 */
public class TutorialRoot extends AnchorPane {

	private MainWindow referenceWindow;
	
	private ImageView picture;
	private Image image;
	
	private int count;
	
	String[] imageArray;

	private SystemController currentController;

	public TutorialRoot(MainWindow inputWindow) {

		referenceWindow = inputWindow;

		currentController = referenceWindow.getCurrentController();

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resourcePackage/tutorialLayout.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(currentController);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
		initialise();

	}

	private void initialise(){
		
		count = 0;
		
		imageArray = new String[20];
		
		imageArray[0] = "./resourcePackage/tutorial0.jpg";
		imageArray[1] = "./resourcePackage/tutorial1.jpg";
		imageArray[2] = "./resourcePackage/tutorial2.jpg";
		imageArray[3] = "./resourcePackage/tutorial3.jpg";
		imageArray[4] = "./resourcePackage/tutorial4.jpg";
		imageArray[5] = "./resourcePackage/tutorial5.jpg";
		imageArray[6] = "./resourcePackage/tutorial6.jpg";
		
		picture = new ImageView();
		
        image = new Image(imageArray[count]);
        
        picture.setImage(image);
        
        currentController.getTutOnePane().getChildren().add(picture);
	}
	
	/*Loop through tutorial*/
	public void next(){
		if(count == 6){
			referenceWindow.getStage().setScene(referenceWindow.getCurrentScene());
		}
		else{
			currentController.getTutOnePane().getChildren().remove(picture);
			image = null;
			picture = null;
			picture = new ImageView();
			image = new Image(imageArray[++count]);
			picture.setImage(image);
			currentController.getTutOnePane().getChildren().add(picture);
		}
	}
	
	/*Loop through tutorial*/
	public void previous(){
		if(count == 0){
			referenceWindow.getStage().setScene(referenceWindow.getSplashScene());
		}
		else{
			currentController.getTutOnePane().getChildren().remove(picture);
			image = null;
			picture = null;
			picture = new ImageView();
			image = new Image(imageArray[--count]);
			picture.setImage(image);
			currentController.getTutOnePane().getChildren().add(picture);
		}
	}

	public void exit() {
		referenceWindow.getStage().setScene(referenceWindow.getSplashScene());		
	}
	
}
