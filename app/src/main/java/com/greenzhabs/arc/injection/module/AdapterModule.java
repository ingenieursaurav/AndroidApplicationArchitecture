package com.greenzhabs.arc.injection.module;

import android.content.Context;

import com.greenzhabs.arc.R;
import com.greenzhabs.arc.api.request.ApiHandler;
import com.greenzhabs.arc.injection.scope.AppContext;
import com.greenzhabs.arc.ui.paged.PagedAdapter;

import dagger.Module;
import dagger.Provides;

/** Saurav on 05-04-2018. */
@Module
public class AdapterModule {
  @Provides
  PagedAdapter providedPagedAdapter(@AppContext Context context, ApiHandler apiHandler) {
    return new PagedAdapter(context, R.layout.item_loading1, apiHandler);
  }
}
