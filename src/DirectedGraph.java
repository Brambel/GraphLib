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
	
	private class noWork implements Work{

		public Node<?> doWork(Node<?> n) {
			// empty to be used for default
			return n;
		}
		
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
			to.addEdge();
		}

	}


	public void removeEdge(Node<?> from, Node<?> to) {
		if(graph.containsKey(from)){
			if(graph.get(from).contains(to)){
				graph.get(from).remove(to);
				to.decEdge();
				if(to.getEdges()==0){
					graph.remove(to);
				}
			}
		}

	}


	public Graph dfs(Graph g, Node<?> head) {
		Graph temp = new DirectedGraph();
		Deque<Node<?>> st = new ArrayDeque<Node<?>>();
		Node<?> current = head;
		do{
			st.push(current);
			for(Node<?> n : g.neighbors(current)){
				temp.addEdge(current.copy(), n.copy());
				st.push(n);
				current.setUsed(true);
				current=st.getFirst();
				if(current.isUsed()){
					//add operations on nodes here
					st.pop();
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
		Node<?> current = head;
		do{
			st.push(current);
			for(Node<?> n : g.neighbors(current)){
				temp.addEdge(current.copy(), n.copy());
				st.push(n);
				current.setUsed(true);
				current=st.getLast();
				if(current.isUsed()){
					//add operations on nodes here
					st.removeLast();
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
