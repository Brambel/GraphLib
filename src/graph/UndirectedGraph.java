package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class UndirectedGraph implements Graph {

	List<List<data>> graph = new Vector<>();
	List<Node<?>> nodes = new Vector<>();
	
	//class to hold an int so we don't have to use the immutable Integer
	private class data{
		public int val;
		data(int x){
			val=x;
		}
	}
	
	public boolean adjacent(Node<?> from, Node<?> to) {
		if(nodes.contains(from)&&nodes.contains(to)){
			if(graph.get(nodes.indexOf(from)).get(nodes.indexOf(to)).val>0){
				return true;
			}
		}
		return false;
	}
	
	public int getWeight(Node<?> from, Node<?> to){
		return graph.get(nodes.indexOf(from)).get(nodes.indexOf(to)).val;
	}
	
	public Vector<Node<?>> neighbors(Node<?> n) {
		int index = nodes.indexOf(n);
		Vector<Node<?>> temp = new Vector<>();
		for(int i =0;i<nodes.size();++i){
			if(graph.get(index).get(i).val==1){
				temp.addElement(nodes.get(i));
			}
		}	
		return temp;
	}

	public void addEdge(Node<?> from, Node<?> to) {
		addEdge(from, to, 1);
	}
	
	public void addEdge(Node<?>from, Node<?> to, int weight){
		if(!nodes.contains(from)){
			nodes.add(from);
			this.expand();
		}
		if(!nodes.contains(to)){
			nodes.add(to);
			this.expand();
		}
		int a = nodes.indexOf(to);
		int b = nodes.indexOf(from);
		graph.get(a).get(b).val=weight;
		graph.get(b).get(a).val=weight;
	}

	private void expand() {
		int size = nodes.size()-1;			//size of graph edges before node was added
		List<data> temp = new Vector<>();
		for(int i=0;i<size;++i){
			temp.add(new data(0));
		}
		graph.add(temp); 					//this tacks the new vector to the end already full of zeros
		for(List<data> n : graph){
			n.add(new data(0));				//add a zero to the end of each list in the graph
		}
	}
	
	public void removeEdge(Node<?> from, Node<?> to) {
		int a = nodes.indexOf(from);
		int b = nodes.indexOf(to);
		if(graph.get(a).get(b).val!=0){
			graph.get(a).get(b).val=0;
			graph.get(b).get(a).val=0;
		}
	}

	public Vector<Node<?>> getNodes() {	
		return (Vector<Node<?>>) nodes;
	}
	
	public String toString(){
		String temp="";
		for(Node<?> n : nodes){
			temp+=n.getValue()+" -> "+this.neighbors(n).toString()+"\n";
		}
		return temp;
	}

	public Node<?> findMatch(Node<?> n) {
		for(Node<?> x : nodes){
			if(x.equal(n)){
				return x;
			}
		}
		return null;
	}
	
	 public boolean hasCycle(int start) {
		 List<Boolean> vertexList = new ArrayList<>();
		 for(Boolean b : vertexList){
			 b = false;
		 }
		 
		 return cycle(start, vertexList);
	 }
		
	 
	 private boolean cycle(int start, List<Boolean> vertexList){
		 int max= nodes.size()-1;
		 vertexList.set(start, true);
		 
		    for (int j = 0; j < max; j++) {  
		        if (graph.get(start).get(j).val > 1  &&  (vertexList.get(j)  ||  hasCycle(j)))
		            return true;
		    }
		    return false;
	    }
	 

}
