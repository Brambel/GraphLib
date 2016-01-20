
public interface Work {

	public default Node<?> doWork(Node<?> n){
		return n;
	}

}
