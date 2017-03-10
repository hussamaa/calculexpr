package br.eti.hussamaismail.calculexpr.parse;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import br.eti.hussamaismail.calculexpr.domain.Token;

public class ParseUtil {

  private final static Stack<Token> operatorStack = new Stack<>();

  private static boolean hasTopOperatorInStackGreaterPrecedence(final Token currentOperator) {
    final Token topOperator = operatorStack.peek();
    final String topOperatorValue = String.valueOf(topOperator.getValue());
    final String currentOperatorValue = String.valueOf(currentOperator.getValue());
    boolean hasTopOperatorGreaterPrecedence = false;
    if ((currentOperatorValue.equals("+") || currentOperatorValue.equals("-"))
        && (topOperatorValue.equals("*") || topOperatorValue.equals("/"))) {
      hasTopOperatorGreaterPrecedence = true;
    }
    return hasTopOperatorGreaterPrecedence;
  }

  private static boolean isTopOperatorInStackALeftBracket() {
    final Token topOperator = operatorStack.peek();
    return topOperator.getValue().equals("(");
  }

  public static List<Token> sortTokensInReversePolishNotation(final List<Token> tokens) {
    final List<Token> reversePolishNotationList = new ArrayList<Token>();
    System.out.println("TOKENS (in-prefix): " + tokens);
    for (final Token token : tokens) {
      switch (token.getType()) {
        case NUMBER:
          reversePolishNotationList.add(token);
          break;
        case OPERATOR:
          if (operatorStack.isEmpty()) {
            operatorStack.push(token);
          } else {
            while (hasTopOperatorInStackGreaterPrecedence(token)) {
              final Token highPrecedenceOperator = operatorStack.pop();
              reversePolishNotationList.add(highPrecedenceOperator);
            }
            operatorStack.push(token);
          }
          break;
        case BEGIN_EXPRESSION:
          operatorStack.push(token);
          break;
        case END_EXPRESSION:
          while (isTopOperatorInStackALeftBracket() == false) {
            final Token tokenNotLeftBracket = operatorStack.pop();
            reversePolishNotationList.add(tokenNotLeftBracket);
          }
          operatorStack.pop();
          break;
        default:
          throw new RuntimeException(
              token.getValue() + " is not supportable by the reverse polish conversion");
      }
    }    
    while (!operatorStack.isEmpty()){
      reversePolishNotationList.add(operatorStack.pop());
    }      
    
    System.out.println("TOKENS (reverse polish): " + reversePolishNotationList);    
    return reversePolishNotationList;
  }

}
