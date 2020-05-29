package edu.brown.cs.student.repl;

import java.util.List;

/**
 * Interface that defines common properties for Commands.
 */
public interface Command {
  /**
   * Method to execute a given command.
   * @param receivedInput the parsed user input from the command line
   * @return a list of strings representing the result of executing the command
   */
  List<String> executeCommand(List<String> receivedInput);

  /**
   * Method that checks whether a given command is valid.
   * @param receivedInput the parsed user input from the command line
   * @return true if valid, false if not valid
   */
  boolean isCommandValid(List<String> receivedInput);
}
