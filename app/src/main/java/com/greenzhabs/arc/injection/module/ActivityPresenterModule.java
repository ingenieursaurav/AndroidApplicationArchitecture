package com.greenzhabs.arc.injection.module;

import com.greenzhabs.arc.api.request.ApiHandler;
import com.greenzhabs.arc.db.DbHelper;
import com.greenzhabs.arc.injection.scope.ActivityScope;
import com.greenzhabs.arc.ui.carousal.mvp.CarousalContract;
import com.greenzhabs.arc.ui.carousal.mvp.CarousalPresenter;
import com.greenzhabs.arc.ui.login.mvp.LoginContract;
import com.greenzhabs.arc.ui.login.mvp.LoginImpl;
import com.greenzhabs.arc.ui.login.mvp.LoginRepository;
import com.greenzhabs.arc.ui.paged.mvp.PagedContract;
import com.greenzhabs.arc.ui.paged.mvp.PagedImpl;
import com.greenzhabs.arc.ui.paged.mvp.PagedRepository;
import com.greenzhabs.arc.ui.post.mvp.PostContract;
import com.greenzhabs.arc.ui.post.mvp.PostImpl;
import com.greenzhabs.arc.ui.post.mvp.PostRepository;
import com.greenzhabs.arc.ui.splash.mvp.SplashContract;
import com.greenzhabs.arc.ui.splash.mvp.SplashImpl;
import com.greenzhabs.arc.ui.splash.mvp.SplashRepository;

import dagger.Module;
import dagger.Provides;

/** Saurav on 30-10-2017. */
@Module
public class ActivityPresenterModule {

  @Provides
  @ActivityScope
  SplashContract.Presenter providesSplashPresenter(SplashImpl splash) {
    return splash;
  }

  @Provides
  @ActivityScope
  SplashContract.Repository providesSplashApi(ApiHandler handler, DbHelper dbHelper) {
    return new SplashRepository(handler, dbHelper);
  }

  @Provides
  @ActivityScope
  PostContract.Presenter providesPostPresenter(PostImpl post) {
    return post;
  }

  @Provides
  @ActivityScope
  PostContract.Repository providesPostApi(ApiHandler handler, DbHelper dbHelper) {
    return new PostRepository(handler, dbHelper);
  }

  @Provides
  @ActivityScope
  LoginContract.Presenter providesLoginPresenter(LoginImpl login) {
    return login;
  }

  @Provides
  @ActivityScope
  LoginContract.Repository providesLoginApi() {
    return new LoginRepository();
  }

  @Provides
  @ActivityScope
  PagedContract.Presenter providesPagedPresenter(PagedImpl login) {
    return login;
  }

  @Provides
  @ActivityScope
  PagedContract.Repository providesPagedApi(ApiHandler handler) {
    return new PagedRepository(handler);
  }

  @Provides
  @ActivityScope
  CarousalContract.Presenter providesCarousalPresenter(CarousalPresenter carousalPresenter) {
    return carousalPresenter;
  }
}
