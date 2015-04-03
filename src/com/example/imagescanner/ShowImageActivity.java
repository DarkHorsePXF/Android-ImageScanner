package com.example.imagescanner;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

public class ShowImageActivity extends ActionBarActivity {
	public static final String EXTRA_IMAGE_INDEX="image_index";
	
	MyViewPager vpImages;
	//ViewPager vpImages;
	ArrayList<HashMap<String, Object>> picList;
	int pagerPosition;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		//vpImages=(JellyViewPager) findViewById(R.id.vpImages);
		vpImages= (MyViewPager) findViewById(R.id.vpImages);
		pagerPosition=getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
		this.picList=MainActivity.picList;
		ImagePagerAdapter adapter=new ImagePagerAdapter(picList, getBaseContext(),vpImages);
		vpImages.setAdapter(adapter);
		vpImages.setCurrentItem(pagerPosition);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
	
	
}
