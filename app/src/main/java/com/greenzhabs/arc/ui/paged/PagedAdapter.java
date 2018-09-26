package com.greenzhabs.arc.ui.paged;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.greenzhabs.arc.Constant;
import com.greenzhabs.arc.R;
import com.greenzhabs.arc.api.request.ApiHandler;
import com.greenzhabs.arc.api.response.ApiError;
import com.greenzhabs.arc.api.response.ApiResponse;
import com.greenzhabs.arc.api.response.callback.SimpleApiResponseListener;
import com.greenzhabs.arc.base.pagination.PagedRecyclerViewAdapter;
import com.greenzhabs.arc.injection.scope.AppContext;
import com.greenzhabs.arc.ui.comment.CommentActivity;
import com.greenzhabs.arc.ui.post.Post;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

/** Saurav on 04-04-2018. */
public class PagedAdapter extends PagedRecyclerViewAdapter<Post, RecyclerView.ViewHolder> {

  private static final String TAG = PagedAdapter.class.getSimpleName();
  private Call<ApiResponse<Post>> postApi;

  public PagedAdapter(
      @AppContext Context context, @LayoutRes int loadLayout, ApiHandler apiHandler) {
    super(context, loadLayout, apiHandler, new ArrayList<Post>());
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (viewType == LOAD_ITEM)
      return new LoadHolder(
          LayoutInflater.from(parent.getContext()).inflate(getLoadingLayout(), parent, false));
    return new PagedHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
    switch (getItemViewType(position)) {
      case NORMAL_ITEM:
        final Post post = getItem(position);
        if (post != null) {
          PagedHolder holder = (PagedHolder) viewHolder;
          holder.txtTitle.setText(post.getTitle());
          holder.txtBody.setText(post.getBody());
          holder.btnDelete.setVisibility(View.GONE);
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
        break;
      case LOAD_ITEM:
        break;
    }
  }

  @Override
  protected void loadMore(int page) {
    String start = String.valueOf(page * Constant.ITEMS_PER_PAGE);
    postApi =
        apiHandler.getPosts(
            start,
            new SimpleApiResponseListener<List<Post>>() {
              @Override
              public void success(List<Post> posts) {
                addItems(posts);
              }

              @Override
              public void error(ApiError error) {
                setLastPage();
              }
            });
  }

  @Override
  public void cancelApi() {
    if (postApi != null) postApi.cancel();
  }

  static class PagedHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txtTitle)
    TextView txtTitle;

    @BindView(R.id.txtBody)
    TextView txtBody;

    @BindView(R.id.btnDelete)
    ImageButton btnDelete;

    PagedHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
