package com.greenzhabs.arc.util.formatter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/** Saurav on 30-10-2017. */
public class DateFormatter implements Formatter {

  private static final String TAG = DateFormatter.class.getSimpleName();
  private SimpleDateFormat sdf;

  public DateFormatter() {
    this(null);
  }

  public DateFormatter(@Nullable String timeZoneId) {
    this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    if (timeZoneId != null) sdf.setTimeZone(TimeZone.getTimeZone(timeZoneId));
  }

  @Override
  public String format(@NonNull Date date, @NonNull String pattern) {
    try {
      sdf.applyPattern(pattern);
      return sdf.format(date);
    } catch (NullPointerException ignored) {
    }
    return "";
  }

  @Override
  public Date format(@NonNull String date, @NonNull String pattern) {
    sdf.applyPattern(pattern);
    try {
      return sdf.parse(date);
    } catch (ParseException | NullPointerException ignored) {
    }
    return null;
  }

  @Override
  public String format(
      @NonNull String date, @NonNull String fromPattern, @NonNull String toPattern) {
    return format(format(date, fromPattern), toPattern);
  }
}
