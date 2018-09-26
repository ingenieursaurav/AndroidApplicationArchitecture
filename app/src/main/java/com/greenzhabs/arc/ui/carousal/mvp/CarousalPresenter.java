package com.greenzhabs.arc.ui.carousal.mvp;

import com.greenzhabs.arc.base.PresenterImpl;

import javax.inject.Inject;

/** Saurav on 06-04-2018. */
public class CarousalPresenter extends PresenterImpl<CarousalContract.View>
    implements CarousalContract.Presenter {

  @Inject
  CarousalPresenter() {}

  @Override
  public void attach(CarousalContract.View view) {
    super.attach(view);
    if (isViewAttached()) {
      view().init();
      view().setUpToolbar();
    }
  }

  @Override
  public void message(int message) {}

  @Override
  public void message(String message) {}
}
