package edu.brown.cs.student.main;

import edu.brown.cs.student.repl.REPL;
import joptsimple.OptionParser;
import joptsimple.OptionSet;


/**
 * The Main class of our project. This is where execution begins.
 *
 */
public final class Main {

  private static final int DEFAULT_PORT = 4567;

  /**
   * The initial method called when execution begins.
   *
   * @param args
   *          An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }

  private String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  private void run() {
    // Parse command line arguments
    OptionParser parser = new OptionParser();
    parser.accepts("gui");
    parser.accepts("port").withRequiredArg().ofType(Integer.class)
        .defaultsTo(DEFAULT_PORT);
    OptionSet options = parser.parse(args);

    REPL repl = new REPL(); // begin the REPL
    GUI gui = new GUI(repl);

    if (options.has("gui")) {
      gui.runSparkServer((int) options.valueOf("port"));
    }
    repl.runREPL();
  }
}
