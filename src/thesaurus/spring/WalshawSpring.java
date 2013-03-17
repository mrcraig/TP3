package thesaurus.spring;
import java.awt.geom.Point2D;
import java.util.LinkedList;

import thesaurus.parser.Vertex;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.ArrayList;
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
			
		//	myX =(size-i)*this.width/6;    
		//	myY =(size-i)*this.length/6; 
			myX =  Math.random()* this.width*1;    
		   myY =  Math.random()* this.length*1;
			System.out.println(myX+ " "+myY);
			lstVertices.get(i).setPos (create(myX, myY));    				//place vertices at random
			lstVertices.get(i).setPDis(create(0, 0));
								//initialize displacement of every vertex to 0

			k = Math.sqrt(((double) this.area / (double) this.size));//k;// compute optimal pairwise distance
			//System.out.println(k);
		}
		//k=20576.906738115205;
		//k=24128.56191321812;
		//k=68800.906738115205;
		System.out.println(k);
		mySpring();
		
	}


	private void mySpring() {
		int converged =50;double count1 = 1;
		while (converged >1) converged -= 1; {
			for (Vertex v : this.lstVertices) {
				v.getDis().setLocation(0, 0); 
				
				for (Vertex u : this.lstVertices) {
					if (!(u.equals(v))) {
						
						double disX = v.getPos().getX() - u.getPos().getX();   // difference of x coordinate
						double disY = v.getPos().getY() - u.getPos().getY();   // difference of y coordinate
						double deltaLength = Math.max(EPSILON,  u.getPos()	   // if distance between vertices is zero, since  
								.distanceSq(v.getPos()));				       // couldn't divided by zero use EPSILON
						
						double delta = Math.max(EPSILON, Math.sqrt((u.getPos().getX()*u.getPos().getX())+
							(u.getPos().getY()*u.getPos().getY())));
						//double rforce = repulsionF(Math.abs(deltaLength)/disX,Math.abs(u.getPos().getX()));     // repulsion force (distance)
						//assert Double.isNaN(rforce) == false : "Unexpected mathematical result in FRSpring Layout:Spring [Repulsion force]";
						
						
							disX = v.getDis().getX() + deltaLength/Math.abs(deltaLength) *repulsionF( Math.abs(deltaLength),Math.abs(delta));             // displacement of x
							disY = v.getDis().getY() + deltaLength/Math.abs(deltaLength)  *repulsionF( Math.abs(deltaLength),Math.abs(delta)); 			  //  displacement of y
							v.getDis().setLocation(disX,disY);
					
					}
				}
					Vertex source = v;		
				for (Vertex target : source.getSynomyns()){
					
					if (target.getPos() == null) continue;
					
					
									
					double deltaLength = Math.max(EPSILON,
							source.getPos().distanceSq(target.getPos()));
					
					
					double deltaTar = Math.max(EPSILON, Math.sqrt((target.getPos().getX()*target.getPos().getX())+
							(target.getPos().getY()*target.getPos().getY())));
					double deltaSor = Math.max(EPSILON, Math.sqrt((source.getPos().getX()*source.getPos().getX())+
							(source.getPos().getY()*source.getPos().getY())));
					
					double tdisX = target.getDis().getX() +(deltaLength/Math.abs(deltaLength))*attractionF(Math.abs(deltaLength),Math.abs(deltaSor),Math.abs(deltaTar));					// displacement  edge x coordinate
					double tdisY = target.getDis().getY() +(deltaLength/Math.abs(deltaLength))*attractionF(Math.abs(deltaLength),Math.abs(deltaSor),Math.abs(deltaTar));	
					target.getDis().setLocation(tdisX, tdisY);
					
			}
				if( v.equals(myWord))continue;
			System.out.println(v.getDis());
			Point2D oldPosistion = new Point2D.Double(v.getPos().getX(), v.getPos().getY());	
			double newPosX = v.getPos().getX() +( v.getDis().getX()/Math.max(Math.abs(v.getDis().getX()),EPSILON)*
					Math.min(temperature ,Math.abs(v.getDis().getX()))); 
			double newPosY = v.getPos().getY() + (v.getDis().getY()/Math.max(Math.abs(v.getDis().getY()),EPSILON)* 
					Math.min(temperature, Math.abs(v.getDis().getY()))); 
			newPosX = Math.max(30, Math.min(newPosX, width));					// limit max displacement to frame
			newPosY = Math.max(30, Math.min(newPosY, length));
			v.getPos().setLocation( new Point2D.Double(newPosX, newPosY));
			System.out.println(v.getPos());
			double changeX = v.getPos().getX() - oldPosistion.getX();
			double changeY = v.getPos().getY() - oldPosistion.getY();
			double change = Math.sqrt((changeX * changeX)+(changeY *changeY));
			count1= count1*0.98;
			System.out.println(count1+"  " +change+" "+k);
			if (Math.abs(change)> k* 0.0001) converged=0;
			}//end of the first loop that sets displacement to zero.
			temperature = cool(temperature);
		   }//first
			
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

		return (((-1*w*(k * k))/ x));
	}
	/* calculates attraction force between edges y is length of the edge*/
	private double attractionF(double x, double d, double w) {
		return (((x-k)/d)-repulsionF(x,w));
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


