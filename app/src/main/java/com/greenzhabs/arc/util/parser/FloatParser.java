package com.greenzhabs.arc.util.parser;

public class FloatParser implements Parser<Float> {

  @Override
  public Float getValue(String val) {
    try {
      return Float.parseFloat(val);
    } catch (NumberFormatException | NullPointerException ignored) {
    }
    return Float.NaN;
  }
}
