package Maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Custom.Tuple;
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
			for(int i = 2; i < height - 2; i++) {
				for(int j = 2; j < width - 2; j++) {
					cellList[i][j].walls.add(new Wall(cellList[i][j], cellList[i - 1][j]));
					cellList[i][j].walls.add(new Wall(cellList[i][j], cellList[i + 1][j]));
					cellList[i][j].walls.add(new Wall(cellList[i][j], cellList[i][j - 1]));
					cellList[i][j].walls.add(new Wall(cellList[i][j], cellList[i][j + 1]));
				}
			}
			return cellList;
		}
		
		private List<String> mazeToString(Cell[][] cellList) {
			List<String> mazeString = new ArrayList<>();
			int height = cellList.length;
			int width = cellList[0].length;
			
			
			
			return mazeString;
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
			  int width = Integer.parseInt(receivedInput.get(1));
			  int height = Integer.parseInt(receivedInput.get(2));
			  
			  Cell[][] cellList = new Cell[height][width];
			  cellList = initialMaze(cellList);
			  
			  // Pick random cell and add to maze, add walls to wall list.
			  Cell[][] maze = new Cell[height][width];
			  List<Wall> wallList = new ArrayList<>();
			  Random random = new Random();
			  
			  Cell randomCell = cellList[random.nextInt(height)][random.nextInt(width)];
			  maze[randomCell.location.first][randomCell.location.second] = randomCell;
			  randomCell.visited = true;
			  wallList.addAll(randomCell.walls);
			  
			  int counter = 0;
			  // While there are walls in the list
			  while(!wallList.isEmpty()) {
				  // Select a random wall from wall list
				  Wall randomWall = wallList.remove(random.nextInt(wallList.size()));
				  
				  // If only one of the wall's parent cells have been visited
				  if(!randomWall.parent_two.visited) {
					  // Mark as passage, add cell to maze, add cell's walls to wall list
					  Cell neighborCell = randomWall.parent_two;
					  randomWall.open = true;
					  maze[neighborCell.location.first][neighborCell.location.second] = neighborCell;
					  wallList.addAll(neighborCell.walls);
				  } else {
					  wallList.remove(randomWall);
				  }
				  
				  if(counter++ > 100000) {
					  returnPrintStatement.add("Infinite while loop!");
					  return returnPrintStatement;
				  }
			  }
			  
			  returnPrintStatement.add("Maze successfully generated!");
			  returnPrintStatement.addAll(mazeToString(cellList));
			  
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
	      return receivedInput.size() == 3;
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
