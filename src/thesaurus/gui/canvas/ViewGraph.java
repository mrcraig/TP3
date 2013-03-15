package thesaurus.gui.canvas;

import java.util.LinkedList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import thesaurus.gui.window.VisualisationRoot;
import thesaurus.parser.Vertex;

public class ViewGraph {
	private static final int SYNONYM = 1;
	private static final int ANTONYM = 0;
	
	private int windowWidth;
	private int windowHeight;
	private Vertex vertex;
	private VisualisationRoot vr;
	private Canvas graph;
	private GraphicsContext gc;
	private int xOffset = 0;
	private int yOffset = 0;
	
	private int displaySynonyms;
	private int displayAntonyms;
	private int groupingEnabled;
	
	public ViewGraph(int width, int height, Vertex vertex, VisualisationRoot vr, int displaySynonyms, int displayAntonyms, int groupingEnabled){
		windowWidth = width;
		windowHeight = height;
		this.vertex = vertex;
		this.vr = vr;
		this.displaySynonyms = displaySynonyms;
		this.displayAntonyms = displayAntonyms;
		this.groupingEnabled = groupingEnabled;
		
		//Move window to right to support dual view better
		if(windowWidth<500){
			this.xOffset=windowWidth/2;
		}
		
		//Centre origin node in canvas
		xOffset = (int) (vertex.getPos().getX() - (windowWidth/2));
		yOffset = (int) (vertex.getPos().getY() - (windowHeight/2));
		
		start();
	}
	
	public ScrollPane returnGraph(){
		ScrollPane sp = new ScrollPane();
		sp.setPrefSize(windowWidth, windowHeight);
		sp.setMaxSize(windowWidth, windowHeight);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		sp.setVbarPolicy(ScrollBarPolicy.NEVER);
		sp.setStyle("-fx-background-color:transparent;");
		sp.setContent(graph);
		return sp;
	}
	
	private void drawMainNode(Vertex v){
		int nodeWidth = v.getWord().length() * 12;
		
		gc.setStroke(Color.BLACK);
		gc.setFill(Color.rgb(176,220,247));
		gc.setLineWidth(2);
		gc.strokeOval((v.getPos().getX()-(nodeWidth/2)-xOffset),(v.getPos().getY()-13-yOffset), nodeWidth, 36);
		gc.fillOval((v.getPos().getX()+1-(nodeWidth/2)-xOffset),(v.getPos().getY()-12-yOffset),nodeWidth-2,34);
		gc.setFill(Color.BLACK);
		gc.setFont(new Font(14));
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText(v.getWord(), (v.getPos().getX()-xOffset), (v.getPos().getY()+10-yOffset));
	}
	
	private void drawSynNode(Vertex v){
		int nodeWidth = v.getWord().length() * 8;
		
		gc.setStroke(Color.BLACK);
		gc.setFill(Color.rgb(191, 247, 176));
		gc.setLineWidth(2);
		gc.strokeOval((v.getPos().getX()-(nodeWidth/2)-xOffset),(v.getPos().getY()-10-yOffset), nodeWidth, 30);
		gc.fillOval((v.getPos().getX()+1-(nodeWidth/2)-xOffset),(v.getPos().getY()-9-yOffset),nodeWidth-2,28);
		gc.setFill(Color.BLACK);
		gc.setFont(new Font(11));
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText(v.getWord(), (v.getPos().getX()-xOffset), (v.getPos().getY()+9-yOffset));
	}
	
	private void drawAntNode(Vertex v){
		int nodeWidth = v.getWord().length() * 8;
		
		gc.setStroke(Color.BLACK);
		gc.setFill(Color.rgb(242, 211, 211));
		gc.setLineWidth(2);
		gc.strokeOval((v.getPos().getX()-(nodeWidth/2)-xOffset),(v.getPos().getY()-10-yOffset), nodeWidth, 30);
		gc.fillOval((v.getPos().getX()+1-(nodeWidth/2)-xOffset),(v.getPos().getY()-9-yOffset),nodeWidth-2,28);
		gc.setFill(Color.BLACK);
		gc.setFont(new Font(11));
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText(v.getWord(), (v.getPos().getX()-xOffset), (v.getPos().getY()+9-yOffset));
	}
	
	private void drawConnector(double x1, double y1, double x2, double y2, int type){
		if(type==1){
			//Synonym
			gc.setStroke(Color.GREEN);
		} else {
			//Antonym
			gc.setStroke(Color.RED);
		}
		
		gc.setLineWidth(2);
		gc.strokeLine(x1-xOffset, y1-yOffset, x2-xOffset, y2-yOffset);
	}
	
	private void drawGraph(){
		
		/* DRAW NODE CONNECTORS */
		//Synonyms of root node
		if(displaySynonyms==1){
			for(Vertex v:vertex.getSynomyns()){
				drawConnector(vertex.getPos().getX(),vertex.getPos().getY(),v.getPos().getX(),v.getPos().getY(),SYNONYM);
				//Synonyms of synonyms
				for(Vertex c:v.getSynomyns()){
					drawConnector(v.getPos().getX(),v.getPos().getY(),c.getPos().getX(),c.getPos().getY(),SYNONYM);
					//Synonyms of synonyms of synonyms
					for(Vertex m:c.getSynomyns()){
						drawConnector(c.getPos().getX(),c.getPos().getY(),m.getPos().getX(),m.getPos().getY(),SYNONYM);
					}
					//antonyms of synonyms of synonyms
					/*
					if(displayAntonyms==1){
						if(!c.getAntonyms().isEmpty()){
							System.out.printf("Number of antonyms: %d\n",c.getAntonyms().size());
							for(Vertex m:c.getAntonyms()){
								System.out.printf("--: %s\n--X: %d\n--Y: %d", m.getWord(),m.getPos().getX(),m.getPos().getY());
								drawConnector(c.getPos().getX(),c.getPos().getY(),m.getPos().getX(),m.getPos().getY(),ANTONYM);
							}
						}
					}*/
				}
				//Antonyms of synonyms
				
				/*if(displayAntonyms==1){
					for(Vertex c:v.getAntonyms()){
						drawConnector(v.getPos().getX(),v.getPos().getY(),c.getPos().getX(),c.getPos().getY(),ANTONYM);
						//Synonyms of antonyms of synonyms
						/*
						for(Vertex m:c.getSynomyns()){
							drawConnector(c.getPos().getX(),c.getPos().getY(),m.getPos().getX(),m.getPos().getY(),SYNONYM);
						}
						//antonyms of antonyms of synonyms
						for(Vertex m:c.getAntonyms()){
							drawConnector(c.getPos().getX(),c.getPos().getY(),m.getPos().getX(),m.getPos().getY(),ANTONYM);
						}
					}
				}*/
			}
		}
		
		//Antonyms of root node
		if(displayAntonyms==1){
			for(Vertex v:vertex.getAntonyms()){
				drawConnector(vertex.getPos().getX(),vertex.getPos().getY(),v.getPos().getX(),v.getPos().getY(),ANTONYM);
				//Synonyms of antonyms
				if(displaySynonyms==1){
					for(Vertex c:v.getSynomyns()){
						drawConnector(v.getPos().getX(),v.getPos().getY(),c.getPos().getX(),c.getPos().getY(),ANTONYM);
						//Synonyms of synonyms of antonyms
						for(Vertex m:c.getSynomyns()){
							drawConnector(c.getPos().getX(),c.getPos().getY(),m.getPos().getX(),m.getPos().getY(),ANTONYM);
						}
						//antonyms of synonyms of antonyms
						/*
						for(Vertex m:c.getAntonyms()){
							System.out.printf("--: %s\n--Y: %d\n", m.getWord(),m.getPos().getY());
							drawConnector(c.getPos().getX(),c.getPos().getY(),m.getPos().getX(),m.getPos().getY(),ANTONYM);
						}*/
					}
				}
				//Antonyms of antonyms
				/*for(Vertex c:v.getAntonyms()){
					drawConnector(v.getPos().getX(),v.getPos().getY(),c.getPos().getX(),c.getPos().getY(),ANTONYM);
					//Synonyms of antonyms of antonyms
					
					if(displaySynonyms==1){
						for(Vertex m:c.getSynomyns()){
							drawConnector(c.getPos().getX(),c.getPos().getY(),m.getPos().getX(),m.getPos().getY(),SYNONYM);
						}
					}
					//antonyms of antonyms of antonyms
					for(Vertex m:c.getAntonyms()){
						drawConnector(c.getPos().getX(),c.getPos().getY(),m.getPos().getX(),m.getPos().getY(),ANTONYM);
					}
				}*/
			}
		}
		
		/* DRAW NODES */
		if(displaySynonyms==1){
			for(Vertex l:vertex.getSynomyns()){
				drawSynNode(l);
				//Synonyms of synonyms
				for(Vertex c:l.getSynomyns()){
					drawSynNode(c);
					//Synonyms of synonyms of synonyms
					for(Vertex m:c.getSynomyns()){
						drawSynNode(m);
					}
					//antonyms of synonyms of synonyms
					/*
					if(displayAntonyms==1){
						for(Vertex m:c.getAntonyms()){
							drawAntNode(m);
						}
					}*/
				}
				//Antonyms of synonyms
				/*if(displayAntonyms==1){
					for(Vertex c:l.getAntonyms()){
						drawAntNode(c);
						//Synonyms of antonyms of synonyms
						
						for(Vertex m:c.getSynomyns()){
							drawSynNode(m);
						}
						//antonyms of antonyms of synonyms
						for(Vertex m:c.getAntonyms()){
							drawAntNode(m);
						}
					}
				}*/
			}
		}
		
		//Antonyms of root node
		if(displayAntonyms==1){
			for(Vertex l:vertex.getAntonyms()){
				drawAntNode(l);
				//Synonyms of antonyms
				if(displaySynonyms==1){
					for(Vertex c:l.getSynomyns()){
						drawAntNode(c);
						//Synonyms of synonyms of antonyms
						for(Vertex m:c.getSynomyns()){
							drawAntNode(m);
						}/*
						//antonyms of synonyms of antonyms
						for(Vertex m:c.getAntonyms()){
							drawAntNode(m);
						}*/
					}
				}
				//Antonyms of antonyms
				/*
				for(Vertex c:l.getAntonyms()){
					drawAntNode(c);
					//Synonyms of antonyms of antonyms
					/*
					if(displaySynonyms==1){
						for(Vertex m:c.getSynomyns()){
							drawSynNode(m);
						}
					}
					//antonyms of antonyms of antonyms
					for(Vertex m:c.getAntonyms()){
						drawAntNode(m);
					}
				}*/
			}
		}
			
		//Draw main node
		drawMainNode(vertex);
		
	}
	
	private void resetGraph(){
		gc.setFill(Color.rgb(242,242,242));
		gc.fillRect(0, 0, windowWidth*2, windowHeight*2);
		gc.setFill(Color.BLACK);
	}
	
	public void setScale(double scale){
		if(scale+graph.getScaleX()>=1){
			scale += graph.getScaleX();
			graph.setScaleX(scale);
			graph.setScaleY(scale);
		}
		else{
			graph.setScaleX(1.0);
			graph.setScaleY(1.0);
		}
	}
	
	private void start() {
		graph = new Canvas(windowWidth,windowHeight);
		gc = graph.getGraphicsContext2D();
		
		resetGraph();
		drawGraph();
		
		
		/**
		 * Action Methods
		 */
		 
		final LinkedList<Double> curX = new LinkedList<Double>();
		final LinkedList<Double> curY = new LinkedList<Double>();
		
		graph.addEventHandler(MouseEvent.MOUSE_DRAGGED,
				new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				curX.add(e.getX());
				curY.add(e.getY());
				
				if(curX.size()>1){
					xOffset += curX.get(0)-curX.get(1);
					yOffset += curY.get(0)-curY.get(1);

					resetGraph();
					drawGraph();
					
					curX.removeFirst();
					curY.removeFirst();
				}
			}
		});
		
		graph.addEventHandler(MouseEvent.MOUSE_RELEASED,
				new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				curX.clear();
				curY.clear();
			}
		});
		
		graph.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override public void handle(ScrollEvent event) {
                setScale(event.getDeltaY()/400);
            }
        });
		
		graph.addEventHandler(MouseEvent.MOUSE_CLICKED, 
				new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				if(e.getClickCount()==2){
					//On double click, search for clicked node
					double clickX = e.getX() + xOffset;
					double clickY = e.getY() + yOffset;
					
					if(displaySynonyms==1){
						for(Vertex v:vertex.getSynomyns()){
							double nodeWidth = v.getWord().length() * 5;
							
							if((clickX > v.getPos().getX()-nodeWidth) && clickX < (v.getPos().getX() + nodeWidth)){
								if((clickY > v.getPos().getY()-13) && clickY < (v.getPos().getY()+13)){
									//found.
									System.out.println("//" + vertex.getSynomyns());
									vr.doClickSearchGraph(v.getWord());
									break;
								}
							}
							//child nodes to go here.
							for(Vertex c:v.getSynomyns()){
								double nodewidth = c.getWord().length() * 5;
								if((clickX > c.getPos().getX()-nodeWidth) && clickX < (c.getPos().getX() + nodeWidth)){
									if((clickY > c.getPos().getY()-13) && clickY < (c.getPos().getY()+13)){
										//found.
										System.out.println(c.getPos());
										vr.doClickSearchGraph(c.getWord());
									}
								}
								for(Vertex m:c.getSynomyns()){
									nodewidth = m.getWord().length() * 5;
									if((clickX > m.getPos().getX()-nodeWidth) && clickX < (m.getPos().getX() + nodeWidth)){
										if((clickY > m.getPos().getY()-13) && clickY < (m.getPos().getY()+13)){
											vr.doClickSearchGraph(m.getWord());
										}
									}
								}
							}
							/*
							for(Vertex m:c.getAntonyms()){
								nodewidth = m.getWord().length() * 5;
								if((clickX > m.getPos().getX()-nodeWidth) && clickX < (m.getPos().getX() + nodeWidth)){
									if((clickY > m.getPos().getY()-13) && clickY < (m.getPos().getY()+13)){
										vr.doClickSearchGraph(m.getWord());
									}
								}
							}*/
						}
						/*for(Vertex c:v.getAntonyms()){
							double nodewidth = c.getWord().length() * 5;
							if((clickX > c.getPos().getX()-nodeWidth) && clickX < (c.getPos().getX() + nodeWidth)){
								if((clickY > c.getPos().getY()-13) && clickY < (c.getPos().getY()+13)){
									//found.
									System.out.println(c.getPos());
									vr.doClickSearchGraph(c.getWord());
								}
							}
							for(Vertex m:c.getSynomyns()){
								nodewidth = m.getWord().length() * 5;
								if((clickX > m.getPos().getX()-nodeWidth) && clickX < (m.getPos().getX() + nodeWidth)){
									if((clickY > m.getPos().getY()-13) && clickY < (m.getPos().getY()+13)){
										vr.doClickSearchGraph(m.getWord());
									}
								}
							}
							for(Vertex m:c.getAntonyms()){
								nodewidth = m.getWord().length() * 5;
								if((clickX > m.getPos().getX()-nodeWidth) && clickX < (m.getPos().getX() + nodeWidth)){
									if((clickY > m.getPos().getY()-13) && clickY < (m.getPos().getY()+13)){
										vr.doClickSearchGraph(m.getWord());
									}
								}
							}
						}*/
					}
					/*ant*/
					if(displayAntonyms==1){
						for(Vertex v:vertex.getAntonyms()){
							double nodeWidth = v.getWord().length() * 5;
							
							if((clickX > v.getPos().getX()-nodeWidth) && clickX < (v.getPos().getX() + nodeWidth)){
								if((clickY > v.getPos().getY()-13) && clickY < (v.getPos().getY()+13)){
									//found.
									System.out.println("//" + vertex.getSynomyns());
									vr.doClickSearchGraph(v.getWord());
									break;
								}
							}
							//child nodes to go here.
							for(Vertex c:v.getSynomyns()){
								double nodewidth = c.getWord().length() * 5;
								if((clickX > c.getPos().getX()-nodeWidth) && clickX < (c.getPos().getX() + nodeWidth)){
									if((clickY > c.getPos().getY()-13) && clickY < (c.getPos().getY()+13)){
										//found.
										System.out.println(c.getPos());
										vr.doClickSearchGraph(c.getWord());
									}
								}
								for(Vertex m:c.getSynomyns()){
									nodewidth = m.getWord().length() * 5;
									if((clickX > m.getPos().getX()-nodeWidth) && clickX < (m.getPos().getX() + nodeWidth)){
										if((clickY > m.getPos().getY()-13) && clickY < (m.getPos().getY()+13)){
											vr.doClickSearchGraph(m.getWord());
										}
									}
								}
							}
								/*
								for(Vertex m:c.getAntonyms()){
									nodewidth = m.getWord().length() * 5;
									if((clickX > m.getPos().getX()-nodeWidth) && clickX < (m.getPos().getX() + nodeWidth)){
										if((clickY > m.getPos().getY()-13) && clickY < (m.getPos().getY()+13)){
											vr.doClickSearchGraph(m.getWord());
										}
									}
							}*/
						}
						/*
						for(Vertex c:v.getAntonyms()){
							double nodewidth = c.getWord().length() * 5;
							if((clickX > c.getPos().getX()-nodeWidth) && clickX < (c.getPos().getX() + nodeWidth)){
								if((clickY > c.getPos().getY()-13) && clickY < (c.getPos().getY()+13)){
									//found.
									System.out.println(c.getPos());
									vr.doClickSearchGraph(c.getWord());
								}
							}
							for(Vertex m:c.getSynomyns()){
								nodewidth = m.getWord().length() * 5;
								if((clickX > m.getPos().getX()-nodeWidth) && clickX < (m.getPos().getX() + nodeWidth)){
									if((clickY > m.getPos().getY()-13) && clickY < (m.getPos().getY()+13)){
										vr.doClickSearchGraph(m.getWord());
									}
								}
							}
							for(Vertex m:c.getAntonyms()){
								nodewidth = m.getWord().length() * 5;
								if((clickX > m.getPos().getX()-nodeWidth) && clickX < (m.getPos().getX() + nodeWidth)){
									if((clickY > m.getPos().getY()-13) && clickY < (m.getPos().getY()+13)){
										vr.doClickSearchGraph(m.getWord());
									}
								}
							}
						}*/
					}
				}
			}
		});
	}
}
