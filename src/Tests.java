import static org.junit.Assert.*;
import java.util.Vector;
import org.junit.Test;

public class Tests {

	private class work implements Work{

		@Override
		public Node<?> doWork(Node<?> n) {
			System.out.println(n.getValue());
			return n;
		}
		
	}
	
	@Test
	public void directedTest() {
		
		Vector<Node<?>> nodes = new Vector<>();
		nodes.add(new Node<Integer>(10));
		Graph graph = new DirectedGraph();
		for(int i=0;i<10;++i){
			nodes.add(new Node<Integer>(i));
			graph.addEdge(nodes.get(0),nodes.get(nodes.size()-1));
		}
		assertEquals("node 0 neighbors: ",10,graph.neighbors(nodes.get(0)).size());
		assertFalse("node 0 used: ", nodes.get(0).isUsed());
		
		for(int i=1;i<11;++i){
			assertEquals("node "+i+" neighbors: ",0, graph.neighbors(nodes.get(i)).size());
			assertFalse("nodes "+i+" used: ", nodes.get(i).isUsed());
		}
		
		assertTrue("graph.adjacent(): ",graph.adjacent(nodes.get(0),nodes.get(1)));
		assertFalse("graph.adjacent(): ", graph.adjacent(nodes.get(1), nodes.get(0)));
		assertEquals("graph.getNodes(): ",nodes.size(),graph.getNodes().size());
		
		graph.removeEdge(nodes.get(0),nodes.get(1));
		assertEquals("removed edge 10->1: ", 9,graph.neighbors(nodes.get(0)).size());
		graph.addEdge(nodes.get(0), nodes.get(1));
		
		Graph dfs = graph.dfs(graph, nodes.get(0));
		Graph bfs = graph.bfs(graph, nodes.get(0));
		
		assertEquals("dfs subgraph nodes (head = 0): ",11,dfs.getNodes().size());
		assertEquals("bfs subgraph nodes (head = 0): ",11,bfs.getNodes().size());
		
		dfs = graph.dfs(graph, nodes.get(1)); 
		bfs = graph.bfs(graph, nodes.get(1));
		
		assertEquals("dfs subgraph nodes (head = 1): ",0,dfs.getNodes().size());
		assertEquals("bfs subgraph nodes (head = 1): ",0,bfs.getNodes().size());
		
		System.out.println("\ndirected");
		System.out.println("printing dfs nodes");
		graph.dfs(graph, nodes.get(1), new work());
		System.out.println("printing bfs nodes");
		graph.bfs(graph, nodes.get(0), new work());
		
	}

	@Test
	public void undirectedTest(){
		Vector<Node<?>> nodes = new Vector<>();
		nodes.add(new Node<Integer>(10));
		Graph graph = new UndirectedGraph();
		for(int i=0;i<10;++i){
			nodes.add(new Node<Integer>(i));
			graph.addEdge(nodes.get(0),nodes.get(nodes.size()-1));
		}
		assertEquals("node 0 neighbors: ",10,graph.neighbors(nodes.get(0)).size());
		assertFalse("node 0 used: ", nodes.get(0).isUsed());
		
		for(int i=1;i<11;++i){
			assertEquals("node "+i+" neighbors: ",1, graph.neighbors(nodes.get(i)).size());
			assertFalse("nodes "+i+" used: ", nodes.get(i).isUsed());
		}
		
		assertTrue("graph.adjacent(): ",graph.adjacent(nodes.get(0),nodes.get(1)));
		assertTrue("graph.adjacent(): ", graph.adjacent(nodes.get(1), nodes.get(0)));
		assertEquals("graph.getNodes(): ",nodes.size(),graph.getNodes().size());
		
		graph.removeEdge(nodes.get(0),nodes.get(1));
		assertEquals("removed edge 10->1: ", 9,graph.neighbors(nodes.get(0)).size());
		graph.addEdge(nodes.get(0), nodes.get(1));
		
		Graph dfs = graph.dfs(graph, nodes.get(0));
		Graph bfs = graph.bfs(graph, nodes.get(0));
		
		assertEquals("dfs subgraph nodes (head = 0): ",11,dfs.getNodes().size());
		assertEquals("bfs subgraph nodes (head = 0): ",11,bfs.getNodes().size());
		
		dfs = graph.dfs(graph, nodes.get(1));
		bfs = graph.bfs(graph, nodes.get(1));
		
		assertEquals("dfs subgraph nodes (head = 1): ",11,dfs.getNodes().size());
		assertEquals("bfs subgraph nodes (head = 1): ",11,bfs.getNodes().size());
		
		System.out.println("\nundirected");
		System.out.println("printing dfs nodes");
		graph.dfs(graph, nodes.get(1), new work());
		System.out.println("printing bfs nodes");
		graph.bfs(graph, nodes.get(0), new work());
	}

}
