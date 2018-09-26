package com.greenzhabs.arc;

import android.app.Application;

import com.greenzhabs.arc.injection.component.AppComponent;
import com.greenzhabs.arc.injection.component.DaggerAppComponent;
import com.greenzhabs.arc.injection.module.ApiModule;
import com.greenzhabs.arc.injection.module.AppModule;
import com.greenzhabs.arc.injection.module.CommonModule;
import com.greenzhabs.arc.injection.module.PersistenceModule;

/** saurav on 28-10-2017. */
public class ArcApp extends Application {

  private static AppComponent component;

  public static AppComponent component() {
    return component;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    component =
        DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .apiModule(new ApiModule())
            .commonModule(new CommonModule())
            .persistenceModule(new PersistenceModule())
            .build();
  }
}
