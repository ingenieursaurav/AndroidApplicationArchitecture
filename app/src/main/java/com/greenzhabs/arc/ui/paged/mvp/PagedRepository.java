package com.greenzhabs.arc.ui.paged.mvp;

import com.greenzhabs.arc.api.request.ApiHandler;
import com.greenzhabs.arc.api.response.ApiError;
import com.greenzhabs.arc.api.response.ApiResponse;
import com.greenzhabs.arc.api.response.callback.ApiResponseListener;
import com.greenzhabs.arc.base.RepositoryImpl;
import com.greenzhabs.arc.ui.post.Post;

import java.util.List;

import retrofit2.Call;

/** Saurav on 30-10-2017. */
public class PagedRepository extends RepositoryImpl<PagedContract.Presenter>
    implements PagedContract.Repository {

  private ApiHandler apiHandler;
  private Call<ApiResponse<Post>> mCall;

  public PagedRepository(ApiHandler apiHandler) {
    this.apiHandler = apiHandler;
  }

  @Override
  public void getPosts(final ApiResponseListener<List<Post>> listener) {
    mCall =
        apiHandler.getPosts(
            String.valueOf(0),
            new ApiResponseListener<List<Post>>() {
              @Override
              public void success(List<Post> list) {
                listener.success(list);
              }

              @Override
              public void authFailure() {
                listener.authFailure();
              }

              @Override
              public void error(ApiError error) {
                listener.error(error);
              }
            });
  }

  @Override
  public void cancel() {
    if (mCall != null) mCall.cancel();
  }
}
