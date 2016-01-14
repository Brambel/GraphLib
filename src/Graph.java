import java.util.Vector;


public abstract class Graph {
	
	public abstract boolean adjacent(Node<?> from, Node<?> to);
	public abstract Vector<Node<?>> neighbors(Node<?> n);
	public abstract void addEdge(Node<?> from, Node<?> to);
	public abstract void removeEdge(Node<?> from, Node<?> to);
	public abstract Graph dfs(Graph g, Node<?> head);
	public abstract Graph bfs(Graph g, Node<?> head);
	public abstract Vector<Node<?>> getNodes();
}
