package br.eti.hussamaismail.calculexpr;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.eti.hussamaismail.calculexpr.parse.LexicalAnalyzer;
import br.eti.hussamaismail.calculexpr.parse.Token;
import br.eti.hussamaismail.calculexpr.parse.TokenType;

/**
 * This class tests some methods presents in the small lexical analyzer.
 * 
 * @author Hussama Ismail
 */
public class LexicalAnalyzerTest {

  private LexicalAnalyzer lexicalAnalyzer;

  @Before
  public void prepare() {
    lexicalAnalyzer = LexicalAnalyzer.getInstance();
  }

  @Test
  public void testTokens1() throws IOException {
    final String expression = "2 + 3";
    final List<Token> tokens = lexicalAnalyzer.getTokens(expression);
    assertEquals(tokens.size(), 3);
    assertEquals(tokens.get(0).getType(), TokenType.NUMBER);
    assertEquals(tokens.get(1).getType(), TokenType.OPERATOR);
    assertEquals(tokens.get(2).getType(), TokenType.NUMBER);
  }

  @Test
  public void testTokens2() throws IOException {
    final String expression = "( 2 + ( 3 * 2 ) * sin ( 10 ))";
    final List<Token> tokens = lexicalAnalyzer.getTokens(expression);
    assertEquals(tokens.size(), 14);
    assertEquals(tokens.get(0).getType(), TokenType.BEGIN_EXPRESSION);
    assertEquals(tokens.get(1).getType(), TokenType.NUMBER);
    assertEquals(tokens.get(2).getType(), TokenType.OPERATOR);
    assertEquals(tokens.get(3).getType(), TokenType.BEGIN_EXPRESSION);
    assertEquals(tokens.get(4).getType(), TokenType.NUMBER);
    assertEquals(tokens.get(5).getType(), TokenType.OPERATOR);
    assertEquals(tokens.get(6).getType(), TokenType.NUMBER);
    assertEquals(tokens.get(7).getType(), TokenType.END_EXPRESSION);
    assertEquals(tokens.get(8).getType(), TokenType.OPERATOR);
    assertEquals(tokens.get(9).getType(), TokenType.FUNCTION);
    assertEquals(tokens.get(10).getType(), TokenType.BEGIN_EXPRESSION);
    assertEquals(tokens.get(11).getType(), TokenType.NUMBER);
    assertEquals(tokens.get(12).getType(), TokenType.END_EXPRESSION);
    assertEquals(tokens.get(13).getType(), TokenType.END_EXPRESSION);
  }

  @Test
  public void testTokens3() throws IOException {
    final String expression = "cos(1)+sin(1)+sqrt(3)+log(2)";
    final List<Token> tokens = lexicalAnalyzer.getTokens(expression);
    assertEquals(tokens.size(), 19);
    assertEquals(tokens.get(0).getType(), TokenType.FUNCTION);
    assertEquals(tokens.get(1).getType(), TokenType.BEGIN_EXPRESSION);
    assertEquals(tokens.get(2).getType(), TokenType.NUMBER);
    assertEquals(tokens.get(3).getType(), TokenType.END_EXPRESSION);
    assertEquals(tokens.get(4).getType(), TokenType.OPERATOR);
    assertEquals(tokens.get(5).getType(), TokenType.FUNCTION);
    assertEquals(tokens.get(6).getType(), TokenType.BEGIN_EXPRESSION);
    assertEquals(tokens.get(7).getType(), TokenType.NUMBER);
    assertEquals(tokens.get(8).getType(), TokenType.END_EXPRESSION);
    assertEquals(tokens.get(9).getType(), TokenType.OPERATOR);
    assertEquals(tokens.get(10).getType(), TokenType.FUNCTION);
    assertEquals(tokens.get(11).getType(), TokenType.BEGIN_EXPRESSION);
    assertEquals(tokens.get(12).getType(), TokenType.NUMBER);
    assertEquals(tokens.get(13).getType(), TokenType.END_EXPRESSION);
    assertEquals(tokens.get(14).getType(), TokenType.OPERATOR);
    assertEquals(tokens.get(15).getType(), TokenType.FUNCTION);
    assertEquals(tokens.get(16).getType(), TokenType.BEGIN_EXPRESSION);
    assertEquals(tokens.get(17).getType(), TokenType.NUMBER);
    assertEquals(tokens.get(18).getType(), TokenType.END_EXPRESSION);
  }

  @Test
  public void testTokens4() throws IOException {
    final String expression = "a12 = 3";
    final List<Token> tokens = lexicalAnalyzer.getTokens(expression);
    assertEquals(tokens.size(), 3);
    assertEquals(tokens.get(0).getType(), TokenType.IDENTIFIER);
    assertEquals(tokens.get(1).getType(), TokenType.ASSIGNMENT);
    assertEquals(tokens.get(2).getType(), TokenType.NUMBER);
  }

}
