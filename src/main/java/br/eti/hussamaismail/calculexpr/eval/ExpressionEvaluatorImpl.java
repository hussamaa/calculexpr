package br.eti.hussamaismail.calculexpr.eval;

/**
 * This class implements the expression evaluator used in this project. In particular, it is
 * provided a singleton instance which performs the evaluation of an arithmetic expression.
 * 
 * @author Hussama Ismail
 */
public class ExpressionEvaluatorImpl implements ExpressionEvaluator {

  private static ExpressionEvaluatorImpl instance;

  private ExpressionEvaluatorImpl() {}

  /**
   * Return an instance of ExpressionEvaluator.
   * 
   * @return
   */
  public static ExpressionEvaluatorImpl getInstance() {
    if (instance == null) {
      instance = new ExpressionEvaluatorImpl();
    }
    return instance;
  }

  /** {@inheritDoc} */
  public double eval(final String expression) {
    //
    // TODO: parse and evaluate expr; return its value
    //
    return 0;
  }

}
