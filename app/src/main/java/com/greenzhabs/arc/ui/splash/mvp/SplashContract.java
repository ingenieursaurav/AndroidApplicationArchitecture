package com.greenzhabs.arc.ui.splash.mvp;

import com.greenzhabs.arc.base.BasePresenter;
import com.greenzhabs.arc.base.BaseRepository;
import com.greenzhabs.arc.base.BaseView;

/** Saurav on 11-11-2017. */
public interface SplashContract {

  interface View extends BaseView {
    void gotoPostPage();
  }

  interface Presenter extends BasePresenter<View> {
    void startTimer();
  }

  interface Repository extends BaseRepository<Presenter> {}
}
