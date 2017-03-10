package br.eti.hussamaismail.calculexpr;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import br.eti.hussamaismail.calculexpr.domain.Token;
import br.eti.hussamaismail.calculexpr.domain.TokenType;
import br.eti.hussamaismail.calculexpr.parse.BasicLexicalAnalyzer;
import br.eti.hussamaismail.calculexpr.parse.LexicalAnalyzer;
import br.eti.hussamaismail.calculexpr.parse.ParseUtil;

/**
 * Class which contains the unitary tests in order to validate the methods available at ParseUtil.
 * 
 * @author Hussama Ismail
 */
public class ParseUtilTest {

  private static LexicalAnalyzer lexicalAnalizer;

  @BeforeClass
  public static void prepare() {
    lexicalAnalizer = BasicLexicalAnalyzer.getInstance();
  }

  @Test
  public void testSortBasedOnReversePolishNotation() {
    final String expression = "9 + 24 / ( 7 - 3 )";
    final List<Token> tokens = lexicalAnalizer.getTokens(expression);
    
    System.out.println(tokens);
    
    final List<Token> sortedTokens = ParseUtil.sortTokensInReversePolishNotation(tokens);
    assertEquals(7, sortedTokens.size());
    assertEquals(TokenType.NUMBER, sortedTokens.get(0).getType());
    assertEquals(9.0, Double.parseDouble( (String) sortedTokens.get(0).getValue()), 0);
    assertEquals(TokenType.NUMBER, sortedTokens.get(1).getType());
    assertEquals(24.0, Double.parseDouble( (String) sortedTokens.get(1).getValue()), 0);
    assertEquals(TokenType.NUMBER, sortedTokens.get(2).getType());
    assertEquals(7.0, Double.parseDouble( (String) sortedTokens.get(2).getValue()), 0);
    assertEquals(TokenType.NUMBER, sortedTokens.get(3).getType());
    assertEquals(3.0, Double.parseDouble( (String) sortedTokens.get(3).getValue()), 0);
    assertEquals(TokenType.OPERATOR, sortedTokens.get(4).getType());
    assertEquals("-", sortedTokens.get(4).getValue());
    assertEquals(TokenType.OPERATOR, sortedTokens.get(5).getType());
    assertEquals("/", sortedTokens.get(5).getValue());
    assertEquals(TokenType.OPERATOR, sortedTokens.get(6).getType());
    assertEquals("+", sortedTokens.get(6).getValue());
  }

}
