package com.greenzhabs.arc.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.greenzhabs.arc.util.formatter.DateFormatter;
import com.greenzhabs.arc.util.parser.DoubleParser;
import com.greenzhabs.arc.util.parser.FloatParser;
import com.greenzhabs.arc.util.parser.IntParser;
import com.greenzhabs.arc.util.parser.LongParser;

import java.util.Date;

/** Saurav on 30-10-2017. */
public class Util {

  /**
   * Checks whether device is connected to network or not
   *
   * @param context Application context
   * @return true-> if connected, false-> if not connected
   */
  public boolean isOnline(Context context) {
    if (context == null) return false;
    ConnectivityManager cm =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    if (cm == null) return false;
    NetworkInfo netInfo = cm.getActiveNetworkInfo();
    return netInfo != null && netInfo.isConnected();
  }

  /**
   * Shows Toast message
   *
   * @param context Application Context
   * @param message Message to display
   */
  public void toast(@NonNull Context context, @NonNull String message) {
    ToastUtil.toast(context, message);
  }

  /**
   * Shows Toast message
   *
   * @param context Application Context
   * @param message Message to display
   */
  public void toast(@NonNull Context context, @StringRes int message) {
    ToastUtil.toast(context, message);
  }

  /** Returns DateFormatter object with default TimeZone */
  public DateFormatter getDateFormatter() {
    return getDateFormatter(null);
  }

  /**
   * Returns DateFormatter object with specified TimeZone
   *
   * @param timeZoneId Timezone Id
   */
  public DateFormatter getDateFormatter(@Nullable String timeZoneId) {
    return new DateFormatter(timeZoneId);
  }

  /** {@link DateFormatter#format(Date, String)} */
  public String format(@NonNull Date date, @NonNull String pattern) {
    return getDateFormatter().format(date, pattern);
  }

  /** {@link DateFormatter#format(String, String)} */
  public Date format(@NonNull String date, @NonNull String pattern) {
    return getDateFormatter().format(date, pattern);
  }

  /** {@link DateFormatter#format(String, String, String)} */
  public String format(
      @NonNull String date, @NonNull String fromPattern, @NonNull String toPattern) {
    return getDateFormatter().format(date, fromPattern, toPattern);
  }

  /**
   * {@link DateFormatter#format(Date, String)}
   *
   * @param timeZoneId Timezone id to be used
   */
  public String formatTz(@NonNull String timeZoneId, @NonNull Date date, @NonNull String pattern) {
    return getDateFormatter(timeZoneId).format(date, pattern);
  }

  /**
   * {@link DateFormatter#format(String, String)}
   *
   * @param timeZoneId Timezone id to be used
   */
  public Date formatTz(@NonNull String timeZoneId, @NonNull String date, @NonNull String pattern) {
    return getDateFormatter(timeZoneId).format(date, pattern);
  }

  /**
   * {@link DateFormatter#format(String, String, String)}
   *
   * @param timeZoneId Timezone id to be used
   */
  public String formatTz(
      @NonNull String timeZoneId,
      @NonNull String date,
      @NonNull String fromPattern,
      @NonNull String toPattern) {
    return getDateFormatter(timeZoneId).format(date, fromPattern, toPattern);
  }

  /**
   * Converts String to Int
   *
   * @param val String value to be parsed.
   */
  public int toInt(@NonNull String val) {
    return new IntParser().getValue(val);
  }

  /**
   * Converts String to Double
   *
   * @param val String value to be parsed.
   */
  public double toDouble(@NonNull String val) {
    return new DoubleParser().getValue(val);
  }

  /**
   * Converts String to Long
   *
   * @param val String value to be parsed.
   */
  public long toLong(@NonNull String val) {
    return new LongParser().getValue(val);
  }

  /**
   * Converts String to Float
   *
   * @param val String value to be parsed.
   */
  public float toFloat(@NonNull String val) {
    return new FloatParser().getValue(val);
  }

  /**
   * Checks whether the password is valid password or not.<br>
   *
   * <ul>
   *   <li>Password should contain at-least one Uppercase character
   *   <li>Password should contain at-least one number
   *   <li>Length must be 8<= length <17
   * </ul>
   *
   * @param pass Password given by the user
   */
  @CheckResult
  public boolean isLegalPassword(String pass) {
    try {
      return pass != null
          && pass.trim().length() != 0
          && pass.matches(".*[A-Z].*")
          && pass.matches(".*[a-z].*")
          && pass.matches(".*[0-9].*")
          && pass.length() >= 8
          && pass.length() < 17;
    } catch (NullPointerException ignored) {
    }
    return false;
  }
}
