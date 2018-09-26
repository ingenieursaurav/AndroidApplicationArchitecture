package com.greenzhabs.arc.injection.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.greenzhabs.arc.db.DbHelper;
import com.greenzhabs.arc.injection.scope.AppContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/** Saurav on 31-10-2017. */
@Module
public class PersistenceModule {

  @Provides
  @NonNull
  @Singleton
  DbHelper providesDbHelper(@AppContext Context context) {
    return new DbHelper(context);
  }
}
