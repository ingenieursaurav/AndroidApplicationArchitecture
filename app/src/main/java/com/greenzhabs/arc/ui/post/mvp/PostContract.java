package com.greenzhabs.arc.ui.post.mvp;

import com.greenzhabs.arc.api.response.callback.ApiResponseListener;
import com.greenzhabs.arc.base.BasePresenter;
import com.greenzhabs.arc.base.BaseRepository;
import com.greenzhabs.arc.base.BaseView;
import com.greenzhabs.arc.ui.post.Post;

import java.util.List;

/** Saurav on 30-10-2017. */
public interface PostContract {

  interface View extends BaseView {

    void init();

    void setUpToolbar();

    void updateView(List<Post> list);
  }

  interface Presenter extends BasePresenter<View> {
    void getPosts();

    void showPostDeleteDialog();
  }

  interface Repository extends BaseRepository<Presenter> {
    void getPosts(ApiResponseListener<List<Post>> listener);
  }
}
