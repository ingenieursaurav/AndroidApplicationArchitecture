package com.greenzhabs.arc.injection.module;

import android.content.Context;

import com.greenzhabs.arc.injection.scope.FragmentScope;
import com.greenzhabs.arc.injection.scope.ScopeContext;
import com.greenzhabs.arc.util.ProgressUtil;

import dagger.Module;
import dagger.Provides;

/** Saurav on 28-10-2017. */
@Module
public class FragmentModule {

  private Context context;

  public FragmentModule(Context context) {
    this.context = context;
  }

  @ScopeContext
  @Provides
  @FragmentScope
  Context providesFragmentContext() {
    return context;
  }

  @Provides
  @FragmentScope
  ProgressUtil providesProgressDialog(@ScopeContext Context context) {
    return new ProgressUtil(context);
  }
}
