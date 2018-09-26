package com.greenzhabs.arc.injection.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.greenzhabs.arc.ArcApp;
import com.greenzhabs.arc.api.request.ApiHandler;
import com.greenzhabs.arc.injection.scope.AppContext;
import com.greenzhabs.arc.injection.scope.ScopeContext;
import com.greenzhabs.arc.ui.paged.PagedAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/** Saurav on 28-10-2017. */
@Module
public class AppModule {

  private ArcApp arcApp;

  public AppModule(ArcApp arcApp) {
    this.arcApp = arcApp;
  }

  @Provides
  @AppContext
  ArcApp providesApplication() {
    return arcApp;
  }

  @Provides
  @NonNull
  @Singleton
  @AppContext
  Context providesGlobalContext() {
    return arcApp.getApplicationContext();
  }
}
