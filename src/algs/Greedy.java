package algs;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import graph.*;

public class Greedy {
	private List<Edge> tour = new Vector<Edge>();
	private Map<Node<?>, Integer> used = new HashMap<>();
	
	private List<Edge> getEdges(Graph g){
		List<Edge> edges = new Vector<Edge>();
		List<Node<?>> nodes=g.getNodes();
		for(Node<?> from : nodes){
			List<Node<?>> temp = g.neighbors(from);
			for(Node<?> to : temp){
				edges.add(new Edge(from, to, g.getWeight(from, to)));
			}
		}
		//at this point we have duplicate edges
		for(int i=edges.size()-1;i>=0;--i){
			for(Edge n : edges){
				if(edges.get(i).getTo()==n.getFrom() && edges.get(i).getFrom() == n.getTo() && edges.indexOf(n) != i){
					edges.remove(i);//delete the copy at the end so we perserve the loop
					break;//there are only two copies of each in an undirected graph
				}
			}
		}
		edges.sort((a, b) -> a.getWeight()>b.getWeight() ? 1 : a.getWeight()<b.getWeight() ? -1: 0);
		return edges;
	}
	
	private Boolean loadMap(Node<?> a, Node<?> b){
		if(used.get(a)>1||used.get(b)>1){//make sure we don't already have 2 of either node
			return false;
		}else{
			if(used.containsKey(a)){//Increment the count or make the first entry for each node
				used.put(a,  used.get(a)+1);
			}else{
				used.put(a, 1);
			}
			if(used.containsKey(b)){
				used.put(a,  used.get(b)+1);
			}else{
				used.put(b,  1);
			}
		}
		return true;
	}
	
	private Boolean hasCycle(Edge e){//checks if adding the new edge would cause a cycle
		UndirectedGraph temp = new UndirectedGraph();
		temp.addEdge(e.getFrom(), e.getTo());
		for(Edge n : tour){
			temp.addEdge(n.getFrom(), n.getTo());
		}
		
		return temp.hasCycle(0);
	}
	
	public List<Edge> generate_tour (int n, Graph g){
		List<Edge> tour = new Vector<Edge>();
		List<Edge> edges = getEdges(g);
		
		
		do{
			Edge possible = edges.remove(0);
			if(loadMap(possible.getTo(), possible.getFrom())){//make sure the edge belongs in the map
				if(hasCycle(possible)){
					used.put(possible.getFrom(), used.get(possible.getFrom())-1);//need to decremnt the used map
					used.put(possible.getTo(), used.get(possible.getTo())-1);
				}else{ //add it to our list of tours
					tour.add(possible);
				}
				
			}
			
		}while(tour.size() < edges.size()-1);
		
		return tour;
	}
}
