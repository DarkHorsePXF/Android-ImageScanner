package com.example.imagescanner;

import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class ImageLoaderFactory {
	/*
	 * simplify step 1 and step 2 in MainActivity
	 */
	public static ImageLoader getImageLoader(Context context){
		ImageLoader imageLoader=ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
		return imageLoader;
	}
}
