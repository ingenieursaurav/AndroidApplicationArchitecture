package com.greenzhabs.arc.util.parser;

public class LongParser implements Parser<Long> {

  @Override
  public Long getValue(String val) {
    try {
      return Long.parseLong(val);
    } catch (NumberFormatException | NullPointerException ignored) {
    }
    return 0L;
  }
}
