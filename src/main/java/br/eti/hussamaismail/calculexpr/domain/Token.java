package br.eti.hussamaismail.calculexpr.domain;

/**
 * This class represents a token object, which is composed by a value and a type.
 * 
 * @author Hussama Ismail
 */
public class Token {

  private Object value;
  private TokenType type;

  public Object getValue() {
    return value;
  }

  public TokenType getType() {
    return type;
  }

  public void setValue(final Object value) {
    this.value = value;
  }

  public void setType(final TokenType type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "[\"" + value + "\", " + type + "]";
  }
  
}
