package com.greenzhabs.arc.ui.carousal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.greenzhabs.arc.R;
import com.greenzhabs.arc.base.BaseActivity;
import com.greenzhabs.arc.injection.component.ActivityComponent;
import com.greenzhabs.arc.ui.carousal.mvp.CarousalContract;
import com.greenzhabs.arc.util.PagerIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

/** Saurav on 06-04-2018. */
public class CarousalActivity extends BaseActivity<CarousalContract.Presenter>
    implements CarousalContract.View {
  @BindView(R.id.toolbar)
  Toolbar toolbar;

  @BindView(R.id.pager)
  ViewPager pager;

  @BindView(R.id.indicator)
  PagerIndicator indicator;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_carousal);
    ButterKnife.bind(this);
    getPresenter().attach(this);
  }

  @Override
  protected void inject(ActivityComponent component) {
    component.inject(this);
  }

  @Override
  public void init() {
    pager.setAdapter(new ViewPagerAdapter(this));
    pager.setPageTransformer(false, new DepthPageTransformer());
    indicator.setUpIndicator(pager);
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

  public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;
    private int[] items = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4};

    public ViewPagerAdapter(@NonNull Context context) {
      this.context = context;
      inflater = LayoutInflater.from(context);
    }

    @DrawableRes
    private int getItem(int position) {
      return items[position];
    }

    @Override
    public int getCount() {
      return items.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
      return view == object;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
      return PagerAdapter.POSITION_NONE;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
      ImageView itemView = (ImageView) inflater.inflate(R.layout.item_carousal, container, false);
      Glide.with(context).load(getItem(position)).into(itemView);
      container.addView(itemView);
      return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
      container.removeView((View) object);
    }
  }
}
