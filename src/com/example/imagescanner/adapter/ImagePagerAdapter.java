package com.example.imagescanner.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

import com.example.imagescanner.widget.MyViewPager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImagePagerAdapter extends PagerAdapter {
	ArrayList<HashMap<String, Object>> list;
	Context mContext;
	ImageLoader imageLoader;
	MyViewPager mViewPager;
	PhotoViewAttacher mAttacher;
	DisplayImageOptions options;
//	DisplayImageOptions options=new DisplayImageOptions.Builder()
//			.cacheInMemory(false)
//			.showImageOnFail(R.drawable.ic_error)
//			.showStubImage(R.drawable.ic_stub)
//			.showImageForEmptyUri(R.drawable.ic_empty)
//			
//			.build();
			
	
	public ImagePagerAdapter(ArrayList<HashMap<String, Object>> list,Context context,MyViewPager viewPager) {
		this.list=list;
		this.mContext=context;
		imageLoader=ImageLoader.getInstance();
		this.mViewPager=viewPager;
		options=new DisplayImageOptions.Builder()
			.cacheInMemory(true)
			.cacheOnDisc(true)
			.build();
	}

	@Override
	public void destroyItem(ViewGroup viewGroup, int position, Object object) {
		viewGroup.removeView((View) object);
		mViewPager.setViewForPosition(position, null);
	}


	@Override
	public Object instantiateItem(ViewGroup view, int position) {
		String path=list.get(position).get("path").toString();
		System.out.println(path);
		//Bitmap bitmap=BitmapFactory.decodeFile(path);
		
		ImageView iv=new ImageView(mContext);
		iv.setScaleType(ScaleType.CENTER);
		iv.setImageURI(Uri.parse("file:/"+path));
		imageLoader.displayImage("file:/"+path, iv,options);
		//iv.setImageBitmap(bitmap);
		mAttacher=new PhotoViewAttacher(iv);
		
		view.addView(iv);
		mViewPager.setViewForPosition(position, iv);
		return iv;
		
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}

}
