package com.greenzhabs.arc.base;

import com.greenzhabs.arc.base.callback.ToastMessage;
import com.greenzhabs.arc.base.exception.ViewNotAttachedException;

/** Saurav on 28-10-2017. */
public abstract class PresenterImpl<BV extends BaseView>
    implements BasePresenter<BV>, ToastMessage {
  private BV mBv;

  @Override
  public void attach(BV bv) {
    mBv = bv;
  }

  @Override
  public BV view() throws ViewNotAttachedException {
    if (!isViewAttached()) {
      throw new ViewNotAttachedException();
    }
    return mBv;
  }

  @Override
  public boolean isViewAttached() {
    return mBv != null;
  }

  @Override
  public void detach() {
    mBv = null;
  }

  @Override
  public abstract void message(int message);

  @Override
  public abstract void message(String message);
}
