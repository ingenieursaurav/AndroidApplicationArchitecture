package com.greenzhabs.arc.ui.splash.mvp;

import com.greenzhabs.arc.api.request.ApiHandler;
import com.greenzhabs.arc.base.RepositoryImpl;
import com.greenzhabs.arc.db.DbHelper;

/** Saurav on 11-11-2017. */
public class SplashRepository extends RepositoryImpl<SplashContract.Presenter>
    implements SplashContract.Repository {

  private ApiHandler mApiHandler;
  private DbHelper dbHelper;

  public SplashRepository(ApiHandler mApiHandler, DbHelper dbHelper) {
    this.mApiHandler = mApiHandler;
    this.dbHelper = dbHelper;
  }

  @Override
  public void cancel() {}
}
