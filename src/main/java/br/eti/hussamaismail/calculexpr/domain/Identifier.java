package br.eti.hussamaismail.calculexpr.domain;

/**
 * This class represents the identifiers.
 * 
 * @author Hussama Ismail
 */
public class Identifier implements Symbol {

  private String name;

  public Identifier(final String name) {
    super();
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

}
