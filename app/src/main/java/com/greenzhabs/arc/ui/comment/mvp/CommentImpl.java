package com.greenzhabs.arc.ui.comment.mvp;

import android.content.Context;

import com.greenzhabs.arc.api.response.ApiError;
import com.greenzhabs.arc.api.response.callback.SimpleApiResponseListener;
import com.greenzhabs.arc.base.PresenterImpl;
import com.greenzhabs.arc.base.exception.ViewNotAttachedException;
import com.greenzhabs.arc.injection.scope.ScopeContext;
import com.greenzhabs.arc.ui.comment.Comment;
import com.greenzhabs.arc.util.ProgressUtil;
import com.greenzhabs.arc.util.Util;

import java.util.List;

import javax.inject.Inject;

/** Saurav on 30-10-2017. */
public class CommentImpl extends PresenterImpl<CommentContract.View>
    implements CommentContract.Presenter {

  private Context mContext;
  private CommentContract.Repository mCommentRepository;
  private ProgressUtil mProgress;
  private Util mUtil;

  @Inject
  CommentImpl(
      @ScopeContext Context context,
      CommentContract.Repository commentRepository,
      ProgressUtil progress,
      Util util) {
    mContext = context;
    mCommentRepository = commentRepository;
    mProgress = progress;
    mUtil = util;
  }

  @Override
  public void attach(CommentContract.View view) {
    super.attach(view);
    view().init();
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
  public void detach() {
    super.detach();
    mCommentRepository.cancel();
  }

  @Override
  public void getComments(int postId) {
    mProgress.showProgress();
    mCommentRepository.getComments(
        String.valueOf(postId),
        new SimpleApiResponseListener<List<Comment>>() {
          @Override
          public void success(List<Comment> comments) {
            try {
              mProgress.dismissProgress();
              view().updateView(comments);
            } catch (ViewNotAttachedException e) {
              e.printStackTrace();
            }
          }

          @Override
          public void error(ApiError error) {
            mProgress.dismissProgress();
            message(error.getReason());
          }
        });
  }
}
