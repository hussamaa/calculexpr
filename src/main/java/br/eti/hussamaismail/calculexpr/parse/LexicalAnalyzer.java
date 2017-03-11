package br.eti.hussamaismail.calculexpr.parse;

import java.io.IOException;
import java.util.List;

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
  List<String> getTokens(final String expression);
  
}
