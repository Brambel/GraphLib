
public class Node<Type> {

	private Type value;
	private Boolean used;
	private int edges;
	
	public Node(Type value){
		this.value = value;
		}
	
	public Type getValue() {
		return value;
	}
	public void setValue(Type value) {
		this.value = value;
	}
	public Boolean isUsed(){
		return used;
	}
	public void setUsed(Boolean used) {
		this.used = used;
	}
	public int getEdges() {
		return edges;
	}
	public void addEdge() {
		this.edges += 1;
	}
	public void decEdge(){
		this.edges -=1;
	}
	protected void setEdge(int i){
		edges=i;
	}
	public Node<Type> copy(){
		Node<Type> temp = new Node<>(value);
		temp.setUsed(used);
		temp.setEdge(edges);
		return temp;
	}
	
	
}
