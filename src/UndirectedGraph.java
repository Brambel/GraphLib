
import java.util.List;
import java.util.Vector;

public class UndirectedGraph extends Graph {

	List<List<Integer>> graph = new Vector<>();
	List<Node<?>> nodes = new Vector<>();
	
	@Override
	public boolean adjacent(Node<?> from, Node<?> to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vector<Node<?>> neighbors(Node<?> n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEdge(Node<?> from, Node<?> to) {
		// TODO Auto-generated method stub
	}

	@Override
	public void removeEdge(Node<?> from, Node<?> to) {
		// TODO Auto-generated method stub

	}

	@Override
	public Graph dfs(Graph g, Node<?> head) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph bfs(Graph g, Node<?> head) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Node<?>> getNodes() {
		// TODO Auto-generated method stub
		return null;
	}

}
