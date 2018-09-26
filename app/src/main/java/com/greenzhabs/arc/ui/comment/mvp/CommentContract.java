package com.greenzhabs.arc.ui.comment.mvp;

import com.greenzhabs.arc.api.response.callback.ApiResponseListener;
import com.greenzhabs.arc.base.BasePresenter;
import com.greenzhabs.arc.base.BaseRepository;
import com.greenzhabs.arc.base.BaseView;
import com.greenzhabs.arc.ui.comment.Comment;

import java.util.List;

/** Saurav on 30-10-2017. */
public interface CommentContract {
  interface View extends BaseView {
    void init();

    void updateView(List<Comment> list);
  }

  interface Presenter extends BasePresenter<View> {
    void getComments(int postId);
  }

  interface Repository extends BaseRepository<Presenter> {
    void getComments(String postId, ApiResponseListener<List<Comment>> listener);
  }
}
