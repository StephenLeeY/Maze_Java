package Maze;

import java.util.ArrayList;
import java.util.List;

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
		 * Execute the create command.
		 * @param receivedInput the parsed user input from the command line
		 * @return a list of strings representing result of Command
		 */
		@Override
		public List<String> executeCommand(List<String> receivedInput) {
		  List<String> returnPrintStatement = new ArrayList<>();
		  
		  try {
			  int width = Integer.parseInt(receivedInput.get(1));
			  int height = Integer.parseInt(receivedInput.get(2));
			  Cell[][] maze = new Cell[height][width];
			  
			  for(int i = 0; i < height; i++) {
				  for(int j = 0; j < width; j++) {
					  maze[i][j] = new Cell(new Tuple<Integer, Integer>(i, j));
				  }
			  }
			  
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
