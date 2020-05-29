package edu.brown.cs.student.repl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that defines an extensible REPL.
 */
public class REPL {
  private Map<String, Command> commands;

  /**
   * Constructor for REPL.
   */
  public REPL() {
    commands = new HashMap<String, Command>();
  }

  /**
   * This method allows the repl to register commands.
   * @param str  a string that maps to an actual command
   * @param cmd  a Command object
   */
  public void register(String str, Command cmd) {
    commands.put(str, cmd);
  }

  /**
   *  Creates a command line interface for the user to interact with.
   */
  public void runREPL() {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String input;

      while ((input = br.readLine()) != null) {
        if (!input.equals("")) {
          List<String> tokenizedInput = tokenizeInput(input);
          List<String> output = runCommand(tokenizedInput);
          processREPL(output);
        }
      }
      br.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Method that tokenizes input based on desired regex.
   * @param input user input that was read from the command line
   * @return a tokenized list corresponding to user input
   */
  public List<String> tokenizeInput(String input) {
    List<String> tokenized = new ArrayList<String>();
    Matcher regexMatches = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(input);
    while (regexMatches.find()) {
      tokenized.add(regexMatches.group(1));
    }
    return tokenized;
  }

  /**
   * Runs a command if the user string exists in the commands hashmap.
   * @param tokenized a tokenized list of user commands
   * @return List of Strings representing the result of executing the command
   */
  public List<String> runCommand(List<String> tokenized) {
    List<String> results = new ArrayList<>();
    if (commands.containsKey(tokenized.get(0))) {
      results = commands.get(tokenized.get(0)).executeCommand(tokenized);
    } else {
      System.err.println("ERROR: That command does not exist.");
    }
    return results;
  }

  /**
   * Processes results from Commands for the REPL side.
   * @param results the results from running executeCommand()
   */
  public void processREPL(List<String> results) {
    for (String row: results) {
      if (row.startsWith("ERROR")) {
        System.err.println(row);
      } else {
        // print results from command
        String[] splitResults = row.split(",");
        System.out.println(splitResults[0]);
      }
    }
  }
}
