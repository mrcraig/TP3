package thesaurus.gui.window;

import java.io.File;
import java.util.ArrayList;

import thesaurus.controller.SystemController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * This main window class sets out what is displayed in the window.
 * Initially it sets ups a Splash page to be displayed and
 * send a reference of itself to the splash page so that
 * the splash splash page can change the contents of the window.
 */
public class MainWindow extends Application {

	private Stage stageCurrent;
	private Scene splashRootScene;
	private SplashRoot splashRootCurrent;
	private VisualisationRoot visualisationRootCurrent;
	private TutorialRoot tutorialRootCurrent;
	private SystemController currentController;
	private File currentRecentFile;
	private ArrayList<String> currentRecentArray;
	private ObservableList<String> currentRecentList;
	private Scene sceneChoice;

	public void launchProgram(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {

		setCurrentController(new SystemController(this));

		File recentFile = new File("./src/resourcePackage/recentfiles.dat");
		if(!recentFile.exists()) {
			recentFile.createNewFile();
		}
		setCurrentRecentFile(recentFile);

		splashRootCurrent = new SplashRoot(this);
		stageCurrent = stage;
		stageCurrent.setResizable(false);
		splashRootScene = new Scene(splashRootCurrent);
		setCurrentScene(splashRootScene);
		stageCurrent.setScene(splashRootScene);
		stageCurrent.setTitle("The Graphical Thesaurus by Team O");
		stageCurrent.getIcons().add(new Image("file:/../src/resourcePackage/o.png"));
		stageCurrent.show();

	}

	public Stage getStage() {
		return stageCurrent;
	}

	public SplashRoot getSplashRoot() {
		return splashRootCurrent;
	}

	public Scene getSplashScene() {
		return splashRootScene;
	}

	public void setVisualisationRoot(VisualisationRoot inputRoot) {
		visualisationRootCurrent = inputRoot;
	}

	public VisualisationRoot getVisualisationRoot() {
		return visualisationRootCurrent;
	}

	public void setCurrentController(SystemController currentControllerInput) {
		currentController = currentControllerInput;
	}

	public SystemController getCurrentController() {
		return currentController;
	}

	public TutorialRoot getTutorialRootCurrent() {
		return tutorialRootCurrent;
	}

	public void setTutorialRootCurrent(TutorialRoot tutorialRootCurrent2) {
		tutorialRootCurrent = tutorialRootCurrent2;
	}

	public File getCurrentRecentFile() {
		return currentRecentFile;
	}

	public void setCurrentRecentFile(File currentRecentFileInput) {
		currentRecentFile = currentRecentFileInput;
	}

	public ArrayList<String> getCurrentRecentArray() {
		return currentRecentArray;
	}

	public void setCurrentRecentArray(ArrayList<String> currentRecentArrayInput) {
		currentRecentArray = currentRecentArrayInput;
	}

	public ObservableList<String> getCurrentRecentList() {
		return currentRecentList;
	}

	public void setCurrentRecentList(ObservableList<String> currentRecentListInput) {
		currentRecentList = currentRecentListInput;
	}
	
	public void setCurrentScene(Scene input){
		sceneChoice = input;
	}
	
	public Scene getCurrentScene(){
		return sceneChoice;
	}

}