package br.eti.hussamaismail.calculexpr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.eti.hussamaismail.calculexpr.domain.Bracket;
import br.eti.hussamaismail.calculexpr.domain.Function;
import br.eti.hussamaismail.calculexpr.domain.Number;
import br.eti.hussamaismail.calculexpr.domain.Operator;
import br.eti.hussamaismail.calculexpr.domain.Symbol;
import br.eti.hussamaismail.calculexpr.domain.enums.BracketType;
import br.eti.hussamaismail.calculexpr.domain.enums.FunctionType;
import br.eti.hussamaismail.calculexpr.domain.enums.OperatorType;
import br.eti.hussamaismail.calculexpr.exception.InvalidExpressionException;
import br.eti.hussamaismail.calculexpr.parse.BasicLexicalAnalyzer;
import br.eti.hussamaismail.calculexpr.parse.LexicalAnalyzer;

/**
 * This class tests some methods presents in the small lexical analyzer.
 * 
 * @author Hussama Ismail
 */
public class BasicLexicalAnalyzerTest {

  private LexicalAnalyzer lexicalAnalyzer;

  @Before
  public void prepare() {
    lexicalAnalyzer = BasicLexicalAnalyzer.getInstance();
  }

  @Test
  public void testGetTokens1() {
    final String expression = "2+3";
    final List<String> tokens = lexicalAnalyzer.getTokens(expression);
    checkTokensOfTwoOperandOperations(tokens, 2, "+", 3);
  }

  @Test
  public void testGetTokens2() {
    final String expression = "2-3";
    final List<String> tokens = lexicalAnalyzer.getTokens(expression);
    checkTokensOfTwoOperandOperations(tokens, 2, "-", 3);
  }

  @Test
  public void testGetTokens3() {
    final String expression = "2*3";
    final List<String> tokens = lexicalAnalyzer.getTokens(expression);
    checkTokensOfTwoOperandOperations(tokens, 2, "*", 3);
  }

  @Test
  public void testGetTokens4() {
    final String expression = "2/3";
    final List<String> tokens = lexicalAnalyzer.getTokens(expression);
    checkTokensOfTwoOperandOperations(tokens, 2, "/", 3);
  }

  @Test
  public void testGetTokens5() {
    final String expression = "1-1";
    final List<String> tokens = lexicalAnalyzer.getTokens(expression);
    checkTokensOfTwoOperandOperations(tokens, 1, "-", 1);
  }

  @Test
  public void testGetTokens7() {
    final String expression = "1--1";
    final List<String> tokens = lexicalAnalyzer.getTokens(expression);
    checkTokensOfTwoOperandOperations(tokens, 1, "-", -1);
  }

  @Test
  public void testGetTokens8() {
    final String expression = "10-0";
    final List<String> tokens = lexicalAnalyzer.getTokens(expression);
    checkTokensOfTwoOperandOperations(tokens, 10, "-", 0);
  }

  @Test
  public void testGetTokens9() {
    final String expression = "(2+(3*2)*sin(10))";
    final List<String> tokens = lexicalAnalyzer.getTokens(expression);
    assertEquals(tokens.size(), 14);
    assertEquals("(", tokens.get(0));
    assertEquals("2.0", tokens.get(1));
    assertEquals("+", tokens.get(2));
    assertEquals("(", tokens.get(3));
    assertEquals("3.0", tokens.get(4));
    assertEquals("*", tokens.get(5));
    assertEquals("2.0", tokens.get(6));
    assertEquals(")", tokens.get(7));
    assertEquals("*", tokens.get(8));
    assertEquals("sin", tokens.get(9));
    assertEquals("(", tokens.get(10));
    assertEquals("10.0", tokens.get(11));
    assertEquals(")", tokens.get(12));
    assertEquals(")", tokens.get(13));
  }

  @Test
  public void testGetSymbols1() {
    final String expression = "2+3";
    final List<Symbol> symbols = lexicalAnalyzer.getSymbols(expression);
    checkSymbolsOfTwoOperandOperations(symbols, 2, OperatorType.ADDITION, 3);
  }

  @Test
  public void testGetSymbols2() {
    final String expression = "2-3";
    final List<Symbol> symbols = lexicalAnalyzer.getSymbols(expression);
    checkSymbolsOfTwoOperandOperations(symbols, 2, OperatorType.SUBTRACTION, 3);
  }

  @Test
  public void testGetSymbols3() {
    final String expression = "2*3";
    final List<Symbol> symbols = lexicalAnalyzer.getSymbols(expression);
    checkSymbolsOfTwoOperandOperations(symbols, 2, OperatorType.MULTIPLICATION, 3);
  }

  @Test
  public void testGetSymbols4() {
    final String expression = "2/3";
    final List<Symbol> symbols = lexicalAnalyzer.getSymbols(expression);
    checkSymbolsOfTwoOperandOperations(symbols, 2, OperatorType.DIVISION, 3);
  }

  @Test
  public void testGetSymbols5() {
    final String expression = "sqrt(4)";
    final List<Symbol> symbols = lexicalAnalyzer.getSymbols(expression);
    checkSymbolsOfFunctionCalls(symbols, FunctionType.SQRT, 4);
  }

  @Test
  public void testGetSymbols6() {
    final String expression = "cos(2)";
    final List<Symbol> symbols = lexicalAnalyzer.getSymbols(expression);
    checkSymbolsOfFunctionCalls(symbols, FunctionType.COS, 2);
  }

  @Test
  public void testGetSymbols7() {
    final String expression = "log(4)";
    final List<Symbol> symbols = lexicalAnalyzer.getSymbols(expression);
    checkSymbolsOfFunctionCalls(symbols, FunctionType.LOG, 4);
  }

  @Test
  public void testGetSymbols8() {
    final String expression = "sin(1)";
    final List<Symbol> symbols = lexicalAnalyzer.getSymbols(expression);
    checkSymbolsOfFunctionCalls(symbols, FunctionType.SIN, 1);
  }

  @Test
  public void testGetSymbols9() {
    final String expression = "(2+(3*2)*sin(10))";
    final List<Symbol> symbols = lexicalAnalyzer.getSymbols(expression);
    assertEquals(symbols.size(), 14);
    assertTrue(symbols.get(0) instanceof Bracket);
    assertEquals(BracketType.PARENTHESES_START, ((Bracket) symbols.get(0)).getType());
    assertTrue(symbols.get(1) instanceof Number);
    assertEquals(2.0, ((Number) symbols.get(1)).getValue(), 0.1);
    assertTrue(symbols.get(2) instanceof Operator);
    assertEquals(OperatorType.ADDITION, ((Operator) symbols.get(2)).getType());
    assertTrue(symbols.get(3) instanceof Bracket);
    assertEquals(BracketType.PARENTHESES_START, ((Bracket) symbols.get(3)).getType());
    assertTrue(symbols.get(4) instanceof Number);
    assertEquals(3.0, ((Number) symbols.get(4)).getValue(), 0.1);
    assertTrue(symbols.get(5) instanceof Operator);
    assertEquals(OperatorType.MULTIPLICATION, ((Operator) symbols.get(5)).getType());
    assertTrue(symbols.get(6) instanceof Number);
    assertEquals(2.0, ((Number) symbols.get(6)).getValue(), 0.1);
    assertTrue(symbols.get(7) instanceof Bracket);
    assertEquals(BracketType.PARENTHESES_END, ((Bracket) symbols.get(7)).getType());
    assertTrue(symbols.get(8) instanceof Operator);
    assertEquals(OperatorType.MULTIPLICATION, ((Operator) symbols.get(8)).getType());
    assertTrue(symbols.get(9) instanceof Function);
    assertEquals(FunctionType.SIN, ((Function) symbols.get(9)).getType());
    assertTrue(symbols.get(10) instanceof Bracket);
    assertEquals(BracketType.PARENTHESES_START, ((Bracket) symbols.get(10)).getType());
    assertTrue(symbols.get(11) instanceof Number);
    assertEquals(10.0, ((Number) symbols.get(11)).getValue(), 0.1);
    assertTrue(symbols.get(12) instanceof Bracket);
    assertEquals(BracketType.PARENTHESES_END, ((Bracket) symbols.get(12)).getType());
    assertTrue(symbols.get(13) instanceof Bracket);
    assertEquals(BracketType.PARENTHESES_END, ((Bracket) symbols.get(13)).getType());
  }
  
  @Test(expected=InvalidExpressionException.class)
  public void testGetSymbols10() {
    final String expression = "#@";
    lexicalAnalyzer.getSymbols(expression);    
  }

  private void checkTokensOfTwoOperandOperations(final List<String> tokens, final double lhs,
      final String operation, final double rhs) {
    assertEquals(tokens.size(), 3);
    assertEquals(String.valueOf(lhs), tokens.get(0));
    assertEquals(operation, tokens.get(1));
    assertEquals(String.valueOf(rhs), tokens.get(2));
  }

  private void checkSymbolsOfTwoOperandOperations(final List<Symbol> symbols, final double lhs,
      final OperatorType operatorType, final double rhs) {
    assertEquals(symbols.size(), 3);
    assertTrue(symbols.get(0) instanceof Number);
    assertEquals(2.0, ((Number) symbols.get(0)).getValue(), 0.1);
    assertTrue(symbols.get(1) instanceof Operator);
    assertEquals(operatorType, ((Operator) symbols.get(1)).getType());
    assertTrue(symbols.get(2) instanceof Number);
    assertEquals(3.0, ((Number) symbols.get(2)).getValue(), 0.1);
  }

  private void checkSymbolsOfFunctionCalls(final List<Symbol> symbols,
      final FunctionType functionType, final double param) {
    assertEquals(symbols.size(), 4);
    assertTrue(symbols.get(0) instanceof Function);
    assertEquals(functionType, ((Function) symbols.get(0)).getType());
    assertTrue(symbols.get(1) instanceof Bracket);
    assertEquals(BracketType.PARENTHESES_START, ((Bracket) symbols.get(1)).getType());
    assertTrue(symbols.get(2) instanceof Number);
    assertEquals(param, ((Number) symbols.get(2)).getValue(), 0.1);
    assertTrue(symbols.get(3) instanceof Bracket);
    assertEquals(BracketType.PARENTHESES_END, ((Bracket) symbols.get(3)).getType());
  }

}
