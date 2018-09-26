package com.greenzhabs.arc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.greenzhabs.arc.db.table.PostTable;
import com.greenzhabs.arc.ui.post.Post;

import java.util.ArrayList;
import java.util.List;

import static com.greenzhabs.arc.db.table.PostTable.COL_BODY;
import static com.greenzhabs.arc.db.table.PostTable.COL_ID;
import static com.greenzhabs.arc.db.table.PostTable.COL_TITLE;
import static com.greenzhabs.arc.db.table.PostTable.COL_USER_ID;
import static com.greenzhabs.arc.db.table.PostTable.TABLE_NAME;

/** Saurav on 31-10-2017. */
public class DbHelper extends SQLiteOpenHelper {

  private static final String DB_NAME = "Arc.db";
  private static final int DB_VERSION = 1;

  public DbHelper(Context context) {
    super(context, DB_NAME, null, DB_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(PostTable.TABLE_QUERY);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

  public void insertPost(List<Post> list) {
    SQLiteDatabase db = getWritableDatabase();
    for (Post post : list) {
      ContentValues values = new ContentValues();
      values.put(COL_ID, post.getId());
      values.put(COL_USER_ID, post.getUserId());
      values.put(COL_TITLE, post.getTitle());
      values.put(COL_BODY, post.getBody());
      db.insert(TABLE_NAME, null, values);
    }
  }

  public List<Post> getPosts() {
    List<Post> list = new ArrayList<>();
    SQLiteDatabase db = getReadableDatabase();
    Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
    if (cursor != null) {
      while (cursor.moveToNext()) {
        Post post = new Post();
        post.setId(cursor.getInt(cursor.getColumnIndex(PostTable.COL_ID)));
        post.setUserId(cursor.getInt(cursor.getColumnIndex(PostTable.COL_USER_ID)));
        post.setTitle(cursor.getString(cursor.getColumnIndex(PostTable.COL_TITLE)));
        post.setBody(cursor.getString(cursor.getColumnIndex(PostTable.COL_BODY)));
        list.add(post);
      }
    }
    if (cursor != null) {
      cursor.close();
    }
    return list;
  }
}
