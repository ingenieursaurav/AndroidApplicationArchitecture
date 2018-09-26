package com.greenzhabs.arc.api.response.callback;

import com.greenzhabs.arc.api.response.ApiError;

/** Saurav on 28-10-2017. */
public interface ApiResponseListener<T> {

  void success(T t);

  void authFailure();

  void error(ApiError error);
}
