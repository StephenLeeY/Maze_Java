package maze;

public class Wall {

	public Cell parent_one;
	public Cell parent_two;
	public boolean open;
	
	public Wall(Cell parent_one, Cell parent_two) {
		this.parent_one = parent_one;
		this.parent_two = parent_two;
		this.open = false;
	}
	
	@Override
	public boolean equals(Object o) {
        if (o == this) { 
            return true; 
        } 
        if (!(o instanceof Wall)) { 
            return false; 
        } 
          
        Wall compare = (Wall) o; 
          
        return compare.parent_one != null && 
        		compare.parent_two != null &&
        		compare.parent_one != compare.parent_two &&
        		(compare.parent_one == this.parent_one || compare.parent_one == this.parent_two) &&
        		(compare.parent_two == this.parent_one || compare.parent_two == this.parent_two);
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.parent_one.hashCode();
		result = 31 * result + this.parent_two.hashCode();
		
		return result;
	}
}
