package com.greenzhabs.arc.util.parser;

public class DoubleParser implements Parser<Double> {
  @Override
  public Double getValue(String val) {
    try {
      return Double.parseDouble(val);
    } catch (NumberFormatException | NullPointerException ignored) {
    }
    return Double.NaN;
  }
}
