package thesaurus.parser;
import java.awt.geom.Point2D;
import java.util.LinkedList;



public class Vertex {
   
    private LinkedList<Vertex> synonyms; 
    private LinkedList<Vertex> antonyms;
    private LinkedList<Vertex> groupings;
    private String id; 
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
    	synonyms = new LinkedList<Vertex>();
    	antonyms = new LinkedList<Vertex>();
    	groupings = new LinkedList<Vertex>();
    	id = i;
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
    	sb.append("Vertex "); sb.append(this.id+" ");
    	sb.append(this.word +"\nSynonyms\n ");
     	for(Vertex n : this.synonyms)
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
     	sb.append("\nGroupings\n");
     	for(Vertex g : this.groupings)
     	{
     		if(g==null)continue;
     		sb.append(g.getWord());
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
        return synonyms;
    }
	
	public LinkedList<Vertex> getAntonyms()
	{
		return antonyms;
	}
	
	public LinkedList<Vertex> getGroupings()
	{
		return groupings;
	}
     
    public String getID()
    {
    	return id;
    }
    
    public void setID(String n)
    {
    	id = n;
    }   
    
    public void addSynonym(Vertex n)
    {
        synonyms.addLast(n);
    }
    
    public void addAntonym(Vertex n)
    {
    	antonyms.addLast(n);
    }
    
    public void addGrouping(Vertex g)
    {
    	groupings.add(g);
    }
    
    public void removeSynonym(Vertex s)
    {
    	synonyms.remove(s);
    }
    
    public void removeAntonym(Vertex a)
    {
    	antonyms.remove(a);
    }
    
    public void removeGrouping(Vertex g)
    {
    	synonyms.remove(g);
    }
    
    public void setSynonyms(LinkedList<Vertex> syns)
    {
    	this.synonyms = syns;
    }
    
    public void setAntonyms(LinkedList<Vertex> ants)
    {
    	this.antonyms = ants;
    }
    
    public void setGroupings(LinkedList<Vertex> g)
    {
    	this.groupings = g;
    }
    
    public int vertexDegree(){
        return synonyms.size();
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((antonyms == null) ? 0 : antonyms.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((synonyms == null) ? 0 : synonyms.hashCode());
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		result = prime * result
				+ ((groupings == null) ? 0 : groupings.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Vertex other = (Vertex) obj;
		return this.id.equals(other.id);
	}
}