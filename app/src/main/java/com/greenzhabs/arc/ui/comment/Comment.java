package com.greenzhabs.arc.ui.comment;

import com.google.gson.annotations.Expose;

/** Saurav on 30-10-2017. */
public class Comment {
  @Expose private String postId; // 1,
  @Expose private String id; // 1,
  @Expose private String name; // "id labore ex et quam laborum",
  @Expose private String email; // "Eliseo@gardner.biz",

  @Expose
  private String body; // "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo
  // necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium"

  public String getPostId() {
    return postId;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getBody() {
    return body;
  }
}
