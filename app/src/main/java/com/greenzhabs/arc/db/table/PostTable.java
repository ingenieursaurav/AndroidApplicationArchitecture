package com.greenzhabs.arc.db.table;

/** Saurav on 31-10-2017. */
public class PostTable {
  public static final String TABLE_NAME = "Post";
  public static final String COL_ID = "id";
  public static final String COL_USER_ID = "userId";
  public static final String COL_TITLE = "title";
  public static final String COL_BODY = "body";

  public static final String TABLE_QUERY =
      "create table "
          + TABLE_NAME
          + "("
          + COL_ID
          + " INTEGER,"
          + COL_USER_ID
          + " INTEGER,"
          + COL_TITLE
          + " TEXT,"
          + COL_BODY
          + " TEXT)";
}
