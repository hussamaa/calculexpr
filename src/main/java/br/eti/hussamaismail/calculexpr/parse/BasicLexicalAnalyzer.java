package br.eti.hussamaismail.calculexpr.parse;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

/**
 * This class contains a simple implementation of a lexical analyzer used by the
 * ExpressionEvaluator.
 * 
 * @author Hussama Ismail
 */
public class BasicLexicalAnalyzer implements LexicalAnalyzer {

  private static BasicLexicalAnalyzer instance;

  private BasicLexicalAnalyzer() {}

  private static final char DIVISION_SYMBOL = '/';

  /**
   * Returns a singleton of BasicLexicalAnalyzer.
   * @return
   */
  public static BasicLexicalAnalyzer getInstance() {
    if (instance == null) {
      instance = new BasicLexicalAnalyzer();
    }
    return instance;
  }

  /** {@inheritDoc}} */
  public List<String> getTokens(final String expression) {
    final List<String> tokens = new LinkedList<>();
    final StringReader stringReader = new StringReader(expression);
    final StreamTokenizer tokenizer = new StreamTokenizer(stringReader);
    tokenizer.ordinaryChar(DIVISION_SYMBOL);
    try {
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
    } catch (IOException exp) {
      throw new RuntimeException(exp);
    } finally {
      stringReader.close();
    }
    return tokens;
  }

}
