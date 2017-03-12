package br.eti.hussamaismail.calculexpr.exception;

import br.eti.hussamaismail.calculexpr.domain.Identifier;

/**
 * Exception raised when an expression is invalid (syntax).
 * 
 * @author Hussama Ismail
 */
public class InvalidExpressionException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * Default invalid expression message.
   */
  public InvalidExpressionException() {
    super(InvalidExpressionMessages.GENERAL_INVALID_EXPRESSION.getMessage());
  }

  /**
   * Exception for not recognized tokens.
   * 
   * @param token
   */
  public InvalidExpressionException(final Identifier identifier) {
    super(String.format(InvalidExpressionMessages.IDENTIFIER_NOT_FOUND.getMessage(),
        identifier.getName()));
  }

  /**
   * Exception for not recognized tokens.
   * 
   * @param token
   */
  public InvalidExpressionException(final String token) {
    super(String.format(InvalidExpressionMessages.TOKEN_NOT_FOUND.getMessage(), token));
  }

  /**
   * Exception for default messages.
   * 
   * @param message
   */
  public InvalidExpressionException(final InvalidExpressionMessages message) {
    super(message.getMessage());
  }

}
