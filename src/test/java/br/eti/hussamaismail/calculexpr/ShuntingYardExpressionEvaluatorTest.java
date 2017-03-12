package br.eti.hussamaismail.calculexpr;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import br.eti.hussamaismail.calculexpr.evaluation.ExpressionEvaluator;
import br.eti.hussamaismail.calculexpr.evaluation.ShuntingYardExpressionEvaluator;
import br.eti.hussamaismail.calculexpr.exception.InvalidExpressionException;

/**
 * This class describe the unit tests responsible for evaluating the basic arithmetics expressions
 * and its expected results. Especially, each test case has a different expression to be evaluated.
 * 
 * @author Hussama Ismail
 */
public class ShuntingYardExpressionEvaluatorTest {

  private String expression;
  private static ExpressionEvaluator evaluator;

  @BeforeClass
  public static void prepare() {
    evaluator = ShuntingYardExpressionEvaluator.getInstance();
  }

  @Test
  public void testAddition1() {
    expression = "3+2";
    assertEquals(5, evaluator.eval(expression), 0);
  }

  @Test
  public void testAddition11() {
    expression = "3 + 2";
    assertEquals(5, evaluator.eval(expression), 0);
  }

  @Test
  public void testAddition2() {
    expression = "3+2+103";
    assertEquals(108, evaluator.eval(expression), 0);
  }

  @Test
  public void testAddition3() {
    expression = "103.2+993.253+99.21+1000";
    assertEquals(2195.663, evaluator.eval(expression), 0.001);
  }

  @Test
  public void testAddition33() {
    expression = "103.2 + 993.253 + 99.21 + 1000";
    assertEquals(2195.663, evaluator.eval(expression), 0.001);
  }

  @Test
  public void testAddition4() {
    expression = ".1231 + 50 -.12";
    assertEquals(50.0031, evaluator.eval(expression), 0.1);
  }


  @Test
  public void testSubtraction1() {
    expression = "100-10";
    assertEquals(90, evaluator.eval(expression), 0);
  }

  @Test
  public void testSubtraction11() {
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
    expression = "23.3-10.2";
    assertEquals(13.1, evaluator.eval(expression), 0.01);
  }

  @Test
  public void testAdditionAndSubtraction1() {
    expression = "50+10-10-100+100-50";
    assertEquals(0, evaluator.eval(expression), 0);
  }

  @Test
  public void testAdditionAndSubtraction11() {
    expression = "50 + 10 - 10 - 100 + 100 - 50";
    assertEquals(0, evaluator.eval(expression), 0);
  }

  @Test
  public void testMultiplication1() {
    expression = "3*5";
    assertEquals(15, evaluator.eval(expression), 0);
  }

  @Test
  public void testMultiplication11() {
    expression = "3 * 5";
    assertEquals(15, evaluator.eval(expression), 0);
  }

  @Test
  public void testMultiplication2() {
    expression = "3.3*3*30";
    assertEquals(297, evaluator.eval(expression), 0.1);
  }

  @Test
  public void testMultiplication3() {
    expression = "-3.3*3*30*-1*10*0.5";
    assertEquals(1485, evaluator.eval(expression), 0.1);
  }

  @Test
  public void testDivision1() {
    expression = "100/4";
    assertEquals(25, evaluator.eval(expression), 0);
  }

  @Test
  public void testDivision2() {
    expression = "1/4";
    assertEquals(0.25, evaluator.eval(expression), 0);
  }

  @Test
  public void testDivision3() {
    expression = "102/32/0.5/10";
    assertEquals(0.6375, evaluator.eval(expression), 0);
  }

  @Test
  public void testDivision33() {
    expression = "102 / 32 / 0.5 / 10";
    assertEquals(0.6375, evaluator.eval(expression), 0);
  }

  @Test
  public void testPrecedence1() {
    expression = "1+2*2*5+2";
    assertEquals(23, evaluator.eval(expression), 0);
  }

  @Test
  public void testPrecedence11() {
    expression = "1 + 2 * 2 * 5 + 2";
    assertEquals(23, evaluator.eval(expression), 0);
  }

  @Test
  public void testPrecedence2() {
    expression = "1+2*2*5+2/2+2*0.5";
    assertEquals(23, evaluator.eval(expression), 0);
  }

  @Test
  public void testPrecedence22() {
    expression = "1 + 2 * 2 * 5 + 2 / 2 + 2 * 0.5";
    assertEquals(23, evaluator.eval(expression), 0);
  }

  @Test
  public void testBrackets1() {
    expression = "1+(2+3)";
    assertEquals(6, evaluator.eval(expression), 0);
  }

  @Test
  public void testBrackets11() {
    expression = "1 + (2 + 3)";
    assertEquals(6, evaluator.eval(expression), 0);
  }

  @Test
  public void testBrackets2() {
    expression = "3*(2+3)";
    assertEquals(15, evaluator.eval(expression), 0);
  }

  @Test
  public void testBrackets22() {
    expression = "3 * (2 + 3)";
    assertEquals(15, evaluator.eval(expression), 0);
  }

  @Test
  public void testBrackets3() {
    expression = "3*(2+3)+(3-2)*10";
    assertEquals(25, evaluator.eval(expression), 0);
  }

  @Test
  public void testBrackets33() {
    expression = "3 * (2 + 3) + ( 3 - 2 ) * 10";
    assertEquals(25, evaluator.eval(expression), 0);
  }

  @Test
  public void testFunctions1() {
    expression = "3+sin(90)+cos(0)*3";
    assertEquals(6.8939, evaluator.eval(expression), 0.1);
  }

  @Test
  public void testFunctions11() {
    expression = "3 + sin(90) + cos (0) * 3";
    assertEquals(6.8939, evaluator.eval(expression), 0.1);
  }

  @Test
  public void testFunctions2() {
    expression = "sin(0)+4*3+sqrt(25)+cos(90)+cos(45)+sin(1)+sqrt(23)";
    assertEquals(22.7145, evaluator.eval(expression), 0.1);
  }

  @Test
  public void testFunctions3() {
    expression = "3*3+2+sqrt(30)+log(2)+sin(3)+sin(4)+sqrt(3)+2+3-1*3+0-1+4+21+log(45)+25";
    assertEquals(70.54783, evaluator.eval(expression), 0.1);
  }

  @Test
  public void testSimpleSignalChange1() {
    expression = "-(23+2)";
    assertEquals(-25, evaluator.eval(expression), 0.1);
  }

  @Test
  public void testSimpleSignalChange2() {
    expression = "-(-23+2)";
    assertEquals(21, evaluator.eval(expression), 0.1);
  }

  @Test
  public void testSimpleSignalChange3() {
    expression = "-(1-2-(2+2))";
    assertEquals(5.0, evaluator.eval(expression), 0.1);
  }

  @Test
  public void testMultipleAssignments1() {
    evaluator.removeAllBindings();
    expression = "a = 3";
    assertEquals(3, evaluator.eval(expression), 0.1);
    expression = "a = (a * 3)";
    assertEquals(9, evaluator.eval(expression), 0.1);
  }

  @Test
  public void testMultipleAssignments2() {
    evaluator.removeAllBindings();
    expression = "a = 3 + 2 + 4 + 5";
    assertEquals(14, evaluator.eval(expression), 0.1);
    expression = "b = (3 * 5)";
    assertEquals(15, evaluator.eval(expression), 0.1);
    expression = "a + b * 2";
    assertEquals(44, evaluator.eval(expression), 0.1);
  }

  @Test
  public void testSpecialVariable() {
    evaluator.removeAllBindings();
    expression = "5 + 2";
    assertEquals(7, evaluator.eval(expression), 0.1);
    expression = "3 * _";
    assertEquals(21, evaluator.eval(expression), 0.1);
  }

  @Test
  public void testInverseExpressionAndVariable() {
    evaluator.removeAllBindings();
    expression = "a=1";
    assertEquals(1, evaluator.eval(expression), 0.1);
    expression = "a=-a";
    assertEquals(-1, evaluator.eval(expression), 0.1);
  }

  @Test
  public void testInverseExpressionAndVariable1() {
    evaluator.removeAllBindings();
    expression = "a = 1";
    assertEquals(1, evaluator.eval(expression), 0.1);
    expression = "a = -a";
    assertEquals(-1, evaluator.eval(expression), 0.1);
  }

  @Test
  public void testInverseExpressionAndVariable2() {
    evaluator.removeAllBindings();
    expression = "a = 1";
    assertEquals(1, evaluator.eval(expression), 0.1);
    expression = "a = -a";
    assertEquals(-1, evaluator.eval(expression), 0.1);
  }

  @Test(expected=InvalidExpressionException.class)
  public void testExpressionInvalidIdentifier() {
    evaluator.removeAllBindings();
    expression = "a = 1";
    assertEquals(1, evaluator.eval(expression), 0.1);
    expression = "a = a + b";
    assertEquals(-1, evaluator.eval(expression), 0.1);
  }
  
  @Test
  public void testExpression() {
    expression = "()";
    assertEquals(0, evaluator.eval(expression), 0.1);  
  }
  
  @Test(expected=InvalidExpressionException.class)
  public void testInvalidExpression2() {
    expression = "())";
    assertEquals(1, evaluator.eval(expression), 0.1);  
  }
  
  @Test(expected=InvalidExpressionException.class)
  public void testInvalidExpression3() {
    expression = "(";
    assertEquals(1, evaluator.eval(expression), 0.1);  
  }
  
  @Test(expected=InvalidExpressionException.class)
  public void testInvalidExpression4() {
    expression = ")";
    assertEquals(1, evaluator.eval(expression), 0.1);  
  }
}
