package br.eti.hussamaismail.calculexpr.domain.enums;

/**
 * Types of operators and its precedence level.
 * 
 * @author Hussama Ismail
 */
public enum OperatorType {

  ADDITION(1), SUBTRACTION(1), DIVISION(2), MULTIPLICATION(2), ASSIGNMENT(0);

  private int precedenceLevel;

  private OperatorType(final int precedenceLevel) {
    this.precedenceLevel = precedenceLevel;
  }

  public int getPrecedenceLevel() {
    return precedenceLevel;
  }

}
