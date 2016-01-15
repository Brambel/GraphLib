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
		
		Vector<Node<?>> nodes2 = new Vector<>();
		Node<Integer> nexus = new Node<>(10);
		nodes2.add(nexus);
		Graph graph2 = new UndirectedGraph();
		for(int i=0;i<10;++i){
			nodes2.add(new Node<Integer>(i));
			graph2.addEdge(nodes2.get(nodes2.size()-1),nodes2.get(0));
		}
		
		System.out.println(graph2.toString());
		System.out.println("nexus has : "+graph2.neighbors(nexus).size()+" connections");
		//test traversing and printing graph
	}

}
