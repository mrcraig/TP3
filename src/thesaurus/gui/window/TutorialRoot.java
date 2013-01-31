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
		
		picture = new ImageView();
		
        image = new Image("./resourcePackage/tutorial0.jpg");
        
        picture.setImage(image);
        
        currentController.getTutOnePane().getChildren().add(picture);
	}
	
	public void next(){
		System.out.println("sss");
		currentController.getTutOnePane().getChildren().remove(picture);
		image = null;
		picture = null;
		picture = new ImageView();
		image = new Image("./resourcePackage/tutorial1.jpg");
		picture.setImage(image);
		currentController.getTutOnePane().getChildren().add(picture);
	}
	
}
