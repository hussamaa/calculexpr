package br.eti.hussamaismail.calculexpr.evaluation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import br.eti.hussamaismail.calculexpr.domain.Bracket;
import br.eti.hussamaismail.calculexpr.domain.Function;
import br.eti.hussamaismail.calculexpr.domain.Identifier;
import br.eti.hussamaismail.calculexpr.domain.Operand;
import br.eti.hussamaismail.calculexpr.domain.Operator;
import br.eti.hussamaismail.calculexpr.domain.Symbol;
import br.eti.hussamaismail.calculexpr.domain.enums.BracketType;
import br.eti.hussamaismail.calculexpr.domain.enums.OperatorType;
import br.eti.hussamaismail.calculexpr.exception.InvalidExpressionException;
import br.eti.hussamaismail.calculexpr.parse.BasicLexicalAnalyzer;
import br.eti.hussamaismail.calculexpr.parse.LexicalAnalyzer;

/**
 * This class implements the expression evaluator used in this project. In particular, it is uses an
 * algorithm called Shunting Yard, which uses the Reverse Polish Notation.
 * 
 * An explanation and the pseudo-code can be found at
 * <a href="https://en.wikipedia.org/wiki/Shunting-yard_algorithm">Wikipedia</a>
 * 
 * @author Hussama Ismail
 */
public class ShuntingYardExpressionEvaluator implements ExpressionEvaluator {

  private static ShuntingYardExpressionEvaluator instance;
  private static final String DEFAULT_ANSWER_SYMBOL = "_";

  private Map<String, Double> bindings;
  private LexicalAnalyzer lexicalAnalyzer;
  private Stack<Symbol> operatorStack;
  private Identifier resultBinding;

  private ShuntingYardExpressionEvaluator() {
    this.bindings = new HashMap<>();
    this.operatorStack = new Stack<>();
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

  /** {@inheritDoc} */
  public double eval(final String expression) {
    final List<Symbol> symbols = lexicalAnalyzer.getSymbols(expression);
    final List<Symbol> sortedSymbols = sortSymbolsInReversePolishNotation(symbols);
    prepareEvaluationData(sortedSymbols);
    for (int index = 0; index < sortedSymbols.size(); index++) {
      final Symbol symbol = sortedSymbols.get(index);
      if (symbol instanceof Operand) {
        operatorStack.push(symbol);
      } else if (symbol instanceof Operator) {
        performOperator(symbol);
      } else if (symbol instanceof Function) {
        final Function function = (Function) symbol;
        final Operand operand = (Operand) sortedSymbols.get(++index);
        performArithmeticFunction(function, operand);
      }
    }
    return getResultEvaluation();
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

  /**
   * This method sort the symbols list in the reverse polish notation, which is required by Shunting
   * yard algorithm.
   * 
   * For instance, '3', '+', '2' is rewritten as '3','2','+'.
   * 
   * Basically, the method to rewrite in-fix into post-fix expressions, does:
   * 
   * 1) Check if the symbol is a number and if it is true, just includes it in the sorted list.
   * 
   * 2) If the symbol is an operator does: Check if the operator stack is empty and in this case,
   * includes the symbol in the operator stack. Otherwise, compares the precedence of the current
   * symbol and the elements inside the stack (starting from the top). The elements which contains
   * greater or equal precedence should be moved to the sorted list.
   * 
   * 3) In case of brackets (parentheses start), this algorithm includes it into the operator stack.
   * Otherwise (parentheses end), it moves the operators inside the stack into sorted list (until
   * finding another parentheses start).
   * 
   * 4) Move the leftovers (symbols into the operator stack) into the sorted list.
   * 
   * @param symbols
   * @return
   */
  private List<Symbol> sortSymbolsInReversePolishNotation(final List<Symbol> symbols) {

    final List<Symbol> sortedSymbols = new LinkedList<>();
    operatorStack.clear();

    for (final Symbol symbol : symbols) {
      if (symbol instanceof Operand) {
        sortedSymbols.add(symbol);
      } else if (symbol instanceof Function) {
        sortedSymbols.add(symbol);
      } else if (symbol instanceof Operator) {
        if (operatorStack.isEmpty()) {
          operatorStack.push(symbol);
        } else {
          final Operator currentOperator = (Operator) symbol;
          Symbol topStack = operatorStack.peek();
          while (topStack != null && (topStack instanceof Operator) && ((Operator) topStack)
              .getType().getPrecedenceLevel() >= currentOperator.getType().getPrecedenceLevel()) {
            sortedSymbols.add(operatorStack.pop());
            topStack = operatorStack.size() > 0 ? operatorStack.peek() : null;
          }
          operatorStack.push(currentOperator);
        }
      } else if (symbol instanceof Bracket) {
        final Bracket bracket = (Bracket) symbol;
        if (bracket.getType() == BracketType.PARENTHESES_START) {
          operatorStack.push(bracket);
        } else {
          while ((operatorStack.size() > 0) && !isSymbolTopStackALeftBracket()) {
            sortedSymbols.add(operatorStack.pop());
          }
          if (operatorStack.size() > 0) {
            operatorStack.pop();
          } else {
            throwsInvalidExpressionException();
          }
        }
      }
    }

    while (!operatorStack.isEmpty()) {
      final Symbol remainedSymbol = operatorStack.pop();
      if (remainedSymbol instanceof Bracket) {
        throwsInvalidExpressionException();
      } else {
        sortedSymbols.add(remainedSymbol);
      }
    }

    return sortedSymbols;
  }

  /**
   * Check if symbol on the top of the stack is a left bracket.
   * 
   * @return
   */
  private boolean isSymbolTopStackALeftBracket() {
    final Symbol topStack = operatorStack.peek();
    return (topStack instanceof Bracket)
        && ((Bracket) topStack).getType().equals(BracketType.PARENTHESES_START);
  }

  /**
   * This method returns the result of the expression evaluation, which is represented by the last
   * element inside the operator stack. In particular, it also check if the result was previously
   * set (via an assignment operation). In this case, it is not necessary to set the result anymore.
   * 
   * @return
   */
  private double getResultEvaluation() {
    if (!resultBinding.isAssigned()) {
      final Operand result = (Operand) operatorStack.pop();
      resultBinding.setName(DEFAULT_ANSWER_SYMBOL);
      resultBinding.setValue(result.getValue());
    }
    bindings.put(resultBinding.getName(), resultBinding.getValue());
    return resultBinding.getValue();
  }

  /**
   * Method that validates restrictions and performs the operator action.
   * 
   * @param symbol
   */
  private void performOperator(final Symbol symbol) {
    final Operator operator = (Operator) symbol;
    if (operator.getType().equals(OperatorType.ASSIGNMENT)) {
      checkThereIsOneSymbolIntoTheStack();
      resultBinding.setValue(((Operand) operatorStack.peek()).getValue());
      resultBinding.setAssigned(true);
    } else {
      checkThereAreTwoSymbolsIntoTheStack();
      double result = performArithmeticOperation(operator);
      operatorStack.push(new Operand(result));
    }
  }

  /**
   * Executes the correspondent arithmetic operation.
   * 
   * @param operator
   * @return
   */
  private double performArithmeticOperation(final Operator operator) {
    final Operand rightHandSide = (Operand) operatorStack.pop();
    final Operand leftHandSide = (Operand) operatorStack.pop();
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
        throwsInvalidExpressionException();
        break;
    }
    return result;
  }

  /**
   * Executes the correspondent arithmetic function.
   * 
   * @param function
   * @param operand
   * @return
   */
  private void performArithmeticFunction(final Function function, final Operand operand) {
    double result = 0;
    switch (function.getType()) {
      case SIN:
        result = Math.sin(operand.getValue());
        break;
      case COS:
        result = Math.cos(operand.getValue());
        break;
      case LOG:
        result = Math.log10(operand.getValue());
        break;
      case SQRT:
        result = Math.sqrt(operand.getValue());
        break;
      default:
        throwsInvalidExpressionException();
        break;
    }
    operatorStack.push(new Operand(result));
  }

  /**
   * This method is responsible for cleaning variables and objects used during the evaluation
   * process. In addition, it loads all binding values for its respective identifier.
   */
  private void prepareEvaluationData(final List<Symbol> symbols) {
    operatorStack.clear();
    resultBinding = new Identifier(DEFAULT_ANSWER_SYMBOL);
    for (int idx = 0; idx < symbols.size(); idx++) {
      final Symbol symbol = symbols.get(idx);
      final Identifier identifier = (symbol instanceof Identifier) ? (Identifier) symbol : null;
      if (identifier != null) {
        final Double identifierValue = bindings.get(identifier.getName());
        if (identifierValue == null) {
          throwsInvalidExpressionException();
        } else {
          identifier.setValue(identifierValue);
        }
      }
    }
  }

  /**
   * Check if there is one element inside the stack.
   */
  private void checkThereIsOneSymbolIntoTheStack() {
    if (operatorStack.size() == 1) {
      throwsInvalidExpressionException();
    }
  }

  /**
   * Check if there are two symbols inside the stack.
   */
  private void checkThereAreTwoSymbolsIntoTheStack() {
    if (operatorStack.size() < 2) {
      throwsInvalidExpressionException();
    }
  }

  /**
   * Throws an invalid expression exception.;
   */
  private void throwsInvalidExpressionException() {
    throw new InvalidExpressionException();
  }

}
