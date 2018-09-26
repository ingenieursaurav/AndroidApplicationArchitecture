package com.greenzhabs.arc.api.request;

import android.content.Context;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.greenzhabs.arc.R;
import com.greenzhabs.arc.api.response.ApiError;
import com.greenzhabs.arc.api.response.ApiResponse;
import com.greenzhabs.arc.api.response.ApiResponseListenerNotAttachedException;
import com.greenzhabs.arc.api.response.StatusCode;
import com.greenzhabs.arc.api.response.callback.ApiResponseListener;
import com.greenzhabs.arc.injection.scope.AppContext;
import com.greenzhabs.arc.ui.comment.Comment;
import com.greenzhabs.arc.ui.post.Post;
import com.greenzhabs.arc.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/** Saurav on 28-10-2017. */
public class ApiHandler {

  private Context mContext;
  private Api mApi;
  private Retrofit mRetrofit;
  private Util util;

  public ApiHandler(@AppContext Context mContext, Api mApi, Retrofit mRetrofit, Util util) {
    this.mContext = mContext;
    this.mApi = mApi;
    this.mRetrofit = mRetrofit;
    this.util = util;
  }

  private ApiError parseError(Response<?> response) {
    Converter<ResponseBody, ApiError> converter =
        mRetrofit.responseBodyConverter(ApiError.class, new Annotation[0]);

    ApiError error = null;

    try {
      if (response != null && response.errorBody() != null) {
        try {
          String errorString = response.errorBody().string();
          if (isJSONValid(errorString)) {
            error = converter.convert(response.errorBody());
          } else error = new ApiError(response.code(), errorString);
        } catch (NullPointerException e) {
          e.printStackTrace();
        }
      }

    } catch (IOException ignored) {
    }
    if (error == null) error = new ApiError(0, "");
    return error;
  }

  private boolean isJSONValid(String test) {
    try {
      new JSONObject(test);
    } catch (JSONException ex) {
      // edited, to include @Arthur's comment
      // e.g. in case JSONArray is valid as well...
      try {
        new JSONArray(test);
      } catch (JSONException ex1) {
        return false;
      }
    }
    return true;
  }

  @CheckResult
  public Call<ApiResponse<Post>> getPosts(final ApiResponseListener<List<Post>> listener) {
    return getPosts(null, listener);
  }

  @CheckResult
  public Call<ApiResponse<Post>> getPosts(
      @Nullable String start, final ApiResponseListener<List<Post>> listener) {
    if (listener == null) throw new ApiResponseListenerNotAttachedException();
    if (!util.isOnline(mContext)) {
      listener.error(
          new ApiError(StatusCode.NO_INTERNET, mContext.getString(R.string.no_internet)));
      return null;
    }

    Call<ApiResponse<Post>> call =
        TextUtils.isEmpty(start) ? mApi.getPosts() : mApi.getPosts(start);
    call.enqueue(
        new Callback<ApiResponse<Post>>() {
          @Override
          public void onResponse(
              @NonNull Call<ApiResponse<Post>> call,
              @NonNull Response<ApiResponse<Post>> response) {
            if (response.isSuccessful()) {
              ApiResponse<Post> apiResponse = response.body();
              if (apiResponse != null) {
                if (!apiResponse.isError()) {
                  if (apiResponse.getList().isEmpty())
                    listener.error(new ApiError(0, "No Data Found"));
                  else listener.success(apiResponse.getList());
                } else {
                  listener.error(apiResponse.getError());
                }
              } else {
                listener.error(new ApiError(0, "No Data Found"));
              }
            } else {
              if (response.code() == StatusCode.AUTH_ERROR) {
                listener.authFailure();
              } else {
                ApiError apiError = null;
                if (response.errorBody() != null) apiError = parseError(response);
                if (apiError == null) apiError = new ApiError(response.code(), "No Data Found");
                listener.error(apiError);
              }
            }
          }

          @Override
          public void onFailure(@NonNull Call<ApiResponse<Post>> call, Throwable t) {
            listener.error(new ApiError(0, "Please try again..."));
          }
        });
    return call;
  }

  public Call<ApiResponse<Comment>> getComments(
      String postId, final ApiResponseListener<List<Comment>> listener) {
    if (listener == null) throw new ApiResponseListenerNotAttachedException();
    if (!util.isOnline(mContext)) {
      listener.error(
          new ApiError(StatusCode.NO_INTERNET, mContext.getString(R.string.no_internet)));
      return null;
    }
    Call<ApiResponse<Comment>> call = mApi.getComments(postId);
    call.enqueue(
        new Callback<ApiResponse<Comment>>() {
          @Override
          public void onResponse(
              Call<ApiResponse<Comment>> call, Response<ApiResponse<Comment>> response) {
            if (response.isSuccessful()) {
              ApiResponse<Comment> apiResponse = response.body();
              if (apiResponse != null) {
                if (!apiResponse.isError()) {
                  listener.success(apiResponse.getList());
                } else {
                  listener.error(apiResponse.getError());
                }
              } else {
                listener.error(new ApiError(0, "No Data Found"));
              }
            } else {
              if (response.code() == StatusCode.AUTH_ERROR) {
                listener.authFailure();
              } else {
                ApiError apiError = null;
                if (response.errorBody() != null) apiError = parseError(response);
                if (apiError == null) apiError = new ApiError(response.code(), "No Data Found");
                listener.error(apiError);
              }
            }
          }

          @Override
          public void onFailure(Call<ApiResponse<Comment>> call, Throwable t) {
            listener.error(new ApiError(0, "Please try again..."));
          }
        });
    return call;
  }
}
