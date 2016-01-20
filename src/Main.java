import java.util.Vector;

public class Main {

	public static void main(String[] args) {
		
		//setup
		Vector<Node<?>> nodes = new Vector<>();
		nodes.addElement(new Node<Character>('A')); //0
		nodes.addElement(new Node<Character>('B')); //1
		nodes.addElement(new Node<Character>('C')); //2
		nodes.addElement(new Node<Character>('D')); //3
		nodes.addElement(new Node<Character>('E')); //4
		nodes.addElement(new Node<Character>('F')); //5
		nodes.addElement(new Node<Character>('G')); //6
		
		Graph graph = new DirectedGraph(); //since answer must be directed
		
		//A's connections
		graph.addEdge(nodes.get(0), nodes.get(1));
		graph.addEdge(nodes.get(0), nodes.get(2));
		graph.addEdge(nodes.get(0), nodes.get(3));
		graph.addEdge(nodes.get(0), nodes.get(4));
		graph.addEdge(nodes.get(0), nodes.get(5));
		
		//B's connections
		graph.addEdge(nodes.get(1), nodes.get(0));
		graph.addEdge(nodes.get(1), nodes.get(2));
		graph.addEdge(nodes.get(1), nodes.get(3));
		graph.addEdge(nodes.get(1), nodes.get(4));
		graph.addEdge(nodes.get(1), nodes.get(5));
		
		//C's connections
		graph.addEdge(nodes.get(2), nodes.get(0));
		graph.addEdge(nodes.get(2), nodes.get(1));
		graph.addEdge(nodes.get(2), nodes.get(5));
		graph.addEdge(nodes.get(2), nodes.get(6));
		
		//D's connections
		graph.addEdge(nodes.get(3), nodes.get(0));
		graph.addEdge(nodes.get(3), nodes.get(1));
		graph.addEdge(nodes.get(3), nodes.get(4));
		graph.addEdge(nodes.get(3), nodes.get(5));
		graph.addEdge(nodes.get(3), nodes.get(6));
		
		//E's connections
		graph.addEdge(nodes.get(4), nodes.get(0));
		graph.addEdge(nodes.get(4), nodes.get(1));
		graph.addEdge(nodes.get(4), nodes.get(3));
		graph.addEdge(nodes.get(4), nodes.get(5));
		graph.addEdge(nodes.get(4), nodes.get(5));
		graph.addEdge(nodes.get(4), nodes.get(6));
		
		//F's connections
		graph.addEdge(nodes.get(5), nodes.get(0));
		graph.addEdge(nodes.get(5), nodes.get(1));
		graph.addEdge(nodes.get(5), nodes.get(2));
		graph.addEdge(nodes.get(5), nodes.get(3));
		graph.addEdge(nodes.get(5), nodes.get(4));
		graph.addEdge(nodes.get(5), nodes.get(6));
		
		//G's connections
		graph.addEdge(nodes.get(6), nodes.get(2));
		graph.addEdge(nodes.get(6), nodes.get(3));
		graph.addEdge(nodes.get(6), nodes.get(4));
		graph.addEdge(nodes.get(6), nodes.get(5));

		System.out.println("Our graph is built :\n"+graph.toString());
		
		//work starts here
		for(Node<?> n : nodes){
			System.out.println("DFS with start node "+n.getValue());
			System.out.println(graph.dfs(graph, n));
			System.out.println("BFS with start node "+n.getValue());
			System.out.println(graph.bfs(graph, n));
		}
		
		System.out.println("A DFS or BFS search provids no solution\n"
				+ "This graph can have no solution because it has 5 nodes with an odd number of edges.");
	}

}
