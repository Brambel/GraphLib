import java.util.Vector;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector<Node<?>> nodes = new Vector<>();
		nodes.add(new Node<Integer>(10));
		Graph graph = new DirectedGraph();
		for(int i=0;i<10;++i){
			nodes.add(new Node<Integer>(i));
			graph.addEdge(nodes.get(nodes.size()-1),nodes.get(0));
		}
		
		System.out.println(graph.toString());
		//test traversing and printing graph
	}

}
