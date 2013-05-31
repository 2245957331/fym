package com.fym.app.ui;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

import com.fym.app.R;
import com.fym.app.common.Constants.Extra;
import com.fym.app.ui.base.BaseActivity;
import com.umeng.update.UmengUpdateAgent;

public class SplashActivity extends BaseActivity {
	private String mLoadingDir = "loading";
	private String[] IMAGES;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			getLoadingFiles();
			goNext();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View view=View.inflate(this, R.layout.start_activity, null);
		setContentView(view);
		Animation animation=AnimationUtils.loadAnimation(this, R.anim.alpha);
		view.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {}
			@Override
			public void onAnimationRepeat(Animation arg0) {}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				mHandler.removeMessages(0);
				mHandler.sendEmptyMessageDelayed(0, 500);
			}
		});
		UmengUpdateAgent.setUpdateOnlyWifi(false);
		UmengUpdateAgent.update(this);

	}
	
	private void getLoadingFiles()
	{
		List<String> fileNames = null;
		try {
			fileNames = Arrays.asList(getAssets().list(mLoadingDir));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IMAGES = new String[fileNames.size()];
		for (int i = 0; i < fileNames.size(); i++)
		{
			IMAGES[i] = "assets://" + mLoadingDir + "/" + fileNames.get(i);
		}
	}

	protected void onResume() {
		super.onResume();
	}

	private void goNext() {
		if (IMAGES.length > 0)
		{
			Intent intent = new Intent(this, LoadingActivity.class);
			intent.putExtra(Extra.IMAGES, IMAGES);
			startActivity(intent);
			defaultFinish();
		}
		else
		{
			openActivity(MainActivity.class);
			defaultFinish();
		}
	};

}
