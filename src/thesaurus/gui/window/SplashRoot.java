package thesaurus.gui.window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import thesaurus.controller.*;
import thesaurus.gui.window.VisualisationRoot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/** 
 * This class is an extension of JavaFX2 node AnchorPane
 * and literally defines what is shown on the screen.
 * It also has mouseHandlers doCreate and doImport.
 */
public class SplashRoot extends AnchorPane {

	private MainWindow referenceWindow;
	
	private SystemController currentController;

	public SplashRoot(MainWindow inputWindow) {
		
		referenceWindow = inputWindow;
		
		currentController = new SystemController(referenceWindow);

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resourcePackage/splashLayout.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(currentController);
		
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
	}

}
