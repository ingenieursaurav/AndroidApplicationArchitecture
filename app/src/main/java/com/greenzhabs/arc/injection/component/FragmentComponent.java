package com.greenzhabs.arc.injection.component;

import com.greenzhabs.arc.injection.module.FragmentModule;
import com.greenzhabs.arc.injection.module.FragmentPresenterModule;
import com.greenzhabs.arc.injection.scope.FragmentScope;
import com.greenzhabs.arc.ui.comment.CommentFragment;
import com.greenzhabs.arc.ui.post.delete.PostDeleteDialog;

import dagger.Subcomponent;

/** Saurav on 28-10-2017. */
@FragmentScope
@Subcomponent(modules = {FragmentModule.class, FragmentPresenterModule.class})
public interface FragmentComponent {

  void inject(CommentFragment fragment);

  void inject(PostDeleteDialog fragment);
}
