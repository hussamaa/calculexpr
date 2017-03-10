package br.eti.hussamaismail.calculexpr.evaluation;

/**
 * This interface describes the methods required in an expression evaluator used in this project.
 * 
 * @author Hussama Ismail
 */
public interface ExpressionEvaluator {

  /**
   * Evaluates an arithmetic expression. The grammar of accepted expressions is the following:
   * 
   * <code>
   * 
   *   expr ::= expr binop expr | '-' expr | '(' expr ')' | binding | term | function
   *   binop ::= '+' | '-' | '*' | '/'
   *   binding ::= identifier '=' expr
   *   term ::= number | identifier
   *   number ::= integer | decimal
   *   integer ::= '0' | ('1' - '9') ('0' - '9')*
   *   decimal ::= ( integer )? '.' ('0' - '9')*
   *   identifier ::= ('a' - 'z' | 'A' - 'Z') ('a' - 'z' | 'A' - 'Z' | '0' - '9')*
   *   function ::= ('sqrt' | 'log' | 'sin' | 'cos') '(' expr ')'
   * 
   * </code>
   * 
   * NOTE: whitespace is not allowed in expressions.
   * 
   * Evaluation of binary operators follows the convention that multiplication and division take
   * precedence over addition and subtraction.
   * 
   * Functions are implemented in terms of the respective static methods in java.lang.Math.
   * 
   * The bindings produced during the evaluation of the given expression are stored in a map, where
   * they remain available for the evaluation of subsequent expressions.
   * 
   * Before leaving this method, the value of the given expression is bound to the special variable
   * named "_".
   * 
   * @param expr well-formed arithmetic expression
   * @return the value of the given expression
   */
  double eval(String expression);

  /**
   * Method responsible for showing the result of all previous expression in cache.
   */
  void showAllBindings();

  /**
   * Method responsible for erasing all results of previous expressions in cache.
   */
  void removeAllBindings();

  /**
   * Method responsible for removing specific bindings.
   */
  void removeBindings(String[] strings);

}
