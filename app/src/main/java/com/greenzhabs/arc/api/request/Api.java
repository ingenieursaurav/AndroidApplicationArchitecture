package com.greenzhabs.arc.api.request;

import com.greenzhabs.arc.Constant;
import com.greenzhabs.arc.api.response.ApiResponse;
import com.greenzhabs.arc.ui.comment.Comment;
import com.greenzhabs.arc.ui.post.Post;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

  @GET("/posts")
  Call<ApiResponse<Post>> getPosts();

  @GET("/posts?_limit=" + Constant.ITEMS_PER_PAGE)
  Call<ApiResponse<Post>> getPosts(@Query("_start") String start);

  @GET("/posts/{postId}/comments")
  Call<ApiResponse<Comment>> getComments(@Path("postId") String postId);
}
