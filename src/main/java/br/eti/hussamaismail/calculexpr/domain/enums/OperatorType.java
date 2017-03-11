package br.eti.hussamaismail.calculexpr.domain.enums;

/**
 * Types of operators and its precedence level.
 * 
 * @author Hussama Ismail
 */
public enum OperatorType {

  ADDITION(0), SUBTRACTION(0), DIVISION(1), MULTIPLICATION(1), ASSIGNMENT(0), OTHER(0);

  private int precedenceLevel;

  private OperatorType(final int precedenceLevel) {
    this.precedenceLevel = precedenceLevel;
  }

  public int getPrecedenceLevel() {
    return precedenceLevel;
  }

}
