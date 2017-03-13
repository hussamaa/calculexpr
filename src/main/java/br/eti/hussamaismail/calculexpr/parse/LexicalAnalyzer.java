package br.eti.hussamaismail.calculexpr.parse;

import java.io.IOException;
import java.util.List;

import br.eti.hussamaismail.calculexpr.domain.Symbol;

/**
 * This interface represents a lexical analyzer. In particular, this component is responsible for
 * parsing an expression and retrieving a set of tokens.
 * 
 * @author Hussama Ismail
 */
public interface LexicalAnalyzer {

  /**
   * Retrieve all tokens of an expression.
   * 
   * @param expression
   * @return
   * @throws IOException
   */
  List<String> getTokens(String expression);

  /**
   * Generate a list of Symbols based on an expression.
   * 
   * @param tokens
   * @return
   */
  List<Symbol> getSymbols(String expression);

}
