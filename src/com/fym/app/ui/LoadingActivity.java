package com.fym.app.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fym.app.R;
import com.fym.app.common.Constants;
import com.fym.app.common.Constants.Extra;
import com.fym.app.ui.base.BaseActivity;

public class LoadingActivity extends BaseActivity {
	/** Called when the activity is first created. */
	private ViewPager mViewPager;
	private int currIndex = 0;
	private LinearLayout mPageIndex;
	private int mSize = 0;
	private ImageView[] mPage;
	private String[] mImages;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading_activity);
		Bundle bundle = getIntent().getExtras();
		mImages = bundle.getStringArray(Extra.IMAGES);
		
		mSize = mImages.length;
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		mPageIndex = (LinearLayout) findViewById(R.id.pageindex);

		InitRes();
	}

	private void InitRes() {
		

		mPage = new ImageView[mSize];
		mPage[0] = (ImageView) this.getViewClone(R.layout.loadingviewitem,
				R.id.pagein);
		mPageIndex.addView(mPage[0]);
		for (int i = 1; i < mSize; i++) {
			mPage[i] = (ImageView) this.getViewClone(R.layout.loadingviewitem,
					R.id.pageout);
			mPageIndex.addView(mPage[i]);
		}

		final ArrayList<View> views = new ArrayList<View>();
		// final ArrayList<String> titles = new ArrayList<String>();
		View tmpView;
		LayoutInflater li = LayoutInflater.from(this);
		for (int i = 0; i < mSize; i++) {
			tmpView = li.inflate(R.layout.loadingview, null);
			ImageView img = (ImageView) tmpView.findViewById(R.id.backimg);
			imageLoader.displayImage(mImages[i], img);

			//最后一个view让按钮可见
			if (i == mSize - 1)
			{
				tmpView.findViewById(R.id.startBtn).setVisibility(View.VISIBLE);
			}
			views.add(tmpView);
		}

		PagerAdapter mPagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager) container).removeView(views.get(position));
			}

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(views.get(position));
				return views.get(position);
			}
		};

		mViewPager.setAdapter(mPagerAdapter);
	}

	private View getViewClone(int layout, int resid) {
		LayoutInflater li = LayoutInflater.from(this);
		View parentView = li.inflate(layout, null);
		View view = parentView.findViewById(resid);
		// 需要把获取的view从父view中移除，不然会产生异常
		((ViewGroup) parentView).removeView(view);
		return view;
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;

			if (arg0 == 0) {
				mPage[0].setImageDrawable(getResources().getDrawable(
						R.drawable.page_now));
				mPage[1].setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				if (currIndex == arg0 + 1) {
					animation = new TranslateAnimation(20 * (arg0 + 1),
							20 * arg0, 0, 0);
				}
			} else if (arg0 == mSize - 1) {
				mPage[arg0].setImageDrawable(getResources().getDrawable(
						R.drawable.page_now));
				mPage[arg0 - 1].setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				if (currIndex == arg0 - 1) {
					animation = new TranslateAnimation(20 * (arg0 - 1),
							20 * arg0, 0, 0);
				}
				
			} else {
				if (arg0 < mSize - 1) {
					mPage[arg0].setImageDrawable(getResources().getDrawable(
							R.drawable.page_now));
					mPage[arg0 - 1].setImageDrawable(getResources()
							.getDrawable(R.drawable.page));
					mPage[arg0 + 1].setImageDrawable(getResources()
							.getDrawable(R.drawable.page));
					if (currIndex == arg0 - 1) {
						animation = new TranslateAnimation(20 * (arg0 - 1),
								20 * arg0, 0, 0);
					} else if (currIndex == arg0 + 1) {
						animation = new TranslateAnimation(20 * (arg0 + 1),
								20 * arg0, 0, 0);
					}
				}
			}
			currIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(300);
			// mPageImg.startAnimation(animation);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	public void startbtn(View v) {
		setContentView(R.layout.startbtn_ani);

		ImageView mLeft = (ImageView) findViewById(R.id.imageLeft);
		ImageView mRight = (ImageView) findViewById(R.id.imageRight);

		AnimationSet anim = new AnimationSet(true);
		TranslateAnimation mytranslateanim = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
				-1f, Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, 0f);
		mytranslateanim.setDuration(2000);
		anim.addAnimation(mytranslateanim);
		anim.setFillAfter(true);
		mLeft.startAnimation(anim);

		AnimationSet anim1 = new AnimationSet(true);
		TranslateAnimation mytranslateanim1 = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
				+1f, Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, 0f);
		mytranslateanim1.setDuration(1500);
		anim1.addAnimation(mytranslateanim1);
		anim1.setFillAfter(true);
		mRight.startAnimation(anim1);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
				startActivity(intent);
				LoadingActivity.this.finish();
			}
		}, 1500);
	}
}
