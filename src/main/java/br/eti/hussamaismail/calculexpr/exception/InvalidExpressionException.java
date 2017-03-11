package br.eti.hussamaismail.calculexpr.exception;

/**
 * Exception raised when an expression is invalid (syntax).
 * 
 * @author Hussama Ismail
 */
public class InvalidExpressionException extends RuntimeException {

  private static final String GENERAL_INVALID_EXPRESSION = "The expression is invalid.";

  private static final long serialVersionUID = 1L;

  /**
   * Default invalid expression message.
   */
  public InvalidExpressionException() {
    super(GENERAL_INVALID_EXPRESSION);
  }

}
