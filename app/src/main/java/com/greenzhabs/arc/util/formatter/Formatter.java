package com.greenzhabs.arc.util.formatter;

import java.util.Date;

/** Saurav on 30-10-2017. */
public interface Formatter {

  /**
   * Formats the given date object to the specified pattern
   *
   * @param date {@link Date} object
   * @param pattern Pattern to format(e.g, yyyy-MM-dd)
   */
  String format(Date date, String pattern);

  /**
   * Formats the given date object to the specified pattern
   *
   * @param date string date value
   * @param pattern Pattern to which the date follows(e.g, yyyy-MM-dd)
   */
  Date format(String date, String pattern);

  /**
   * Formats the given date object to the specified pattern
   *
   * @param date string date value
   * @param fromPattern Pattern to which the date follows(e.g, yyyy-MM-dd)
   * @param toPattern Pattern to format(e.g, yyyy-MM-dd)
   */
  String format(String date, String fromPattern, String toPattern);
}
