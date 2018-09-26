package com.greenzhabs.arc.base;

/** Saurav on 28-10-2017. */
public interface BasePresenter<BV extends BaseView> {
  void attach(BV bv);

  BV view();

  /**
   * Returns whether the view is attached to Activity/Fragment or not
   *
   * @return true -> if attached, false -> is not attached
   */
  boolean isViewAttached();

  void detach();
}
