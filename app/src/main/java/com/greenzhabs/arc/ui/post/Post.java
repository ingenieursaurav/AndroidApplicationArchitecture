package com.greenzhabs.arc.ui.post;

import com.google.gson.annotations.Expose;

/** Saurav on 30-10-2017. */
public class Post {
  @Expose private int userId; // 1,
  @Expose private int id; // 1,

  @Expose
  private String
      title; // "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",

  @Expose private String body; // "quia et susci

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}
