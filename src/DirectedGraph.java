import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


public class DirectedGraph implements Graph {

	Map<Node<?>, Vector<Node<?>>> graph;
	
	public DirectedGraph(){
		graph = new HashMap<>();
	}
	

	public boolean adjacent(Node<?> from, Node<?> to) {
		if(graph.containsKey(from)){
			if(graph.get(from).contains(to)){
				return true;
			}
		}
		return false;
	}

	public Vector<Node<?>> neighbors(Node<?> n) {
		if(graph.containsKey(n)){
			return graph.get(n);
		}
		return null;
	}


	public void addEdge(Node<?> from, Node<?> to) {
		if(!graph.containsKey(from)){	
			graph.put(from, new Vector<Node<?>>());
		}
		if(!graph.containsKey(to)){
			graph.put(to, new Vector<Node<?>>());
		}
		if(!graph.get(from).contains(to)){
			graph.get(from).add(to);
		}

	}


	public void removeEdge(Node<?> from, Node<?> to) {
		if(graph.containsKey(from)){
			if(graph.get(from).contains(to)){
				graph.get(from).remove(to);
			}
		}

	}


	public Graph dfs(Graph g, Node<?> head) {
		Graph temp = new DirectedGraph();
		Deque<Node<?>> st = new ArrayDeque<Node<?>>();
		Node<?> current;
		st.push(head);			//push head to stack
		do{	
			current = st.pop();
			Node<?> currentCopy = current.copy();
			for(Node<?> n : g.neighbors(current)){			//go through all of currents connected nodes
				if(!n.isUsed()){							//if we've not used it yet
					temp.addEdge(currentCopy, n.copy());	//add it to the return graph
					st.push(n);								//add it to queue
					current.setUsed(true);					//mark as used
				}
			}
			current = st.pop();		//get new current
		}while(!st.isEmpty());
		for(Node<?> n : g.getNodes()){	//reset all used values
			n.setUsed(false);
		}
		return temp;
	}
	
	public Graph dfs(Graph g, Node<?> head, Work work) {
		Graph temp = new DirectedGraph();
		Deque<Node<?>> st = new ArrayDeque<Node<?>>();
		Deque<Node<?>> workSt = new ArrayDeque<Node<?>>();
		Node<?> current;
		Node<?> currentCopy;
		st.push(head);
		workSt.push(head.copy());
		do{
			current=st.pop();
			currentCopy = work.doWork(workSt.pop());
			
			for(Node<?> n : g.neighbors(current)){
				if(!n.isUsed()){
					workSt.push(n.copy());
					temp.addEdge(currentCopy, workSt.getFirst());
					st.push(n);
					current.setUsed(true);
				}
			}
		}while(!st.isEmpty());
		
		for(Node<?> n : g.getNodes()){
			n.setUsed(false);
		}
		return temp;
	}


	public Graph bfs(Graph g, Node<?> head) {
		Graph temp = new DirectedGraph();
		Deque<Node<?>> st = new ArrayDeque<Node<?>>();
		Node<?> current;
		st.push(head);
		do{
			current = st.removeLast();
			for(Node<?> n : g.neighbors(current)){
				if(!n.isUsed()){
					temp.addEdge(current.copy(), n.copy());
					st.push(n);
					current.setUsed(true);
				}
			}
		}while(!st.isEmpty());
		for(Node<?> n : g.getNodes()){
			n.setUsed(false);
		}
		return temp;
	}
	
	public Graph bfs(Graph g, Node<?> head, Work work) {
		Graph temp = new DirectedGraph();
		Deque<Node<?>> st = new ArrayDeque<Node<?>>();
		Deque<Node<?>> workSt = new ArrayDeque<Node<?>>();
		Node<?> current;
		Node<?> currentCopy;
		st.push(head);
		workSt.push(head.copy());
		do{
			current=st.removeLast();
			currentCopy = work.doWork(workSt.removeLast());
			
			for(Node<?> n : g.neighbors(current)){
				if(!n.isUsed()){
					workSt.push(n.copy());
					temp.addEdge(currentCopy, workSt.getFirst());
					st.push(n);
					current.setUsed(true);
				}
			}
		}while(!st.isEmpty());
		
		for(Node<?> n : g.getNodes()){
			n.setUsed(false);
		}
		return temp;
	}

	public Vector<Node<?>> getNodes() {
		return new Vector<Node<?>>(graph.keySet());
	};
	
	public String toString(){
		String temp="";
		for(Node<?> n : graph.keySet()){
			temp+=n.toString()+"\r";
		}
		return temp;
	}

}
