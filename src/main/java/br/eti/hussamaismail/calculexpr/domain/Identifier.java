package br.eti.hussamaismail.calculexpr.domain;

/**
 * This class represents the identifiers.
 * 
 * @author Hussama Ismail
 */
public class Identifier extends Operand implements Symbol {

  private String name;
  private boolean assigned;

  public Identifier(final String name) {
    super();
    this.name = name;
  }

  public Identifier() {
    super();
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public boolean isAssigned() {
    return assigned;
  }

  public void setAssigned(final boolean assigned) {
    this.assigned = assigned;
  }

  @Override
  public String toString() {
    return "Identifier [name=" + name + ", value=" + getValue() + "]";
  }

}
