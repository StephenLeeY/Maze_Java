package maze;

import java.util.ArrayList;
import java.util.List;

import custom.Tuple;

public class Cell {
	
	public List<Wall> walls;
	public Tuple<Integer, Integer> location;
	public boolean visited;

	public Cell(Tuple<Integer, Integer> location) {
		this.walls = new ArrayList<>();
		this.location = location;
		this.visited = false;
	}
	
	@Override
	public boolean equals(Object o) {
        if (o == this) { 
            return true; 
        } 
        if (!(o instanceof Cell)) { 
            return false; 
        } 
          
        Cell compare = (Cell) o; 
          
        return compare.location != null && compare.location == this.location &&
        		compare.walls != null && compare.walls == this.walls;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.location.hashCode();
		result = 31 * result + this.walls.hashCode();
		
		return result;
	}
}
