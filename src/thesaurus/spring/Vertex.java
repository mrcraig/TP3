package thesaurus.spring;


import java.awt.geom.Point2D;
import java.util.LinkedList;



/**
 class to represent a vertex in a graph
*/
public class Vertex {
   
    private LinkedList<Vertex> adjList ; 
    private String index; 
    private boolean visited = false;
    
    
    
    public boolean isVisited()
    {
		return visited;
	}

	public void setVisited(boolean visited) 
	{
		this.visited = visited;
	}

	
    private Point2D pos;
    private Point2D dis;
    
    public Vertex(String i)
    {
    	adjList = new LinkedList<Vertex>();
    	index =  i;
    }    
    
   
    
      
    
    public Point2D getPos() {
		return pos;
	}
    public Point2D getDis() {
		return dis;
	}
    public void setPDis(Point2D dis) {
		this.dis = dis;
	}

	public void setPos(Point2D pos) {
		this.pos = pos;
	}

	public LinkedList<Vertex> getAdjList(){
        return adjList;
    }
    
    public String getIndex(){
    	return index;
    }
    
     
    
    public void addToAdjList(Vertex n){
        adjList.addLast(n);
    }
    
    public int vertexDegree(){
        return adjList.size();
    }
}
