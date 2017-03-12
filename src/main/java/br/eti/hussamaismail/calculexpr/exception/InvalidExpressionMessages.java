package br.eti.hussamaismail.calculexpr.exception;

/**
 * Enum which contains the exception messages used in this project. 
 * 
 * @author Hussama Ismail
 */
public enum InvalidExpressionMessages {

  TOKEN_NOT_FOUND           ("Token '%s' not found."),
  OPERATOR_NOT_FOUND        ("Operator not found."),
  FUNCTION_NOT_FOUND        ("Function not found."),
  IDENTIFIER_NOT_FOUND      ("Identifier '%s' not found."),
  MISMATCHED_PARENTHESES    ("Mismatched parentheses."),
  GENERAL_INVALID_EXPRESSION("The expression is invalid.");
  
  private String message;

  private InvalidExpressionMessages(final String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
