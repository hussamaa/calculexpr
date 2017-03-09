package br.eti.hussamaismail.calculexpr.parse;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods to help the expression evaluator.
 * 
 * @author Hussama Ismail
 */
public class ExpressionParser {

  private static ExpressionParser instance;

  private ExpressionParser() {}

  public static ExpressionParser getInstance() {
    if (instance == null) {
      instance = new ExpressionParser();
    }
    return instance;
  }

  /**
   * Retrieve all tokens of an expression.
   * 
   * @param expression
   * @return
   * @throws IOException
   */
  public List<String> getTokens(final String expression) throws IOException {
    final List<String> tokens = new ArrayList<>();
    final StringReader stringReader = new StringReader(expression);
    final StreamTokenizer tokenizer = new StreamTokenizer(stringReader);
    while (StreamTokenizer.TT_EOF != tokenizer.nextToken()) {
      switch (tokenizer.ttype) {
        case StreamTokenizer.TT_WORD:
          tokens.add(tokenizer.sval);
          break;
        case StreamTokenizer.TT_NUMBER:
          tokens.add(String.valueOf(tokenizer.nval));
          break;
        default:
          tokens.add(String.valueOf((char) tokenizer.ttype));
      }
    }
    stringReader.close();
    return tokens;
  }
}
