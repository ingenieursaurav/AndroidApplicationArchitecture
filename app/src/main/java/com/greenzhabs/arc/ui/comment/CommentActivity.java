package com.greenzhabs.arc.ui.comment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.greenzhabs.arc.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentActivity extends AppCompatActivity {

  public static final String TAG = CommentActivity.class.getSimpleName();
  public static final String EXTRA_POST_ID = TAG + ".EXTRA_POST_ID";

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_comment);
    ButterKnife.bind(this);

    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    int postId = getIntent().getIntExtra(EXTRA_POST_ID, -1);
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.container, CommentFragment.newInstance(postId))
        .commit();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
