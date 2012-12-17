package thesaurus.gui.window;

import java.io.IOException;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import thesaurus.controller.SystemController;

/** 
 * This class is an extension of JavaFX2 node AnchorPane
 * and literally defines what is shown on the screen.
 * It also has mouseHandlers doCreate and doImport.
 */
public class SplashRoot extends AnchorPane {

	private MainWindow referenceWindow;

	private SystemController currentController;

	public SplashRoot(MainWindow inputWindow) throws IOException {

		referenceWindow = inputWindow;

		currentController = referenceWindow.getCurrentController();

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resourcePackage/splashLayout.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(currentController);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
		referenceWindow.setCurrentRecentArray(createRecentListArray());
		currentController.populateList();

	}
	
	private ObservableList<String> createRecentListArray() throws IOException {
		ObservableList<String> toReturn = FXCollections.observableArrayList();
		Scanner fileScanner = new Scanner(referenceWindow.getCurrentRecentFile(), "UTF-8");
		while(fileScanner.hasNextLine()){
			toReturn.add(fileScanner.nextLine());
		}
		fileScanner.close();
		return toReturn;		
	}

}
