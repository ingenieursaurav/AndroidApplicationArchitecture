package com.greenzhabs.arc.injection.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.greenzhabs.arc.api.cookie.CookieGenerator;
import com.greenzhabs.arc.api.request.Api;
import com.greenzhabs.arc.api.request.ApiHandler;
import com.greenzhabs.arc.api.response.ApiResponse;
import com.greenzhabs.arc.api.response.CustomJsonDeserializer;
import com.greenzhabs.arc.injection.scope.AppContext;
import com.greenzhabs.arc.ui.comment.Comment;
import com.greenzhabs.arc.ui.post.Post;
import com.greenzhabs.arc.util.Util;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/** Saurav on 28-10-2017. */
@Module
public class ApiModule {

  @Provides
  ApiHandler providesApiHandler(
      @AppContext Context context, Api api, Retrofit retrofit, Util util) {
    return new ApiHandler(context, api, retrofit, util);
  }

  @Provides
  Api providesApi(Retrofit retrofit) {
    return retrofit.create(Api.class);
  }

  @Provides
  Retrofit providesRetrofit(OkHttpClient httpClient, Converter.Factory factory) {
    return new Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .client(httpClient)
        .addConverterFactory(factory)
        .build();
  }

  @Provides
  OkHttpClient providesOkHttpClient(CookieGenerator cookieGenerator) {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return new OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .cookieJar(new JavaNetCookieJar(cookieGenerator.getCookieHandler()))
        .addInterceptor(interceptor)
        .build();
  }

  @Provides
  Converter.Factory providesGsonFactory(Gson gson) {
    return GsonConverterFactory.create(gson);
  }

  @Provides
  Gson providesGson() {
    return new GsonBuilder()
        .registerTypeAdapter(getPostType(), new CustomJsonDeserializer<>(Post.class))
        .registerTypeAdapter(getCommentType(), new CustomJsonDeserializer<>(Comment.class))
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .create();
  }

  private Type getPostType() {
    return new TypeToken<ApiResponse<Post>>() {}.getType();
  }

  private Type getCommentType() {
    return new TypeToken<ApiResponse<Comment>>() {}.getType();
  }
}
