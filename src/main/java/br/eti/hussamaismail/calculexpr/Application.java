package br.eti.hussamaismail.calculexpr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import br.eti.hussamaismail.calculexpr.eval.ExpressionEvaluator;
import br.eti.hussamaismail.calculexpr.eval.ExpressionEvaluatorImpl;

/**
 * Main class for calculexpr project.
 * 
 * @author Hussama Ismail
 */
public final class Application {

  private static final ExpressionEvaluator evaluator = ExpressionEvaluatorImpl.getInstance();

  public static void main(final String[] args) {

    System.out.println("*********************************************");
    System.out.println("* Calculatrice d'expression            v0.1 *");
    System.out.println("* Hussama Ismail - hussama.ismail@gmail.com *");
    System.out.println("*********************************************");
    System.out.println("\nPlease type yours expressions:");

    try {
      final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      final PrintWriter out = new PrintWriter(System.out, true);
      while (true) {
        try {
          String line = in.readLine();
          if (line == null) {
            break;
          }
          line = line.trim();
          if (line.isEmpty()) {
            continue;
          }
          if (!line.startsWith(":")) {
            /* handle expression */
            out.println(evaluator.eval(line));
          } else {
            /* handle command */
            final String[] command = line.split("\\s+", 2);
            switch (command[0]) {
              case ":vars":
                evaluator.showAllBindings();
                break;
              case ":clear":
                if (command.length == 1) {
                  /* clear all */
                  evaluator.removeAllBindings();
                } else {
                  /* clear requested */
                  evaluator.removeBindings(command[1].split("\\s+"));
                }
                break;
              case ":exit":
              case ":quit":
                System.exit(0);
                break;
              default:
                throw new RuntimeException("unrecognized command " + line);
            }
          }
        } catch (final Exception ex) {
          System.err.println("[ERROR] " + ex.getMessage());
        }
      }
    } catch (final Throwable ex) {
      System.err.println("[FATAL ERROR] " + ex.getMessage());
    }
  }
}
