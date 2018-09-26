package com.greenzhabs.arc.ui.splash.mvp;

import android.content.Context;
import android.os.Handler;

import com.greenzhabs.arc.base.PresenterImpl;
import com.greenzhabs.arc.injection.scope.ScopeContext;
import com.greenzhabs.arc.util.Util;

import javax.inject.Inject;

/** Saurav on 11-11-2017. */
public class SplashImpl extends PresenterImpl<SplashContract.View>
    implements SplashContract.Presenter {

  private static final long SPLASH_TIMEOUT = 2000;
  private Context mContext;
  private SplashContract.Repository mRepository;
  private Util mUtil;

  @Inject
  SplashImpl(@ScopeContext Context mContext, SplashContract.Repository mRepository, Util mUtil) {
    this.mContext = mContext;
    this.mRepository = mRepository;
    this.mUtil = mUtil;
  }

  @Override
  public void attach(SplashContract.View view) {
    super.attach(view);
    startTimer();
  }

  @Override
  public void message(int message) {
    mUtil.toast(mContext, message);
  }

  @Override
  public void message(String message) {
    mUtil.toast(mContext, message);
  }

  @Override
  public void startTimer() {
    new Handler()
        .postDelayed(
            new Runnable() {
              @Override
              public void run() {
                view().gotoPostPage();
              }
            },
            SPLASH_TIMEOUT);
  }
}
