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
    final String expression = " 9 + 24 / ( 7 - 3 )";
    final List<Token> tokens = lexicalAnalizer.getTokens(expression);
    final List<Token> sortedTokens = ParseUtil.sortTokensInReversePolishNotation(tokens);
    assertEquals(sortedTokens.size(), 7);
    assertEquals(sortedTokens.get(0).getType(), TokenType.NUMBER);
    assertEquals(sortedTokens.get(0).getValue(), 9);
    assertEquals(sortedTokens.get(1).getType(), TokenType.NUMBER);
    assertEquals(sortedTokens.get(1).getValue(), 24);
    assertEquals(sortedTokens.get(2).getType(), TokenType.NUMBER);
    assertEquals(sortedTokens.get(2).getValue(), 7);
    assertEquals(sortedTokens.get(3).getType(), TokenType.NUMBER);
    assertEquals(sortedTokens.get(3).getValue(), 3);
    assertEquals(sortedTokens.get(4).getType(), TokenType.OPERATOR);
    assertEquals(sortedTokens.get(4).getValue(), "-");
    assertEquals(sortedTokens.get(5).getType(), TokenType.OPERATOR);
    assertEquals(sortedTokens.get(5).getValue(), "/");
    assertEquals(sortedTokens.get(6).getType(), TokenType.OPERATOR);
    assertEquals(sortedTokens.get(6).getValue(), "+");
  }

}
