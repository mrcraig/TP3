package thesaurus.gui.table;

import thesaurus.parser.Vertex;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class ViewTable extends Application {
 
    private TableView<TabData> table = new TableView<TabData>();
    private final ObservableList<TabData> data = FXCollections.observableArrayList();
    final HBox hb = new HBox();
    
    private int windowWidth;
    private int windowHeight;
    private Vertex vertex;
 
    public static void main(String[] args) {
        launch(args);
    }
    
    public ViewTable(int windowWidth, int windowHeight, Vertex vertex){
    	this.windowHeight = windowHeight;
    	this.windowWidth = windowWidth;
    	this.vertex = vertex;
    }
 
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(450);
        stage.setHeight(550);
 
        table.setEditable(true);
 
        TableColumn firstNameCol = new TableColumn("Word");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Vertex, String>("word"));
 
        TableColumn lastNameCol = new TableColumn("Synonym");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Vertex, String>("synonym"));
 
        TableColumn emailCol = new TableColumn("Antonym");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Vertex, String>("antonym"));
 
        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
 
//        final TextField addFirstName = new TextField();
//        addFirstName.setPromptText("First Name");
//        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
//        final TextField addLastName = new TextField();
//        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
//        addLastName.setPromptText("Last Name");
//        final TextField addEmail = new TextField();
//        addEmail.setMaxWidth(emailCol.getPrefWidth());
//        addEmail.setPromptText("Email");
// 
//        final Button addButton = new Button("Add");
//        addButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//                data.add(new Person(
//                        addFirstName.getText(),
//                        addLastName.getText(),
//                        addEmail.getText()));
//                addFirstName.clear();
//                addLastName.clear();
//                addEmail.clear();
//            }
//        });
// 
//        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
//        hb.setSpacing(3);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
    
    public TableView<TabData> getTable(){
    	return table;
    }
 
    public static class TabData {
 
        private final SimpleStringProperty word;
        private final SimpleStringProperty synonym;
        private final SimpleStringProperty antonym;
 
        private TabData(String word, String synonym, String antonym) {
            this.word = new SimpleStringProperty(word);
            this.synonym = new SimpleStringProperty(synonym);
            this.antonym = new SimpleStringProperty(antonym);
        }
 
        public String getWord() {
            return word.get();
        }
 
        public void setWord(String word) {
            this.word.set(word);
        }
 
        public String getSynonym() {
            return synonym.get();
        }
 
        public void setSynonym(String synonym) {
            this.synonym.set(synonym);
        }
 
        public String getAntonym() {
            return antonym.get();
        }
 
        public void setAntonym(String antonym) {
            this.antonym.set(antonym);
        }
    }
} 