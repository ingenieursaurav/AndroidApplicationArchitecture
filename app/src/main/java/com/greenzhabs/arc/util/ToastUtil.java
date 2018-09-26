package com.greenzhabs.arc.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

/** Saurav on 02-11-2017. */
public class ToastUtil {

  static void toast(Context context, @NonNull String message) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
  }

  static void toast(Context context, @StringRes int message) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
  }
}
