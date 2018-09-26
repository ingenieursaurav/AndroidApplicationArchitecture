package com.greenzhabs.arc.ui.carousal.mvp;

import com.greenzhabs.arc.base.BasePresenter;
import com.greenzhabs.arc.base.BaseView;

/** Saurav on 06-04-2018. */
public interface CarousalContract {
  interface View extends BaseView {
    void init();

    void setUpToolbar();
  }

  interface Presenter extends BasePresenter<View> {}
}
