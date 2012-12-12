package thesaurus.gui.window;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import thesaurus.controller.SystemController;

/** 
 * This class is an extension of JavaFX2 node AnchorPane
 * and literally defines what is shown on the screen.
 * It also has mouseHandlers doCreate and doImport.
 */
public class TutorialRoot extends AnchorPane {

	private MainWindow referenceWindow;

	private SystemController currentController;

	public TutorialRoot(MainWindow inputWindow) {

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

	}

}
