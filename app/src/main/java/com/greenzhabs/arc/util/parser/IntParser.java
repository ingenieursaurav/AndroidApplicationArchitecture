package com.greenzhabs.arc.util.parser;

public class IntParser implements Parser<Integer> {

  @Override
  public Integer getValue(String val) {
    try {
      return Integer.parseInt(val);
    } catch (NumberFormatException | NullPointerException ignored) {
    }
    return 0;
  }
}
