package com.greenzhabs.arc.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.greenzhabs.arc.R;
import com.greenzhabs.arc.base.BaseActivity;
import com.greenzhabs.arc.injection.component.ActivityComponent;
import com.greenzhabs.arc.ui.login.mvp.LoginContract;
import com.greenzhabs.arc.ui.post.PostActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginContract.Presenter>
    implements LoginContract.View {

  @BindView(R.id.edtUsername)
  EditText edtUsername;

  @BindView(R.id.edtPassword)
  EditText edtPassword;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
    getPresenter().attach(this);
  }

  @Override
  protected void inject(ActivityComponent component) {
    component.inject(this);
  }

  @OnClick(R.id.btnLogin)
  public void onViewClicked() {
    getPresenter().login(edtUsername.getText().toString(), edtPassword.getText().toString());
  }

  @Override
  public void gotoPostPage() {
    Intent intent = new Intent(this, PostActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }
}
