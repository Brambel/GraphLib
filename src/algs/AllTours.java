package algs;

import java.util.ArrayList;
import java.util.List;


public class AllTours {
	
	class Node{
		int level;
		List<Integer> path = new ArrayList<>();
		int bound;
	}
	
	
	public void travelTour(int n, int w[][], List<Node> optTour, int minLength){
		List<Node> pq = new ArrayList<>();
		Node u = new Node();
		Node v = new Node();
		
		v.level=0;
		v.path.add(1);
		
		
	}
}
