package com.greenzhabs.arc.ui.post.mvp;

import com.greenzhabs.arc.api.request.ApiHandler;
import com.greenzhabs.arc.api.response.ApiError;
import com.greenzhabs.arc.api.response.ApiResponse;
import com.greenzhabs.arc.api.response.callback.ApiResponseListener;
import com.greenzhabs.arc.base.RepositoryImpl;
import com.greenzhabs.arc.db.DbHelper;
import com.greenzhabs.arc.ui.post.Post;

import java.util.List;

import retrofit2.Call;

/** Saurav on 30-10-2017. */
public class PostRepository extends RepositoryImpl<PostContract.Presenter>
    implements PostContract.Repository {

  private ApiHandler apiHandler;
  private DbHelper dbHelper;
  private Call<ApiResponse<Post>> mCall;

  public PostRepository(ApiHandler apiHandler, DbHelper dbHelper) {
    this.apiHandler = apiHandler;
    this.dbHelper = dbHelper;
  }

  @Override
  public void getPosts(final ApiResponseListener<List<Post>> listener) {
    mCall =
        apiHandler.getPosts(
            new ApiResponseListener<List<Post>>() {
              @Override
              public void success(List<Post> list) {
                dbHelper.insertPost(list);
                listener.success(dbHelper.getPosts());
              }

              @Override
              public void authFailure() {
                listener.authFailure();
              }

              @Override
              public void error(ApiError error) {
                listener.success(dbHelper.getPosts());
                listener.error(error);
              }
            });
  }

  @Override
  public void cancel() {
    if (mCall != null) mCall.cancel();
  }
}
