import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Vector;


public interface Graph {
	
	public boolean adjacent(Node<?> from, Node<?> to);
	public Vector<Node<?>> neighbors(Node<?> n);
	public void addEdge(Node<?> from, Node<?> to);
	public void removeEdge(Node<?> from, Node<?> to);
	public Node<?> findMatch(Node<?> n);
	public default  Graph dfs(Graph g, Node<?> head){
		Graph temp;
		if(g.getClass().toString().contains("DirectedGraph")){
			temp = new DirectedGraph();
		}else{
			temp = new UndirectedGraph();
		}
		 
		Deque<Node<?>> st = new ArrayDeque<Node<?>>();
		Node<?> current;
		st.push(head);			//push head to stack
		do{	
			current = st.pop();								//get new current
			Node<?> currentCopy = current.copy();			//create a single copy so we don't get unconnected nodes in graph
			for(Node<?> n : g.neighbors(current)){			//go through all of currents connected nodes
				if(currentCopy.isUsed()){					//keep from adding nodes as both currentCopy and n 
					currentCopy = temp.findMatch(currentCopy); //replace current copy with copy already in graph
				}
				current.setUsed(true);						//mark as used
				if(!n.isUsed()){							//if we've not used it yet
					temp.addEdge(currentCopy, n.copy());	//add it to the return graph
					st.push(n);								//add it to queue
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
		Graph temp;
		if(g.getClass().toString().contains("DirectedGraph")){
			temp = new DirectedGraph();
		}else{
			temp = new UndirectedGraph();
		}
		
		Deque<Node<?>> st = new ArrayDeque<Node<?>>();
		Node<?> current;
		st.push(head);
		do{
			current = st.removeLast();
			Node<?> currentCopy = current.copy();
			for(Node<?> n : g.neighbors(current)){
				if(currentCopy.isUsed()){					
					currentCopy = temp.findMatch(currentCopy); 
				}
				current.setUsed(true);
				if(!n.isUsed()){
					temp.addEdge(currentCopy, n.copy());
					st.push(n);
					n.setUsed(true);
				}
			}
		}while(!st.isEmpty());
		for(Node<?> n : g.getNodes()){
			n.setUsed(false);
		}
		return temp;
	}
	public Vector<Node<?>> getNodes();
	public String toString();
}
