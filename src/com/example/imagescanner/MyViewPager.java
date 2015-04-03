package com.example.imagescanner;

import java.util.HashMap;
import java.util.LinkedHashMap;

import android.R.transition;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class MyViewPager extends ViewPager {
	float mTrans;
	final float MAX_SCALE=0.5F;
	View mLeftView;
	View mRightView;
	float mScale;
	private HashMap<Integer, View> mChildViews=new LinkedHashMap<Integer,View>();
	
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
		return mChildViews.get(position);
	}

	private boolean isSmall(float offSet) {
		return Math.abs(offSet)<0.0001;
	}
	
	public void setViewForPosition(int position,View view){
		mChildViews.put(position,view);
	}

	

}
