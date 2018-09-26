package com.greenzhabs.arc.ui.comment.mvp;

import com.greenzhabs.arc.api.request.ApiHandler;
import com.greenzhabs.arc.api.response.ApiResponse;
import com.greenzhabs.arc.api.response.callback.ApiResponseListener;
import com.greenzhabs.arc.base.RepositoryImpl;
import com.greenzhabs.arc.ui.comment.Comment;

import java.util.List;

import retrofit2.Call;

/** Saurav on 30-10-2017. */
public class CommentRepository extends RepositoryImpl<CommentContract.Presenter>
    implements CommentContract.Repository {

  private ApiHandler apiHandler;
  private Call<ApiResponse<Comment>> mCall;

  public CommentRepository(ApiHandler apiHandler) {
    this.apiHandler = apiHandler;
  }

  @Override
  public void getComments(String postId, final ApiResponseListener<List<Comment>> listener) {
    mCall = apiHandler.getComments(postId, listener);
  }

  @Override
  public void cancel() {
    if (mCall != null) mCall.cancel();
  }
}
