package com.greenzhabs.arc.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.greenzhabs.arc.ArcApp;
import com.greenzhabs.arc.injection.component.FragmentComponent;
import com.greenzhabs.arc.injection.module.FragmentModule;
import com.greenzhabs.arc.injection.module.FragmentPresenterModule;

import javax.inject.Inject;

/** Saurav on 28-10-2017. */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

  @Inject P presenter;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    FragmentComponent component =
        ArcApp.component()
            .getFragmentComponent(new FragmentModule(getContext()), new FragmentPresenterModule());
    inject(component);
  }

  protected abstract void inject(FragmentComponent component);

  protected P getPresenter() {
    return presenter;
  }
}
