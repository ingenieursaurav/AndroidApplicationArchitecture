package com.greenzhabs.arc.ui.post.delete.mvp;

import android.content.Context;

import com.greenzhabs.arc.base.PresenterImpl;
import com.greenzhabs.arc.injection.scope.ScopeContext;

import javax.inject.Inject;

/** Saurav on 02-11-2017. */
public class PostDeleteImpl extends PresenterImpl<PostDeleteContract.View>
    implements PostDeleteContract.Presenter {

  private Context mContext;

  @Inject
  PostDeleteImpl(@ScopeContext Context mContext) {
    this.mContext = mContext;
  }

  @Override
  public void message(int message) {}

  @Override
  public void message(String message) {}
}
