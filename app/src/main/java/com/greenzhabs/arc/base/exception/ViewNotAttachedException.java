package com.greenzhabs.arc.base.exception;

/** Saurav on 28-10-2017. */
public class ViewNotAttachedException extends RuntimeException {
  public ViewNotAttachedException() {
    super("BaseView not attached to View.");
  }
}
