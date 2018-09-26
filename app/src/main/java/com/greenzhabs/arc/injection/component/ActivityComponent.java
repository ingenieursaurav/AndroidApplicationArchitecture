package com.greenzhabs.arc.injection.component;

import com.greenzhabs.arc.injection.module.ActivityModule;
import com.greenzhabs.arc.injection.module.ActivityPresenterModule;
import com.greenzhabs.arc.injection.scope.ActivityScope;
import com.greenzhabs.arc.ui.carousal.CarousalActivity;
import com.greenzhabs.arc.ui.login.LoginActivity;
import com.greenzhabs.arc.ui.paged.PagedActivity;
import com.greenzhabs.arc.ui.post.PostActivity;
import com.greenzhabs.arc.ui.splash.SplashActivity;

import dagger.Subcomponent;

/** Saurav on 28-10-2017. */
@ActivityScope
@Subcomponent(modules = {ActivityModule.class, ActivityPresenterModule.class})
public interface ActivityComponent {

  void inject(SplashActivity splashActivity);

  void inject(PostActivity activity);

  void inject(LoginActivity activity);

  void inject(PagedActivity pagedActivity);

  void inject(CarousalActivity carousalActivity);
}
