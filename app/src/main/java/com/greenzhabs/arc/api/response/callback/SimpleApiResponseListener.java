package com.greenzhabs.arc.api.response.callback;

import com.greenzhabs.arc.api.response.ApiError;

/** Saurav on 31-10-2017. */
public abstract class SimpleApiResponseListener<T> implements ApiResponseListener<T> {
  @Override
  public abstract void success(T t);

  @Override
  public void authFailure() {}

  @Override
  public abstract void error(ApiError error);
}
