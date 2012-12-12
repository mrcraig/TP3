package thesaurus.gui.window;

import thesaurus.controller.SystemController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.image.Image;

/** 
 * This main window class sets out what is displayed in the window.
 * Initially it sets ups a Splash page to be displayed and
 * send a reference of itself to the splash page so that
 * the splash splash page can change the contents of the window.
 */
public class MainWindow extends Application {

	private Stage stageCurrent;
	private Scene splashSceneCurrent;
	private VisualisationRoot visualisationRootCurrent;
	private TutorialRoot tutorialRootCurrent;
	private SystemController currentController;

	public void launchProgram(String[] args){
		launch(args);
	}

	public void start(Stage stage) throws Exception {

		setCurrentController(new SystemController(this));

		SplashRoot splashRootCurrent = new SplashRoot(this);
		stageCurrent = stage;
		splashSceneCurrent = new Scene(splashRootCurrent);
		stageCurrent.setScene(splashSceneCurrent);
		stageCurrent.setTitle("The Graphical Thesaurus by Team O");
		stageCurrent.getIcons().add(new Image("file:/../src/resourcePackage/o.png"));
		stageCurrent.show();

	}	

	public Stage getStage(){
		return stageCurrent;
	}

	public Scene getSplashScene(){
		return splashSceneCurrent;
	}

	public void setVisualisationRoot(VisualisationRoot inputRoot){
		visualisationRootCurrent = inputRoot;
	}

	public VisualisationRoot getVisualisationRoot(){
		return visualisationRootCurrent;
	}

	public SystemController getCurrentController() {
		return currentController;
	}

	public void setCurrentController(SystemController currentControllerInput) {
		currentController = currentControllerInput;
	}

	public TutorialRoot getTutorialRootCurrent() {
		return tutorialRootCurrent;
	}

	public void setTutorialRootCurrent(TutorialRoot tutorialRootCurrent) {
		this.tutorialRootCurrent = tutorialRootCurrent;
	}

}
