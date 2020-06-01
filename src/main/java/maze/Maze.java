package maze;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import custom.Tuple;
import edu.brown.cs.student.repl.Command;
import edu.brown.cs.student.repl.REPL;

public class Maze {

	public Maze() { }
	
	/**
	 * Method to install Maze's commands into the REPL.
	 * @param repl the REPL of the program
	 */
	public void installCommands(REPL repl) {
	  repl.register("create", new CreateCommand());
	  repl.register("path", new PathCommand());
	}
	
	/**
	 * Private class defining the "create" command.
	 */
	private class CreateCommand implements Command {
		
		/**
		 * Helper function: Initializes maze with all possible walls
		 */
		private Cell[][] initialMaze(Cell[][] cellList) {
			int height = cellList.length;
			int width = cellList[0].length;
			
			// Create maze full of walls
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					cellList[i][j] = new Cell(new Tuple<Integer, Integer>(i, j));
				}
			}
  
			// Deal with corners
			cellList[0][0].walls.add(new Wall(cellList[0][0], cellList[0][1]));
			cellList[0][0].walls.add(new Wall(cellList[0][0], cellList[1][0]));
			cellList[height - 1][0].walls.add(new Wall(cellList[height - 1][0], cellList[height - 1][1]));
			cellList[height - 1][0].walls.add(new Wall(cellList[height - 1][0], cellList[height - 2][0]));
			cellList[0][width - 1].walls.add(new Wall(cellList[0][width - 1], cellList[0][width - 2]));
			cellList[0][width - 1].walls.add(new Wall(cellList[0][width - 1], cellList[1][width - 1]));
			cellList[height - 1][width - 1].walls.add(new Wall(cellList[height - 1][width - 1], cellList[height - 1][width - 2]));
			cellList[height - 1][width - 1].walls.add(new Wall(cellList[height - 1][width - 1], cellList[height - 2][width - 1]));
  
			// Deal with edges
			for(int i = 1; i < height - 1; i++) {
				cellList[i][0].walls.add(new Wall(cellList[i][0], cellList[i - 1][0]));
				cellList[i][0].walls.add(new Wall(cellList[i][0], cellList[i][1]));
				cellList[i][0].walls.add(new Wall(cellList[i][0], cellList[i + 1][0]));
	  
				cellList[i][width - 1].walls.add(new Wall(cellList[i][width - 1], cellList[i - 1][width - 1]));
				cellList[i][width - 1].walls.add(new Wall(cellList[i][width - 1], cellList[i][width - 2]));
				cellList[i][width - 1].walls.add(new Wall(cellList[i][width - 1], cellList[i + 1][width - 1]));
			}
			for(int i = 1; i < width - 1; i++) {
				cellList[0][i].walls.add(new Wall(cellList[0][i], cellList[0][i - 1]));
				cellList[0][i].walls.add(new Wall(cellList[0][i], cellList[1][i]));
				cellList[0][i].walls.add(new Wall(cellList[0][i], cellList[0][i + 1]));
	  
				cellList[height - 1][i].walls.add(new Wall(cellList[height - 1][i], cellList[height - 1][i - 1]));
				cellList[height - 1][i].walls.add(new Wall(cellList[height - 1][i], cellList[height - 2][i]));
				cellList[height - 1][i].walls.add(new Wall(cellList[height - 1][i], cellList[height - 1][i + 1]));
			}
  
			// Deal with center
			for(int i = 1; i < height - 1; i++) {
				for(int j = 1; j < width - 1; j++) {
					cellList[i][j].walls.add(new Wall(cellList[i][j], cellList[i - 1][j]));
					cellList[i][j].walls.add(new Wall(cellList[i][j], cellList[i + 1][j]));
					cellList[i][j].walls.add(new Wall(cellList[i][j], cellList[i][j - 1]));
					cellList[i][j].walls.add(new Wall(cellList[i][j], cellList[i][j + 1]));
				}
			}
			return cellList;
		}
		
		private void printMaze(Cell[][] cellList) {
			int height = cellList.length;
			int width = cellList[0].length;
			
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					Cell currCell = cellList[i][j];
					String s = "";
					for(Wall w : currCell.walls) {
						if(!w.open) {
							s += "(" + w.parent_one.location.first + ", " + w.parent_one.location.second + ") <-\\-> ("
									+ w.parent_two.location.first + ", " + w.parent_two.location.second + "), ";
						} else {
							s += "(" + w.parent_one.location.first + ", " + w.parent_one.location.second + ") <-> ("
									+ w.parent_two.location.first + ", " + w.parent_two.location.second + "), ";
						}
					}
					if(s.length() != 0) {
						s = s.substring(0, s.length() - 2);
					}
					System.out.println("Cell (" + i + ", " + j + "): [" + s + "]");
				}
			}
		}
		
		/**
		 * Execute the create command.
		 * @param receivedInput the parsed user input from the command line
		 * @return a list of strings representing result of Command
		 */
		@Override
		public List<String> executeCommand(List<String> receivedInput) {
		  List<String> returnPrintStatement = new ArrayList<>();
		  
		  if(!this.isCommandValid(receivedInput)) {
			  returnPrintStatement.add("Invalid format for command: create");
			  return returnPrintStatement;
		  }
		  
		  try {
			  // Prim's algorithm
			  int height = Integer.parseInt(receivedInput.get(1));
			  int width = Integer.parseInt(receivedInput.get(2));
			  
			  Cell[][] cellList = new Cell[height][width];
			  cellList = initialMaze(cellList);
			  
			  // Pick a random cell, add to maze
			  Cell[][] maze = new Cell[height][width];
			  Random random = new Random();
			  Cell randomCell = cellList[random.nextInt(height)][random.nextInt(width)];
			  maze[randomCell.location.first][randomCell.location.second] = randomCell;
			  randomCell.visited = true;
			  
			  // Add walls to queue. 
			  PriorityQueue<Wall> wallList = new PriorityQueue<>(2, new WallComparator());
			  // double wallWeight = 1;
			  for(Wall w : randomCell.walls) w.weight = random.nextInt(10); //wallWeight;
			  wallList.addAll(randomCell.walls);
			  
			  // While there are walls in the list
			  while(!wallList.isEmpty()) {
				  // Select a random wall from wall list
				  Wall randomWall = wallList.poll();
				  
				  // If only one of the wall's parent cells have been visited
				  if(!randomWall.parent_two.visited) {
					  Cell neighborCell = randomWall.parent_two;
					  // Mark wall as passage
					  randomWall.open = true;
					  neighborCell.walls.get(neighborCell.walls.indexOf(randomWall)).open = true;
					  neighborCell.visited = true;
					  // Add neighbor cell to maze
					  maze[neighborCell.location.first][neighborCell.location.second] = neighborCell;
					  // Adjust weight
					  // wallWeight *= -1.01; --> Possible future maze patterns?
					  for(Wall w : neighborCell.walls) w.weight = random.nextInt(10); // wallWeight;
					  // Add neighbor cell's walls to queue
					  wallList.addAll(neighborCell.walls);
				  } else {
					  wallList.remove(randomWall);
				  }
			  }
			  
			  returnPrintStatement.add("Maze successfully generated!");
			  
			  // Draw maze
			  MazeUI displayClass = new MazeUI(cellList);
			  displayClass.run();
			  
			  //printMaze(cellList);
			  
			  return returnPrintStatement;
		  } catch (NumberFormatException e) {
			  returnPrintStatement.add("Create command must take in integers for maze size parameters");
			  return returnPrintStatement;
		  }
		}		  
		
		/**
		 * Checks that the create Command was given in the correct format.
		 * @param receivedInput tokenized list of user's command
		 * @return boolean representing if the command is of the correct format
		 */
	    @Override
	    public boolean isCommandValid(List<String> receivedInput) {
	    	try {
	    		return receivedInput.size() == 3 &&
	    				Integer.parseInt(receivedInput.get(1)) > 1 &&
	    				Integer.parseInt(receivedInput.get(2)) > 1;
	    	} catch (NumberFormatException e) {
	    		return false;
	    	}
	    }
	}
	
	/**
	 * Private class defining the "path" command.
	 */
	private class PathCommand implements Command {
	
		/**
		 * Execute the path command.
		 * @param receivedInput the parsed user input from the command line
		 * @return a list of strings representing result of Command
		 */
		@Override
		public List<String> executeCommand(List<String> receivedInput) {
		  List<String> returnPrintStatement = new ArrayList<>();
		  
		  return returnPrintStatement;
		}
		
		/**
		 * Checks that the path Command was given in the correct format.
		 * @param receivedInput tokenized list of user's command
		 * @return boolean representing if the command is of the correct format
		 */
	    @Override
	    public boolean isCommandValid(List<String> receivedInput) {
	      return receivedInput.size() == 1;
	    }
	}
}
