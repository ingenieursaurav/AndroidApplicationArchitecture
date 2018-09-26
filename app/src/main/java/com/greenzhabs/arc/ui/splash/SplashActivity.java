package com.greenzhabs.arc.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import com.greenzhabs.arc.R;
import com.greenzhabs.arc.base.BaseActivity;
import com.greenzhabs.arc.injection.component.ActivityComponent;
import com.greenzhabs.arc.ui.login.LoginActivity;
import com.greenzhabs.arc.ui.splash.mvp.SplashContract;

public class SplashActivity extends BaseActivity<SplashContract.Presenter>
    implements SplashContract.View {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    getPresenter().attach(this);
  }

  @Override
  protected void inject(ActivityComponent component) {
    component.inject(this);
  }

  @Override
  public void gotoPostPage() {
    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }
}
