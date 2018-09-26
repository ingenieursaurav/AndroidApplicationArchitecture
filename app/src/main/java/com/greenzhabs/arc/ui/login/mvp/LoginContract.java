package com.greenzhabs.arc.ui.login.mvp;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.greenzhabs.arc.base.BasePresenter;
import com.greenzhabs.arc.base.BaseRepository;
import com.greenzhabs.arc.base.BaseView;

/** Saurav on 20-11-2017. */
public interface LoginContract {

  interface View extends BaseView {
    void gotoPostPage();
  }

  interface Presenter extends BasePresenter<View> {
    void login(@NonNull String userName, @NonNull String password);

    @CheckResult
    boolean verify(@NonNull String userName, @NonNull String password);
  }

  interface Repository extends BaseRepository<Presenter> {
    @CheckResult
    boolean login(@NonNull String userName, @NonNull String password);
  }
}
