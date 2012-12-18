package thesaurus.parser;
import java.awt.geom.Point2D;
import java.util.LinkedList;



public class Vertex {
   
    private LinkedList<Vertex> synonyms; 
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
    	synonyms = new LinkedList<Vertex>();
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
     
    public String getIndex()
    {
    	return index;
    }
    
    public void setIndex(String n)
    {
    	index = n;
    }   
    
    public void addSynonym(Vertex n)
    {
        synonyms.addLast(n);
    }
    
    public void addAntonym(Vertex n)
    {
    	antonyms.addLast(n);
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
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result
				+ ((synonyms == null) ? 0 : synonyms.hashCode());
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (antonyms == null) {
			if (other.antonyms != null)
				return false;
		} else if (!antonyms.equals(other.antonyms))
			return false;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
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
