package Maze;

import java.util.ArrayList;
import java.util.List;

import Custom.Tuple;

public class Cell {
	
	public List<Wall> walls;
	public Tuple<Integer, Integer> location;
	public boolean visited;

	public Cell(Tuple<Integer, Integer> location) {
		this.walls = new ArrayList<>();
		this.location = location;
		this.visited = false;
	}
}
