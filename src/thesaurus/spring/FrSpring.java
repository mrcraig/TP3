package thesaurus.spring;
import java.awt.geom.Point2D;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.ArrayList;
import java.util.LinkedList;
import thesaurus.parser.*;
public class FrSpring {
	
	private int area;
	private int size;
	private double k;
	private double temprature;
	private LinkedList<Vertex> lstVertices  ;
	private double EPSILON = 0.000001D;
	private thesaurus.parser.Vertex myWord;
	private int layerIndex = 1;
	final private int length = 300;
	final private int width = 690;
	private boolean overlap= false;
	private boolean overEdgeCrossing = false;
	private int count;
	
	public FrSpring(Vertex v1) {
		
		this.temprature = ((double) this.width / 10);
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
			
			// myX =-size-(i*i);    
			//myY =-size-(i*i) ;	
			myX = Math.random() * this.width;    
			myY = Math.random() * this.length;
			lstVertices.get(i).setPos (create(myX, myY));    				//place vertices at random
			lstVertices.get(i).setPDis(create(0, 0));
								//initialize displacement of every vertex to 0

			k = Math.sqrt(((double) this.area / (double) this.size)); k*=3050; // compute optimal pairwise distance
			//System.out.println(k);
		}
		System.out.println("Initial vertices");
		for (Vertex v : lstVertices){
			System.out.println("Vertex: " + v.getWord() + " {" + v.getPos().getX() + "," + v.getPos().getY() + "}");
		}
		//k=20576.906738115205;
		//k=24128.56191321812;
		//k=68800.906738115205;
		System.out.println(k);
		mySpring();
		
	}



	private void mySpring() {
		for (int ite = 0; ite < (700); ite++) {
			for (Vertex v : this.lstVertices) {
				v.getDis().setLocation(0, 0); 
				//boolean v1_OnBorder = isOnBorder(v.getDis());
				for (Vertex u : this.lstVertices) {
					//boolean u_OnBorder = isOnBorder(u.getDis());
					//if (v1_OnBorder & u_OnBorder)continue;
					if(u.equals(lstVertices.get(0)))continue;
					if (!(u.equals(v))) {
						//boolean u_OnBorder = isOnBorder(u.getDis());
					//	if (v1_OnBorder & u_OnBorder)continue;
						double disX = v.getPos().getX() - u.getPos().getX();   // difference of x coordinate
						double disY = v.getPos().getY() - u.getPos().getY();   // difference of y coordinate
						double deltaLength = Math.max(EPSILON,  v.getPos()	   // if distance between vertices is zero, since  
								.distanceSq(u.getPos()));				       // couldn't divided by zero use EPSILON
						
						
						double rforce = repulsionF(Math.abs(deltaLength));     // repulsion force (distance)
						assert Double.isNaN(rforce) == false : "Unexpected mathematical result in FRSpring Layout:Spring [Repulsion force]";
						
						
							disX = (v.getDis().getX() + (disX * rforce * 10));             // displacement of x
							disY = (v.getDis().getY() + (disY * rforce * 10));			  //  displacement of y
							v.getDis().setLocation(disX,disY);
					
					}
				}
			} // this is the end of the the loop that repulse every every vertex
			for (int i = 0; i < this.size; i++) { // the edges of the graph
				Vertex source = this.lstVertices.get(i);
				
				if (source.getPos() == null) continue;
				boolean v_OnBorder = isOnBorder(source.getDis());
				for (Vertex target : source.getSynomyns()){
					if(target.equals(lstVertices.get(0))) continue;
					if (target.equals(lstVertices.get(i)))continue;
					if (target.getPos() == null) continue;
					
					boolean u_OnBorder = isOnBorder(target.getDis());
					double disX = source.getPos().getX() - target.getPos().getX();
					double disY = source.getPos().getY() - target.getPos().getY();
									
					double deltaLength = Math.max(EPSILON,
							source.getPos().distanceSq(target.getPos()));
					double aforce = 1;
					
					 aforce = attractionF(Math.abs(deltaLength));        //compute attraction force
					assert Double.isNaN(aforce) == false : "Unexpected mathematical result in FRSpring Layout:Spring[Attraction force]";
					
					double myAForce = aforce;
					
					
					for (Vertex myS : lstVertices.get(0).getSynomyns()){
						if (source.equals(myS)&& (!target.equals(myS))&& (!target.equals(lstVertices.get(0)))) myAForce = aforce*50000;
						if ((source.equals(myS))&& target.equals(lstVertices.get(0))) {myAForce = 2000;}
						if ((source.equals(lstVertices.get(0)))&&(target.equals(myS))&& getCons(myS,target))myAForce = aforce*200000;
						if ((source.equals(lstVertices.get(0)))&&(target.equals(myS))){  myAForce = aforce*5500;}
						
						//if ( !target.equals(myS)) {myAForce = aforce/10;}
						if (source.equals(myS) && (target.equals(myS))) myAForce = aforce*990000;
					}
					     
					//if ((source.equals(lstVertices.get(0)))&&(target.equals(myS))){  myAForce = aforce*170;}
					
					
					
				
				if ((u_OnBorder) && (!(source.equals(lstVertices.get(0))))){
						double sdisX = (source.getDis().getX() - (disX *2000* myAForce));					// displacement  edge x coordinate
						double sdisY = (source.getDis().getY() - (disY *2000* myAForce));					// displacement edge y coordinate
						source.getDis().setLocation(sdisX, sdisY);}
					else
					{
						double sdisX = (source.getDis().getX() - (disX *1* myAForce));					// displacement  edge x coordinate
						double sdisY = (source.getDis().getY() - (disY*1* myAForce));					// displacement edge y coordinate
						source.getDis().setLocation(sdisX, sdisY);
					}
					
					
					if (v_OnBorder){
						double tdisX = (target.getDis().getX() + (disX *2000* myAForce));					// displacement  edge x coordinate
						double tdisY = (target.getDis().getY() + (disY *2000* myAForce));	
						target.getDis().setLocation(tdisX, tdisY);
					}
					else
					{
				
					double tdisX = (target.getDis().getX() + (disX *1* myAForce));					// displacement  edge x coordinate
					double tdisY = (target.getDis().getY() + (disY *1* myAForce));	
					target.getDis().setLocation(tdisX, tdisY);
					}
			}
			}

			for (int j = 0; j < this.size; j++) {
                
				if (this.lstVertices.get(j).equals(myWord))continue;
				

				double deltaforce = Math.sqrt((this.lstVertices.get(j).getDis().getX()*this.lstVertices.get(j).getDis().getX())
									+(this.lstVertices.get(j).getDis().getY()*this.lstVertices.get(j).getDis().getY()));
				
				double myDelta = Math.max(deltaforce, EPSILON);
				double newXDisp = (this.lstVertices.get(j).getDis().getX() / myDelta)          //use temperature to scale x
									* Math.min(deltaforce, temprature);
				assert Double.isNaN(newXDisp) == false : "Unexpected mathematical result in FRSpring Layout:Spring [newXDisp]";
				
				
				double newYDisp = (this.lstVertices.get(j).getDis().getY() / myDelta)          //use temperature to scale x
				* Math.min(deltaforce, temprature);
				assert Double.isNaN(newYDisp) == false : "Unexpected mathematical result in FRSpring Layout:Spring [newYDisp]";
				
				double newX = this.lstVertices.get(j).getPos().getX()+Math.max(-100, Math.min(100, newXDisp));				// adjust position  using displacement scaled by temperature
				double newY = this.lstVertices.get(j).getPos().getY()+Math.max(-100, Math.min(100, newYDisp));

				newX = Math.max(30, Math.min(newX, width));					// limit max displacement to frame
				newY = Math.max(30, Math.min(newY, length));
				this.lstVertices.get(j).getPos().setLocation(newX, newY); 

			}
			
			
			temprature *= (1.0 - (ite / (double) (700))); // reduce temperature
		}
		/* the is for test only, Begin testing */
		Point2D[] tmp = new Point2D[size];
		for (int i = 0; i < this.size; i++) {
			tmp[i]= this.lstVertices.get(i).getPos();
		}
		count = 0;
		
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
	private double repulsionF(double x) {

		return (((k * k)/ x));
	}

	/* calculates attraction force between edges y is length of the edge*/
	private double attractionF(double y) {
		return (((y * y) / (k)));
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
private boolean getCons(Vertex sor, Vertex tar){
	if (sor.equals(tar)) return false;
	for (Vertex b:sor.getSynomyns()){
	if(	b.equals(tar)) return true;
		
	}
	return false;
}

}

