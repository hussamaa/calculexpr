package br.eti.hussamaismail.calculexpr.evaluation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements the expression evaluator used in this project. In particular, it is
 * provided a singleton instance which performs the evaluation of an arithmetic expression.
 * 
 * @author Hussama Ismail
 */
public class ExpressionEvaluatorImpl implements ExpressionEvaluator {

  private static ExpressionEvaluatorImpl instance;

  private Map<String, Double> bindings;

  private ExpressionEvaluatorImpl() {
    this.bindings = new HashMap<>();
  }

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

    return 0;
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
