package br.eti.hussamaismail.calculexpr.parse;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import br.eti.hussamaismail.calculexpr.domain.Symbol;
import br.eti.hussamaismail.calculexpr.domain.SymbolFactory;

/**
 * This class contains a simple implementation of a lexical analyzer used by the
 * ExpressionEvaluator.
 * 
 * @author Hussama Ismail
 */
public class BasicLexicalAnalyzer implements LexicalAnalyzer {

  private static BasicLexicalAnalyzer instance;
  private SymbolFactory symbolFactory;

  private static final char DIVISION_SYMBOL = '/';

  private BasicLexicalAnalyzer() {
    this.symbolFactory = SymbolFactory.getInstance();
  }

  /**
   * Returns a singleton of BasicLexicalAnalyzer.
   * 
   * @return
   */
  public static BasicLexicalAnalyzer getInstance() {
    if (instance == null) {
      instance = new BasicLexicalAnalyzer();
    }
    return instance;
  }

  /** {@inheritDoc} */
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

  /** {@inheritDoc} */
  public List<Symbol> getSymbols(final String expression) {
    final List<String> tokens = getTokens(expression);
    final List<Symbol> symbols = new LinkedList<>();
    for (final String token : tokens) {
      final Symbol symbol = symbolFactory.createSymbol(token);
      symbols.add(symbol);
    }
    return symbols;
  }

}
