
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

	public Vector<Node<?>> neighbors(Node<?> n) {
		int index = nodes.indexOf(n);
		Vector<Node<?>> temp = new Vector<>();
		for(int i =0;i<nodes.size();++i){
			if(graph.get(index).get(i).val==1){
				temp.addElement(nodes.get(i));
			}
		}	
		return temp;
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
	
	public void removeEdge(Node<?> from, Node<?> to) {
		int a = nodes.indexOf(from);
		int b = nodes.indexOf(to);
		if(graph.get(a).get(b).val!=0){
			graph.get(a).get(b).val=0;
			graph.get(b).get(a).val=0;
			from.decEdge();
			to.decEdge();
		}
	}

	public Graph dfs(Graph g, Node<?> head) {
		// TODO Auto-generated method stub
		return null;
	}

	public Graph bfs(Graph g, Node<?> head) {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<Node<?>> getNodes() {
		Vector<Node<?>> temp = new Vector<>();
		for(Node<?> n : nodes){
			if(n.getEdges()>0){
				temp.add(n);
			}
		}
		
		return temp;
	}
	public String toString(){
		String temp="";
		for(Node<?> n : nodes){
			temp+=n.toString()+"\r";
		}
		return temp;
	}

}
