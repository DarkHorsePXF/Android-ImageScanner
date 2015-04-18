package com.example.imagescanner;

import java.lang.ref.SoftReference;
import java.net.CacheRequest;
import java.util.HashMap;
import java.util.WeakHashMap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.R.transition;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class MyViewPager extends ViewPager {
	float mTrans;
	final float MAX_SCALE=0.5F;
	View mLeftView;
	View mRightView;
	float mScale;
//	private LruCache<Integer, View> mLruCache=new LruCache<Integer, View>(4*1024*1024);
	private WeakHashMap<Integer, View> mChildViews=new WeakHashMap<Integer,View>();
			
	
	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public MyViewPager(Context context){
		super(context);
	}
	
	@Override
	protected void onPageScrolled(int position, float offSet, int offSetPixels) {
		
		mTrans=isSmall(offSet)?0:offSet;
		mLeftView=findViewFromObject(position);
		mRightView=findViewFromObject(position+1);
		animateStack(mLeftView,mRightView,offSet,offSetPixels);
		super.onPageScrolled(position, offSet, offSetPixels);
	}

	protected void animateStack(View leftView, View rightView, float offSet,
			int offSetPixels) {
		if(rightView!=null){
			mScale=(1-MAX_SCALE)*offSet+MAX_SCALE;
			mTrans=-getWidth() - getPageMargin() + offSetPixels;
			rightView.setScaleX(offSet);
			rightView.setScaleY(offSet);
			rightView.setTranslationX(mTrans);
		}
		if(leftView!=null){
			leftView.bringToFront();
		}
	}

	private View findViewFromObject(int position) {
		View view=mChildViews.get(position);
		
		return view;
	}

	private boolean isSmall(float offSet) {
		return Math.abs(offSet)<0.0001;
	}
	
	public void setViewForPosition(int position,View view){
		mChildViews.put(position,view);
	}

	

}
