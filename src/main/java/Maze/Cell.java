package Maze;

import Custom.Tuple;

public class Cell {
	
	private int[] edges;
	private Tuple<Integer, Integer> location;
	private boolean visited;

	public Cell(Tuple<Integer, Integer> location) {
		this.setEdges(new int[] {1, 1, 1, 1});
		this.setLocation(location);
		this.setVisited(false);
	}
	
	// --------------

	public int[] getEdges() {
		return edges;
	}

	public void setEdges(int[] edges) {
		this.edges = edges;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public Tuple<Integer, Integer> getLocation() {
		return location;
	}

	public void setLocation(Tuple<Integer, Integer> location) {
		this.location = location;
	}
	
}
