package br.eti.hussamaismail.calculexpr.domain;

import br.eti.hussamaismail.calculexpr.domain.enums.FunctionType;

/**
 * This class represents the functions.
 * 
 * @author Hussama Ismail
 */
public class Function implements Symbol {

  private FunctionType type;

  public Function(final FunctionType type) {
    super();
    this.type = type;
  }

  public FunctionType getType() {
    return type;
  }

  public void setType(final FunctionType type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Function [type=" + type + "]";
  }

}
