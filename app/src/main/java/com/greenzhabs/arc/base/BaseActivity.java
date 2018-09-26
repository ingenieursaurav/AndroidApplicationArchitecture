package com.greenzhabs.arc.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.greenzhabs.arc.ArcApp;
import com.greenzhabs.arc.injection.component.ActivityComponent;
import com.greenzhabs.arc.injection.module.ActivityModule;
import com.greenzhabs.arc.injection.module.ActivityPresenterModule;

import javax.inject.Inject;

/** Saurav on 28-10-2017. */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

  @Inject P presenter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityComponent component =
        ArcApp.component()
            .getActivityComponent(new ActivityModule(this), new ActivityPresenterModule());
    inject(component);
  }

  protected abstract void inject(ActivityComponent component);

  protected P getPresenter() {
    return presenter;
  }
}
