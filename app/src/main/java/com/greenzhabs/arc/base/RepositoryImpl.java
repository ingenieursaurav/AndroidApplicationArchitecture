package com.greenzhabs.arc.base;

/** Saurav on 11-11-2017. */
public abstract class RepositoryImpl<BP extends BasePresenter> implements BaseRepository<BP> {
  public abstract void cancel();
}
