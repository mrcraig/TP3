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
	final private int length =1000;
	final private int width = 1500;
	private boolean overlap= false;
	private boolean overEdgeCrossing = false;
	private double constK = 1.000;
	
	
	
	public FrSpring(Vertex v1) {
		
		this.temprature = ((double) this.width / 50);
		this.area = (this.width) * (this.length);
		this.myWord = v1;
		lstVertices = new LinkedList<Vertex>();
		//layerIndex = 
		getSnVertex(this.myWord);			           	//add the vertices into arraylist
		getAnVertex(this.myWord);
		this.size =  this.lstVertices.size();
		
		double myX = 0.0;   
		double myY=0.0;
		for (int i = 0; i < this.size; i++) {
			if (i == 0) {
				 myX = ((double) (this.width) / 2.3);    
				 myY = (((double) this.length) / 3.0);	
				lstVertices.get(i).setPos(create(myX,myY));
				lstVertices.get(i).setPDis(create(0, 0));
				continue;
																	                 	// this is the center of the canvas
			}
			
			myX =  Math.random() * this.width;    
	        myY = Math.random() * this.length;
			lstVertices.get(i).setPos (create(myX, myY));    				//place vertices at random
			lstVertices.get(i).setPDis(create(0, 0));
								//initialize displacement of every vertex to 0

			constK = Math.sqrt(((double) this.area / (double) this.size));k=23*constK; // compute optimal pairwise distance
			
			//System.out.println(k);
		}
		
		//System.out.println(k);
		mySpring();
		
	}



	private void mySpring() {
		for (int ite = 0; ite < (900); ite++) {
			for (Vertex v : this.lstVertices) {
				v.getDis().setLocation(0, 0); 
				//boolean v1_OnBorder = isOnBorder(v.getDis());
				for (Vertex u : this.lstVertices) {
					if (!(u.equals(v))) {
						double disX = v.getPos().getX() - u.getPos().getX();   // difference of x coordinate
						double disY = v.getPos().getY() - u.getPos().getY();   // difference of y coordinate
						double deltaLength = Math.max(EPSILON,  v.getPos()	   // if distance between vertices is zero, since  
								.distanceSq(u.getPos()));				       // couldn't divided by zero use EPSILON
						double rforce = repulsionF(Math.abs(deltaLength));     // repulsion force (distance)
						assert Double.isNaN(rforce) == false : "Unexpected mathematical result in FRSpring Layout:Spring [Repulsion force]";
							disX = (v.getDis().getX() + (disX * rforce));             // displacement of x
							disY = (v.getDis().getY() + (disY *rforce));			  //  displacement of y
							v.getDis().setLocation(disX,disY);
					
					}
				}
			} // this is the end of the the loop that repulse every every vertex
			for (int i = 0; i < this.size; i++) { // the edges of the graph
				Vertex source = this.lstVertices.get(i);
				
				if (source.getPos() == null) continue;
				for (Vertex target : source.getSynomyns() ){
					if(target.equals(lstVertices.get(0))) continue;
					if (target.equals(lstVertices.get(i)))continue;
					if (target.getPos() == null) continue;
					
					double disX = source.getPos().getX() - target.getPos().getX();
					double disY = source.getPos().getY() - target.getPos().getY();
									
					double deltaLength = Math.max(EPSILON,
							source.getPos().distanceSq(target.getPos()));
					double aForce = 1;
					aForce = attractionF(Math.abs(deltaLength));        //compute attraction force
					//if (source.equals(myWord)|| target.equals(myWord)) aForce*= 10;
					assert Double.isNaN(aForce) == false : "Unexpected mathematical result in FRSpring Layout:Spring[Attraction force]";
				
						double sdisX = (source.getDis().getX() - (disX * aForce));					// displacement  edge x coordinate
						double sdisY = (source.getDis().getY() - (disY* aForce));					// displacement edge y coordinate
						source.getDis().setLocation(sdisX, sdisY);
					//}
					
									
					double tdisX = (target.getDis().getX() + (disX * aForce));					// displacement  edge x coordinate
					double tdisY = (target.getDis().getY() + (disY * aForce));	
					target.getDis().setLocation(tdisX, tdisY);
					}
				for (Vertex target : source.getAntonyms() ){
					if(target.equals(lstVertices.get(0))) continue;
					if (target.equals(lstVertices.get(i)))continue;
					if (target.getPos() == null) continue;
					
					double disX = source.getPos().getX() - target.getPos().getX();
					double disY = source.getPos().getY() - target.getPos().getY();
									
					double deltaLength = Math.max(EPSILON,
							source.getPos().distanceSq(target.getPos()));
					double aForce = 1;
					aForce = attractionF(Math.abs(deltaLength));        //compute attraction force
					//if (source.equals(myWord)|| target.equals(myWord)) aForce*= 10;
					assert Double.isNaN(aForce) == false : "Unexpected mathematical result in FRSpring Layout:Spring[Attraction force]";
				
						double sdisX = (source.getDis().getX() - (disX * aForce));					// displacement  edge x coordinate
						double sdisY = (source.getDis().getY() - (disY* aForce));					// displacement edge y coordinate
						source.getDis().setLocation(sdisX, sdisY);
					//}
					
									
					double tdisX = (target.getDis().getX() + (disX * aForce));					// displacement  edge x coordinate
					double tdisY = (target.getDis().getY() + (disY * aForce));	
					target.getDis().setLocation(tdisX, tdisY);
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
				
				double newX = this.lstVertices.get(j).getPos().getX()+newXDisp;//Math.max(-100, Math.min(100, newXDisp));				// adjust position  using displacement scaled by temperature
				double newY = this.lstVertices.get(j).getPos().getY()+newYDisp;Math.max(-50, Math.min(100, newYDisp));

				newX = Math.max(30, Math.min(newX, width));					// limit max displacement to frame
				newY = Math.max(30, Math.min(newY, length));
				this.lstVertices.get(j).getPos().setLocation(newX, newY); 

			}
			
			
			temprature *= (1.0 - (ite / (double) (7000))); // reduce temperature
			
		}
		
		/* the is for test only, Begin testing */
		Point2D[] tmp = new Point2D[size];
		for (int i = 0; i < this.size; i++) {
			tmp[i]= this.lstVertices.get(i).getPos();
			//System.out.print(this.lstVertices.get(i).getWord()+" ");
		}
		int count = 0;
		
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++){
				
				if (i!=j){
					
					if(tmp[i].equals(tmp[j])){
						count++;System.out.println(j);					
					}
					
				}
			}

		}
		for(Vertex ve: this.lstVertices){
			ve.setVisited(false);
		}
		int optimaztion = 0;
		while (optimaztion > 10 ) {mySpring(); optimaztion++;}
		
		if (count != 0)	mySpring();
		
		

	}

	public boolean isOverlap() {
		return overlap;
	}

	public boolean isOverEdgeCrossing() {
		return overEdgeCrossing;
	}


	/* calculates repulsion force between non-adjacent vertices x is a distance calculated by pythagoras   */
	private double repulsionF(double x) {

		return ((k *k)/ x)*12 ;
	}

	/* calculates attraction force between edges y is length of the edge*/
	private double attractionF(double y) {
		return (((y * y) / (k)))*1.5;
	}

	/* create and returns coordinate points */
	private Point2D create(double x, double y) {
		return new Point2D.Double(x, y);
	}

	/* add all the sys vetex to the linked list 
	 * and also helps to identify the actual number of vertices (size)*/
	private int getSnVertex(thesaurus.parser.Vertex word) {
		int count = 1;
		this.lstVertices.addFirst(word);word.setVisited(true);
		for (Vertex ver : word.getSynomyns()){
			if (!(ver.isVisited())){
			this.lstVertices.addLast(ver); ver.setVisited(true);count++;}
			for (Vertex inVer : ver.getSynomyns()){
				if (!(inVer.isVisited())){
				this.lstVertices.addLast(inVer);inVer.setVisited(true);count++;;}
				for (Vertex thirdLayer : inVer.getSynomyns()){
					if (!(thirdLayer.isVisited())){
					this.lstVertices.addLast(thirdLayer);thirdLayer.setVisited(true);count++;;}
				}
			}
			
		}
			
		return count;
	}
	private int getAnVertex(thesaurus.parser.Vertex word) {
		int count = 1;
		//this.lstVertices.addFirst(word);word.setVisited(true);
		for (Vertex ver : word.getAntonyms()){
			if (!(ver.isVisited())){
			this.lstVertices.addLast(ver); ver.setVisited(true);count++;}
			for (Vertex inVer : ver.getSynomyns()){
				if (!(inVer.isVisited())){
				this.lstVertices.addLast(inVer);inVer.setVisited(true);count++;;}
				for (Vertex thirdLayer : inVer.getSynomyns()){
					if (!(thirdLayer.isVisited())){
					this.lstVertices.addLast(thirdLayer);thirdLayer.setVisited(true);count++;;}
				}
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


}

