
public class Node<Type> {

	private Type value;
	private Boolean used;
	
	public Node(Type value){
		this.value = value;
		used = false;
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

	public Node<Type> copy(){
		Node<Type> temp = new Node<>(value);
		temp.setUsed(used);
		return temp;
	}
	public String toString(){
		return "Value: "+value+", Used: "+used;
	}
	
	public boolean equal(Node<?> n){
		return n.getValue().equals(value);
	}
	
	
}
