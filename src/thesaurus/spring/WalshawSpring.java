package thesaurus.spring;
import java.awt.geom.Point2D;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.ArrayList;
import java.util.LinkedList;
import thesaurus.parser.*;
public class WalshawSpring {
	
	private int area;
	private int size;
	private double k;
	private double temperature;
	private LinkedList<Vertex> lstVertices  ;
	private double EPSILON = 0.000001D;
	private thesaurus.parser.Vertex myWord;
	private int layerIndex = 1;
	final private int length = 300;
	final private int width = 690;
	private boolean overlap= false;
	private boolean overEdgeCrossing = false;
	
	public WalshawSpring(Vertex v1) {
		
		this.temperature = ((double) this.width / 10);
		this.area = (this.width) * (this.length);
		this.myWord = v1;
		lstVertices = new LinkedList<Vertex>();
		layerIndex = getVertex(this.myWord);			           	//add the vertices into arraylist
		this.size =  this.lstVertices.size();
		
		double myX = 0.0;   
		double myY=0.0;
		for (int i = 0; i < this.size; i++) {
			if (i == 0) {
				 myX = ((double) (this.width) / 2.0);    
				 myY = (((double) this.length) / 2.0);	
				lstVertices.get(i).setPos(create(myX,myY));
				lstVertices.get(i).setPDis(create(0, 0));
				continue;
																	                 	// this is the center of the canvas
			}
			
			 myX =i*50;    
			myY =i*50;	
		//	myX =  Math.random()* this.width*1;    
			//myY =  Math.random()* this.length*1;
			System.out.println(myX+ " "+myY);
			lstVertices.get(i).setPos (create(myX, myY));    				//place vertices at random
			lstVertices.get(i).setPDis(create(0, 0));
								//initialize displacement of every vertex to 0

			k = Math.sqrt(((double) this.area / (double) this.size)); //k*=3050; // compute optimal pairwise distance
			//System.out.println(k);
		}
		//k=20576.906738115205;
		//k=24128.56191321812;
		//k=68800.906738115205;
		System.out.println(k);
		mySpring();
		
	}


	private void mySpring() {
		int converged = 0;
		int count1 = 0;
		while (converged != 1) {
			converged = 1;
			for (Vertex v : this.lstVertices) {
				if (v == null) continue;
				Point2D oldPosistion = new Point2D.Double(v.getPos().getX(), v.getPos().getY());
				
				v.getDis().setLocation(0, 0); 
				//boolean v1_OnBorder = isOnBorder(v.getDis());
				for (Vertex u : this.lstVertices) {
					//System.out.println("I am in");
					if (!(u.equals(v))) {
						
						double disX = u.getPos().getX() - v.getPos().getX();   // difference of x coordinate
						double disY = u.getPos().getY() - v.getPos().getY();   // difference of y coordinate
						double deltaLength = Math.max(EPSILON,  u.getPos()	   // if distance between vertices is zero, since  
								.distanceSq(v.getPos()));				       // couldn't divided by zero use EPSILON
						
						
						
						disX = (v.getDis().getX() + (deltaLength/Math.abs(deltaLength))
									*repulsionF(Math.abs(deltaLength), u.getPos().getX()));             // displacement of x
						disY = (v.getDis().getY() + (deltaLength/Math.abs(deltaLength)) 
									*repulsionF(Math.abs(deltaLength), u.getPos().getY()));			  //  displacement of y
						
						v.getDis().setLocation(disX,disY);
					
					}
				}
			
			
				
				

				
				for (Vertex u : v.getSynomyns()){

				

					
					if (u.getPos() == null) continue;
					
					//boolean u_OnBorder = isOnBorder(target.getDis());
					double disX = u.getPos().getX() - v.getPos().getX();
					double disY = u.getPos().getY() - v.getPos().getY();
									
					double deltaLength = Math.max(EPSILON,
							u.getPos().distanceSq(v.getPos()));
					
					
					disX = (v.getDis().getX() + (deltaLength/Math.abs(deltaLength))
							*attractionF(Math.abs(deltaLength),Math.abs(v.getPos().getX()), Math.abs(u.getPos().getX())));             // displacement of x
					disY = (v.getDis().getX() + (deltaLength/Math.abs(deltaLength))
							*attractionF(Math.abs(deltaLength),Math.abs(v.getPos().getY()), Math.abs(u.getPos().getY())));		  //  displacement of y
					     
					      v.getDis().setLocation(Math.max(disX,EPSILON),Math.max(disY, EPSILON));
					      
		
			}
			
				
			double newPosX = v.getPos().getX() +( v.getDis().getX()/Math.abs(v.getDis().getX()))* Math.min(temperature ,Math.abs(v.getDis().getX())); 
			double newPosY = v.getPos().getY() + (v.getDis().getY()/Math.abs(v.getDis().getY()))* Math.min(temperature, Math.abs(v.getDis().getY())); 
			v.getPos().setLocation( new Point2D.Double(newPosX, newPosY));
			double changeX = v.getPos().getX() - oldPosistion.getX();
			double changeY = v.getPos().getY() - oldPosistion.getY();
			double change = Math.sqrt((changeX * changeX)+(changeY *changeY));
			count1++;
			System.out.println(change);
			if (Math.abs(change)> k*0.01) converged=0;
			}//end of the first loop that sets displacement to zero.
			temperature = cool(temperature);
		   }
			
		/* the is for test only, Begin testing */
		Point2D[] tmp = new Point2D[size];
		for (int i = 0; i < this.size; i++) {
			tmp[i]= this.lstVertices.get(i).getPos();
		}
		int count = 0;
		
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++){
				
				if (i!=j){
					
					if(tmp[i].equals(tmp[j])){
						count++;System.out.println(j);					
					}
					
					
					/*if ( myD < fixD){
						this.overlap = true;
						//System.out.println(Math.abs((tmp[i].distance(tmp[j])))+ " "+this.lstVertices.get(i).getIndex() +" "+this.lstVertices.get(j).getIndex());//break;	
					}*/
				}
			}

		}
		for(Vertex ve: this.lstVertices){
			ve.setVisited(false);
		}
		System.out.println("replication: "+count);
	//	int index = 0;
		
		//if (count != 0)	mySpring();
		/* end of the test. */

	}

	public boolean isOverlap() {
		return overlap;
	}

	public boolean isOverEdgeCrossing() {
		return overEdgeCrossing;
	}


	/* calculates repulsion force between non-adjacent vertices x is a distance calculated by pythagoras   */
	private double repulsionF(double x, double w) {

		return (((-0.000008*w*(k * k))/ x));
	}

	/* calculates attraction force between edges y is length of the edge*/
	private double attractionF(double x, double d, double w) {
		return ((x-k)/d)-repulsionF(x,w);
	}

	/* create and returns coordinate points */
	private Point2D create(double x, double y) {
		return new Point2D.Double(x, y);
	}

	/* this method adds all vertices into lsVisited which is used for repulsion of vertices 
	 * and also helps to identify the actual number of vertices (size)*/
	private int getVertex(thesaurus.parser.Vertex word) {
		int count = 1;
		this.lstVertices.addFirst(word);word.setVisited(true);

		for (Vertex ver : word.getSynomyns()){
			if (!(ver.isVisited())){
			this.lstVertices.addLast(ver); ver.setVisited(true);count++;}
			for (Vertex inVer : ver.getSynomyns()){

				if (!(inVer.isVisited())){
				this.lstVertices.addLast(inVer);inVer.setVisited(true);count++;;}
			}
		}
			
		return count;
	}



	public Vertex getCoordinates() {
		return myWord;
	}
public LinkedList<Vertex> getVertices(){
	return this.lstVertices;
}
public boolean isOnBorder(Point2D x ){
	return (x.getX()==0.0 || x.getY()==0.0||x.getX()==(double)this.width|| x.getY() == (double)this.length);
}

private double cool(double t){
	
	
	return (1.0 - (t) / (double)(50));
}

}


