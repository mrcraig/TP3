package thesaurus.spring;
import java.awt.geom.Point2D;
//import java.util.LinkedList;
//import java.util.List;
import java.util.ArrayList;
import thesaurus.parser.*;
public class FrSpring2 {
	private int length;
	private int width;
	private int area;
	private int size;
	private double k;
	private double temprature;

	private double[][] dis;
	private ArrayList<Vertex> lstVertices;
	private ArrayList<Point2D> pos;
	private double EPSILON = 0.000001D;
	private Vertex myWord;


	public FrSpring2(Vertex myVer) {
		this.length = 800;
		this.width = 800;
		this.temprature = ((double) this.width / 10);
		this.area = (this.width) * (this.length);
		this.myWord = myVer;
		getVertex();				//add the vertices into arraylist
		this.size =  this.lstVertices.size();


		for (int i = 0; i < this.size; i++) {
			if (i == 0) {

				lstVertices.get(i).setPos(create((double) this.length / 2.0,
						((double) this.width) / 2.0));
				lstVertices.get(i).setPDis(create(0, 0));
				continue;													                 	// this is the center of the canvas
			}

			double myX = Math.random() * this.width;    
			double myY = Math.random() * this.length;							
			lstVertices.get(i).setPos (create(myX, myY));    				//place vertices at random
			lstVertices.get(i).setPDis(create(0, 0));						//initialize displacement of every vertex to 0

			k = Math.sqrt(((double) this.area / (double) this.size)); k *= 10; // compute optimal pairwise distance
		}
		mySpring();
	}



	private void mySpring() {
		for (int ite = 0; ite < 100; ite++) {
			for (Vertex v : this.lstVertices) {
				v.getDis().setLocation(0, 0);
				for (Vertex u : this.lstVertices) {
					if (!(u.equals(v))) {

						double disX = v.getPos().getX() - u.getPos().getX();   // difference of x coordinate
						double disY = v.getPos().getY() - u.getPos().getY();   // difference of y coordinate
						double deltaLength = Math.max(EPSILON,  v.getPos()	   // if distance between vertices is zero, since  
								.distanceSq(u.getPos()));				       // couldn't divided by zero use EPSILON  

						double rforce = repulsionF(Math.abs(deltaLength));     // repulsion force (distance)

						disX = (v.getPos().getX() + (disX * rforce));             // displacement of x
						disY = (v.getPos().getY() + (disY * rforce));			  //  displacement of y
						v.getDis().setLocation(disX,disY);
					}
				}
			} // this is the end of the first inner loop
			for (int i = 1; i < this.size; i++) { // the edges of the graph
				double disX = pos.get(0).getX() - pos.get(i).getX();
				double disY = pos.get(0).getY() - pos.get(i).getY();

				double deltaLength = Math.max(EPSILON,
						pos.get(0).distanceSq(pos.get(i)));
				double aforce = attractionF(Math.abs(deltaLength));          //compute attraction force

				dis[i][0] = (dis[i][0] + (disX * aforce));					// displacement  edge x coordinate
				dis[i][1] = (dis[i][1] + (disY * aforce));					// displacement edge y coordinate
			}

			for (int j = 1; j < this.size; j++) {
				double newXDisp = dis[j][0] / Math.abs(dis[j][0])           //use temperature to scale x
				* Math.min(Math.abs(dis[j][0]), temprature);

				double newYDisp = dis[j][1] / Math.abs(dis[j][1])							// use temperature to scale y
				* Math.min(Math.abs(dis[j][1]), temprature);

				double newX = pos.get(j).getX() + newXDisp;					// adjust position  using displacement scaled by temperature
				double newY = pos.get(j).getY() + newYDisp;

				newX = Math.max(0, Math.min(newX, width));					// limit max displacement to frame
				newY = Math.max(0, Math.min(newY, length));
				pos.get(j).setLocation(newX, newY); 

			}
			temprature *= (1.0 - ite / (double) 100); // reduce temperature
		}
		/* the is for test only, Begin testing */
		double[][] tmp = new double[size][2];
		for (int i = 0; i < this.size; i++) {
			tmp[i][0]= pos.get(i).getX();
			tmp[i][1] = pos.get(i).getY();

		}
		int count = 0;
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++){
				if (i!=j){
					if(tmp[i][0] == tmp[j][0]  && tmp[i][1] == tmp[j][1]){
						count++;
					}
				}
			}

		}
		System.out.println("Replicated coordinates =" +count);
		/* end of the test. */

	}

	/* calculates repulsion force between non-adjacent vertices x is a distance calculated by pythagoras   */
	private double repulsionF(double x) {

		return ((k * k) / x);
	}

	/* calculates attraction force between edges y is length of the edge*/
	private double attractionF(double y) {
		return ((y * y) / k);
	}

	/* create and returns coordinate points */
	private Point2D create(double x, double y) {
		return new Point2D.Double(x, y);
	}

	/* this method adds all vertices into lsVisited which is used for repulsion of vertices 
	 * and also helps to identify the actual number of vertices (size)*/
	private void getVertex() {
		this.lstVertices.add(myWord);
		for (Vertex ver : this.myWord.getAdjList()){
			if (ver.isVisited())continue;
			this.lstVertices.add(ver);
			for (Vertex inVer : ver.getAdjList()){
				if (inVer.isVisited()) continue;
				this.lstVertices.add(inVer);
			}
		}

	}

	/*	private void setUPMian(){

		disVetexMap.add(myWord);
		DisEdgeMap myEdgeList = new DisEdgeMap(indexDisp);      // add source edge
		for (Vertex ver : this.myWord.getAdjList()){
			indexDisp++;									//Increment index
			myEdgeList.addEdge(indexDisp);					//add to end edge as target
			disVetexMap.add(ver);					// add to displacement vertex mapping

	}
		myLimite = indexDisp;							// this is the limit of layering
		myEdge.add(myEdgeList);                        //add list of edges to a list
		for (Vertex ver : this.myWord.getAdjList()){

		}
	}
	private void setUSecondary(Vertex ver){
			DisEdgeMap myList = new DisEdgeMap(0);
			for (Vertex innerVer : ver.getAdjList()){
				if (isNewVertex(innerVer)){
					index++;
				}
			}
		}*/




	private boolean isNewVertex(Vertex ver){
		for (Vertex myV : lstVertices){
			if (myV.equals(ver)) return false;
		}
		return true;
	}

	public ArrayList<Point2D> getCoordinates() {
		return pos;
	}


}
