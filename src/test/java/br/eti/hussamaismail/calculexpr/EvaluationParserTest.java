package br.eti.hussamaismail.calculexpr;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import br.eti.hussamaismail.calculexpr.parse.ExpressionParser;

/**
 * This class tests some methods presents in the evaluation parser.
 * 
 * @author Hussama Ismail
 */
public class EvaluationParserTest {

  private ExpressionParser parser;

  @Before
  public void prepare() {
    parser = ExpressionParser.getInstance();
  }

  @Test
  public void testNumberOfTokens1() throws IOException {
    String expression = "2 + 3";
    assertEquals(parser.getTokens(expression).size(), 3);
  }

  @Test
  public void testNumberOfTokens2() throws IOException {
    String expression = "( 2 + ( 3 * 2 ) * sin ( 10 ))";
    assertEquals(parser.getTokens(expression).size(), 14);
  }

  @Test
  public void testNumberOfTokens3() throws IOException {
    String expression = "-( -2 + ( 3 * 2 ) * sin ( 10 ))";
    assertEquals(parser.getTokens(expression).size(), 15);
  }
  
  @Test
  public void testNumberOfTokens4() throws IOException {
    String expression = "-( -2 + ( 3 * 2 ) * sin ( 10.10 ))";
    assertEquals(parser.getTokens(expression).size(), 15);
  }

}
