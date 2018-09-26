package com.greenzhabs.arc.api.response;

import java.util.ArrayList;
import java.util.List;

/** Saurav on 28/10/2017. */
public class ApiResponse<RS> {
  private List<RS> list;
  private RS data;
  private ApiError error;
  private boolean isError = false;

  ApiResponse(ArrayList<RS> list) {
    this.list = list;
  }

  public ApiResponse(RS data) {
    this.data = data;
  }

  ApiResponse(ApiError error) {
    isError = true;
    this.error = error;
  }

  public List<RS> getList() {
    return list;
  }

  public RS getData() {
    return data;
  }

  public ApiError getError() {
    return error;
  }

  public boolean isError() {
    return isError;
  }
}
