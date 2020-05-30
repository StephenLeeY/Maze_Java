package Maze;

public class Wall {

	public Cell parent_one;
	public Cell parent_two;
	public boolean open;
	
	public Wall(Cell parent_one, Cell parent_two) {
		this.parent_one = parent_one;
		this.parent_two = parent_two;
		this.open = false;
	}
}
