package com.greenzhabs.arc.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.WindowManager;

import com.greenzhabs.arc.R;

/** Saurav on 30-10-2017. */
public class ProgressUtil {

  private ProgressDialog dialog;

  public ProgressUtil(Context context) {
    dialog = new ProgressDialog(context);
    dialog.setCancelable(false);
    dialog.setMessage(context.getString(R.string.loading));
    dialog.setIndeterminate(true);
  }

  public void setMessage(@NonNull String message) {
    if (dialog != null) dialog.setMessage(message);
  }

  public void resetMessage(@NonNull Context context) {
    if (dialog != null) dialog.setMessage(context.getString(R.string.loading));
  }

  public void showProgress() {
    try {
      if (dialog != null && !dialog.isShowing()) dialog.show();
    } catch (WindowManager.BadTokenException | IllegalArgumentException e) {
      e.printStackTrace();
    }
  }

  public void dismissProgress() {
    try {
      if (dialog != null && dialog.isShowing()) dialog.dismiss();
    } catch (WindowManager.BadTokenException | IllegalArgumentException e) {
      e.printStackTrace();
    }
  }

  public void dismissProgress(Context context) {
    resetMessage(context);
    dismissProgress();
  }
}
