package thesaurus.gui.window;
import thesaurus.gui.window.MainWindow;

public class Driver {

	/** 
	 * This driver class simply instantiates a main window
	 * as a non-static object.
	 */
	public static void main(String[] args) {

		MainWindow currentWindow = new MainWindow();

		currentWindow.launchProgram(args);

	}

}