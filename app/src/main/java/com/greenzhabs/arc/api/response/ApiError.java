package com.greenzhabs.arc.api.response;

/** Saurav on 28/10/2017. */
public class ApiError {
  private int error;
  private String reason;

  public ApiError(int error, String reason) {
    this.error = error;
    this.reason = reason;
  }

  public int getError() {
    return error;
  }

  public String getReason() {
    return reason;
  }
}
