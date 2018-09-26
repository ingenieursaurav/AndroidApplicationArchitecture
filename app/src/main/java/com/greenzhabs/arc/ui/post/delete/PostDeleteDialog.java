package com.greenzhabs.arc.ui.post.delete;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.greenzhabs.arc.base.BaseDialogFragment;
import com.greenzhabs.arc.injection.component.FragmentComponent;
import com.greenzhabs.arc.ui.post.delete.mvp.PostDeleteContract;

/** Saurav on 02-11-2017. */
public class PostDeleteDialog extends BaseDialogFragment<PostDeleteContract.Presenter> {

  public static final String TAG = PostDeleteDialog.class.getSimpleName();

  public static PostDeleteDialog newInstance() {
    return new PostDeleteDialog();
  }

  @Override
  protected void inject(FragmentComponent component) {
    component.inject(this);
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
    builder.setTitle("Delete Post");
    builder.setMessage("Do you wish to delete the post?");
    builder.setPositiveButton(
        "Ok",
        new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            dismiss();
          }
        });
    return builder.create();
  }
}
