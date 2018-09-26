package com.greenzhabs.arc.ui.login.mvp;

import android.support.annotation.NonNull;

import com.greenzhabs.arc.base.RepositoryImpl;

/** Saurav on 20-11-2017. */
public class LoginRepository extends RepositoryImpl<LoginContract.Presenter>
    implements LoginContract.Repository {

  @Override
  public void cancel() {}

  @Override
  public boolean login(@NonNull String userName, @NonNull String password) {
    return userName.equals("test@gmail.com") && password.equals("Test1234");
  }
}
