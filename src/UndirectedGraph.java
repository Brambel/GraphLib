
import java.util.List;
import java.util.Vector;

public class UndirectedGraph implements Graph {

	List<List<data>> graph = new Vector<>();
	List<Node<?>> nodes = new Vector<>();
	
	//private inner class to hold the an int since Integer is bullshit
	private class data{
		public int val;
		data(int x){
			val=x;
		}
	}

	public boolean adjacent(Node<?> from, Node<?> to) {
		if(nodes.contains(from)&&nodes.contains(to)){
			if(graph.get(nodes.indexOf(from)).get(nodes.indexOf(to)).val==1){
				return true;
			}
		}
		return false;
	}

	@Override
	public Vector<Node<?>> neighbors(Node<?> n) {
		// TODO Auto-generated method stub
		return null;
	}


	public void addEdge(Node<?> from, Node<?> to) {
		if(!nodes.contains(from)){
			nodes.add(from);
			this.expand();
		}
		if(!nodes.contains(to)){
			nodes.add(to);
			this.expand();
		}
		int a = nodes.indexOf(to);
		int b = nodes.indexOf(from);
		
		
		if(graph.get(a).get(b)==null ||graph.get(a).get(b).val==0){
			to.addEdge();
			from.addEdge();
		}
		graph.get(a).get(b).val=1;
		graph.get(b).get(a).val=1;
	}

	private void expand() {
		int size = nodes.size()-1;//size of graph edges before node was added
		List<data> temp = new Vector<>();
		for(int i=0;i<size;++i){
			temp.add(new data(0));
		}
		graph.add(temp); //this tacks the new vector to the end already full of zeros
		for(List<data> n : graph){
			n.add(new data(0));//add a zero to the end of each list in the graph
		}
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
