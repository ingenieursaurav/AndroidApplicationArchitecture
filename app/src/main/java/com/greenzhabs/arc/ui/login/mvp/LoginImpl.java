package com.greenzhabs.arc.ui.login.mvp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Patterns;

import com.greenzhabs.arc.base.PresenterImpl;
import com.greenzhabs.arc.base.exception.ViewNotAttachedException;
import com.greenzhabs.arc.injection.scope.ScopeContext;
import com.greenzhabs.arc.util.Logger;
import com.greenzhabs.arc.util.ProgressUtil;
import com.greenzhabs.arc.util.Util;

import javax.inject.Inject;

/** Saurav on 20-11-2017. */
public class LoginImpl extends PresenterImpl<LoginContract.View>
    implements LoginContract.Presenter {

  private static final String TAG = LoginImpl.class.getSimpleName();
  private Context mContext;
  private LoginContract.Repository mRepository;
  private ProgressUtil mProgress;
  private Util mUtil;

  @Inject
  LoginImpl(
      @ScopeContext Context mContext,
      LoginContract.Repository mRepository,
      ProgressUtil mProgress,
      Util mUtil) {
    this.mContext = mContext;
    this.mRepository = mRepository;
    this.mProgress = mProgress;
    this.mUtil = mUtil;
  }

  @Override
  public void login(@NonNull String userName, @NonNull String password) {
    if (verify(userName, password)) {
      if (mRepository.login(userName, password))
        try {
          view().gotoPostPage();
        } catch (ViewNotAttachedException e) {
          Logger.tag(TAG).e(e.getMessage());
        }
      else message("Invalid username/password");
    }
  }

  @Override
  public boolean verify(@NonNull String userName, @NonNull String password) {
    if (!Patterns.EMAIL_ADDRESS.matcher(userName).matches()) {
      message("Enter valid email address");
      return false;
    }
    if (!mUtil.isLegalPassword(password)) {
      message("Enter valid password");
      return false;
    }
    return true;
  }

  @Override
  public void message(int message) {
    mUtil.toast(mContext, message);
  }

  @Override
  public void message(String message) {
    mUtil.toast(mContext, message);
  }
}
