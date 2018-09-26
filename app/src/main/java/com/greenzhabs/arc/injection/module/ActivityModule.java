package com.greenzhabs.arc.injection.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.greenzhabs.arc.injection.scope.ActivityScope;
import com.greenzhabs.arc.injection.scope.ScopeContext;
import com.greenzhabs.arc.util.ProgressUtil;

import dagger.Module;
import dagger.Provides;

/** Saurav on 28-10-2017. */
@Module
public class ActivityModule {

  private AppCompatActivity activity;

  public ActivityModule(AppCompatActivity activity) {
    this.activity = activity;
  }

  @ActivityScope
  @ScopeContext
  @Provides
  Context providesActivityContext() {
    return activity;
  }

  @Provides
  @ActivityScope
  ProgressUtil providesProgressDialog(@ScopeContext Context context) {
    return new ProgressUtil(context);
  }
}
