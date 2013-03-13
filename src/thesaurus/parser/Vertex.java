package thesaurus.parser;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;



public class Vertex {
   
    private ArrayList<Vertex> synonyms; 
    private ArrayList<Vertex> antonyms;
    private ArrayList<Vertex> groupings;
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
    	synonyms = new ArrayList<Vertex>();
    	antonyms = new ArrayList<Vertex>();
    	groupings = new ArrayList<Vertex>();
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

	public ArrayList<Vertex> getSynomyns()
	{
        return synonyms;
    }
	
	public ArrayList<Vertex> getAntonyms()
	{
		return antonyms;
	}
	
	public ArrayList<Vertex> getGroupings()
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
        synonyms.add(n);
    }
    
    public void addAntonym(Vertex n)
    {
    	antonyms.add(n);
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
    
    public void setSynonyms(ArrayList<Vertex> syns)
    {
    	this.synonyms = syns;
    }
    
    public void setAntonyms(ArrayList<Vertex> ants)
    {
    	this.antonyms = ants;
    }
    
    public void setGroupings(ArrayList<Vertex> g)
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

	public boolean equals(Vertex obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = obj;
		if (antonyms == null) {
			if (other.antonyms != null)
				return false;
		} else if (!antonyms.equals(other.antonyms))
			return false;
		if (groupings == null) {
			if(other.groupings != null)
				return false;
		}	else if(!groupings.equals(other.groupings))
				return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (synonyms == null) {
			if (other.synonyms != null)
				return false;
		} else if (!synonyms.equals(other.synonyms))
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}
}