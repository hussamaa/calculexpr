package br.eti.hussamaismail.calculexpr.domain;

/**
 * This class represents the numbers.
 * 
 * @author Hussama Ismail
 */
public class Number implements Symbol {

  private double value;

  public Number() {
    super();
  }

  public Number(final double value) {
    super();
    this.value = value;
  }

  public double getValue() {
    return value;
  }

  public void setValue(final double value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Number [value=" + value + "]";
  }
  
}
