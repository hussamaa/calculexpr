package br.eti.hussamaismail.calculexpr;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import br.eti.hussamaismail.calculexpr.evaluation.ExpressionEvaluator;
import br.eti.hussamaismail.calculexpr.evaluation.ShuntingYardExpressionEvaluator;

/**
 * This class describe the unit tests responsible for evaluating the basic arithmetics expressions
 * and its expected results. Especially, each test case has a different expression to be evaluated.
 * 
 * @author Hussama Ismail
 */
public class ArithmeticsTest {

  private String expression;
  private static ExpressionEvaluator evaluator;

  @BeforeClass
  public static void prepare() {
    evaluator = ShuntingYardExpressionEvaluator.getInstance();
  }

  @Test
  public void testAddition1() {
    expression = "3 + 2";
    assertEquals(5, evaluator.eval(expression), 0);
  }

  @Test
  public void testAddition2() {
    expression = "3 + 2 + 103";
    assertEquals(108, evaluator.eval(expression), 0);
  }

  @Test
  public void testAddition3() {
    expression = "103.2 + 993.253 + 99.21 + 1000";
    assertEquals(2195.663, evaluator.eval(expression), 0.001);
  }

  @Test
  public void testSubtraction1() {
    expression = "100 - 10";
    assertEquals(90, evaluator.eval(expression), 0);
  }

  @Test
  public void testSubtraction2() {
    expression = "10 - 100";
    assertEquals(-90, evaluator.eval(expression), 0);
  }

  @Test
  public void testSubtraction3() {
    expression = "23.3 - 10.2";
    assertEquals(13.1, evaluator.eval(expression), 0.01);
  }

  @Test
  public void testAdditionAndSubtraction1() {
    expression = "50 + 10 - 10 - 100 + 100 - 50";
    assertEquals(0, evaluator.eval(expression), 0);
  }

  @Test
  public void testMultiplication1() {
    expression = "3 * 5";
    assertEquals(15, evaluator.eval(expression), 0);
  }

  @Test
  public void testMultiplication2() {
    expression = "3.3 * 3 * 30";
    assertEquals(297, evaluator.eval(expression), 0);
  }

  @Test
  public void testMultiplication3() {
    expression = "-3.3 * 3 * 30 * -1 * 10 * 0.5";
    assertEquals(1485, evaluator.eval(expression), 0);
  }

  @Test
  public void testDivision1() {
    expression = "100 / 4";
    assertEquals(25, evaluator.eval(expression), 0);
  }

  @Test
  public void testDivision2() {
    expression = "1 / 4";
    assertEquals(0.25, evaluator.eval(expression), 0);
  }

  @Test
  public void testDivision3() {
    expression = "102 / 32 / 0.5 / 10";
    assertEquals(0.6375, evaluator.eval(expression), 0);
  }

  @Test
  public void testPrecedence1() {
    expression = "1 + 2 * 2 * 5 + 2";
    assertEquals(23, evaluator.eval(expression), 0);
  }

  @Test
  public void testPrecedence2() {
    expression = "1 + 2 * 2 * 5 + 2 / 2 + 2 * 0.5";
    assertEquals(23, evaluator.eval(expression), 0);
  }

  @Test
  public void testBrackets1() {
    expression = "1 + (2 + 3)";
    assertEquals(6, evaluator.eval(expression), 0);
  }

  @Test
  public void testBrackets2() {
    expression = "3 * (2 + 3)";
    assertEquals(18, evaluator.eval(expression), 0);
  }

  @Test
  public void testBrackets3() {
    expression = "3 * (2 + 3) + ( 3 - 2 ) * 10";
    assertEquals(25, evaluator.eval(expression), 0);
  }

}
