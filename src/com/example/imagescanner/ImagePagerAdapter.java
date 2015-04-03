package com.example.imagescanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImagePagerAdapter extends PagerAdapter {
	ArrayList<HashMap<String, Object>> list;
	Context mContext;
	ImageLoader imageLoader;
	MyViewPager mViewPager;
	public ImagePagerAdapter(ArrayList<HashMap<String, Object>> list,Context context,MyViewPager viewPager) {
		this.list=list;
		this.mContext=context;
		imageLoader=ImageLoader.getInstance();
		this.mViewPager=viewPager;
	}

	@Override
	public void destroyItem(ViewGroup viewGroup, int position, Object object) {
		viewGroup.removeView((View) object);
	}


	@Override
	public Object instantiateItem(ViewGroup view, int position) {
		AlertUtil.alert(mContext, "开始加载");
		String path=list.get(position).get("path").toString();
		System.out.println(path);
		//Bitmap bitmap=BitmapFactory.decodeFile(path);
		
		ImageView iv=new ImageView(mContext);
		iv.setScaleType(ScaleType.CENTER);
		iv.setImageURI(Uri.parse("file:/"+path));
		imageLoader.displayImage("file:/"+path, iv);
		//iv.setImageBitmap(bitmap);
		
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
