package thesaurus.gui.window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
		writeToRecentFile();
		
		currentController.addListenerListView();
		

	}
	
	private ArrayList<String> createRecentListArray() throws IOException {
		ArrayList<String> toReturn = new ArrayList<String>();
		Scanner fileScanner = new Scanner(referenceWindow.getCurrentRecentFile(), "UTF-8");
		while(fileScanner.hasNextLine()){
			toReturn.add(fileScanner.nextLine());
		}
		fileScanner.close();
		return toReturn;		
	}
	
	public void writeToRecentFile() throws IOException{
		File toWrite = referenceWindow.getCurrentRecentFile();
		OutputStreamWriter fileWriter;
		fileWriter = new OutputStreamWriter(new FileOutputStream(toWrite),"UTF-8");
		StringBuilder currentBuilder = new StringBuilder();
		for(String s: referenceWindow.getCurrentRecentArray()){
			System.out.println(s);
			currentBuilder.append(s+"\n");
		}
		System.out.println("Content: \n");
		fileWriter.write(currentBuilder.toString());
		fileWriter.close();
		System.out.println("");
	}

}
