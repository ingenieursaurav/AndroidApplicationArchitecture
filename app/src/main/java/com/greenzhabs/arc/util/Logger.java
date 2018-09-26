package com.greenzhabs.arc.util;

import android.support.annotation.NonNull;
import android.util.Log;

import com.greenzhabs.arc.BuildConfig;

/**
 * Saurav on 2-11-2017.<br>
 * <br>
 * Use this class to print all the log in the application. Logs are displayed only for the Debug
 * mode.<br>
 * Usage:<br>
 * ------------<br>
 *
 * <p>Logger.tag("TAG").v("MESSAGE");
 *
 * <p>Logger.tag("TAG").d("MESSAGE");
 *
 * <p>Logger.tag("TAG").i("MESSAGE");
 *
 * <p>Logger.tag("TAG").w("MESSAGE");
 *
 * <p>Logger.tag("TAG").e("MESSAGE");
 */
public class Logger {
  private String tag;

  private Logger(String tag) {
    this.tag = tag;
  }

  /**
   * Returns new Logger instance with specified tag.
   *
   * @param tag Tag to be displayed
   */
  @NonNull
  public static Logger tag(@NonNull String tag) {
    return new Logger(tag);
  }

  /**
   * Prints message in verbose
   *
   * @param message Message to be printed
   */
  public void v(String message) {
    if (BuildConfig.DEBUG) Log.v(tag, message);
  }
  /**
   * Prints message in debug
   *
   * @param message Message to be printed
   */
  public void d(String message) {
    if (BuildConfig.DEBUG) Log.d(tag, message);
  }
  /**
   * Prints message in info
   *
   * @param message Message to be printed
   */
  public void i(String message) {
    if (BuildConfig.DEBUG) Log.i(tag, message);
  }
  /**
   * Prints message in warning
   *
   * @param message Message to be printed
   */
  public void w(String message) {
    if (BuildConfig.DEBUG) Log.w(tag, message);
  }
  /**
   * Prints message in error
   *
   * @param message Message to be printed
   */
  public void e(String message) {
    if (BuildConfig.DEBUG) Log.e(tag, message);
  }
}
