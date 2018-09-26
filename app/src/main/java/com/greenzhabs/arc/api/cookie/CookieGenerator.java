package com.greenzhabs.arc.api.cookie;

import android.content.Context;
import android.util.Log;

import com.greenzhabs.arc.injection.scope.AppContext;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

import javax.inject.Inject;

/** Saurav on 28/8/2017. */
public class CookieGenerator {

  private static final String TAG = CookieGenerator.class.getSimpleName();

  private Context context;

  @Inject
  CookieGenerator(@AppContext Context context) {
    this.context = context;
  }

  public CookieHandler getCookieHandler() {
    PersistentCookieStore persistentCookieStore = new PersistentCookieStore(context);
    CookieHandler cookieHandler = new CookieManager(persistentCookieStore, CookiePolicy.ACCEPT_ALL);
    try {
      String phpSession =
          persistentCookieStore.getCookies().get(0).getName()
              + "="
              + persistentCookieStore.getCookies().get(0).getValue();
      Log.e(TAG, phpSession);
      android.webkit.CookieManager.getInstance()
          .setCookie(
              persistentCookieStore.getCookies().get(0).getName(),
              persistentCookieStore.getCookies().get(0).getValue());
    } catch (Exception e) {
      Log.d(TAG, e.getMessage());
    }
    return cookieHandler;
  }
}
