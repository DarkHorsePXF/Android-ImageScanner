package com.example.imagescanner.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.imagescanner.R;
import com.example.imagescanner.imageloader.ImageLoaderFactory;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class ImageLoaderAdapter extends BaseAdapter implements Adapter {
	Context mContext;
	ArrayList<HashMap<String, Object>> picList;
	ViewHolder holder;
	ImageLoader imageLoader;
	
	
	
	
	DisplayImageOptions options;
	
	public ImageLoaderAdapter(Context context,ArrayList<HashMap<String, Object>> picList) {
		this.mContext=context;
		this.picList=picList;
		
		imageLoader=ImageLoaderFactory.getImageLoader(mContext);	//step 1:get ImageLoader
		options=new DisplayImageOptions.Builder()	//step2:set options of ImageLoader
		.cacheInMemory(true)	
		.cacheOnDisk(true)
		.considerExifParams(true)
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageOnFail(R.drawable.ic_error)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.build();
		
		
		
	}
	@Override
	public int getCount() {
		return picList.size();
	}

	@Override
	public Object getItem(int position) {
		return picList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView=LayoutInflater.from(mContext).inflate(R.layout.layout_image_content, null);
			holder=new ViewHolder();
			holder.iv=(ImageView) convertView.findViewById(R.id.ivImage);
			convertView.setTag(holder);
		}
		String path=(String) picList.get(position).get("path");
		holder=(ViewHolder) convertView.getTag();
		holder.iv.setImageResource(R.drawable.ic_launcher);
		
		imageLoader.displayImage(		//step3:display images
				"file:/"+path, 	//URL:	local files-->"file:/..."
								//		Internet files-->"http://..."
								//		contentProvider-->"content:/..."
								//		drawable files-->"drawable:/..."
								
				holder.iv, 		//display on this imageView 
				options);		//displayed options
		return convertView;
	}
	class ViewHolder{
		ImageView iv;
	}

}
