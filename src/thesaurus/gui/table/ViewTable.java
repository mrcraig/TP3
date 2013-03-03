package thesaurus.gui.table;

import java.util.LinkedList;

import com.sun.net.ssl.SSLContext;

import thesaurus.gui.window.VisualisationRoot;
import thesaurus.parser.Vertex;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
 
public class ViewTable {
 
    private TableView<TabData> table = new TableView<TabData>();
    private final ObservableList<TabData> data = FXCollections.observableArrayList();
    final HBox hb = new HBox();
    
    private int windowWidth;
    private int windowHeight;
    private Vertex vertex;
    private VisualisationRoot vr;
    
    public ViewTable(int windowWidth, int windowHeight, Vertex vertex, VisualisationRoot vr){
    	this.windowHeight = windowHeight;
    	this.windowWidth = windowWidth;
    	this.vertex = vertex;
    	this.vr = vr;
    	
    	start();
    }
 
    public void start() {
 
        table.setEditable(false);
        table.setMaxSize(windowWidth, windowHeight);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
 
        TableColumn wordCol = new TableColumn("Word");
        wordCol.setPrefWidth(windowWidth/3-10);
        wordCol.setResizable(false);
        wordCol.setCellValueFactory(
                new PropertyValueFactory<Vertex, String>("word"));
        
        TableColumn synCol = new TableColumn("Synonym");
        synCol.setPrefWidth(windowWidth/3);
        synCol.setResizable(false);
        synCol.setCellValueFactory(
                new PropertyValueFactory<Vertex, String>("synonym"));
 
        TableColumn antCol = new TableColumn("Antonym");
        antCol.setPrefWidth(windowWidth/3);
        antCol.setResizable(false);
        antCol.setCellValueFactory(
                new PropertyValueFactory<Vertex, String>("antonym"));
 
        table.setItems(data);
        table.getColumns().addAll(wordCol, synCol, antCol);
        
        importData();
    }
    
    public TableView<TabData> getTable(){
    	return table;
    }
    
    private boolean arrayContains(LinkedList<String> arr,String con){
    	for(String s:arr){
    		if(s.compareTo(con)==0)
    			return true;
    	}
    	
    	return false;
    }
    
    private void importData(){
    	LinkedList<String> rootWords = new LinkedList<String>();
		String synList="";
		String antList="";
		
    	if(!arrayContains(rootWords,vertex.getWord())){
    		
    		for(int i=0;i<vertex.getSynomyns().size();i++){
        		if(windowWidth>400){
    	    		if(i%4==0 && i>0){
    	    			synList += "\n";
    	    		} else if(i>0){
    	    			synList += ", ";
    	    		}
        		} else {
        			if(i>0){
        				synList += "\n";
        			}
        		}
        		synList += vertex.getSynomyns().get(i).getWord();
        	}
        	
        	for(int i=0;i<vertex.getAntonyms().size();i++){
        		if(windowWidth>400){
    	    		if(i%4==0 && i>0){
    	    			antList += "\n";
    	    		} else if(i>0){
    	    			antList += ", ";
    	    		}
        		} else {
        			if(i>0){
        				antList += "\n";
        			}
        		}
        		antList += vertex.getSynomyns().get(i).getWord();
        	}
    	}
    	
    	//Add data to table
    	data.add(new TabData(vertex.getWord(),synList,antList));
    	rootWords.add(vertex.getWord());
    	
    	for(Vertex v: vertex.getSynomyns()){
    		if(!arrayContains(rootWords,v.getWord())){
	    		synList="";
	    		antList="";
	    		
	    		for(int i=0;i<v.getSynomyns().size();i++){
	    			if(windowWidth>400){
	    				if(i%4==0 && i>0){
	    					synList += "\n";
	    				} else if(i>0){
	    					synList+=", ";
	    				}
	    			} else {
	    				if(i>0){
	    					synList += "\n";
	    				}
	    			}
	    			synList+=v.getSynomyns().get(i).getWord();
	    		}
	    		
	    		for(int i=0;i<v.getAntonyms().size();i++){
	    			if(windowWidth>400){
	    				if(i%4==0 && i>0){
	    					antList += "\n";
	    				} else if(i>0){
	    					antList+=", ";
	    				}
	    			} else {
	    				if(i>0){
	    					antList += "\n";
	    				}
	    			}
	    			antList+=v.getAntonyms().get(i).getWord();
	    		}
	    		data.add(new TabData(v.getWord(),synList,antList));
	    		rootWords.add(v.getWord());
	    		
	    		for(Vertex n:v.getSynomyns()){
	    			if(!arrayContains(rootWords,n.getWord())){
	    				synList="";
	    	    		antList="";
	    	    		
	    	    		for(int i=0;i<n.getSynomyns().size();i++){
	    	    			if(windowWidth>400){
	    	    				if(i%4==0 && i>0){
	    	    					synList += "\n";
	    	    				} else if(i>0){
	    	    					synList+=", ";
	    	    				}
	    	    			} else {
	    	    				if(i>0){
	    	    					synList += "\n";
	    	    				}
	    	    			}
	    	    			synList+=n.getSynomyns().get(i).getWord();
	    	    		}
	    	    		
	    	    		for(int i=0;i<n.getAntonyms().size();i++){
	    	    			if(windowWidth>400){
	    	    				if(i%4==0 && i>0){
	    	    					antList += "\n";
	    	    				} else if(i>0){
	    	    					antList+=", ";
	    	    				}
	    	    			} else {
	    	    				if(i>0){
	    	    					antList += "\n";
	    	    				}
	    	    			}
	    	    			antList+=n.getAntonyms().get(i).getWord();
	    	    		}
	    	    		//Add data to table
	    	    		data.add(new TabData(n.getWord(),synList,antList));
	    	    		rootWords.add(n.getWord());
	    			}
	    		}
    		}
    	}
    	
    	
    	/* ANTONYMS */
    	
    	for(Vertex v: vertex.getAntonyms()){
    		if(!arrayContains(rootWords,v.getWord())){
	    		synList="";
	    		antList="";
	    		
	    		for(int i=0;i<v.getSynomyns().size();i++){
	    			if(windowWidth>400){
	    				if(i%4==0 && i>0){
	    					synList += "\n";
	    				} else if(i>0){
	    					synList+=", ";
	    				}
	    			} else {
	    				if(i>0){
	    					synList += "\n";
	    				}
	    			}
	    			synList+=v.getSynomyns().get(i).getWord();
	    		}
	    		
	    		for(int i=0;i<v.getAntonyms().size();i++){
	    			if(windowWidth>400){
	    				if(i%4==0 && i>0){
	    					antList += "\n";
	    				} else if(i>0){
	    					antList+=", ";
	    				}
	    			} else {
	    				if(i>0){
	    					antList += "\n";
	    				}
	    			}
	    			antList+=v.getAntonyms().get(i).getWord();
	    		}
	    		data.add(new TabData(v.getWord(),synList,antList));
	    		rootWords.add(v.getWord());
	    		
	    		for(Vertex n:v.getSynomyns()){
	    			if(!arrayContains(rootWords,n.getWord())){
	    				synList="";
	    	    		antList="";
	    	    		
	    	    		for(int i=0;i<n.getSynomyns().size();i++){
	    	    			if(windowWidth>400){
	    	    				if(i%4==0 && i>0){
	    	    					synList += "\n";
	    	    				} else if(i>0){
	    	    					synList+=", ";
	    	    				}
	    	    			} else {
	    	    				if(i>0){
	    	    					synList += "\n";
	    	    				}
	    	    			}
	    	    			synList+=n.getSynomyns().get(i).getWord();
	    	    		}
	    	    		
	    	    		for(int i=0;i<n.getAntonyms().size();i++){
	    	    			if(windowWidth>400){
	    	    				if(i%4==0 && i>0){
	    	    					antList += "\n";
	    	    				} else if(i>0){
	    	    					antList+=", ";
	    	    				}
	    	    			} else {
	    	    				if(i>0){
	    	    					antList += "\n";
	    	    				}
	    	    			}
	    	    			antList+=n.getAntonyms().get(i).getWord();
	    	    		}
	    	    		//Add data to table
	    	    		data.add(new TabData(n.getWord(),synList,antList));
	    	    		rootWords.add(n.getWord());
	    			}
	    		}
    		}
    	}
    	
    	/* Main Vertex 
    	String synList = "";
    	String antList = "";
    	
    	for(int i=0;i<vertex.getSynomyns().size();i++){
    		if(windowWidth>400){
	    		if(i%4==0 && i>0){
	    			synList += "\n";
	    		} else if(i>0){
	    			synList += ", ";
	    		}
    		} else {
    			if(i>0){
    				synList += "\n";
    			}
    		}
    		synList += vertex.getSynomyns().get(i).getWord();
    	}
    	
    	for(int i=0;i<vertex.getAntonyms().size();i++){
    		if(windowWidth>400){
	    		if(i%4==0 && i>0){
	    			antList += "\n";
	    		} else if(i>0){
	    			antList += ", ";
	    		}
    		} else {
    			if(i>0){
    				antList += "\n";
    			}
    		}
    		antList += vertex.getSynomyns().get(i).getWord();
    	}
    	//Add data to table
    	data.add(new TabData(vertex.getWord(),synList,antList));
    	
    	//Add synonyms
    	for(Vertex v:vertex.getSynomyns()){
    		synList = "";
    		antList = "";
    		
    		for(int i=0;i<v.getSynomyns().size();i++){
    			if(windowWidth>400){
	        		if(i%4==0 && i>0){
	        			synList += "\n";
	        		} else if(i>0){
	        			synList += ", ";
	        		}
    			} else {
    				if(i>0){
    					synList += "\n";
    				}
    			}
        		synList += v.getSynomyns().get(i).getWord();
        		
        		for(i=0;i<v.getAntonyms().size();i++){
            		if(windowWidth>400){
        	    		if(i%4==0 && i>0){
        	    			antList += "\n";
        	    		} else if(i>0){
        	    			antList += ", ";
        	    		}
            		} else {
            			if(i>0){
            				antList += "\n";
            			}
            		}
            		antList += v.getAntonyms().get(i).getWord();
            	}
        	}
    		
    		//Add to table
    		data.add(new TabData(v.getWord(),synList,antList));
    		
    		for(Vertex vant:vertex.getAntonyms()){
    			synList = "";
        		antList = "";
        		
        		for(int i=0;i<vant.getSynomyns().size();i++){
        			if(windowWidth>400){
    	        		if(i%4==0 && i>0){
    	        			synList += "\n";
    	        		} else if(i>0){
    	        			synList += ", ";
    	        		}
        			} else {
        				if(i>0){
        					synList += "\n";
        				}
        			}
            		synList += v.getSynomyns().get(i).getWord();
            		
            		for(i=0;i<v.getAntonyms().size();i++){
                		if(windowWidth>400){
            	    		if(i%4==0 && i>0){
            	    			antList += "\n";
            	    		} else if(i>0){
            	    			antList += ", ";
            	    		}
                		} else {
                			if(i>0){
                				antList += "\n";
                			}
                		}
                		antList += v.getAntonyms().get(i).getWord();
                	}
            	}
    		}
    		
    		//Add to table
    		data.add(new TabData(v.getWord(),synList,antList));
    		
    		
    		//Double click support
    		table.setOnMouseClicked(
    				new EventHandler<MouseEvent>(){
    					public void handle(MouseEvent e){
    						if(e.getClickCount()==2){
    							System.out.println("--- " + table.getSelectionModel().getSelectedItem().getWord());
    							vr.doClickSearchGraph(table.getSelectionModel().getSelectedItem().getWord());
    						}
    					}
    				});
    	}*/
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