package br.eti.hussamaismail.calculexpr.evaluation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import br.eti.hussamaismail.calculexpr.domain.Bracket;
import br.eti.hussamaismail.calculexpr.domain.Number;
import br.eti.hussamaismail.calculexpr.domain.Operator;
import br.eti.hussamaismail.calculexpr.domain.Symbol;
import br.eti.hussamaismail.calculexpr.domain.SymbolFactory;
import br.eti.hussamaismail.calculexpr.domain.enums.BracketType;
import br.eti.hussamaismail.calculexpr.parse.BasicLexicalAnalyzer;
import br.eti.hussamaismail.calculexpr.parse.LexicalAnalyzer;

/**
 * This class implements the expression evaluator used in this project. In particular, it is uses an
 * algorithm called Shunting Yard, which uses the Reverse Polish Notation.
 * 
 * @author Hussama Ismail
 */
public class ShuntingYardExpressionEvaluator implements ExpressionEvaluator {

  private static ShuntingYardExpressionEvaluator instance;

  private SymbolFactory symbolFactory;
  private LexicalAnalyzer lexicalAnalyzer;
  private Stack<Symbol> operatorStack;

  private Map<String, Double> bindings;

  private ShuntingYardExpressionEvaluator() {
    this.bindings = new HashMap<>();
    this.operatorStack = new Stack<>();
    this.symbolFactory = SymbolFactory.getInstance();
    this.lexicalAnalyzer = BasicLexicalAnalyzer.getInstance();
  }

  /**
   * Return an instance of ExpressionEvaluator.
   * 
   * @return
   */
  public static ShuntingYardExpressionEvaluator getInstance() {
    if (instance == null) {
      instance = new ShuntingYardExpressionEvaluator();
    }
    return instance;
  }

  /**
   * Generate a list of Symbols based on a list of tokens.
   * 
   * @param tokens
   * @return
   */
  private List<Symbol> generateListOfSymbols(final List<String> tokens) {
    final List<Symbol> symbols = new LinkedList<>();
    for (final String token : tokens) {
      final Symbol symbol = symbolFactory.createSymbol(token);
      symbols.add(symbol);
    }
    return symbols;
  }

  /**
   * Check if the top of the stack is a left bracket.
   * 
   * @return
   */
  private boolean isSymbolTopStackALeftBracket() {
    final Symbol topStack = operatorStack.peek();
    return (topStack instanceof Bracket)
        && ((Bracket) topStack).getType().equals(BracketType.PARENTHESES_START);
  }

  /**
   * This method sort the symbols list in the reverse polish notation, which is required by Shunting
   * yard algorithm.
   * 
   * For instance, '3', '+', '2' is rewrite as '3','2','+'.
   * 
   * @param symbols
   * @return
   */
  private List<Symbol> sortSymbolsInReversePolishNotation(final List<Symbol> symbols) {
    final List<Symbol> sortedSymbols = new LinkedList<>();
    for (final Symbol symbol : symbols) {
      if (symbol instanceof Number) {
        sortedSymbols.add(symbol);
      } else if (symbol instanceof Operator) {
        if (operatorStack.isEmpty()) {
          operatorStack.push(symbol);
        } else {
          final Operator currentOperator = (Operator) symbol;
          Symbol topStack = operatorStack.peek();
          while ((topStack instanceof Operator) && ((Operator) topStack).getType()
              .getPrecedenceLevel() > currentOperator.getType().getPrecedenceLevel()) {
            sortedSymbols.add(operatorStack.pop());
            topStack = operatorStack.peek();
          }
          operatorStack.push(currentOperator);
        }
      } else if (symbol instanceof Bracket) {
        final Bracket bracket = (Bracket) symbol;
        if (bracket.getType() == BracketType.PARENTHESES_START) {
          operatorStack.push(bracket);
        } else {
          while (!operatorStack.isEmpty() && !isSymbolTopStackALeftBracket()) {
            sortedSymbols.add(operatorStack.pop());
          }
          operatorStack.pop();
        }
      }
    }

    while (!operatorStack.isEmpty()) {
      sortedSymbols.add(operatorStack.pop());
    }

    return sortedSymbols;
  }

  /**
   * Executes the correspondent arithmetic operation.
   * 
   * @param operator
   * @return
   */
  private double performArithmeticOperation(final Operator operator) {
    final Number rightHandSide = (Number) operatorStack.pop();
    final Number leftHandSide = (Number) operatorStack.pop();
    double result = 0;
    switch (operator.getType()) {
      case ADDITION:
        result = leftHandSide.getValue() + rightHandSide.getValue();
        break;
      case DIVISION:
        result = leftHandSide.getValue() / rightHandSide.getValue();
        break;
      case MULTIPLICATION:
        result = leftHandSide.getValue() * rightHandSide.getValue();
        break;
      case SUBTRACTION:
        result = leftHandSide.getValue() - rightHandSide.getValue();
        break;
      default:
        break;
    }
    return result;
  }

  /** {@inheritDoc} */
  public double eval(final String expression) {
    final List<String> tokens = lexicalAnalyzer.getTokens(expression);
    final List<Symbol> symbols = generateListOfSymbols(tokens);
    final List<Symbol> sortedSymbols = sortSymbolsInReversePolishNotation(symbols);

    for (final Symbol symbol : sortedSymbols) {
      if (symbol instanceof Number) {
        operatorStack.push(symbol);
      } else if (symbol instanceof Operator) {
        double result = performArithmeticOperation((Operator) symbol);
        operatorStack.push(new Number(result));
      }
    }
    return ((Number) operatorStack.pop()).getValue();
  }

  /** {@inheritDoc} */
  @Override
  public void showAllBindings() {
    bindings.forEach((name, value) -> System.out.println(name + " = " + value));
  }

  /** {@inheritDoc} */
  @Override
  public void removeAllBindings() {
    bindings.clear();
  }

  /** {@inheritDoc} */
  @Override
  public void removeBindings(final String[] names) {
    bindings.keySet().removeAll(Arrays.asList(names));
  }

}
