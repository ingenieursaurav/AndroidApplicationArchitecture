package com.greenzhabs.arc.injection.component;

import com.greenzhabs.arc.ArcApp;
import com.greenzhabs.arc.injection.module.ActivityModule;
import com.greenzhabs.arc.injection.module.ActivityPresenterModule;
import com.greenzhabs.arc.injection.module.AdapterModule;
import com.greenzhabs.arc.injection.module.ApiModule;
import com.greenzhabs.arc.injection.module.AppModule;
import com.greenzhabs.arc.injection.module.CommonModule;
import com.greenzhabs.arc.injection.module.FragmentModule;
import com.greenzhabs.arc.injection.module.FragmentPresenterModule;
import com.greenzhabs.arc.injection.module.PersistenceModule;

import javax.inject.Singleton;

import dagger.Component;

/** Saurav on 28-10-2017. */
@Component(
  modules = {
    AppModule.class,
    ApiModule.class,
    PersistenceModule.class,
    AdapterModule.class,
    CommonModule.class
  }
)
@Singleton
public interface AppComponent {

  ActivityComponent getActivityComponent(
      ActivityModule activityModule, ActivityPresenterModule activityPresenterModule);

  FragmentComponent getFragmentComponent(
      FragmentModule fragmentModule, FragmentPresenterModule fragmentPresenterModule);

  void inject(ArcApp arcApp);
}
