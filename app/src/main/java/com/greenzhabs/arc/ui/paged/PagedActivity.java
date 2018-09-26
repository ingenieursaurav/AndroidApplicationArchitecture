package com.greenzhabs.arc.ui.paged;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.greenzhabs.arc.R;
import com.greenzhabs.arc.base.BaseActivity;
import com.greenzhabs.arc.injection.component.ActivityComponent;
import com.greenzhabs.arc.ui.paged.mvp.PagedContract;
import com.greenzhabs.arc.ui.post.Post;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/** Saurav on 04-04-2018. */
public class PagedActivity extends BaseActivity<PagedContract.Presenter>
    implements PagedContract.View {

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  @BindView(R.id.postRecycler)
  RecyclerView postRecycler;

  @Inject PagedAdapter pagedAdapter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_post);
    ButterKnife.bind(this);
    getPresenter().attach(this);
  }

  @Override
  protected void inject(ActivityComponent component) {
    component.inject(this);
  }

  @Override
  public void init() {
    postRecycler.setLayoutManager(new LinearLayoutManager(this));
    postRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    pagedAdapter.setUpLoadMore(postRecycler);
    postRecycler.setAdapter(pagedAdapter);
  }

  @Override
  public void setUpToolbar() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void updateView(List<Post> list) {
    pagedAdapter.addItems(list);
  }

  @Override
  protected void onDestroy() {
    pagedAdapter.cancelApi();
    getPresenter().detach();
    super.onDestroy();
  }
}
