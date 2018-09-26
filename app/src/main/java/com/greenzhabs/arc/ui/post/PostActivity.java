package com.greenzhabs.arc.ui.post;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.greenzhabs.arc.R;
import com.greenzhabs.arc.base.BaseActivity;
import com.greenzhabs.arc.injection.component.ActivityComponent;
import com.greenzhabs.arc.ui.carousal.CarousalActivity;
import com.greenzhabs.arc.ui.comment.CommentActivity;
import com.greenzhabs.arc.ui.paged.PagedActivity;
import com.greenzhabs.arc.ui.post.mvp.PostContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostActivity extends BaseActivity<PostContract.Presenter>
    implements PostContract.View {

  private static final String TAG = PostActivity.class.getSimpleName();

  @BindView(R.id.postRecycler)
  RecyclerView postRecycler;

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
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
  }

  @Override
  public void setUpToolbar() {
    setSupportActionBar(toolbar);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_paged, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    Intent intent;
    switch (item.getItemId()) {
      case R.id.action_paged:
        intent = new Intent(this, PagedActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        return true;
      case R.id.action_slider:
        intent = new Intent(this, CarousalActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  protected void onDestroy() {
    getPresenter().detach();
    super.onDestroy();
  }

  @Override
  public void updateView(List<Post> list) {
    postRecycler.setAdapter(
        new PostAdapter(
            this,
            list,
            new PostAdapter.OnItemClickListener() {
              @Override
              public void onItemDeleteClicked() {
                getPresenter().showPostDeleteDialog();
              }
            }));
  }

  static class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    private OnItemClickListener listener;
    private Context context;
    private List<Post> mList;

    PostAdapter(Context context, @NonNull List<Post> mList, OnItemClickListener listener) {
      this.context = context;
      this.mList = mList;
      this.listener = listener;
    }

    @Override
    public PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new PostHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(PostHolder holder, final int position) {
      final Post post = mList.get(position);
      if (post != null) {
        holder.txtTitle.setText(post.getTitle());
        holder.txtBody.setText(post.getBody());
        holder.btnDelete.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                if (listener != null) listener.onItemDeleteClicked();
              }
            });
        holder.itemView.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                Intent intent = new Intent(context, CommentActivity.class);
                intent.putExtra(CommentActivity.EXTRA_POST_ID, post.getId());
                context.startActivity(intent);
              }
            });
      }
    }

    @Override
    public int getItemCount() {
      return mList.size();
    }

    interface OnItemClickListener {
      void onItemDeleteClicked();
    }

    static class PostHolder extends RecyclerView.ViewHolder {
      @BindView(R.id.txtTitle)
      TextView txtTitle;

      @BindView(R.id.txtBody)
      TextView txtBody;

      @BindView(R.id.btnDelete)
      ImageButton btnDelete;

      PostHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
      }
    }
  }
}
