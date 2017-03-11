package br.eti.hussamaismail.calculexpr.domain;

import br.eti.hussamaismail.calculexpr.domain.enums.BracketType;

/**
 * This class represents the brackets.
 * 
 * @author Hussama Ismail
 */
public class Bracket implements Symbol {

  private BracketType type;

  public Bracket(final BracketType type) {
    super();
    this.type = type;
  }

  public BracketType getType() {
    return type;
  }

  public void setType(final BracketType type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Bracket [type=" + type + "]";
  }
 
}
