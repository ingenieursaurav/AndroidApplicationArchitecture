package com.greenzhabs.arc.base.pagination;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;

import com.greenzhabs.arc.R;
import com.greenzhabs.arc.api.request.ApiHandler;
import com.greenzhabs.arc.util.RecyclerViewPositionHelper;

import java.util.ArrayList;
import java.util.List;

import static com.greenzhabs.arc.Constant.ITEMS_PER_PAGE;

/** Saurav on 04-04-2018. */
public abstract class PagedRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder>
    extends RecyclerView.Adapter<VH> {

  protected static final int NORMAL_ITEM = 1;
  protected static final int LOAD_ITEM = 2;

  private static final int DEFAULT_LOADING = R.layout.item_loading;
  private static final String TAG = PagedRecyclerViewAdapter.class.getSimpleName();

  public final ApiHandler apiHandler;
  protected Context context;
  @LayoutRes private int mLoadLayout = 0;
  private List<T> list;
  private boolean isLoading = Boolean.FALSE;
  private boolean isLastPage = Boolean.FALSE;
  private RecyclerView recyclerView;

  public PagedRecyclerViewAdapter(
      Context context, @LayoutRes int loadLayout, ApiHandler apiHandler, List<T> list) {
    this.context = context;
    this.mLoadLayout = loadLayout;
    this.apiHandler = apiHandler;
    this.list = list;
  }

  public PagedRecyclerViewAdapter(Context context, ApiHandler apiHandler, List<T> list) {
    this(context, DEFAULT_LOADING, apiHandler, list);
  }

  /** Initialize the pagination */
  public void setUpLoadMore(@NonNull RecyclerView recyclerView) {
    this.recyclerView = recyclerView;
    this.recyclerView.addOnScrollListener(
        new RecyclerView.OnScrollListener() {
          @Override
          public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            RecyclerViewPositionHelper mRecyclerViewHelper =
                RecyclerViewPositionHelper.createHelper(recyclerView);
            int visibleItemCount = recyclerView.getChildCount();
            int totalItemCount = recyclerView.getLayoutManager().getItemCount();
            int firstVisibleItem = mRecyclerViewHelper.findFirstVisibleItemPosition();
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                && (visibleItemCount + firstVisibleItem) >= totalItemCount
                && firstVisibleItem >= 0) {
              if ((totalItemCount % ITEMS_PER_PAGE) == 0 && !isLastPage()) {
                int page = (getItemCount() / ITEMS_PER_PAGE) /*+ 1*/;
                if (!isLoading) {
                  isLoading = Boolean.TRUE;
                  addLoadingItem();
                  loadMore(page);
                }
              }
            }
          }
        });
  }

  /** Returns the Loading layout to be used when loading */
  @LayoutRes
  protected int getLoadingLayout() {
    return mLoadLayout;
  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  @Override
  public int getItemViewType(int position) {
    if (list.get(position) == null) return LOAD_ITEM;
    else return NORMAL_ITEM;
  }

  /**
   * Retrieves the item from the list
   *
   * @param position Position to which the object to be retrieved
   * @return {@link T} instance object if present
   */
  protected T getItem(int position) {
    return list.get(position);
  }

  /**
   * Load more function called when pagination is required
   *
   * @param page Next Page number to be called
   */
  protected abstract void loadMore(int page);

  /** Cancel in-flight Api calls */
  public abstract void cancelApi();

  /**
   * Adds list of items
   *
   * @param list List of {@link T} instance
   */
  public void addItems(List<T> list) {
    if (isLoading) removeLoadingItem();
    isLoading = Boolean.FALSE;
    if (this.list == null) this.list = new ArrayList<>();
    this.list.addAll(list);
    notifyDataSetChanged();
  }

  /** Appends loading item to the list */
  private void addLoadingItem() {
    if (this.list == null) this.list = new ArrayList<>();
    this.list.add(null);
    notifyItemInserted(list.size() - 1);
    recyclerView.getLayoutManager().scrollToPosition(list.size() - 1);
  }

  /** Removes the loading item from the list */
  private void removeLoadingItem() {
    if (list != null && list.size() > 0 && isLoading) {
      list.remove(list.size() - 1);
      notifyItemRemoved(list.size() - 1);
    }
  }

  /**
   * Returns whether the page is Last page or not
   *
   * @return true -> if last page, false -> if not last page
   */
  private boolean isLastPage() {
    return isLastPage;
  }

  /**
   * Call this function to mark the list has reached the end and further API calls should be
   * restricted.<br>
   * Don't call this for all the error cases. Call Only for the EOD.
   */
  public void setLastPage() {
    removeLoadingItem();
    isLoading = Boolean.FALSE;
    isLastPage = Boolean.TRUE;
  }

  /** Loading View Holder */
  public static class LoadHolder extends RecyclerView.ViewHolder {
    public LoadHolder(View itemView) {
      super(itemView);
    }
  }
}
