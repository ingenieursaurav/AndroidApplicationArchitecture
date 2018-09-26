package com.greenzhabs.arc.injection.module;

import android.support.annotation.NonNull;

import com.greenzhabs.arc.util.Util;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/** Saurav on 30-10-2017. */
@Module
public class CommonModule {

  @Provides
  @NonNull
  @Singleton
  Util providesUtility() {
    return new Util();
  }
}
