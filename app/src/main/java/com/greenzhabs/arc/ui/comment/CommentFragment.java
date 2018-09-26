package com.greenzhabs.arc.ui.comment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greenzhabs.arc.R;
import com.greenzhabs.arc.base.BaseFragment;
import com.greenzhabs.arc.injection.component.FragmentComponent;
import com.greenzhabs.arc.ui.comment.mvp.CommentContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** Saurav on 30-10-2017. */
public class CommentFragment extends BaseFragment<CommentContract.Presenter>
    implements CommentContract.View {

  private static final String TAG = CommentFragment.class.getSimpleName();
  private static final String BUNDLE_POST_ID = TAG + ".BUNDLE_POST_ID";

  @BindView(R.id.commentRecycler)
  RecyclerView commentRecycler;

  Unbinder unbinder;

  private int postId;

  public static CommentFragment newInstance(int postId) {
    Bundle bundle = new Bundle();
    bundle.putInt(BUNDLE_POST_ID, postId);
    CommentFragment fragment = new CommentFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      postId = getArguments().getInt(BUNDLE_POST_ID);
    }
  }

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_comment, container, false);
    unbinder = ButterKnife.bind(this, view);
    getPresenter().attach(this);
    getPresenter().getComments(postId);
    return view;
  }

  @Override
  protected void inject(FragmentComponent component) {
    component.inject(this);
  }

  @Override
  public void onDestroy() {
    getPresenter().detach();
    super.onDestroy();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override
  public void init() {
    commentRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    commentRecycler.addItemDecoration(
        new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
  }

  @Override
  public void updateView(List<Comment> list) {
    commentRecycler.setAdapter(new CommentAdapter(list));
  }

  static class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {

    private List<Comment> mList;

    public CommentAdapter(List<Comment> mList) {
      this.mList = mList;
    }

    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new CommentHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {
      Comment comment = mList.get(position);
      if (comment != null) {
        holder.txtName.setText(comment.getName());
        holder.txtEmail.setText(comment.getEmail());
        holder.txtBody.setText(comment.getBody());
      }
    }

    @Override
    public int getItemCount() {
      return mList.size();
    }

    static class CommentHolder extends RecyclerView.ViewHolder {
      @BindView(R.id.txtName)
      TextView txtName;

      @BindView(R.id.txtEmail)
      TextView txtEmail;

      @BindView(R.id.txtBody)
      TextView txtBody;

      CommentHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
      }
    }
  }
}
