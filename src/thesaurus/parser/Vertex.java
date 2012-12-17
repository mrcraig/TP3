package thesaurus.parser;
import java.awt.geom.Point2D;
import java.util.LinkedList;



public class Vertex {
   
    private LinkedList<Vertex> synomyns; 
    private LinkedList<Vertex> antonyms;
    private String index; 
    private boolean visited = false;
    private String word;
    private Point2D pos;
    private Point2D dis;
    
    
    public boolean isVisited()
    {
		return visited;
	}

	public void setVisited(boolean visited) 
	{
		this.visited = visited;
	}

    public Vertex(String i)
    {
    	synomyns = new LinkedList<Vertex>();
    	antonyms = new LinkedList<Vertex>();
    	index = i;
    }  
    
    
    public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
    
    public String toString()
    {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Vertex "); sb.append(this.index+" ");
    	sb.append(this.word +"\nSynonyms\n ");
     	for(Vertex n : this.synomyns)
    	{
    		if(n==null)continue;
    		sb.append(n.getWord());
    		sb.append(",");
    	}
     	sb.append("\nAntonyms\n");
     	for(Vertex a : this.antonyms)
     	{
     		if(a==null)continue; //a shouldnt be null
     		sb.append(a.getWord());
     		sb.append(",");
     	}
    	sb.append("\n");
    	return sb.toString();
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

	public LinkedList<Vertex> getSynomyns()
	{
        return synomyns;
    }
	
	public LinkedList<Vertex> getAntonyms()
	{
		return antonyms;
	}
     
    public String getIndex()
    {
    	return index;
    }
    
    public void setIndex(String n)
    {
    	index = n;
    }   
    
    public void addSynomyn(Vertex n)
    {
        synomyns.addLast(n);
    }
    
    public void addAntonym(Vertex n)
    {
    	antonyms.addLast(n);
    }
    
    public int vertexDegree(){
        return synomyns.size();
    }
}
