package br.eti.hussamaismail.calculexpr.parse;

import java.io.IOException;
import java.util.List;

import br.eti.hussamaismail.calculexpr.domain.Token;

/**
 * This interface represents a lexical analyzer. In particular, it is responsible for parsing an
 * expression and retrieve a set of tokens.
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
  List<Token> getTokens(final String expression);

}
