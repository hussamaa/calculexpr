package br.eti.hussamaismail.calculexpr.domain;

import br.eti.hussamaismail.calculexpr.domain.enums.OperatorType;

/**
 * This class represents the operators.
 * 
 * @author Hussama Ismail
 */
public class Operator implements Symbol {

  private OperatorType type;
  
  public Operator(final OperatorType type) {
    super();
    this.type = type;
  }

  public OperatorType getType() {
    return type;
  }

  public void setType(final OperatorType type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Operator [type=" + type + "]";
  }
   
}