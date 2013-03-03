package thesaurus.gui.table;

import javafx.beans.property.SimpleStringProperty;

public class TabData {
	 
    private final SimpleStringProperty word;
    private final SimpleStringProperty synonym;
    private final SimpleStringProperty antonym;

    TabData(String word, String synonym, String antonym) {
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