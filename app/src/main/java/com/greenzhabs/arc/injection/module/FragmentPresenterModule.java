package com.greenzhabs.arc.injection.module;

import com.greenzhabs.arc.api.request.ApiHandler;
import com.greenzhabs.arc.injection.scope.FragmentScope;
import com.greenzhabs.arc.ui.comment.mvp.CommentContract;
import com.greenzhabs.arc.ui.comment.mvp.CommentImpl;
import com.greenzhabs.arc.ui.comment.mvp.CommentRepository;
import com.greenzhabs.arc.ui.post.delete.mvp.PostDeleteContract;
import com.greenzhabs.arc.ui.post.delete.mvp.PostDeleteImpl;

import dagger.Module;
import dagger.Provides;

/** Saurav on 30-10-2017. */
@Module
public class FragmentPresenterModule {

  @Provides
  @FragmentScope
  CommentContract.Presenter providesCommentPresenter(CommentImpl comment) {
    return comment;
  }

  @Provides
  @FragmentScope
  CommentContract.Repository providesCommentApi(ApiHandler apiHandler) {
    return new CommentRepository(apiHandler);
  }

  @Provides
  @FragmentScope
  PostDeleteContract.Presenter providesPostDeletePresenter(PostDeleteImpl postDelete) {
    return postDelete;
  }
}
