package Custom;

public class Tuple<X, Y> {

	public X first;
	public Y second;
	
	public Tuple(X first, Y second) {
		this.first = first;
		this.second = second;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
        if (o == this) { 
            return true; 
        } 
        if (!(o instanceof Tuple)) { 
            return false; 
        } 
          
        Tuple<X, Y> compare = (Tuple<X, Y>) o; 
          
        return compare.first != null && compare.first == this.first &&
        		compare.second != null && compare.second == this.second;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.first.hashCode();
		result = 31 * result + this.second.hashCode();
		
		return result;
	}
	
}
