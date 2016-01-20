import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Vector;


public interface Graph {
	
	public boolean adjacent(Node<?> from, Node<?> to);
	public Vector<Node<?>> neighbors(Node<?> n);
	public void addEdge(Node<?> from, Node<?> to);
	public void removeEdge(Node<?> from, Node<?> to);
	public Node<?> findMatch(Node<?> n);
	public default Graph dfs(Graph g, Node<?> head){
		return this.dfs(g, head, new noWork());	//call dfs with noWork
	}
	public default  Graph dfs(Graph g, Node<?> head, Work work){
		Graph temp;
		if(g.getClass().toString().contains("DirectedGraph")){
			temp = new DirectedGraph();
		}else{
			temp = new UndirectedGraph();
		}
		
		Deque<Node<?>> st = new ArrayDeque<Node<?>>();
		Deque<Node<?>> workSt = new ArrayDeque<Node<?>>(); 		//tracks used nodes to apply work in appropriate order
		Node<?> current;
		st.push(head);											//push head to stack
		workSt.push(head.copy());								//push copy to workstack
		do{	
			current = st.pop();									//get new current
			Node<?> currentCopy = workSt.pop();					//create a single copy so we don't get unconnected nodes in graph
			for(Node<?> n : g.neighbors(current)){				//go through all of currents connected nodes
				if(current.isUsed()){							//keep from adding nodes as both currentCopy and n 
					currentCopy = temp.findMatch(currentCopy); 	//replace current copy with copy already in graph
				}else{
					currentCopy = work.doWork(currentCopy);		//otherwise do the work
				}
				
				if(!n.isUsed()){								//if we've not used it yet
					current.setUsed(true);							//mark as used
					st.push(n);									//add it to queue
					workSt.push(n.copy());
					temp.addEdge(currentCopy, workSt.getFirst());//add it to the return graph
					n.setUsed(true);
					
				}
			}	
		}while(!st.isEmpty());
		for(Node<?> n : g.getNodes()){	//reset all used values
			n.setUsed(false);
		}
		return temp;
	}
	public default Graph bfs(Graph g, Node<?> head){
		return this.bfs(g, head, new noWork());
	}
	public default Graph bfs(Graph g, Node<?> head, Work work){
		Graph temp;
		if(g.getClass().toString().contains("DirectedGraph")){
			temp = new DirectedGraph();
		}else{
			temp = new UndirectedGraph();
		}
		
		Deque<Node<?>> st = new ArrayDeque<Node<?>>();
		Deque<Node<?>> workSt = new ArrayDeque<Node<?>>(); 	
		Node<?> current;
		st.push(head);									
		workSt.push(head.copy());					
		do{	
			current = st.removeLast();							
			Node<?> currentCopy = workSt.removeLast();
			for(Node<?> n : g.neighbors(current)){				
				if(current.isUsed()){							
					currentCopy = temp.findMatch(currentCopy); 	
				}else{
					currentCopy=work.doWork(currentCopy);
				}							
				if(!n.isUsed()){
					current.setUsed(true);
					st.push(n);								
					workSt.push(n.copy());
					temp.addEdge(currentCopy, workSt.getFirst());
					n.setUsed(true);
					
				}
			}	
		}while(!st.isEmpty());
		for(Node<?> n : g.getNodes()){	//reset all used values
			n.setUsed(false);
		}
		return temp;
	}
	public Vector<Node<?>> getNodes();
	public String toString();
	
	 class noWork implements Work{ //private inner class to facilitate overload of dfs and bfs
		 
		public Node<?> doWork(Node<?> n) {
			return n;
		}
		
	}
}
