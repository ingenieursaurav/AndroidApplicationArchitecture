package com.greenzhabs.arc.ui.post.mvp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.greenzhabs.arc.api.response.ApiError;
import com.greenzhabs.arc.api.response.callback.SimpleApiResponseListener;
import com.greenzhabs.arc.base.PresenterImpl;
import com.greenzhabs.arc.base.exception.ViewNotAttachedException;
import com.greenzhabs.arc.injection.scope.ScopeContext;
import com.greenzhabs.arc.ui.post.Post;
import com.greenzhabs.arc.ui.post.delete.PostDeleteDialog;
import com.greenzhabs.arc.util.ProgressUtil;
import com.greenzhabs.arc.util.Util;

import java.util.List;

import javax.inject.Inject;

/** Saurav on 30-10-2017. */
public class PostImpl extends PresenterImpl<PostContract.View> implements PostContract.Presenter {

  private Context mContext;
  private PostContract.Repository mPostRepository;
  private ProgressUtil mProgress;
  private Util mUtil;

  @Inject
  PostImpl(
      @ScopeContext Context context,
      PostContract.Repository postRepository,
      ProgressUtil progress,
      Util util) {
    mContext = context;
    mPostRepository = postRepository;
    mProgress = progress;
    mUtil = util;
  }

  @Override
  public void attach(PostContract.View view) {
    super.attach(view);
    view().setUpToolbar();
    view().init();
    getPosts();
  }

  @Override
  public void message(int message) {
    mUtil.toast(mContext, message);
  }

  @Override
  public void message(String message) {
    mUtil.toast(mContext, message);
  }

  @Override
  public void getPosts() {
    mProgress.showProgress();
    mPostRepository.getPosts(
        new SimpleApiResponseListener<List<Post>>() {
          @Override
          public void success(List<Post> list) {
            try {
              mProgress.dismissProgress();
              view().updateView(list);
            } catch (ViewNotAttachedException e) {
              e.printStackTrace();
            }
          }

          @Override
          public void authFailure() {
            super.authFailure();
            // TODO Do Authorization
          }

          @Override
          public void error(ApiError error) {
            mProgress.dismissProgress();
            message(error.getReason());
          }
        });
  }

  @Override
  public void showPostDeleteDialog() {
    PostDeleteDialog deleteDialog = PostDeleteDialog.newInstance();
    deleteDialog.show(
        ((AppCompatActivity) mContext).getSupportFragmentManager(), PostDeleteDialog.TAG);
  }

  @Override
  public void detach() {
    mPostRepository.cancel();
    super.detach();
  }
}
