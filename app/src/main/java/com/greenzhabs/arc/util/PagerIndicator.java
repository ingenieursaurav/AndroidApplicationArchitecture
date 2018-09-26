package com.greenzhabs.arc.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.greenzhabs.arc.R;

/** Saurav on 06-04-2018. */
public class PagerIndicator extends LinearLayout {

  @DrawableRes
  private static final int INDICATOR_DEFAULT = R.drawable.indicator_default,
      INDICATOR_SELECTED = R.drawable.indicator_selected;
  private static final float SCALE_NORMAL = 1f;
  private static final float SCALE_SHRINK = 0.75f;
  private static final int ANIM_DURATION = 300;

  @Dimension private int DEFAULT_RADIUS = 0;
  @Dimension private int mRadius;
  private Drawable defaultIndicator, selectedIndicator;
  private boolean mAutoPlay = Boolean.FALSE;
  private long mAutoPlayInterval = 1000;
  private ViewPager mPager;

  public PagerIndicator(Context context) {
    super(context);
  }

  public PagerIndicator(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    if (!isInEditMode()) init(context, attrs);
  }

  public PagerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    if (!isInEditMode()) init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    DEFAULT_RADIUS = context.getResources().getDimensionPixelSize(R.dimen.indicator_radius);
    if (attrs != null) {
      TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PagerIndicator);
      mRadius = a.getDimensionPixelSize(R.styleable.PagerIndicator_radius, DEFAULT_RADIUS);
      defaultIndicator = a.getDrawable(R.styleable.PagerIndicator_defaultIndicator);
      if (defaultIndicator == null)
        defaultIndicator =
            ResourcesCompat.getDrawable(context.getResources(), INDICATOR_DEFAULT, null);
      selectedIndicator = a.getDrawable(R.styleable.PagerIndicator_selectedIndicator);
      if (selectedIndicator == null)
        selectedIndicator =
            ResourcesCompat.getDrawable(context.getResources(), INDICATOR_SELECTED, null);
      mAutoPlay = a.getBoolean(R.styleable.PagerIndicator_autoPlay, false);
      mAutoPlayInterval = a.getInt(R.styleable.PagerIndicator_autoPlayInterval, 1000);
      a.recycle();
    }
  }

  /**
   * Initialize the indicator for the ViewPager
   *
   * @param viewPager Viewpager instance
   */
  public void setUpIndicator(@Nullable ViewPager viewPager) {
    if (viewPager == null) return;
    this.mPager = viewPager;
    int totalCount = mPager.getAdapter() != null ? mPager.getAdapter().getCount() : 0;
    for (int i = 0; i < totalCount; i++) {
      View view = new View(getContext());
      LinearLayoutCompat.LayoutParams mParams =
          new LinearLayoutCompat.LayoutParams(mRadius, mRadius);
      mParams.setMargins(10, 0, 10, 0);
      view.setLayoutParams(mParams);
      view.setBackground(mPager.getCurrentItem() != i ? defaultIndicator : selectedIndicator);
      if (mPager.getCurrentItem() != i) animateIndicator(view, SCALE_SHRINK);
      addView(view);
    }
    mPager.addOnPageChangeListener(
        new ViewPager.SimpleOnPageChangeListener() {
          @Override
          public void onPageSelected(int position) {
            super.onPageSelected(position);
            for (int i = 0; i < getChildCount(); i++) {
              View mView = getChildAt(i);
              if (i == position) {
                mView.setBackground(selectedIndicator);
                animateIndicator(mView, SCALE_NORMAL);
              } else {
                mView.setBackground(defaultIndicator);
                animateIndicator(mView, SCALE_SHRINK);
              }
            }
          }
        });

    AutoPlayHandler playHandler = new AutoPlayHandler();
    playHandler.play();
  }

  private void animateIndicator(@Nullable View view, @FloatRange(from = 0f, to = 1f) float scale) {
    if (view != null) view.animate().scaleX(scale).scaleY(scale).setDuration(ANIM_DURATION).start();
  }

  private class AutoPlayHandler implements Runnable {
    int position = 0;
    private Handler handler = new Handler();

    void play() {
      if (mAutoPlay) handler.postDelayed(this, mAutoPlayInterval);
    }

    @Override
    public void run() {
      if (mPager.getAdapter() != null) {
        if (position < mPager.getAdapter().getCount()) {
          position++;
        } else position = 0;
        mPager.setCurrentItem(position);
        play();
      }
    }
  }
}
