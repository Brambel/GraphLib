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

	public Vector<Node<?>> getNodes() {
		return new Vector<Node<?>>(graph.keySet());
	};
	
	public String toString(){
		String temp="";
		for(Node<?> n : graph.keySet()){
			temp+=n.getValue()+" -> "+this.neighbors(n).toString()+"\n";
		}
		return temp;
	}


	@Override
	public Node<?> findMatch(Node<?> n) {
		for(Node<?> x : graph.keySet()){
			if(x.equal(n)){
				return x;
			}
		}
		return null;
	}

}
